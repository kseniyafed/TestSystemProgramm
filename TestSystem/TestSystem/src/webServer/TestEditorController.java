
package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;


public class TestEditorController extends AbstractTemplateController{
    public TestEditorController() throws IOException {
    }
    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap<String,Object> model = new HashMap();
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
        String subjectId = extractFromURI(he.getRequestURI());
        String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
        HashMap<String, String> formValues = parseHtmlQuery(requestBody);
        try {
            UserDbGateway udbg = new UserDbGateway();
            SessionDbGateway sdbg = new SessionDbGateway();
            SubjectDbGateway sjdbg = new SubjectDbGateway();
            QuestionDbGateway qdbg= new QuestionDbGateway();
            int idSession = sdbg.getSessionIdFromCookie(cookieStr);
            Subject subject = sjdbg.findById(Integer.parseInt(subjectId));
            User user = udbg.getById(sdbg.getUserIdBySessId(idSession));
            ArrayList<Question> questions = qdbg.
                    findAllByIdSubject(Integer.parseInt(subjectId));
            model.put("questions", questions);
            model.put("login", user.getLogin());
            model.put("subject", subject);

        } catch (SQLException ex) {
            Logger.getLogger(TestEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
           respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "TestEditor.ftl";
    }
    
}
