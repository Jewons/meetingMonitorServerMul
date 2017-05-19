package com.redcdn.commom.monitorcontrol.facade.provider;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;

import com.redcdn.commom.monitorcontrol.result.Result;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

//@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext>
{

    private JAXBContext context;

    private Class<?>[] types =
    { Result.class };

    public JAXBContextResolver() throws Exception
    {
        this.context = new JSONJAXBContext(JSONConfiguration.natural().humanReadableFormatting(true).build(), types);
    }

    public JAXBContext getContext(Class<?> objectType)
    {
        for (Class<?> type : types)
        {
            if (type.isAssignableFrom(objectType))
            {
                return context;
            }
        }
        return null;
    }
}