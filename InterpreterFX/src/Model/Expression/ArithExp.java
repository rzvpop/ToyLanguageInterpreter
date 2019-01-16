package Model.Expression;

import Exceptions.*;
import Model.ADT.MyDictionary;
import Model.Utils.Heap;

public class ArithExp implements IExpression
{
    private IExpression op1, op2;
    private char operator;

    public ArithExp(IExpression op1, IExpression op2, char operator)
    {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    public String toString()
    {
        return "(" + op1.toString() + " " + operator + " " + op2.toString() + ")";
    }

    public int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExprEx, InexVarEx, UndeclaredEx, MemoryEx
    {
        int firstRes = this.op1.evaluate(st, heap);
        int secondRes = this.op2.evaluate(st, heap);

        switch (this.operator)
        {
            case '+':
                return firstRes + secondRes;
                //break;

            case '-':
                return firstRes - secondRes;
                //break;

            case '*':
                return firstRes * secondRes;
                //break;

            case '/':
                if (secondRes == 0)
                {
                    throw new ExprEx("Divide by 0.");
                }

                return firstRes / secondRes;
                //break;

            default:
                throw new SyntaxEx("Invalid boolean operator.");
        }
    }
}
