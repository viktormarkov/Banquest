package vm.hackatonapp.ui.invite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import vm.hackatonapp.App;
import vm.hackatonapp.R;
import vm.hackatonapp.ui.navigation.IBackPress;
import vm.hackatonapp.ui.navigation.INavigation;
import vm.hackatonapp.ui.navigation.ISharedElement;


public class InviteFragment extends MvpAppCompatFragment implements InviteView, ISharedElement, IBackPress {

    @BindView(R.id.phone)
    EditText phoneInput;

    @BindView(R.id.btn_invite)
    Button btnInvite;

    @BindView(R.id.app_bar)
    View appBar;

    @BindView(R.id.cards)
    View cards;

    @InjectPresenter
    InvitePresenter presenter;

    @ProvidePresenter
    InvitePresenter setPresenter() {
        return new InvitePresenter(
                ((INavigation) getActivity()).getRouter(),
                App.getAppComponent().provideBonusInteractor()
        );
    }

    @OnTextChanged(R.id.phone)
    void onPhoneChanged() {
        presenter.onPhoneChanged(phoneInput.getText().toString());
    }

    @OnClick(R.id.btn_invite)
    void onInviteClick() {
       presenter.onInviteClick();
    }

    @Override
    public void enableInviteButton() {
        btnInvite.setEnabled(true);
    }

    @Override
    public void disableInviteButton() {
        btnInvite.setEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invite, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                phoneInput.setHint("");
                phoneInput.setGravity(Gravity.LEFT);
            } else {
                phoneInput.setGravity(Gravity.CENTER_HORIZONTAL);
                phoneInput.setHint(R.string.invite_hint);
            }
        });
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
