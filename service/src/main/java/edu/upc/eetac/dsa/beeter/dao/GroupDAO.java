package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Group;
import edu.upc.eetac.dsa.beeter.entity.GroupCollection;

import java.sql.SQLException;

public interface GroupDAO
{
    public Group createGroup(String userid, String name) throws SQLException;
    public Group getGroupById(String id) throws SQLException;
    public GroupCollection getGroups() throws SQLException;
    public Group updateGroup(String id, String subject, String content) throws SQLException;
    public boolean deleteGroup(String id) throws SQLException;
}
