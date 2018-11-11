package vm.hackatonapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import vm.hackatonapp.models.UserBonus;


@Database(entities = {UserBonus.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserBonusDao userBonusDao();
}
