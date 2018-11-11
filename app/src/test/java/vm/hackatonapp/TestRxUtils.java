package vm.hackatonapp;

import android.support.annotation.NonNull;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vm.hackatonapp.heplers.IRxUtils;

/**
 * Created by Viktor Markov on 11/11/2018.
 */

public class TestRxUtils implements IRxUtils {

    @NonNull
    public <T> FlowableTransformer<T, T> asyncFlowable() {
        return upstream -> upstream;
    }

    @NonNull
    public CompletableTransformer asyncCompletable() {
        return upstream -> upstream;
    }
}
