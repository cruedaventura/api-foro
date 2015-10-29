package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.Exceptions.UserHasNoPermissionsException;
import edu.upc.eetac.dsa.beeter.entity.Group;
import edu.upc.eetac.dsa.beeter.entity.GroupCollection;
import edu.upc.eetac.dsa.beeter.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAOImpl implements GroupDAO
{
    @Override
    public Group createGroup(String userid, String name) throws SQLException, UserHasNoPermissionsException
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


            if (this.userHasPermissions(userid, Role.admin)) {
                stmt = connection.prepareStatement(GroupDAOQuery.CREATE_GROUP);
                stmt.setString(1, groupid);
                stmt.setString(2, userid);
                stmt.setString(3, name);

                stmt.executeUpdate();
            } else {
                throw new UserHasNoPermissionsException(userid);
            }
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

    private boolean userHasPermissions(String userid, Role supposedRoleToHave) throws SQLException
    {
        Connection        connection = null;
        PreparedStatement stmt       = null;
        String userRole = "";

        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOQuery.GET_ROLE_BY_USER);
            stmt.setString(1, userid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userRole = rs.getString("role");
            }

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

        return userRole.equals(supposedRoleToHave.toString());
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
    public GroupCollection getGroups() throws SQLException
    {

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

             stmt = connection.prepareStatement(GroupDAOQuery.GET_GROUP);

            ResultSet rs = stmt.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (rs.next()) {
                    groups.add( new Group(rs.getString("id"), rs.getString("userid"), rs.getString("name")));
            }
            return new GroupCollection(groups);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public Group updateGroup(String id, String subject, String content) throws SQLException
    {
        return null;
    }

    @Override
    public boolean deleteGroup(String id) throws SQLException
    {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(GroupDAOQuery.DELETE_GROUP);
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
    @Override
    public void joinGroup(String userid, String groupid) throws SQLException
    {
        Connection        connection = null;
        PreparedStatement stmt       = null;
        String            id    = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOImpl.UserDAOQuery.UUID);

            stmt = connection.prepareStatement(GroupDAOQuery.JOIN_GROUP);
            stmt.setString(1, userid);
            stmt.setString(2, groupid);

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
    }

}
