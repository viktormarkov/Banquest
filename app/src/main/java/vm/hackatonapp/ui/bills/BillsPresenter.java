package vm.hackatonapp.ui.bills;

import com.arellomobile.mvp.InjectViewState;

import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.ui.BonusOperationPresenter;
import vm.hackatonapp.ui.navigation.IRouter;


@InjectViewState
public class BillsPresenter extends BonusOperationPresenter<BillsView> {
    private IRouter router;

    BillsPresenter(IRouter router, BonusInteractor bonusInteractor) {
        super(bonusInteractor);
        this.router = router;
    }

    void onPayClick() {
        calculateBonus();
    }

    void onBackPressed() {
        router.openRoot();
    }
}
