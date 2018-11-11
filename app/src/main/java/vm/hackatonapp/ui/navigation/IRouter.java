package vm.hackatonapp.ui.navigation;

public interface IRouter {
    void openBills();
    void openInviting();
    void openInsurance();
    void openStatistics();
    void openRoot();
    void closeApp();
    void onBackPressed();
}
