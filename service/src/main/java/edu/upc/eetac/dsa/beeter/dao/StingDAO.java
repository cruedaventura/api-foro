package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Sting;
import edu.upc.eetac.dsa.beeter.entity.StingCollection;

import java.sql.SQLException;

public interface StingDAO
{
    public Sting createSting(String userid, String groupid, String subject, String content) throws SQLException;
    public Sting getStingById(String id) throws SQLException;
   // public Sting getStingbyGroupid (String groupid) throws SQLException;
    public StingCollection getStings(long timestamp, boolean before) throws SQLException;
    public Sting updateSting(String id, String subject, String content) throws SQLException;
    public boolean deleteSting(String id) throws SQLException;

}
