package vm.hackatonapp.ui.bills;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.Completable;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.ui.navigation.IRouter;


@RunWith(MockitoJUnitRunner.class)
public class BillsPresenterTest {

    @Mock
    private BonusInteractor bonusInteractor;

    @Mock
    private IRouter router;

    @Mock
    private BillsView view;

    @Mock
    private BillsView$$State viewState;

    private BillsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new BillsPresenter(router, bonusInteractor);

        Completable completable = Completable.complete();
        Mockito.when(bonusInteractor.calculateBonus()).thenReturn(completable);
    }

    @Test
    public void showSuccess() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onPayClick();

        Mockito.verify(viewState).showSuccess();
    }

    @Test
    public void showError() {
        String errorMessage = "error";
        IOException exception = new IOException(errorMessage);

        Completable completable = Completable.error(exception);
        Mockito.when(bonusInteractor.calculateBonus()).thenReturn(completable);

        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onPayClick();

        Mockito.verify(viewState).showError(errorMessage);
    }

    @Test
    public void exitOnBackPressed() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onBackPressed();
        Mockito.verify(router).openRoot();
    }
}
