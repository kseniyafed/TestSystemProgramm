package webServer;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;

class LoginController extends AbstractTemplateController {

    public LoginController() throws IOException {
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        HashMap<String,Object> model = new HashMap();

        Integer error = extractError(he.getRequestURI());
        
        if (error != null) {
            model.put("err", "Неверный логин или пароль!");
        } 
        respond(model, he);
    }

    protected String getTemplateFilename() {
        return "Authorization.ftl";
    }

    
}