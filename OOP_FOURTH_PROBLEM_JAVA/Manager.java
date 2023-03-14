package OOP_FOURTH_PROBLEM_JAVA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Manager implements Save_and_read {

    private static ArrayList<String> db = new ArrayList<>();
    private static ArrayList<String> messageList = new ArrayList<>();
    private static String fileName = "OOP_FOURTH_PROBLEM_JAVA\\data.csv";

    private ArrayList<String> dList = new ArrayList<>();
    private String messageFile = "message.csv";
    private String message = "";
    private int id;
    private String day;
    private String time;
    private String deadline;
    private String firstName;
    private String secondName;
    private String alert;
    private String color;
    private static Scanner scan = new Scanner(System.in);

    public Manager(  
    String firstName, 
    String secondName)
    {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        String foo = "";
        for (String bar : this.dList) {
            foo += bar + ", ";
        }
        get_color();
        foo += this.color + this.message + this.color;
        return foo;
    }

    private void get_color()
    {
        switch(this.alert)
        {
            case "h":
                this.color = "\033[31";
                break;
            case "a":
                this.color = "\033[93";
                break;
            case "n":
                this.color = "";
                break;
        }
    }

    public void get_date()
    {
        LocalDate dt = LocalDate.now();
        LocalTime tt = LocalTime.now();

        this.day = dt.toString();
        this.time = tt.toString();
    }

    public void set_deadline(int year, int month, int day)
    {
        try {
            LocalDate dead = LocalDate.of(year, month, day);
            this.deadline = dead.toString();
        } catch (Exception e) {
            System.out.println("ERROR ON DATE!");
        }
    }

    public void get_message()
    {
        String message = "";
        System.out.println("Enter message");
        try{
            message = scan.nextLine();
        } catch (Exception e) {
            System.out.println("ERROR ON GETTING MESSAGE.");
        }
        this.message = message;
    }

    public void get_id()
    {
        this.id = (Manager.db.size() / 5);
        System.out.println(this.id);
    }

    public void get_alert()
    {
        int foo = 0;
        System.out.println("Enter urgency status.\n1 - no urgency\n2 - average urgency\n3 - high urgency.");
        try{
            foo = scan.nextInt();
        } catch (Exception e) {
            System.out.println("ERROR ON ALERT");
        }
        switch(foo)
        {
            case 1:
                this.alert = "n";
                break;
            case 2:
                this.alert = "a";
                break;
            case 3:
                this.alert = "h";
                break;
            default:
                get_alert();
        }
    }

    public void read_file()
    {
        String foo = "";
        try{
            foo = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ArrayList<String> bar = new ArrayList<String>();
        foo = foo.replace("\n", "").replace("\r", "").replace("\r\n", "").replaceAll("\n\r", "");
        bar = new ArrayList<String>(Arrays.asList(foo.split(",")));
        Manager.db = bar;
    }

    public void save_file()
    {
        try (FileWriter fw = new FileWriter("data.csv", false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            for(int i = 0; i < db.size(); i++)
            {
                if(i % 5 == 0 && i != 0)
                    out.println();
                out.print(db.get(i) + ",");
            }
        }
        catch (Exception e) {
            System.out.println("ERROR ON WRITING TO FILE!");
        }
    }

    public void save_message()
    {
        try (FileWriter fw = new FileWriter(messageFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
        {
            out.print(this.id + this.alert + ",");
            out.print(this.message);
            out.println();
        }
        catch (Exception e) {
            System.out.println("ERROR ON WRITING TO FILE!");
        }
    }

    public void read_message()
    {
        String foo = "";
        try{
            foo = new String(Files.readAllBytes(Paths.get(messageFile)));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ArrayList<String> bar = new ArrayList<String>();
        foo = foo.replace("\n", "").replace("\r", "").replace("\r\n", "").replaceAll("\n\r", "");
        bar = new ArrayList<String>(Arrays.asList(foo.split(",")));
        Manager.messageList = bar;
    }

    public void make_list()
    {
        String foo = "";
        read_file();
        get_message();
        get_date();
        set_deadline(2023, 03, 14);
        get_id();
        get_alert();
        foo = this.id + this.alert;
        this.dList.add(foo);
        this.dList.add(this.day);
        this.dList.add(this.time);
        this.dList.add(this.deadline);
        this.dList.add(this.firstName + " " + this.secondName);
        save_message();
        db.addAll(this.dList);
        save_file();
        messageList.add(this.id + this.alert + ", ");
        messageList.add(this.message + "\n");
        save_message();
    }
}
