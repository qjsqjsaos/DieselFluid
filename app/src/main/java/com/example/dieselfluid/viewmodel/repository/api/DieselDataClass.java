package com.example.dieselfluid.viewmodel.repository.api;

import com.example.dieselfluid.viewmodel.repository.api.data.SecurityDefinitions;
import com.example.dieselfluid.viewmodel.repository.api.data.Info;

public class DieselDataClass
{
    private String basePath;

    private String paths;

    private String host;

    private String[] schemes;

    private SecurityDefinitions securityDefinitions;

    private String definitions;

    private String swagger;

    private Info info;

    public String getBasePath ()
    {
        return basePath;
    }

    public void setBasePath (String basePath)
    {
        this.basePath = basePath;
    }

    public String getPaths ()
    {
        return paths;
    }

    public void setPaths (String paths)
    {
        this.paths = paths;
    }

    public String getHost ()
    {
        return host;
    }

    public void setHost (String host)
    {
        this.host = host;
    }

    public String[] getSchemes ()
    {
        return schemes;
    }

    public void setSchemes (String[] schemes)
    {
        this.schemes = schemes;
    }

    public SecurityDefinitions getSecurityDefinitions ()
    {
        return securityDefinitions;
    }

    public void setSecurityDefinitions (SecurityDefinitions securityDefinitions)
    {
        this.securityDefinitions = securityDefinitions;
    }

    public String getDefinitions ()
    {
        return definitions;
    }

    public void setDefinitions (String definitions)
    {
        this.definitions = definitions;
    }

    public String getSwagger ()
    {
        return swagger;
    }

    public void setSwagger (String swagger)
    {
        this.swagger = swagger;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [basePath = "+basePath+", paths = "+paths+", host = "+host+", schemes = "+schemes+", securityDefinitions = "+securityDefinitions+", definitions = "+definitions+", swagger = "+swagger+", info = "+info+"]";
    }
}
