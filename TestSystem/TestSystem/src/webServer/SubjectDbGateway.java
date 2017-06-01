package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubjectDbGateway extends DbGateway {

    public SubjectDbGateway() throws SQLException {
    }

    private Subject createSubject(ResultSet result) throws SQLException {
        Subject subject = new Subject();

        subject.put("idSubject", result.getInt("idSubject"));
        subject.put("number", result.getInt("number"));
        subject.put("name", result.getString("name"));
        subject.put("teory", result.getString("teory"));
        return subject;
    }

    ArrayList<Subject> findAll() throws SQLException {

        Statement stmt = getConnection().createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Subject");
        ArrayList<Subject> subjects = new ArrayList();

        while (result.next()) {
            subjects.add(createSubject(result));
        }

        stmt.close();

        return subjects;
    }

    Subject findByName(String name) throws SQLException {
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Subject WHERE name = ?");
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        
        if (!result.isClosed()) {
            return createSubject(result);
        } else {
            stmt.close();
            return null;
        }
    }
     Subject findById(int id) throws SQLException {
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Subject WHERE idSubject = ?");
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        
        if (!result.isClosed()) {
            return createSubject(result);
        } else {
            stmt.close();
            return null;
        }
    }
    void insert(int number,String name,String teory) throws SQLException {
        Statement stmt = getConnection().createStatement();
//        name=name+"<p>";
//        teory="<p>"+teory;
        stmt.execute("INSERT INTO Subject(number,name,teory) VALUES (\""
                 + number + "\", \"" 
                 + name + "\", \"" 
                 + teory + "\")");
        stmt.close();
    }
     public void delete(int idSubject) throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("DELETE FROM Question WHERE idSubject = " + idSubject);
        stmt.execute("DELETE FROM Subject WHERE idSubject = " + idSubject);
        stmt.close();
    }

    void update(String id, String number, String teory) throws SQLException {
        Statement stmt = getConnection().createStatement();
        String br="<p>";
        teory=br+teory;
        stmt.execute("UPDATE Subject SET number = \"" + number + "\", teory = \"" + teory + "\" WHERE idSubject = " + id);
    }

    boolean hasTest(int idSubject) throws SQLException {
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Question WHERE idSubject = ?");
        stmt.setInt(1, idSubject);
        ResultSet result = stmt.executeQuery();
        
        if (!result.isClosed()) {
            return true;
        } else {
            stmt.close();
            return false;
        }
    }
}
