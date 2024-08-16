package by.bsuir.app.controller.command.user;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;
import by.bsuir.app.entity.Room;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RoomService roomService = new RoomService();
        List<Room> freeRoomList = roomService.findFree();
        request.setAttribute("roomList", freeRoomList);
        return CommandResult.forward("/WEB-INF/pages/makeOrder.jsp");
    }
}
