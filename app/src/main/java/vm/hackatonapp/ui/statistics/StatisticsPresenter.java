package vm.hackatonapp.ui.statistics;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Flowable;
import vm.hackatonapp.App;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.ui.navigation.IRouter;


@InjectViewState
public class StatisticsPresenter extends MvpPresenter<StatisticsView> {
    private BonusInteractor bonusInteractor;
    private IRouter router;

    StatisticsPresenter(IRouter router, BonusInteractor bonusInteractor) {
        this.router = router;
        this.bonusInteractor = bonusInteractor;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        bonusInteractor.bonusSummary()
                .subscribe(
                        bonusSummary -> getViewState().setData(bonusSummary),
                        throwable -> getViewState().showError(throwable.getMessage())
                );
    }

    void onBackPressed() {
        router.openRoot();
    }
}
