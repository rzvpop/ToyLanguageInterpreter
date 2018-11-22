package View.Menu;

import Controller.InterpreterController;
import Model.ADT.MyDictionary;
import Model.ADT.MyStack;

public class RunExemple extends Command
{
    InterpreterController ctrl;

    public RunExemple(String key, String description, InterpreterController ctrl)
    {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute()
    {
        try
        {
            ctrl.allStep();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
