package fXmlRpc;

import java.util.Map;
import java.util.List;
import java.util.Date;

public class SystemHandler {

    public String echo(String s)
    {
        return s;
    }

    public Double echo(Double d)
    {
        return d;
    }

    public Integer echo(Integer i)
    {
        return i;
    }

    public Map echo(Map m)
    {
        return m;
    }

    public List echo(List l)
    {
        return l;
    }

    public byte[] echo(byte[] b)
    {
        return b;
    }

    public Date echo(Date d)
    {
        return d;
    }
}
