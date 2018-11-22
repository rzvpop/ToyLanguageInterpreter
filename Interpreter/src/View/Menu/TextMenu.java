package View.Menu;

import Model.ADT.MyDictionary;

import java.util.Map;
import java.util.Scanner;

public class TextMenu
{
    private MyDictionary<String, Command> commands;

    public TextMenu()
    {
        commands = new MyDictionary<String, Command>();
    }

    public void addCommand(Command c)
    {
        commands.put(c.getKey(), c);
    }

    private void PrintMenu()
    {
        for(Map.Entry<String, Command> com : commands.getAll())
        {
            String line = String.format("%4s : %s", com.getValue().getKey(), com.getValue().getDescription());
            System.out.println(line);
        }
    }

    public void show()
    {

        Scanner scan = new Scanner(System.in);

        while(true)
        {
            PrintMenu();
            System.out.println("Input the option:");

            String choice = scan.next();
            Command com = commands.getValue(choice);

            if(com == null)
            {
                System.out.println("Invalid Option");
                continue;
            }
            else
            {
                com.execute();
            }
        }
    }
}
