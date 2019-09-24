package org.chobit.jspy;


public class Test {


    public static void main(String[] args) {
        String[] arr = {"abc", "123"};

        String s = arr[0];
        System.out.println(s);
        arr[0] = "tmp";
        System.out.println(arr[0]);
        System.out.println(s);

    }


}
