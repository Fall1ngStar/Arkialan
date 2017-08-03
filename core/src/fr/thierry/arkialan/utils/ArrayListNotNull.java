package fr.thierry.arkialan.utils;

import java.util.ArrayList;

public class ArrayListNotNull<E> extends ArrayList{
    @Override
    public boolean add(Object o) {
        return o == null ? false : super.add(o);
    }
}