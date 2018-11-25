package Model.Expression;

import Exceptions.ExprEx;
import Exceptions.InexVarEx;
import Exceptions.MemoryEx;
import Exceptions.UndeclaredEx;
import Model.ADT.MyDictionary;
import Model.Utils.Heap;

public class Bool implements IExpression
{
    private IExpression expr1;
    private IExpression expr2;
    private String operator;

    public Bool(IExpression expr1, IExpression expr2, String operator)
    {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExprEx, InexVarEx, UndeclaredEx, MemoryEx
    {
        int firstRes = expr1.evaluate(st, heap);
        int secondRes = expr2.evaluate(st, heap);

        switch(operator)
        {
            case "<":
                return ((firstRes < secondRes)?0:1);
            case "<=":
                return ((firstRes <= secondRes)?0:1);
            case "==":
                return ((firstRes == secondRes)?0:1);
            case "!=":
                return ((firstRes != secondRes)?0:1);
            case ">":
                return ((firstRes > secondRes)?0:1);
            case ">=":
                return ((firstRes >= secondRes)?0:1);
            default:
                throw new RuntimeException("");
        }
    }

    @Override
    public String toString()
    {
        return expr1.toString() + operator + expr2.toString();
    }
}
