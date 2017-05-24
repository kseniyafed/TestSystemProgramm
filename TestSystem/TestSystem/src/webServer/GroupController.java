package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupController extends AbstractTemplateController {

    public GroupController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap<String, Object> model = new HashMap();
        int groupId = Integer.parseInt(extractFromURI(he.getRequestURI()));
        String cookieStr = he.getRequestHeaders().get("Cookie").get(0);

        try {
            SessionDbGateway sessdbg = new SessionDbGateway();
            UserDbGateway udbg = new UserDbGateway();
            GroupDbGateway gdbg = new GroupDbGateway();
            SubjectDbGateway sdbg = new SubjectDbGateway();
            ResultDbGateway rdbg = new ResultDbGateway();
            int idSession = sessdbg.getSessionIdFromCookie(cookieStr);
            User user = udbg.getById(sessdbg.getUserIdBySessId(idSession));
            ArrayList<Subject> subjects =sdbg.findAll();
            ArrayList<User> students = udbg.getAllFromGroup(groupId);
            ArrayList<HashMap> results = new ArrayList();
            for (User student : students) {
                HashMap<String, Object> result = new HashMap();
                ArrayList<Integer> marks = new ArrayList();
                for (Subject subject : subjects) {
                    marks.add(rdbg.getMark(student.getId(),
                            (int) subject.get("idSubject")));
                }
                result.put("name", student.getName());
                result.put("marks", marks);
                results.add(result);

            }
            model.put("results", results);
            model.put("subjects", subjects);
            model.put("login", user.getLogin());
            model.put("group", gdbg.getNameById(groupId));

        } catch (SQLException ex) {
            Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(groupId);
        respond(model, he);
    }

    @Override
    protected String getTemplateFilename() {
        return "GroupPage.ftl";
    } 
}