package Exceptions;

public class FileNotFoundEx extends RuntimeException
{
    public FileNotFoundEx(String s)
    {
        super(s);
    }
}
