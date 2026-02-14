package repositories;

import java.util.ArrayList;
import java.util.List;

public class Repository<E> {
    private List<E> items = new ArrayList<>();

    public void addItem(E item) {
        items.add(item);
    }

    public E getItem(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }
}
