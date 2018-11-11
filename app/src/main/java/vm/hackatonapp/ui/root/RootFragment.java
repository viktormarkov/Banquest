package vm.hackatonapp.ui.root;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import vm.hackatonapp.App;
import vm.hackatonapp.R;
import vm.hackatonapp.ui.navigation.IBackPress;
import vm.hackatonapp.ui.navigation.INavigation;
import vm.hackatonapp.ui.navigation.ISharedElement;


public class RootFragment extends MvpAppCompatFragment implements RootView, ISharedElement, IBackPress {

    @BindView(R.id.bonus_for_invite)
    TextView inviteBonus;

    @BindView(R.id.bonus_for_insurance)
    TextView insuranceBonus;

    @BindView(R.id.bonus_for_bills)
    TextView billsBonus;

    @BindView(R.id.bonus)
    TextView level;

    @BindView(R.id.app_bar)
    View appBar;

    @BindView(R.id.cards)
    View cards;

    @InjectPresenter
    RootPresenter presenter;

    @ProvidePresenter
    RootPresenter setPresenter() {
        return new RootPresenter(
                ((INavigation) getActivity()).getRouter(),
                App.getAppComponent().provideBonusInteractor()
        );
    }

    @OnClick(R.id.btn_invite)
    void onInviteClick() {
        presenter.onInviteClick();
    }

    @OnClick(R.id.btn_bills)
    void onBillsClick() {
        presenter.onBillsClick();
    }

    @OnClick(R.id.btn_insurance)
    void onInsuranceClick() {
        presenter.onInsuranceClick();
    }

    @OnClick(R.id.btn_statistics)
    void onStatisticsClick() {
        presenter.onStatisticsClick();
    }

    @OnLongClick(R.id.btn_statistics)
    boolean onStatisticLongClick() {
        presenter.onStatisticLongClick();
        return true;
    }

    @Override
    public void setLevel(String level) {
        this.level.setText(level);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_root, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public Map<String, View> getSharedViews() {
        Map<String, View> sharedViews = new HashMap();
        sharedViews.put("appBar", appBar);
        sharedViews.put("cards", cards);
        return sharedViews;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
