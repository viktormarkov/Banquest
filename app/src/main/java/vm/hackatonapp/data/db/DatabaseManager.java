package vm.hackatonapp.data.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;


public class DatabaseManager {
    private AppDatabase appDatabase;

    public DatabaseManager(Context context) {
        buildDatabase(context, false);
    }

    @VisibleForTesting
    public DatabaseManager(Context context, boolean allowMainThreadQueries) {
        buildDatabase(context, allowMainThreadQueries);
    }

    private void buildDatabase(Context context, boolean allowMainThreadQueries) {
        RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(context, AppDatabase.class, "database");

        if (allowMainThreadQueries) {
            builder.allowMainThreadQueries();
        }

        appDatabase = builder.build();
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }
}
