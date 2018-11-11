package vm.hackatonapp.ui.insurance;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vm.hackatonapp.ui.BonusOperationView;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface InsuranceView extends BonusOperationView {
}
