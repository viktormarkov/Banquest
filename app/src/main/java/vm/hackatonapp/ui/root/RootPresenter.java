package vm.hackatonapp.ui.root;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.ui.navigation.IRouter;


@InjectViewState
public class RootPresenter extends MvpPresenter<RootView> {
    private BonusInteractor bonusInteractor;
    private IRouter router;

    public RootPresenter(IRouter router, BonusInteractor bonusInteractor) {
        this.router = router;
        this.bonusInteractor = bonusInteractor;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        bonusInteractor.userBonus()
                .subscribe(
                        userBonus -> getViewState().setLevel(String.valueOf(userBonus.getCurrentLevel())),
                        throwable -> getViewState().showError(throwable.getMessage())
                );
    }

    void onInviteClick() {
        router.openInviting();
    }

    void onBillsClick() {
        router.openBills();
    }

    void onInsuranceClick() {
        router.openInsurance();
    }

    void onStatisticsClick() {
        router.openStatistics();
    }

    @SuppressLint("CheckResult")
    void onStatisticLongClick() {
        bonusInteractor.dropUserBonus()
                .subscribe(
                        () -> router.openRoot(),
                        error -> getViewState().showError(error.getMessage())
                );
    }

    void onBackPressed() {
        router.closeApp();
    }
}
