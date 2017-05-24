package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class StudentController extends AbstractTemplateController {

    public StudentController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap<String,Object> model = new HashMap();

        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);

        try {
            UserDbGateway udbg = new UserDbGateway();
            SessionDbGateway sdbg = new SessionDbGateway();
            SubjectDbGateway sjdbg = new SubjectDbGateway();

            int idSession = sdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sdbg.getUserIdBySessId(idSession));
            model.put("login", user.getLogin());

            ArrayList<Subject> subjects =sjdbg.findAll();

            model.put("subjects", subjects);

        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "MainPageStudent.ftl";
    }
}
