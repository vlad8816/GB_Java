package org.example;

import java.util.*;

public class HW_06 {
    public static void main(String[] args) {
        myHashSet myHS = new myHashSet();

        myHS.addd(8);
        myHS.addd(9);
        myHS.addd(33);
        for (int i = 0; i < 7; i++) {
            myHS.add(new Random().nextInt(20));
        }

        System.out.println(myHS.contains(7));
        System.out.println(myHS.toStr());
        System.out.println(myHS.getItem(4));
    }
}

class myHashSet {

    static final Object OBJECT = new Object();
    private LinkedHashMap<Integer, Object> hashMap = new LinkedHashMap<>();

    public boolean add(int num){
        return hashMap.put(num, OBJECT) == null;
    }

    public boolean addd(int num){
        return hashMap.put(num%7, OBJECT) == null;
    }

    public boolean contains(int num) {
        return hashMap.containsKey(num);
    }

    public Object[] toArray() {
        return hashMap.keySet().toArray();
    }

    public String toStr() {
        return Arrays.toString(toArray());
    }

    public Integer getItem(int index) {
        return (Integer) toArray()[index];
    }

}
