package ru.geekbrains.lesson2;

//1.Создать интерфейс MyList с методами get, add, insert, delete. Также интерфейс должен реализоввывать Iterable
//2. На основе интерфейса создать двусвязный список (аналог LinkedList)
//3. На основе интерфейса создать список - динамический массив (аналог. ArrayList)

import java.util.Iterator;

public interface MyList<E> extends Iterable<E> {



    E get(int index);

    void add(E e);

    void insert(E e, int index);

    void delete(E e);

    int size();

    Iterator<E> iterator();

}
