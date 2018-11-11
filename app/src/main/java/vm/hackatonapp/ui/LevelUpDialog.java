package vm.hackatonapp.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vm.hackatonapp.R;
import vm.hackatonapp.heplers.DensityHelper;


public class LevelUpDialog extends DialogFragment {

    @BindView(R.id.level_value)
    TextView levelValue;

    @BindView(R.id.bonus)
    TextView bonus;

    @BindView(R.id.btn_yay)
    Button btnYay;

    private String currentLevel;
    private String currentBonus;

    private OnActionClick clickListener;

    @OnClick(R.id.btn_yay)
    void onPositiveClick() {
        dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.level_up_dialog_layout, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.oval_white_medium_radius_background);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        levelValue.setText(currentLevel);
        bonus.setText(currentBonus);
    }

    @Override
    public void onResume() {
        super.onResume();

        android.view.WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = DensityHelper.dpToPx(getActivity(), 350);
        getDialog().getWindow().setAttributes(params);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        clickListener.onActionClick();
    }

    public void setData(String level, String bonus) {
        currentLevel = level;
        currentBonus = bonus;
    }

    public void setClickListener(OnActionClick listener) {
        clickListener = listener;
    }

    public interface OnActionClick {
        void onActionClick();
    }
}
