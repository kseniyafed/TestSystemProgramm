package webServer;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class TestSystem {

    public static void main(String[] args) throws IOException {
        int port=8030;
        System.out.println("Server started on port:"+port);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new LoginController());
        server.createContext("/authorize", new AuthoController());
        server.createContext("/teacherPage", new TeacherController());
        server.createContext("/studentPage", new StudentController());
        server.createContext("/teoryPage", new TeoryController());
        server.createContext("/testPage", new TestController());
        server.createContext("/resultPage", new ResultController());
        server.createContext("/groupPage", new GroupController());
        server.createContext("/addSubjectPage", new AddSubjectController());
        server.createContext("/createTestPage", new CreateTestController());
        server.createContext("/subjectPage", new SubjectController());
        server.createContext("/testEditor", new TestEditorController());
        
        server.start();
    }
}