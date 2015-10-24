package edu.upc.eetac.dsa.beeter.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupCollection
{
    private List<Group> groups = new ArrayList<>();

       public List<Group> getGroups()
    {
        return this.groups;
    }

    public GroupCollection(List<Group> groups)
    {
        this.groups = groups;
    }
}
