package vm.hackatonapp.ui;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.MvpPresenter;

import vm.hackatonapp.domain.BonusInteractor;

public abstract class BonusOperationPresenter<T extends BonusOperationView> extends MvpPresenter<T> {
    private BonusInteractor bonusInteractor;

    public BonusOperationPresenter(BonusInteractor bonusInteractor) {
        this.bonusInteractor = bonusInteractor;
    }

    @SuppressLint("CheckResult")
    protected void calculateBonus() {
        bonusInteractor.calculateBonus()
                .subscribe(
                        () -> getViewState().showSuccess(),
                        error -> getViewState().showError(error.getMessage())
                );
    }
}
