package vm.hackatonapp.domain;

import android.annotation.SuppressLint;
import android.support.annotation.VisibleForTesting;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vm.hackatonapp.App;
import vm.hackatonapp.data.LevelStorage;
import vm.hackatonapp.data.db.DatabaseRepository;
import vm.hackatonapp.heplers.IRxUtils;
import vm.hackatonapp.heplers.LevelUpgrader;
import vm.hackatonapp.heplers.RxUtils;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.models.Level;
import vm.hackatonapp.models.UserBonus;

public class BonusInteractor {
    private LevelUpListener levelUpListener;
    private DatabaseRepository databaseRepository;
    private LevelStorage levelStorage;
    private IRxUtils rxUtils;
    private LevelUpgrader levelUpgrader;

    public BonusInteractor(DatabaseRepository repository, LevelStorage storage, IRxUtils rxUtils, LevelUpgrader levelUpgrader) {
        this.databaseRepository = repository;
        this.levelStorage = storage;
        this.rxUtils = rxUtils;
        this.levelUpgrader = levelUpgrader;
    }

    public Flowable<BonusSummary> bonusSummary() {
        return loadBonusSummary()
                .compose(rxUtils.asyncFlowable());
    }

    public Flowable<UserBonus> userBonus() {
        return databaseRepository
                .loadUserBonus()
                .compose(rxUtils.asyncFlowable());
    }

    @SuppressLint("CheckResult")
    public Completable calculateBonus() {
        return loadBonusSummary()
                .firstOrError()
                .map(bonusSummary -> {
                    UserBonus userBonus = bonusSummary.getUserBonus();
                    int currentLevel = userBonus.getCurrentLevel();
                    if (currentLevel < Level.MAX_LEVEL) {
                        int newLevel = currentLevel + 1;
                        levelUpgrader.upgrade(bonusSummary.getLevels().get(newLevel), userBonus);

                        databaseRepository
                                .saveUserBonus(userBonus)
                                .subscribe(() -> {}, Throwable::printStackTrace);

                        if (levelUpListener != null) {
                            levelUpListener.onLevelUp(bonusSummary);
                        }
                    }

                    return bonusSummary;
                })
                .flatMapCompletable(statistic -> Completable.complete())
                .compose(rxUtils.asyncCompletable());
    }

    public Completable dropUserBonus() {
        return databaseRepository
                .dropUserBonus()
                .compose(rxUtils.asyncCompletable());
    }

    public Flowable<BonusSummary> levelUpListener() {
        return Flowable.fromPublisher(subscriber -> levelUpListener = subscriber::onNext);
    }

    private Flowable<BonusSummary> loadBonusSummary() {
        return databaseRepository
                .loadUserBonus()
                .map(userBonus -> {
                    Map<Integer, Level> levels = levelStorage.getLevels();
                    return new BonusSummary(levels, userBonus);
                });
    }

    public interface LevelUpListener{
        void onLevelUp(BonusSummary data);
    }
}
