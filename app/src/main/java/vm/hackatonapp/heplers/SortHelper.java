package vm.hackatonapp.heplers;

import java.util.Collections;
import java.util.List;

import vm.hackatonapp.models.Level;


public class SortHelper {
    public static void sortLevels(List<Level> levels) {
        Collections.sort(levels, (first, second) -> first.getLevel() > second.getLevel() ? -1 : 1);
    }
}
