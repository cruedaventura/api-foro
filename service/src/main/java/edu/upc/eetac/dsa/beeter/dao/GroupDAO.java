package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Group;

import java.sql.SQLException;

public interface GroupDAO
{
    public Group createGroup(String userid, String name) throws SQLException;
    public Group getGroupById(String id) throws SQLException;
    public Group getGroup(long timestamp, boolean before) throws SQLException;
    public Group updateGroup(String id, String subject, String content) throws SQLException;
    public boolean deleteGroup(String id) throws SQLException;
}
