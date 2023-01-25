import java.util.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Todolist program to add, edit, store and remove tasks.
 *
 * @Ryder Paredes
 * 1/23/2023
 */
public class todolist
{
    public static void main(String[] args) throws FileNotFoundException
    {        
        //Array List: Stores Tasks
        ArrayList<String> todolist = new ArrayList<String>();

        //Variables
        boolean loop = true; //Loops through the menu infinitely unless user says otherwise. 
        String task = ""; //Variable that stores the initial input of the user.
        String output = ""; //Temporarily stores arraylist elements into string for Message Dialog.
        int count = 0; //Counts the amount of elements in the todolist arraylist.

        //Reads Elements from "userinput.txt" and stores it into todolist.
        try{
            BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"));

            String line;
            while ((line = reader.readLine()) != null) 
            {
                todolist.add(line);
                count++;
            }

            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        
        while(loop)
        {
            String input = JOptionPane.showInputDialog("-------MAIN MENU------ \n" +
                    "1: Add Task \n" +
                    "2: Remove Task \n" +
                    "3: View Tasks \n" +
                    "4: Exit Program \n\n" +
                    "Enter choice: ");

            switch(input)
            {
                case "1":     
                    task = JOptionPane.showInputDialog("Add Task: ");

                    //Add Task Algorithm
                    if(task != null) 
                    {
                        todolist.add(task);
                        count++;
                    }

                    break;
                case "2":
                    //Remove Task Algorithm
                    for(int i= 0; i < count; i++)
                    {
                        String viewList = todolist.get(i).toString();

                        output += (i + 1) + ": " + viewList + "\n";
                    }

                    String convert = JOptionPane.showInputDialog(output + "\n \n" + "Remove task: ");
                    output = ""; //Clears data from "output" variable

                    int remove = Integer.parseInt(convert);

                    todolist.remove(remove - 1);
                    count--;

                    break;
                case "3":
                    //View Tasks Algorithm
                    for(int i= 0; i < count; i++)
                    {
                        String viewList = todolist.get(i).toString();

                        output += (i + 1) + ": " + viewList + "\n";
                    }

                    JOptionPane.showMessageDialog(null, output + "\n");
                    output = ""; //Clears data from "output" variable

                    break;
                default:
                    //Exit Program
                    loop = false;

                    break;
            }
        }

        //Writes arraylist data into "userdata.txt".
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("userdata.txt"));

            for(int i = 0; i < count; i++)
            {
                writer.write(todolist.get(i) + "\n"); 
            }

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

