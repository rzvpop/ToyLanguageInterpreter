package View.Menu;

import Controller.InterpreterController;

public class RunExemple extends Command
{
    private InterpreterController ctrl;

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
