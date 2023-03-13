package OOP_FOURTH_PROBLEM_JAVA;

import java.util.ArrayList;

public interface Save_and_read {
    
    public ArrayList<String> read_file(String file);

    public void save_file(ArrayList<String> str);
}
