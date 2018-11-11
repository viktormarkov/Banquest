package vm.hackatonapp.ui.invite;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vm.hackatonapp.ui.BonusOperationView;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface InviteView extends BonusOperationView {
    void enableInviteButton();
    void disableInviteButton();
}
