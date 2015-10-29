package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyDAOImpl implements ReplyDAO
{
    @Override
    public Reply createReply(String stingid, String userid, String content) throws SQLException
    {
        Connection        connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOImpl.UserDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(ReplyDAOQuery.CREATE_REPLY);
            stmt.setString(1, id);
            stmt.setString(2, stingid);
            stmt.setString(3, userid);
            stmt.setString(4, content);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return this.getReplyById(id);
    }

    @Override
    public Reply getReplyById(String id) throws SQLException
    {
        Reply reply = null;

        Connection        connection = null;
        PreparedStatement stmt       = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(ReplyDAOQuery.GET_REPLY_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reply = new Reply(rs.getString("id"), rs.getString("stingid"), rs.getString("userid"), rs.getString("content"));
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
        return reply;
    }

    @Override
    public Reply updateSting(String id, String subject, String content) throws SQLException
    {
        return null;
    }

    @Override
    public boolean deleteSting(String id) throws SQLException
    {
        return false;
    }
}
