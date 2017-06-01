package webServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestionDbGateway extends DbGateway{
    public QuestionDbGateway() throws SQLException {
    }
    
    public ArrayList<Question> findAllByIdSubject(int idSubject) throws SQLException {
        ArrayList<Question> questions=new ArrayList<>();
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Question WHERE idSubject = ? ORDER BY number");
        stmt.setInt(1, idSubject);
        ResultSet result = stmt.executeQuery();
        
        if (!result.isClosed()) {
            while(result.next()) {
                questions.add( createQuestion(result));
            }
            return questions;
        } else {
            stmt.close();
            return null;
        }
    
    } 
    public boolean checkAnswer(String idQuestion, String answer) throws SQLException{
       
        PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM Question WHERE idQuestion = ? and answer = ?"); 
        stmt.setInt(1, Integer.parseInt(idQuestion));
        stmt.setString(2, answer);
        ResultSet result = stmt.executeQuery();
        
        if (!result.isClosed()) {
            return true;
            }
        else{
            stmt.close();
            return false;  
        }    
    }
    private Question createQuestion(ResultSet result) throws SQLException {
        Question question = new Question();
        question.put("idQuestion", result.getInt("idQuestion"));
        question.put("idSubject", result.getInt("idSubject"));
        question.put("number", result.getInt("number"));
        question.put("formulation", result.getString("formulation"));
        question.put("answer", result.getString("answer"));
        return question;
    }

    void insert(int quesNum,int idSubject, String question, String answer) throws SQLException {
        Statement stmt = getConnection().createStatement();
        question=question+"<p>";
        answer=answer+"<p>";
        stmt.execute("INSERT INTO Question(idSubject,formulation,number,answer) VALUES (\""
                 + idSubject + "\", \"" 
                 + question + "\", \"" 
                 + quesNum + "\", \"" 
                 + answer + "\")");
        stmt.close();
    }

    void delete(int idSubject) throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("DELETE FROM Question WHERE idSubject = " + idSubject);
        stmt.close();
    }
}
