package com.example.dieselfluid.viewmodel.repository.api.data.securitydefinitions;

public class ApiKeyAuth
{
    private String in;

    private String name;

    private String type;

    public String getIn ()
    {
        return in;
    }

    public void setIn (String in)
    {
        this.in = in;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [in = "+in+", name = "+name+", type = "+type+"]";
    }
}