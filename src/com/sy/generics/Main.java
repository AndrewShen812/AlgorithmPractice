package com.sy.generics;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShenYong
 * @date 2020/4/22
 */
public class Main {

    private static class Food {

    }

    private static class Meat extends Food {

    }

    private static class Fruit extends Food {

    }

    private static class FreshMeat extends Meat {

    }

    private static class Pork extends FreshMeat {

    }
    private static class Beef extends FreshMeat {

    }

    private static class Apple extends Fruit {

    }
    private static class Pear extends Fruit {

    }

    public static void main(String[] args) {
        ArrayList<? extends FreshMeat> list1 = new ArrayList<>();
        FreshMeat food = list1.get(0);

        ArrayList<? super Meat> list2 = new ArrayList<>();
        list2.add(new FreshMeat());
        list2.add(new Pork());
        list2.add(new Beef());
    }
}
