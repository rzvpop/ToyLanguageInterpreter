package Model.Expression;

import Exceptions.ExprEx;
import Exceptions.InexVarEx;
import Exceptions.MemoryEx;
import Exceptions.UndeclaredEx;
import Model.ADT.MyDictionary;
import Model.Utils.Heap;

public interface IExpression
{
    int evaluate(MyDictionary<String, Integer> st, Heap heap) throws ExprEx, InexVarEx, UndeclaredEx, MemoryEx;
}
