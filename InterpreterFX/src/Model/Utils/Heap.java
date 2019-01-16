package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class Heap implements IHeap<Integer, Integer>
{
    private HashMap<Integer, Integer> map;
    private int memory;

    public Heap()
    {
        map = new HashMap<>();
        map.put(0, 0);
        memory = 0;
    }

    @Override
    public int allocate(Integer _val)
    {
        ++memory;
        map.put(memory, _val);
        return memory;
    }

    @Override
    public boolean find(Integer mem_loc)
    {
        return map.containsKey(mem_loc);
    }

    @Override
    public Integer readAddr(Integer _addr)
    {
        return map.get(_addr);
    }

    @Override
    public void writeAddr(Integer _addr, Integer _val)
    {
        map.put(_addr, _val);
    }

    @Override
    public void deallocate(Integer _addr)
    {
        map.remove(_addr);
    }

    public void setMap(HashMap<Integer, Integer> _map)
    {
        map = _map;
    }

    public HashMap<Integer, Integer> getMap()
    {
        return map;
    }

    @Override
    public String toString()
    {
        String str = "";

        for(Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            str += "| " + entry.getKey().toString() + " -> " +
                    entry.getValue().toString() + " |\n";
        }

        return str;
    }
}
