package com.techcourse;

import com.techcourse.controller.IndexController;
import com.techcourse.controller.LoginController;
import com.techcourse.controller.LoginViewController;
import com.techcourse.controller.LogoutController;
import com.techcourse.controller.RegisterController;
import com.techcourse.controller.RegisterViewController;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webmvc.org.springframework.web.servlet.mvc.HandlerMapping;
import webmvc.org.springframework.web.servlet.mvc.asis.MyController;

public class ManualHandlerMapping implements HandlerMapping {

    private static final Logger log = LoggerFactory.getLogger(ManualHandlerMapping.class);

    private static final Map<String, MyController> controllers = new HashMap<>();

    @Override
    public void initialize() {
        controllers.put("/", new IndexController());
        controllers.put("/login", new LoginController());
        controllers.put("/login/view", new LoginViewController());
        controllers.put("/logout", new LogoutController());
        controllers.put("/register/view", new RegisterViewController());
        controllers.put("/register", new RegisterController());

        log.info("Initialized Handler Mapping!");
        controllers.keySet()
                .forEach(path -> log.info("Path : {}, Controller : {}", path, controllers.get(path).getClass()));
    }

    @Override
    public MyController getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.debug("Request Mapping Uri : {}", requestURI);
        return controllers.get(requestURI);
    }
}
