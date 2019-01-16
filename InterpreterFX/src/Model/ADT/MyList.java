package Model.ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<E> implements MyIList<E>
{
    private ArrayList<E> elems;

    public MyList()
    {
        this.elems = new ArrayList<>();
    }

    @Override
    public void add(E e)
    {
        this.elems.add(e);
    }

    @Override
    public void remove(E e)
    {
        this.elems.remove(e);
    }

    public E get(int pos)
    {
        return elems.get(pos);
    }

    public void clear()
    {
        this.elems.clear();
    }

    public String toString()
    {
        String str = "";

        for(E e : this.elems)
        {
            str += e.toString() + " | ";
        }

        return str;
    }

    public int size()
    {
        return elems.size();
    }

    public ArrayList<E> GetArrayList()
    {
        return this.elems;
    }

    public void SetList(List<E> list)
    {
        this.elems = (ArrayList<E>)list;
    }
}
