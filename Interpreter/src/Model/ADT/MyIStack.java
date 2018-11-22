package Model.ADT;

public interface MyIStack<E>
{
    void push(E e);
    E pop();
    boolean isEmpty();
}
