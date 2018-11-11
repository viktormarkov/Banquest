package vm.hackatonapp.data.db;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vm.hackatonapp.models.Level;
import vm.hackatonapp.models.UserBonus;


public class DatabaseRepository implements IDatabaseRepository {
    private AppDatabase database;

    public DatabaseRepository(DatabaseManager manager) {
        database = manager.getDatabase();
    }

    @Override
    public Flowable<UserBonus> loadUserBonus() {
        return database.userBonusDao().getAll()
                .map(list -> list.isEmpty() ? createStartedBonus() : list.get(0));
    }

    @Override
    public Completable saveUserBonus(UserBonus statistic) {
        return Completable.fromRunnable(() -> database.userBonusDao().insert(statistic));
    }

    @Override
    public Completable dropUserBonus() {
        return Completable.fromRunnable(() -> database.userBonusDao().deleteAll());
    }

    private UserBonus createStartedBonus() {
        return new UserBonus(Level.MIN_LEVEL, 1.0F, 1.0F, 3.0F, 5.0F);
    }

}
