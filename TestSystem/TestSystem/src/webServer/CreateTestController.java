
package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

class CreateTestController extends AbstractTemplateController {

    public CreateTestController() throws IOException{
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
       HashMap<String, Object> model = new HashMap();
       String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
       String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
       HashMap<String, String> formValues = parseHtmlQuery(requestBody);
       String subjectId = extractFromURI(he.getRequestURI());
        try {
            SessionDbGateway sessdbg = new SessionDbGateway();
            UserDbGateway udbg = new UserDbGateway();
            SubjectDbGateway sjdbg=new SubjectDbGateway();
            QuestionDbGateway qdbg=new QuestionDbGateway();
            int idSession = sessdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sessdbg.getUserIdBySessId(idSession));
            Subject subject = sjdbg.findById(Integer.parseInt(subjectId)); 
            if(TeacherController.quesNum==0) {
                qdbg.delete(Integer.parseInt(subjectId));
            }
           
            if(formValues.get("question")!=null && formValues.get("answer")!=null){
                qdbg.insert(TeacherController.quesNum,Integer.parseInt(subjectId), formValues.get("question"),formValues.get("answer"));
            }
            TeacherController.quesNum++;
            model.put("subject", subject);
            model.put("quesNum",TeacherController.quesNum);
            model.put("login", user.getLogin());
        } catch (SQLException ex) {
            Logger.getLogger(AddSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "CreateNewTestPage.ftl";
    }
    
}
