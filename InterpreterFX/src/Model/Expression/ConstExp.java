package Model.Expression;

import Model.ADT.MyDictionary;
import Model.Utils.Heap;

public class ConstExp implements IExpression
{
    private int value;

    public String toString()
    {
        return Integer.toString(this.value);
    }

    public ConstExp(int value)
    {
        this.value = value;
    }

    public int evaluate(MyDictionary<String, Integer> st, Heap heap)
    {
        return this.value;
    }
}
