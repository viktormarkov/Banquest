package vm.hackatonapp.ui.insurance;

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
public class InsurancePresenterTest {

    @Mock
    private BonusInteractor bonusInteractor;

    @Mock
    private IRouter router;

    @Mock
    private InsuranceView view;

    @Mock
    private InsuranceView$$State viewState;

    private InsurancePresenter presenter;

    @Before
    public void setUp() {
        presenter = new InsurancePresenter(router, bonusInteractor);

        Completable completable = Completable.complete();
        Mockito.when(bonusInteractor.calculateBonus()).thenReturn(completable);
    }

    @Test
    public void showSuccess() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onBuyClick();

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

        presenter.onBuyClick();

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
