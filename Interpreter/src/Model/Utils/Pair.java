package Model.Utils;

public class Pair<T, U>
{
    private T first;
    private U second;

    public Pair(T _first, U _second)
    {
        this.first = _first;
        this.second = _second;
    }

    public T GetFirst()
    {
        return first;
    }

    public U GetSecond()
    {
        return second;
    }

    public boolean equals(Pair<T, U> p)
    {
        return (this.first.equals(p.GetFirst()) && this.second.equals(p.GetSecond()));
    }
}
