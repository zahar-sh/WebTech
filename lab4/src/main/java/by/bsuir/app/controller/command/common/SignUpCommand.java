package by.bsuir.app.controller.command.common;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;
import by.bsuir.app.entity.User;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.UserService;
import by.bsuir.app.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class SignUpCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> signUpData = new HashMap<>();
        signUpData.put("username", login);
        Validation validation = new Validation();
        if (!validation.isValid(signUpData)) {
            String errorName = validation.getInvalidData();
            request.setAttribute("signUpError", errorName);
            return CommandResult.forward("/WEB-INF/pages/login.jsp");
        }

        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findByUsername(login);
        if (optionalUser.isPresent()) {
            request.setAttribute("signUpError", "username");
            return CommandResult.forward("/WEB-INF/pages/login.jsp");
        }
        userService.signUpUser(null, login, password);
        return CommandResult.redirect("controller?command=startLogin");
    }
}
