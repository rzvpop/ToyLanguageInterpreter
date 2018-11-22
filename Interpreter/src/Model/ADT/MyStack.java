package Model.ADT;

import java.util.ArrayList;

public class MyStack<E> implements MyIStack<E>
{
    private ArrayList<E> elems;
    private int top;
    private int size;

    public MyStack(int s)
    {
        this.size = s;
        this.elems = new ArrayList<>(this.size);
        this.top = -1;
    }

    @Override
    public void push(E e)
    {
        if(this.top < this.size)
        {
            this.elems.add(e);
            ++this.top;
        }
    }

    @Override
    public E pop()
    {
        return this.elems.remove(this.top--);
    }

    @Override
    public boolean isEmpty()
    {
        return (this.top == -1);
    }

    public void clear()
    {
        elems.clear();
        top = -1;
    }

    public String toString()
    {
        String str = "-----\n";
        for(int i = this.elems.size() - 1; i >= 0; --i)
        {
            str += "-" + this.elems.get(i).toString() + "\n";
        }
        str += "-----";

        return str;
    }
}
