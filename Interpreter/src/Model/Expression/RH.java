package Model.Expression;

import Exceptions.ExprEx;
import Exceptions.InexVarEx;
import Exceptions.MemoryEx;
import Exceptions.UndeclaredEx;
import Model.ADT.MyDictionary;
import Model.Utils.Heap;

public class RH implements IExpression
{
    private String var_name;

    public RH(String var_name)
    {
        this.var_name = var_name;
    }

    @Override
    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExprEx, InexVarEx, UndeclaredEx, MemoryEx
    {
        if(st.find(var_name))
        {
            int addr = st.getValue(var_name);
            if(heap.find(addr))
            {
                return heap.readAddr(addr);
            }
            else
            {
                throw new MemoryEx("'" + var_name +"' points to an invalid address.");
            }
        }
        else
        {
            throw new UndeclaredEx("No resource with this name: " + var_name + ".");
        }
    }

    @Override
    public String toString()
    {
        return "RH(" + var_name + ")";
    }
}
