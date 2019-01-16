package Model.Utils;

public interface IHeap<K, V>
{
    int allocate(K k);
    boolean find(K k);
    V readAddr(K k);
    void writeAddr(K k, V v);
    void deallocate(K k);
}
