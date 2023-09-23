package InheritanceLab03RandomArrayList;

import java.util.ArrayList;
import java.util.List;

public class RandomArrayList<E> extends ArrayList<E> {

    List<E> list = new ArrayList<>();

    public Object getRandomElement(){

        Object element = list.get(5);
        list.remove(5);
        return element;
    }


}
