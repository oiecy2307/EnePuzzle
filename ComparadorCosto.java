package enepuzzle;

import java.util.Comparator;

public class ComparadorCosto implements Comparator<Tablero> {

    @Override
    public int compare(Tablero x, Tablero y) {
        if (x.getTotalCost() < y.getTotalCost()) {
            return -1;
        }
        if (x.getTotalCost() > y.getTotalCost()) {
            return 1;
        }
        return 0;
    }
}
