package vm.hackatonapp.ui.root;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.models.UserBonus;
import vm.hackatonapp.ui.navigation.IRouter;

@RunWith(MockitoJUnitRunner.class)
public class RootPresenterTest {

    @Mock
    private BonusInteractor bonusInteractor;

    @Mock
    private IRouter router;

    @Mock
    private RootView view;

    @Mock
    private RootView$$State viewState;

    private UserBonus userBonus;

    private RootPresenter presenter;

    @Before
    public void setUp() {
        userBonus = new UserBonus(7, 1.0F, 1.0F, 3.0F, 5.0F);
        presenter = new RootPresenter(router, bonusInteractor);

        Flowable<UserBonus> bonusFlowable = Flowable.just(userBonus);
        Mockito.when(bonusInteractor.userBonus()).thenReturn(bonusFlowable);
    }

    @Test
    public void showDataOnSuccess() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        Mockito.verify(view).setLevel(String.valueOf(userBonus.getCurrentLevel()));
    }

    @Test
    public void showErrorOnFail() {
        String errorMessage = "error";
        IOException exception = new IOException(errorMessage);

        Flowable<UserBonus> bonusFlowable = Flowable.error(exception);
        Mockito.when(bonusInteractor.userBonus()).thenReturn(bonusFlowable);

        presenter.attachView(view);
        presenter.setViewState(viewState);

        Mockito.verify(view).showError(errorMessage);
    }

    @Test
    public void exitOnBackPressed() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onBackPressed();
        Mockito.verify(router).closeApp();
    }

    @Test
    public void dropDataOnLongClick_Success() {
        Completable completable = Completable.complete();
        Mockito.when(bonusInteractor.dropUserBonus()).thenReturn(completable);

        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onStatisticLongClick();
        Mockito.verify(router).openRoot();
    }

    @Test
    public void dropDataOnLongClick_Error() {
        String errorMessage = "error";
        IOException exception = new IOException(errorMessage);

        Completable completable = Completable.error(exception);
        Mockito.when(bonusInteractor.dropUserBonus()).thenReturn(completable);

        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onStatisticLongClick();
        Mockito.verify(viewState).showError(errorMessage);
    }
}
