package vm.hackatonapp;

import android.app.Application;

import vm.hackatonapp.di.AppComponent;
import vm.hackatonapp.di.BonusInteractorModule;
import vm.hackatonapp.di.ContextModule;
import vm.hackatonapp.di.DaggerAppComponent;
import vm.hackatonapp.di.DatabaseManagerModule;
import vm.hackatonapp.di.DatabaseRepositoryModule;
import vm.hackatonapp.di.LevelStorageModule;


public class App extends Application {
    private static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    private AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .bonusInteractorModule(new BonusInteractorModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
