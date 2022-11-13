package ru.geekbrains.lesson2;

import java.util.*;

public class MyArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 100;

    private final Object[] elementData;

    private int size;


    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public E get(int index) {
        if (checkBoundIndex(index)) {
            return (E) elementData[index];
        }
        return null;
    }

    @Override
    public void add(Object o) {
        if (size - 1 == elementData.length) {
            throw new RuntimeException("Array is full");
        }
        if (size < elementData.length) {
            elementData[size] = o;
            size++;
        }
    }

    @Override
    public void insert(Object o, int index) {
        if (checkBoundIndex(index)) {
            if (elementData[index] == null) {
                elementData[index] = o;
                size++;
            } else {
                throw new RuntimeException("proposed index is not empty");
            }
        }
    }

    @Override
    public void delete(Object o) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null) {
                continue;
            }
            if (elementData[i].equals(o)) {
                elementData[i] = null;
                System.out.println("done");
                break;
            }
        }
        throw new RuntimeException("Object " + o + " not found");
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayIterator<>();
    }

    private boolean checkBoundIndex(int index) {
        if (index - 1 > elementData.length) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (index < 0) {
            throw new RuntimeException("index < 0");
        } else {
            return true;
        }
    }



    private class MyArrayIterator<T> implements Iterator<T> {
        private int cursor;

        int lastRet = -1;

        public MyArrayIterator() {
        }

        @Override
        public boolean hasNext() {

            return cursor != size;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] iterData =  elementData;
            if (i >= iterData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (T) iterData[lastRet = i];
        }


    }


}
