package vm.hackatonapp.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.Flowable;
import vm.hackatonapp.data.LevelStorage;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.models.UserBonus;
import vm.hackatonapp.ui.navigation.IRouter;


@RunWith(MockitoJUnitRunner.class)
public class LevelUpPresenterTest {

    @Mock
    private BonusInteractor bonusInteractor;

    @Mock
    private IRouter router;

    @Mock
    private LevelUpView view;

    @Mock
    private LevelUpView$$State viewState;

    private LevelUpPresenter presenter;

    private BonusSummary bonusSummary;

    @Before
    public void setUp() {
        UserBonus userBonus = new UserBonus(7, 1.0F, 1.0F, 3.0F, 5.0F);
        bonusSummary = new BonusSummary(new LevelStorage().getLevels(), userBonus);

        Flowable<BonusSummary> bonusSummaryFlowable = Flowable.just(bonusSummary);
        Mockito.when(bonusInteractor.levelUpListener()).thenReturn(bonusSummaryFlowable);

        presenter = new LevelUpPresenter(router, bonusInteractor);

        presenter.attachView(view);
        presenter.setViewState(viewState);
    }

    @Test
    public void showModal() {
        int currentLevel = bonusSummary.getUserBonus().getCurrentLevel();
        String bonusText = bonusSummary.getLevels().get(currentLevel).getBonusText();

        Mockito.verify(view).setUpModal(String.valueOf(currentLevel), bonusText);
        Mockito.verify(view).showModal();
    }

    @Test
    public void showError() {
        String errorMessage = "error";
        IOException exception = new IOException(errorMessage);

        Flowable<BonusSummary> bonusSummaryFlowable = Flowable.error(exception);
        Mockito.when(bonusInteractor.levelUpListener()).thenReturn(bonusSummaryFlowable);

        presenter = new LevelUpPresenter(router, bonusInteractor);

        presenter.attachView(view);
        presenter.setViewState(viewState);

        Mockito.verify(view).showError(errorMessage);
    }

    @Test
    public void exitOnBackPressed() {
        presenter.onActionClick();
        Mockito.verify(router).openRoot();
    }
}
