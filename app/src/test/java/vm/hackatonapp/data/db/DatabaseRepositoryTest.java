package vm.hackatonapp.data.db;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import vm.hackatonapp.models.UserBonus;

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class DatabaseRepositoryTest {

    private DatabaseRepository repository;

    @Before
    public void setUp() {
        repository = new DatabaseRepository(new DatabaseManager(RuntimeEnvironment.application.getApplicationContext(), true));
    }

    @After
    public void tearDown() {
        repository.dropUserBonus().subscribe();
    }

    @Test
    public void getUserBonus_firstInit() throws InterruptedException {
        List<UserBonus> userBonuses = new ArrayList<>();

        repository.loadUserBonus()
                .subscribe(userBonuses::add);

        Thread.sleep(5000);

        Assert.assertEquals(7, userBonuses.get(0).getCurrentLevel());
    }

    @Test
    public void save_and_get_userBonus() throws InterruptedException {
        List<UserBonus> userBonuses = new ArrayList<>();

        repository.saveUserBonus(new UserBonus(8, 1.0F, 1.0F, 3.0F, 5.0F))
                .subscribe();

        repository.loadUserBonus()
                .subscribe(userBonuses::add);

        Thread.sleep(5000);

        Assert.assertEquals(8, userBonuses.get(0).getCurrentLevel());
    }

    @Test
    public void drop_userBonus() throws InterruptedException {
        List<UserBonus> userBonuses = new ArrayList<>();

        repository.saveUserBonus(new UserBonus(8, 1.0F, 1.0F, 3.0F, 5.0F))
                .subscribe();

        repository.dropUserBonus()
                .subscribe();

        Flowable<UserBonus> flowable = repository.loadUserBonus();
        flowable.subscribe(userBonuses::add, Throwable::printStackTrace);

        Thread.sleep(5000);

        Assert.assertEquals(7, userBonuses.get(0).getCurrentLevel());
    }

}
