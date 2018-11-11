package vm.hackatonapp.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import vm.hackatonapp.models.UserBonus;

@Dao
public interface UserBonusDao {

    @Query("SELECT * FROM UserBonus")
    Flowable<List<UserBonus>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserBonus statistic);

    @Query("DELETE FROM UserBonus")
    void deleteAll();
}
