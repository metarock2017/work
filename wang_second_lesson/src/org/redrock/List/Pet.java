package org.redrock.List;

import java.util.PriorityQueue;

/**
 * Created by wang on 2017/8/6.
 */
public class Pet implements Comparable {
    private String name;

    public Pet(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Pet pet = (Pet) o;
        return this.name.compareTo(pet.getName());
    }

    public static void comparator(PriorityQueue<Pet> pets) {
        PriorityQueue<Pet> priorityQueue1 = new PriorityQueue<>();
        for (Pet pet : pets) {
            //System.out.println(pet.getName());
            switch (pet.getName()) {
                case "cat":
                    System.out.println(pet);
                    break;

                case "dog":
                    System.out.println(pet);
                    break;

                default:
                    priorityQueue1.add(pet);
            }
        }
        System.out.println(priorityQueue1);




//        while (!pets.isEmpty()) {
//            for (Pet pet : pets) {
//
//                if (pet.equals("cat")) {
//                    System.out.println("hhhhhhhhhhhh");
//                    //System.out.println(pets.poll());
//                    System.out.println(pet);
//                    pets.remove(pet);
//                } else if (pet.equals(new Pet("dog"))) {
//                    //System.out.println(pets.poll());
//                    System.out.println(pet);
//                    pets.remove(pet);
//                }
//            }
//            System.out.println(pets.poll());
//
//        }
    }

//    public static void  main() {
//        PriorityQueue<Pet>  priorityQueue = new PriorityQueue<>();
//        Pet aa = new Pet("aa");
//        Pet dog = new Pet("dog");
//        Pet bb = new Pet("bb");
//        Pet cat = new Pet("cat");
//        priorityQueue.add(aa);
//        priorityQueue.add(dog);
//        priorityQueue.add(bb);
//        priorityQueue.add(cat);
//        Pet.comparator(priorityQueue);
//
//    }


//    @Override
//    public boolean equals(Object object) {
//
////        if (obj == null) return false;
////        if (obj == this) return true;
////        Pet pet = (Pet) obj;
//        Pet pet = (Pet) object;
//        if (this.name.equals(pet.getName()))
//            return true;
//        else
//            return false;
////
//        //return true;
//    }


//    public boolean equals(Object obj) {
//
//        if (obj == null) return false;
//        if (obj == this) return true;
//        Pet pet = (Pet) obj;
//
//        return true;
//    }


}
