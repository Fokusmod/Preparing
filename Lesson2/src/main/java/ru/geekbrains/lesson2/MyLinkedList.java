package ru.geekbrains.lesson2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E> {


    private int size;

    private Node<E> first;

    private Node<E> last;

    @Override
    public E get(int index) {
        if (index < size) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            if (node != null) {
                return node.item;
            }
        } else if (index - 1 == size) {
            Node<E> x = last;
            for (int i = size; i > size - 1; i--) {
                x = x.prev;
            }
            if (x != null) {
                return x.item;
            }
        } else {
            throw new RuntimeException("index " + index + " not found");
        }
        throw new RuntimeException("index " + index + " not found");
    }

    @Override
    public void add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    @Override
    public void insert(E e, int index) {
        if (index >= size) {
            add(e);
        } else {
            final Node<E> f = first;
            final Node<E> newNode = new Node<>(null, e, f);
            first = newNode;
            if (f == null)
                last = newNode;
            else
                f.prev = newNode;
            size++;
        }
    }

    @Override
    public void delete(E e) {
        for (Node<E> i = first; i!= null ; i = i.next) {
            final E element = i.item;
            final Node<E> next = i.next;
            final Node<E> prev = i.prev;
            if (i.item.equals(e)){
                if (prev == null) {
                    first = next;
                } else {
                    prev.next = next;
                    i.prev = null;
                }

                if (next == null) {
                    last = prev;
                } else {
                    next.prev = prev;
                    i.next = null;
                }

                i.item = null;
                size--;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator(size);
    }

    Node<E> node(int index) {

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private class MyLinkedListIterator implements Iterator<E> {

        private Node<E> lastReturned;
        private Node<E> next;
        private int nextIndex;

        public MyLinkedListIterator(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
