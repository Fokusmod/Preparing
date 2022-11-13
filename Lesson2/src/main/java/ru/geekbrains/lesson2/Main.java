package ru.geekbrains.lesson2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        MyList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        System.out.println("size: " + myArrayList.size());
        System.out.println("Element-3: " + myArrayList.get(3));
        myArrayList.insert(6,5);
        System.out.println("size: " + myArrayList.size());

        for (Integer integer : myArrayList) {
            System.out.println(integer);
        }

        MyList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.insert(4,0);
        list.delete(4);












    }
}
