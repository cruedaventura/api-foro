package edu.upc.eetac.dsa.beeter.dao;

public class GroupDAOQuery
{
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_GROUP = "insert into groups (id, userid, name) values (UNHEX(?), unhex(?), ?)";
    public final static String GET_GROUP_BY_ID = "select hex(id) as id, hex(userid) as userid, name FROM groups WHERE id = UNHEX(?)";
    public final static String GET_GROUP = "select hex(id) as id, hex(userid) as userid, name FROM groups";
    public final static String GET_GROUP_AFTER = "select hex(id) as id, hex(userid) as userid, subject, creation_timestamp, last_modified from stings  where creation_timestamp > ? order by creation_timestamp desc limit 5";
    public final static String UPDATE_GROUP = "update stings set subject=?, content=? where id=unhex(?) ";
    public final static String DELETE_GROUP = "delete from stings where id=unhex(?)";
}


