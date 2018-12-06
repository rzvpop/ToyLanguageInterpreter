package Exceptions;

public class AlreadyOpenedFileEx extends RuntimeException
{
    public AlreadyOpenedFileEx(String s)
    {
        super(s);
    }
}
