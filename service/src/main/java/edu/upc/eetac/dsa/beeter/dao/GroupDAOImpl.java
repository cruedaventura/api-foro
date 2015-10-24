package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDAOImpl implements GroupDAO
{
    @Override
    public Group createGroup(String userid, String name) throws SQLException
    {
        Connection        connection = null;
        PreparedStatement stmt       = null;
        String            groupid    = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOImpl.UserDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                groupid = rs.getString(1);
            } else {
                throw new SQLException();
            }

            stmt = connection.prepareStatement(GroupDAOQuery.CREATE_GROUP);
            stmt.setString(1, groupid);
            stmt.setString(2, userid);
            stmt.setString(3, name);

            System.out.println(stmt.toString());

            stmt.executeUpdate();
        } catch (SQLException exception) {
            throw exception;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return this.getGroupById(groupid);
    }

    @Override
    public Group getGroupById(String id) throws SQLException
    {
        Group group = null;

        Connection        connection = null;
        PreparedStatement stmt       = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(GroupDAOQuery.GET_GROUP_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                group = new Group(rs.getString("id"), rs.getString("userid"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return group;
    }

    @Override
    public Group getGroup(long timestamp, boolean before) throws SQLException
    {
        return null;
    }

    @Override
    public Group updateGroup(String id, String subject, String content) throws SQLException
    {
        return null;
    }

    @Override
    public boolean deleteGroup(String id) throws SQLException
    {
        return false;
    }
}
