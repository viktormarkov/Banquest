package vm.hackatonapp.ui.insurance;

import com.arellomobile.mvp.InjectViewState;

import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.ui.BonusOperationPresenter;
import vm.hackatonapp.ui.navigation.IRouter;

@InjectViewState
public class InsurancePresenter extends BonusOperationPresenter<InsuranceView> {
    private IRouter router;

    InsurancePresenter(IRouter router, BonusInteractor bonusInteractor) {
        super(bonusInteractor);
        this.router = router;
    }

    void onBuyClick() {
        calculateBonus();
    }

    void onBackPressed() {
        router.openRoot();
    }
}
