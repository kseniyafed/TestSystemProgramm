
package webServer;

import java.util.HashMap;

public class User extends HashMap<String, Object>{
    private int id;
    private String login;
    private String type;
    private String name;
    private int group;

    public void createUser() {
        this.login=(String) get("login");
        this.group = (int) get("idGroup");
        this.type = (String) get("type");
        this.name = (String) get("fullName");
        this.id=(int) get("idUser");
    }

    public boolean isTeacher() {
        boolean isTeacher = false;

        if (type.equals("учитель")) {
            isTeacher = true;
        }
        return isTeacher;
    }

    public String getLogin() {

        return login;
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public int getGroup() {

        return group;
    }
    public int getId() {

        return id;
    }
}
