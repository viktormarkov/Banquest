package vm.hackatonapp.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vm.hackatonapp.data.LevelStorage;
import vm.hackatonapp.data.db.DatabaseRepository;
import vm.hackatonapp.domain.BonusInteractor;
import vm.hackatonapp.heplers.IRxUtils;
import vm.hackatonapp.heplers.LevelUpgrader;

@Module
public class BonusInteractorModule {

    @Provides
    @Singleton
    public BonusInteractor provideBonusInteractor(DatabaseRepository repository, LevelStorage storage,
                                                  IRxUtils rxUtils, LevelUpgrader levelUpgrader) {
        return new BonusInteractor(repository, storage, rxUtils, levelUpgrader);
    }
}
