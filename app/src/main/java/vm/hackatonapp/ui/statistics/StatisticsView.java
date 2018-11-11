package vm.hackatonapp.ui.statistics;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vm.hackatonapp.models.BonusSummary;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface StatisticsView extends MvpView {
    void showError(String error);
    void setData(BonusSummary data);
}
