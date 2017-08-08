package org.redrock.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LinkedList 实现了基本的list接口，但是他在执行插入，移除的操作时比ArrayList更高效
 * 此外他还添加了使其用作栈、队列或双端队列的方法
 * getFirst() element() 方法完全一样 返回列表的第一个元素（不移除）,如果list为空，抛出NoSuchElementException 异常
 * peek() 方法作用一致，但列表为空时返回null
 * removeFirst() remove() 完全一样，他们移除并返回列表的头，列表为空时，抛出NoSuchElementException异常
 * poll() 稍有差异，为空时返回null
 * addFirst() add() addLast() 相同 将某个元素插入到列表的尾端
 * removeLast() 移除并返回列表的最后一个元素
 *
 * Created by wang on 2017/8/6.
 */
public class Parcel6 {
    public static void main(String[] args) {
        //LinkedList<Pet> pets = new LinkedList<>(Pets.arrayList(5));
        //作业：1) 测试以上的方法
        //pets.add(new Pet("cat"));
        //pets.addFirst(new Pet("dog"));
        //pets.addLast(new Pet("aaa"));

        //System.out.println(pets);
        //System.out.println(pets.getFirst());
//        System.out.println(pets.element());
//        System.out.println(pets.peek());

        //pets.removeFirst();
        //pets.remove();
        //pets.poll();
//        System.out.println(pets.removeLast());
//        System.out.println(pets);




        //long startTime = System.nanoTime();

        //作业：2) 将Integer对象加入ArrayList 和 LinkedList 的前，中，后，观察运行时间(数量级10^4以上)
        //这里书写你的程序

        //long endTime = System.nanoTime();
        //System.out.println("运行时间：" + (endTime - startTime));



        LinkedList<Pet> pets = new LinkedList<Pet>(Pets.arrayList(5));
        //System.out.println(pets.get(1));
        //System.out.println(pets);
        long startTime = System.nanoTime();
        for (int i=1; i<100000; i++) {
            //ArrayList更快，
            pets.addLast(new Pet("cat"));
            //LinkedList更快，由于ArrayList是基于数组实现的，而数组是一块连续的内存空间，如果在数组的任意位置插入元素，必然导致在该位置后的所有元素需要重新排列，因此，其效率相对会比较低。
            //pets.add(3, new Pet("cat"));
            //pets.add(0, new Pet("cat"));
        }
        long endTime = System.nanoTime();
        System.out.println(endTime-startTime);


        ArrayList<Pet> pets1 = new ArrayList<>(Pets.arrayList(5));
        long startTime1 = System.nanoTime();
        for (int i=1; i<100000; i++) {
            pets.addLast(new Pet("cat"));
            //pets1.add(3 ,new Pet("cat"));
            //pets1.add(0, new Pet("cat"));
        }
        long endTime1 = System.nanoTime();
        System.out.println(endTime1-startTime1);
        //System.out.println(pets1);

    }
}
