package com.sqq.data.structor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayListStudy {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("s");
        System.out.println("list");

        List<String> l3 = Arrays.asList("");
        l3.add("a");

        LinkedList<String>  list2 = new LinkedList<>();
        list2.add("d");
    }
}
