package webServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;

public abstract class AbstractTemplateController implements HttpHandler {

    protected final Configuration freeMarkerCfg;

    public AbstractTemplateController() throws IOException {
        freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_25);
        freeMarkerCfg.setDirectoryForTemplateLoading(new File("./tmpl"));
    }

    @Override
    public abstract void handle(HttpExchange he) throws IOException;

    protected void respond(HashMap<String, Object> model, HttpExchange exchange) 
            throws IOException {

        Template tmpl = freeMarkerCfg.getTemplate(getTemplateFilename());
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        try (final OutputStreamWriter out = new OutputStreamWriter(response)) {
            tmpl.process(model, out);
        } catch (TemplateException ex) {
            throw new IOException(ex);
        }
        byte[] bytes = response.toByteArray();
        exchange.sendResponseHeaders(200, bytes.length);
        try (final OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    public HashMap<String, String> parseHtmlQuery(String formValuesEncoded)
            throws UnsupportedEncodingException {
        String formValuesDecoded = URLDecoder.decode(formValuesEncoded, "UTF-8");
        String[] formValues = formValuesDecoded.split("&");
        HashMap<String, String> result = new HashMap<>();
        for (String formValue : formValues) {
            String[] valueParts = formValue.split("=");
            if (valueParts.length == 1) {
                result.put(valueParts[0], null);
            } else {
                result.put(valueParts[0], valueParts[1]);
            }
        }
        return result;
    }

    public String extractFromURI(URI uri) {
        String[] uriParts = uri.getPath().split("/");
        try {
            return uriParts[uriParts.length - 1];
        } catch (NumberFormatException e) {
            return "";
        }
    }

    protected abstract String getTemplateFilename();
}