package com.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
	
	public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String str = i + "";
            list.add(str);
        }
        Iterator<String> it = list.iterator();
        for (int i = 0; i < 5; i++) {
            System.out.println((String) it.next());
        }
        it.remove();
        System.out.println("////////////////////////");
        while (it.hasNext()) {
            System.out.println((String) it.next());
        }
        System.out.println("////////////////////////");
        it = list.iterator();
        while (it.hasNext()) {
            System.out.println((String) it.next());
        }
	}
}
