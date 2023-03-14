package OOP_FOURTH_PROBLEM_JAVA;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        Manager m1 = new Manager("Null", "Nullovitch");
        Manager m2 = new Manager("Alex", "Black");
        m1.make_list();
        System.out.println(m1);
        m2.make_list();
        System.out.println(m2);
    }
}
