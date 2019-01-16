package Model.ADT;

import Model.Utils.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary <K, V> implements MyIDictionary<K, V>
{
    private HashMap<K, V> dict;

    public MyDictionary()
    {
        this.dict = new HashMap<>();
    }

    public MyDictionary(MyDictionary<K, V> _my_dict)
    {
        this.dict = _my_dict.dict;
    }

    @Override
    public void put(K k, V v)
    {
        this.dict.put(k, v);
    }

    @Override
    public void remove(K k)
    {
        this.dict.remove(k);
    }

    @Override
    public boolean find(K k) {
        return dict.containsKey(k);
    }

    @Override
    public boolean isEmpty()
    {
        return this.dict.isEmpty();
    }

    @Override
    public V getValue(K k)
    {
        return this.dict.get(k);
    }

    @Override
    public int size()
    {
        return this.dict.size();
    }

    public void clear()
    {
        this.dict.clear();
    }

    public Set<Map.Entry<K, V>> getAll()
    {
        return this.dict.entrySet();
    }

    public Map<K, V> All()
    {
        return dict;
    }

    public Collection<Integer> getValues()
    {
        return (Collection<Integer>) dict.values();
    }
    //V i nloc de Integer :-?

    public String toString()
    {
        String str = "";

        for(Map.Entry<K, V> entry : this.dict.entrySet())
        {
            str += "| " + entry.getKey().toString() + " -> " + entry.getValue().toString() + " |\n";
        }

        return str;
    }
}
