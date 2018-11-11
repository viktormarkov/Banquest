package vm.hackatonapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import vm.hackatonapp.App;
import vm.hackatonapp.R;
import vm.hackatonapp.ui.navigation.INavigation;
import vm.hackatonapp.ui.navigation.IRouter;
import vm.hackatonapp.ui.navigation.Router;
import vm.hackatonapp.ui.root.RootFragment;

public class MainActivity extends MvpAppCompatActivity implements LevelUpView, INavigation {
    private LevelUpDialog levelUpDialog;
    private IRouter router;

    @InjectPresenter
    LevelUpPresenter presenter;

    @ProvidePresenter
    LevelUpPresenter setPresenter() {
        return new LevelUpPresenter(router, App.getAppComponent().provideBonusInteractor());
    }

    private LevelUpDialog.OnActionClick ratingDialogListener = new LevelUpDialog.OnActionClick() {
        @Override
        public void onActionClick() {
            presenter.onActionClick();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        router = new Router(getSupportFragmentManager(), this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levelUpDialog = new LevelUpDialog();
        levelUpDialog.setClickListener(ratingDialogListener);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = new RootFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public void showModal() {
        levelUpDialog.show(getSupportFragmentManager(), "levelUpDialog");
    }

    @Override
    public void hideModal() {
        levelUpDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setUpModal(String level, String bonus) {
        levelUpDialog.setData(level, bonus);
    }

    @Override
    public IRouter getRouter() {
        return router;
    }

    @Override
    public void onBackPressed() {
        router.onBackPressed();
    }
}
