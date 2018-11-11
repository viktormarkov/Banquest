package vm.hackatonapp.ui.statistics;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import io.reactivex.Flowable;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.ui.navigation.IRouter;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsPresenterTest {

    @Mock
    private BonusInteractor bonusInteractor;

    @Mock
    private IRouter router;

    @Mock
    private StatisticsView view;

    @Mock
    private StatisticsView$$State viewState;

    @Mock
    private BonusSummary bonusSummary;

    private StatisticsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new StatisticsPresenter(router, bonusInteractor);

        Flowable<BonusSummary> bonusFlowable = Flowable.just(bonusSummary);
        Mockito.when(bonusInteractor.bonusSummary()).thenReturn(bonusFlowable);
    }

    @Test
    public void showDataOnSuccess() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        Mockito.verify(view).setData(bonusSummary);
    }

    @Test
    public void showErrorOnFail() {
        String errorMessage = "error";
        IOException exception = new IOException(errorMessage);

        Flowable<BonusSummary> bonusFlowable = Flowable.error(exception);
        Mockito.when(bonusInteractor.bonusSummary()).thenReturn(bonusFlowable);

        presenter.attachView(view);
        presenter.setViewState(viewState);

        Mockito.verify(view).showError(errorMessage);
    }

    @Test
    public void exitOnBackPressed() {
        presenter.attachView(view);
        presenter.setViewState(viewState);

        presenter.onBackPressed();
        Mockito.verify(router).openRoot();
    }
}
