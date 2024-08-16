package by.bsuir.app.controller.command.admin;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;
import by.bsuir.app.entity.Room;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRoomsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RoomService roomService = new RoomService();
        List<Room> fullRoomList = roomService.findAll();
        request.setAttribute("roomList", fullRoomList);

        String notifyMessage = request.getParameter("message");
        if (notifyMessage != null) {
            request.setAttribute("notifyMessage", notifyMessage);
        }

        return CommandResult.forward("/WEB-INF/pages/admin/rooms.jsp");
    }
}
