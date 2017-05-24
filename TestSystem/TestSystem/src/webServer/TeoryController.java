package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeoryController extends AbstractTemplateController {

    public TeoryController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap<String, Object> model = new HashMap();
        String subjectName = extractFromURI(he.getRequestURI());
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);
        Subject subject = new Subject();
        try {
            if (!subjectName.equals("")) {
                SubjectDbGateway sjdbg = new SubjectDbGateway();
                SessionDbGateway sessdbg = new SessionDbGateway();
                UserDbGateway udbg = new UserDbGateway();
                subject = sjdbg.findByName(subjectName);
                ResultDbGateway rdbg = new ResultDbGateway();
                int idSession = sessdbg.getSessionIdFromCookie(cookieStr);
                User user = udbg.getById(sessdbg.getUserIdBySessId(idSession));
                model.put("login", user.getLogin());

                if (rdbg.isPassed(user.getId(), (int) subject.get("idSubject"))) {
                    model.put("err", "Вы уже проходили этот тест!");
                    model.put("mark", rdbg.getMark(user.getId(), 
                            (int) subject.get("idSubject")));
                }
                sessdbg.update(sessdbg.getSessionIdFromCookie(cookieStr),
                        (int) subject.get("idSubject"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TeoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        model.put("subject", subject);
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "TeoryPage.ftl";
    }
}
