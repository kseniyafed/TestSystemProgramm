
package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class AddSubjectController extends AbstractTemplateController{
     public AddSubjectController() throws IOException {
    }
    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap<String, Object> model = new HashMap();
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
        String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
        HashMap<String, String> formValues = parseHtmlQuery(requestBody);
       
        try {
            SessionDbGateway sessdbg = new SessionDbGateway();
            UserDbGateway udbg = new UserDbGateway();
            SubjectDbGateway subdbg=new SubjectDbGateway();
            int idSession = sessdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sessdbg.getUserIdBySessId(idSession));
            
            if (formValues.get("subject") != null && formValues.get("number") != null && formValues.get("teory") != null){
                subdbg.insert(Integer.parseInt(formValues.get("number")),formValues.get("subject"),formValues.get("teory"));
   
            }
            
            model.put("login", user.getLogin());
        } catch (SQLException ex) {
            Logger.getLogger(AddSubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "AddSubjectPage.ftl";
    }
}
