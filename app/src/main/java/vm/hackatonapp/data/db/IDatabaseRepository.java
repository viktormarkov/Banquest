package vm.hackatonapp.data.db;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import vm.hackatonapp.models.UserBonus;


public interface IDatabaseRepository {
    Flowable<UserBonus> loadUserBonus();
    Completable saveUserBonus(UserBonus statistic);
    Completable dropUserBonus();

}
