
package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;


public class SubjectController extends AbstractTemplateController{
     public SubjectController() throws IOException{
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
       HashMap<String, Object> model = new HashMap();
       String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
       String subjectId = extractFromURI(he.getRequestURI());
      
       String requestBody = IOUtils.toString(he.getRequestBody(), "UTF-8");
       HashMap<String, String> formValues = parseHtmlQuery(requestBody);
       //System.out.println(formValues.get("number"));
       //System.out.println(formValues.get("teory")); 
       
       
       try {
            UserDbGateway udbg = new UserDbGateway();
            SessionDbGateway sdbg = new SessionDbGateway();
            SubjectDbGateway sjdbg = new SubjectDbGateway();
            if(formValues.get("number")!=null && formValues.get("teory")!=null && subjectId!=null){
                sjdbg.update(subjectId, formValues.get("number"),formValues.get("teory"));
            }
            Subject subject = sjdbg.findById(Integer.parseInt(subjectId));
            int idSession = sdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sdbg.getUserIdBySessId(idSession)); 
            if(sjdbg.hasTest(Integer.parseInt(subjectId))){
                model.put("visible", true);
            }
            model.put("login", user.getLogin());
            model.put("subject", subject);
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
         return "SubjectPage.ftl";
    }
}
