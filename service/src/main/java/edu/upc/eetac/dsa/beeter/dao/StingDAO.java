package edu.upc.eetac.dsa.beeter.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.upc.eetac.dsa.beeter.entity.Sting;
import edu.upc.eetac.dsa.beeter.entity.StingCollection;

import java.sql.SQLException;
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface StingDAO
{
    public Sting createSting(String userid, String subject, String content) throws SQLException;
    public Sting getStingById(String id) throws SQLException;
    public StingCollection getStings() throws SQLException;
    public Sting updateSting(String id, String subject, String content) throws SQLException;
    public boolean deleteSting(String id) throws SQLException;
}
