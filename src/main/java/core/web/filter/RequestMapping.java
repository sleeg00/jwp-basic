package core.web.filter;

import java.util.HashMap;
import java.util.Map;
import next.controller.Controller;
import next.controller.CreateUserController;
import next.controller.ForwardController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LoginController;
import next.controller.LogoutController;
import next.controller.ProfileController;
import next.controller.UpdateUserController;
import next.controller.UserSessionUtils;

public class RequestMapping {
    private static Map<String, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put("/users/create", new CreateUserController());
        controllerMap.put("/users/form", new ForwardController("/user/form.hsp"));
        controllerMap.put("/users/form", new CreateUserController());
        controllerMap.put("/", new HomeController());
        controllerMap.put("/users", new ListUserController());
        controllerMap.put("/users/login", new LoginController());
        controllerMap.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllerMap.put("/users/logout", new LogoutController());
        controllerMap.put("/users/profile", new ProfileController());
        controllerMap.put("/users/update", new UpdateUserController());
        controllerMap.put("/users/updateForm", new UpdateUserController());
    }
    public static Controller getController(String requestUrl) {
        return controllerMap.get(requestUrl);
    }
}
