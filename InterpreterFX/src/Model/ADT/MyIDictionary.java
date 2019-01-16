package Model.ADT;

public interface MyIDictionary<K, V>
{
    void put(K k, V v);
    void remove(K k);
    boolean find(K k);
    boolean isEmpty();
    V getValue(K k);
    int size();
}
