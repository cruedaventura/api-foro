package edu.upc.eetac.dsa.beeter.dao;

public class ReplyDAOQuery
{
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_REPLY = "insert into replies (id, stingid, userid, content) values (UNHEX(?), unhex(?), unhex(?), ?)";
    public final static String GET_REPLY_BY_ID = "select hex(id) as id, hex(stingid) as stingid, hex(userid) as userid, content, created_at from replies where id=unhex(?)";
    public final static String GET_REPLYS = "select hex(id) as id, hex(userid) as userid, hex(groupid) as groupid, subject, creation_timestamp, last_modified from stings where creation_timestamp < ? order by creation_timestamp desc limit 5";
    public final static String GET_REPLYS_AFTER = "select hex(id) as id, hex(userid) as userid, subject, creation_timestamp, last_modified from stings  where creation_timestamp > ? order by creation_timestamp desc limit 5";
    public final static String UPDATE_REPLY = "update stings set subject=?, content=? where id=unhex(?) ";
    public final static String DELETE_REPLY = "delete from stings where id=unhex(?)";
}
