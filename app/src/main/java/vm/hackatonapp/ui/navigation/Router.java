package vm.hackatonapp.ui.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import java.util.Map;

import vm.hackatonapp.R;
import vm.hackatonapp.ui.bills.BillsFragment;
import vm.hackatonapp.ui.insurance.InsuranceFragment;
import vm.hackatonapp.ui.invite.InviteFragment;
import vm.hackatonapp.ui.root.RootFragment;
import vm.hackatonapp.ui.statistics.StatisticsFragment;

/**
 * Created by Viktor Markov on 10/11/2018.
 */

public class Router implements IRouter {
    private FragmentManager fragmentManager;
    private AppCompatActivity activity;

    public Router(FragmentManager fragmentManager, AppCompatActivity activity) {
        this.fragmentManager = fragmentManager;
        this.activity = activity;
    }

    @Override
    public void openBills() {
        openScreen(new BillsFragment());
    }

    @Override
    public void openInviting() {
        openScreen(new InviteFragment());
    }

    @Override
    public void openInsurance() {
        openScreen(new InsuranceFragment());
    }

    @Override
    public void openStatistics() {
        openScreen(new StatisticsFragment());
    }

    @Override
    public void openRoot() {
        openScreen(new RootFragment());
    }

    @Override
    public void closeApp() {
        activity.finish();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment instanceof IBackPress) {
            ((IBackPress) fragment).onBackPressed();
        } else {
            throw new IllegalStateException("All Fragments should implement IBackPress interface");
        }
    }

    private void openScreen(Fragment target) {
        addAnimations(target);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, target);
        addSharedElements(transaction);

        transaction.commit();
    }

    private void addSharedElements(FragmentTransaction transaction) {
        Fragment fragment = fragmentManager.findFragmentById(R.id.container);
        if (fragment instanceof ISharedElement) {
            Map<String, View> sharedElements = ((ISharedElement)fragment).getSharedViews();
            transaction = addSharedElements(transaction, sharedElements);
        }
    }

    private void addAnimations(Fragment target) {
        Transition changeTransform = TransitionInflater.from(activity).
                inflateTransition(R.transition.transitions);

        target.setSharedElementEnterTransition(changeTransform);
        target.setSharedElementReturnTransition(changeTransform);

        target.setEnterTransition(new android.transition.Slide());
        target.setExitTransition(new android.transition.Slide());
    }

    private FragmentTransaction addSharedElements(FragmentTransaction transaction, Map<String, View> sharedElements) {
        View appBar = sharedElements.get("appBar");
        View cards = sharedElements.get("cards");

        if (appBar != null) {
            transaction.addSharedElement(appBar, "appBar");
        }

        if (cards != null) {
            transaction.addSharedElement(cards, "cards");
        }

        return transaction;
    }
}
