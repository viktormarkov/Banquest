package vm.hackatonapp.di;


import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vm.hackatonapp.data.db.AppDatabase;
import vm.hackatonapp.data.db.DatabaseManager;

@Module
public class DatabaseManagerModule {

    @Provides
    @Singleton
    DatabaseManager provideDatabase(@NonNull Context context) {
        return new DatabaseManager(context);
    }
}
