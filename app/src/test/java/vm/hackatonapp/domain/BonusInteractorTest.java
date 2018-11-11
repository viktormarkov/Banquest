package vm.hackatonapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vm.hackatonapp.TestRxUtils;
import vm.hackatonapp.data.LevelStorage;
import vm.hackatonapp.data.db.DatabaseRepository;
import vm.hackatonapp.heplers.IRxUtils;
import vm.hackatonapp.heplers.LevelUpgrader;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.models.Level;
import vm.hackatonapp.models.UserBonus;

import static org.junit.Assert.fail;


@RunWith(MockitoJUnitRunner.class)
public class BonusInteractorTest {

    @Mock
    private DatabaseRepository databaseRepository;

    @Mock
    private LevelStorage levelStorage;

    @Mock
    private LevelUpgrader levelUpgrader;

    @Mock
    private Flowable<UserBonus> userBonusFlowable;

    private BonusInteractor bonusInteractor;
    private UserBonus userBonus;
    private Map<Integer, Level> levels = new HashMap<>();
    private Completable completable = Completable.complete();

    @Before
    public void setUp() {
        IRxUtils rxUtils = new TestRxUtils();

        userBonus = new UserBonus(7, 1.0F, 1.0F, 3.0F, 5.0F);
        userBonusFlowable = Flowable.just(userBonus);
        Mockito.when(databaseRepository.loadUserBonus()).thenReturn(userBonusFlowable);

        levels.put(10, new Level(10, "You earned -1% for credit!",0f, 0f, 1f, 0f));
        levels.put(9, new Level(9, "+20% discount for life insurance",0f, 0f, 0f, 20f));
        levels.put(8, new Level(8, "You earned +1% Cashback!",1f, 0f, 0f, 0f));
        levels.put(7, new Level(7, "You earned +1% for deposit!",0f, 1f, 0f, 0f));
        levels.put(6, new Level(6, "You earned -1% for credit!",0f, 0f, 1f, 0f));
        levels.put(5, new Level(5, "You earned +0.5% Cashback!",0.5f, 0f, 0f, 0f));
        levels.put(4, new Level(4, "+5% discount for life insurance",0f, 0f, 0f, 5f));
        levels.put(3, new Level(3, "You earned +0.5% for deposit!",0f, 0.5f, 0f, 0f));
        levels.put(2, new Level(2, "You earned -0.5% for credit!", 0f, 0f, 0.5f, 0f));
        levels.put(1, new Level(1, "You are amazing :)", 0f, 0f, 0f, 0f));

        Mockito.when(levelStorage.getLevels()).thenReturn(levels);
        Mockito.when(databaseRepository.dropUserBonus()).thenReturn(completable);
        Mockito.when(databaseRepository.saveUserBonus(userBonus)).thenReturn(completable);

        bonusInteractor = new BonusInteractor(databaseRepository, levelStorage, rxUtils, levelUpgrader);
    }

    @Test
    public void getBonusSummary() {
        Flowable<BonusSummary> stream = bonusInteractor.bonusSummary();
        stream.subscribe(bonusSummary -> {
            Assert.assertEquals(userBonus.getCurrentLevel(), bonusSummary.getUserBonus().getCurrentLevel());
            Assert.assertEquals(levels, bonusSummary.getLevels());
        });
    }

    @Test
    public void getUserBonus() {
        Flowable<UserBonus> stream = bonusInteractor.userBonus();
        stream.subscribe(bonus -> {
            Assert.assertEquals(userBonus.getCurrentLevel(), bonus.getCurrentLevel());
        });
    }

    @Test
    public void dropUserBonus() {
        Completable stream = bonusInteractor.dropUserBonus();
        stream.subscribe();

        Mockito.verify(databaseRepository).dropUserBonus();
    }

    @Test
    public void calculateBonus_levelUp() {
        Completable stream = bonusInteractor.calculateBonus();
        stream.subscribe(() -> {}, error -> fail());

        Mockito.verify(levelUpgrader).upgrade(levels.get(userBonus.getCurrentLevel() + 1), userBonus);
        Mockito.verify(databaseRepository).saveUserBonus(userBonus);
    }

    @Test
    public void calculateBonus_maxLevel() {
        userBonus = new UserBonus(10, 1.0F, 1.0F, 3.0F, 5.0F);
        userBonusFlowable = Flowable.just(userBonus);
        Mockito.when(databaseRepository.loadUserBonus()).thenReturn(userBonusFlowable);

        Completable stream = bonusInteractor.calculateBonus();
        stream.subscribe(() -> {}, error -> fail());

        Mockito.verify(levelUpgrader, Mockito.never()).upgrade(levels.get(userBonus.getCurrentLevel() + 1), userBonus);
        Mockito.verify(databaseRepository, Mockito.never()).saveUserBonus(userBonus);
    }
}
