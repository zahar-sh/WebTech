package by.bsuir.app.controller.command.common;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        session.removeAttribute("username");
        session.removeAttribute("role");
        return CommandResult.forward("/WEB-INF/pages/login.jsp");
    }
}
