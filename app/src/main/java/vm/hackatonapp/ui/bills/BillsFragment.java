package vm.hackatonapp.ui.bills;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vm.hackatonapp.App;
import vm.hackatonapp.R;
import vm.hackatonapp.ui.navigation.IBackPress;
import vm.hackatonapp.ui.navigation.INavigation;
import vm.hackatonapp.ui.navigation.ISharedElement;


public class BillsFragment extends MvpAppCompatFragment implements BillsView, ISharedElement, IBackPress {

    @BindView(R.id.btn_pay)
    Button btnPay;

    @BindView(R.id.app_bar)
    View appBar;

    @BindView(R.id.cards)
    View cards;

    @InjectPresenter
    BillsPresenter presenter;

    @ProvidePresenter
    BillsPresenter setPresenter() {
        return new BillsPresenter(
                ((INavigation) getActivity()).getRouter(),
                App.getAppComponent().provideBonusInteractor()
        );
    }

    @OnClick(R.id.btn_pay)
    void onPayClick() {
        presenter.onPayClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bills, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void showSuccess() {
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
