package vm.hackatonapp.ui;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import vm.hackatonapp.App;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.models.BonusSummary;
import vm.hackatonapp.ui.navigation.IRouter;


@InjectViewState
public class LevelUpPresenter extends MvpPresenter<LevelUpView> {
    private IRouter router;

    @SuppressLint("CheckResult")
    LevelUpPresenter(IRouter router, BonusInteractor bonusInteractor) {
        this.router = router;
        bonusInteractor
                .levelUpListener()
                .subscribe(
                        this::showBonusDialog,
                        error -> getViewState().showError(error.getMessage())
                );
    }

    private void showBonusDialog(BonusSummary bonusSummary) {
        int currentLevel = bonusSummary.getUserBonus().getCurrentLevel();
        String bonusText = bonusSummary.getLevels().get(currentLevel).getBonusText();

        getViewState().setUpModal(String.valueOf(currentLevel), bonusText);
        getViewState().showModal();
    }

    void onActionClick() {
        router.openRoot();
    }
}
