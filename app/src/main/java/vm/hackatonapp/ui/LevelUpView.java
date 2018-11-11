package vm.hackatonapp.ui;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface LevelUpView extends MvpView {
    void showError(String error);
    void setUpModal(String level, String bonus);
    void showModal();
    void hideModal();
}
