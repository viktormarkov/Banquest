package vm.hackatonapp.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vm.hackatonapp.data.LevelStorage;

@Module
public class LevelStorageModule {

    @Provides
    @Singleton
    LevelStorage provideLevelStorage() {
        return new LevelStorage();
    }
}
