package vm.hackatonapp.ui.invite;

import com.arellomobile.mvp.InjectViewState;

import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.ui.BonusOperationPresenter;
import vm.hackatonapp.ui.navigation.IRouter;

@InjectViewState
public class InvitePresenter extends BonusOperationPresenter<InviteView> {
    private IRouter router;

    InvitePresenter(IRouter router, BonusInteractor bonusInteractor) {
        super(bonusInteractor);
        this.router = router;
    }

    void onInviteClick() {
        calculateBonus();
    }

    void onPhoneChanged(String phone) {
        if (phone.isEmpty()) {
            getViewState().disableInviteButton();
        } else {
            getViewState().enableInviteButton();
        }
    }

    void onBackPressed() {
        router.openRoot();
    }
}
