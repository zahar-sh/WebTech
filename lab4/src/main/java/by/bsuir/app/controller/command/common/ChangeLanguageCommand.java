package by.bsuir.app.controller.command.common;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter("lang");
        String query = request.getQueryString();
        session.setAttribute("language", language);
        if (query.length() > 46) {
            String page = query.substring(46);
            return CommandResult.redirect("controller?command=" + page);
        } else {
            return CommandResult.redirect("controller?command=" + "startPage");
        }
    }
}
