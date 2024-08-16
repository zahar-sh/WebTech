package by.bsuir.app.controller.command.common;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;
import by.bsuir.app.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartLoginCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.forward("/WEB-INF/pages/login.jsp");
    }
}
