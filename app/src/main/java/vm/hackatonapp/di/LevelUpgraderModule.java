package vm.hackatonapp.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vm.hackatonapp.heplers.LevelUpgrader;

@Module
public class LevelUpgraderModule {

    @Provides
    @Singleton
    LevelUpgrader provideLevelUpgrader() {
        return new LevelUpgrader();
    }
}
