package vm.hackatonapp.heplers;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;

/**
 * Created by Viktor Markov on 11/11/2018.
 */

public interface IRxUtils {
    <T> FlowableTransformer<T, T> asyncFlowable();
    CompletableTransformer asyncCompletable();
}
