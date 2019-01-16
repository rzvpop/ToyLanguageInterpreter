package Model.Expression;

import Exceptions.InexVarEx;
import Model.ADT.MyDictionary;
import Model.Utils.Heap;

public class VarExp implements IExpression
{
    private String name;

    public VarExp(String name) {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }

    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws InexVarEx
    {
        if (st.find(this.name))
        {
            return st.getValue(this.name);
        } else {
            throw new InexVarEx("Inexistent variable '" + this.name + "'");
        }
    }
}