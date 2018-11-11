package vm.hackatonapp.ui.statistics;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vm.hackatonapp.App;
import vm.hackatonapp.R;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.models.Level;
import vm.hackatonapp.models.UserBonus;
import vm.hackatonapp.heplers.SortHelper;
import vm.hackatonapp.ui.navigation.IBackPress;
import vm.hackatonapp.ui.navigation.INavigation;
import vm.hackatonapp.ui.navigation.ISharedElement;


public class StatisticsFragment extends MvpAppCompatFragment implements StatisticsView, ISharedElement, IBackPress {

    @BindView(R.id.rv_statistics)
    RecyclerView statistics;

    @BindView(R.id.credit)
    TextView credit;

    @BindView(R.id.deposit)
    TextView deposit;

    @BindView(R.id.insurance)
    TextView insurance;

    @BindView(R.id.cashback)
    TextView cashback;

    private StatisticsAdapter adapter;

    @InjectPresenter
    StatisticsPresenter presenter;

    @ProvidePresenter
    StatisticsPresenter setPresenter() {
        return new StatisticsPresenter(
                ((INavigation) getActivity()).getRouter(),
                App.getAppComponent().provideBonusInteractor()
        );
    }

    @Override
    public void setData(BonusSummary data) {
        List<Level> levelList = new ArrayList<>(data.getLevels().values());
        SortHelper.sortLevels(levelList);
        adapter.setLevels(levelList);
        UserBonus statistic = data.getUserBonus();
        adapter.setUserLevel(statistic.getCurrentLevel());
        setValue(credit, statistic.getCredit());
        setValue(deposit, statistic.getDeposit());
        setValue(insurance, statistic.getLifeInsurance());
        setValue(cashback, statistic.getCashback());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        statistics.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StatisticsAdapter(getActivity());
        adapter.setUserLevel(8);
        statistics.setAdapter(adapter);
    }

    private void setValue(TextView target, float value) {
        String formattedValue = String.format("%.1f%%", value).replaceAll(".0", "");
        target.setText(formattedValue);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public Map<String, View> getSharedViews() {
        Map<String, View> sharedViews = new HashMap();
        sharedViews.put("appBar", null);
        sharedViews.put("cards", null);
        return sharedViews;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
