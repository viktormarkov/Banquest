package vm.hackatonapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vm.hackatonapp.heplers.IRxUtils;
import vm.hackatonapp.heplers.RxUtils;

@Module
public class RxUtilsModule {

    @Provides
    @Singleton
    IRxUtils provideRxUtils() {
        return new RxUtils();
    }
}
