package edu.upc.eetac.dsa.beeter.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group
{
    private String id;
    private String userid;
    private String name;


    public Group(String id, String userid, String name)
    {
        this.id = id;
        this.userid = userid;
        this.name = name;
    }


    public String getId()
    {
        return id;
    }

    public String getUserid()
    {
        return userid;
    }

    public String getName()
    {
        return name;
    }

}
