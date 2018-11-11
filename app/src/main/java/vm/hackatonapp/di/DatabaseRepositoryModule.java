package vm.hackatonapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vm.hackatonapp.data.db.DatabaseManager;
import vm.hackatonapp.data.db.DatabaseRepository;

@Module
public class DatabaseRepositoryModule {

    @Provides
    @Singleton
    DatabaseRepository provideDatabaseReposutory(DatabaseManager manager) {
        return new DatabaseRepository(manager);
    }
}
