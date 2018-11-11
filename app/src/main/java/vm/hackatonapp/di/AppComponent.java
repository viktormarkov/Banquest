package vm.hackatonapp.di;

import javax.inject.Singleton;

import dagger.Component;
import vm.hackatonapp.domain.BonusInteractor;

@Component(modules = {ContextModule.class, DatabaseManagerModule.class, DatabaseRepositoryModule.class,
        LevelStorageModule.class, BonusInteractorModule.class, RxUtilsModule.class, LevelUpgraderModule.class})
@Singleton
public interface AppComponent {
    BonusInteractor provideBonusInteractor();
}
