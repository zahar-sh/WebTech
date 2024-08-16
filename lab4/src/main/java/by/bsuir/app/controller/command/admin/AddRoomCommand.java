package by.bsuir.app.controller.command.admin;

import by.bsuir.app.controller.command.Command;
import by.bsuir.app.controller.command.CommandResult;
import by.bsuir.app.entity.Room;
import by.bsuir.app.exception.ServiceException;
import by.bsuir.app.service.RoomService;
import by.bsuir.app.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRoomCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String roomNumber = request.getParameter("roomNumber");

        Validation validation = new Validation();
        Map<String, String> values = new HashMap<>();
        values.put("roomNumber", roomNumber);
        if (!validation.isValid(values)) {
            return CommandResult.redirect("controller?command=showRooms&message=" + "invalidRoom");
        }

        RoomService roomService = new RoomService();
        roomService.saveRoom(null, roomNumber, false);

        List<Room> roomList = roomService.findAll();
        request.setAttribute("roomList", roomList);

        return CommandResult.redirect("controller?command=showRooms&message=" + "added");
    }
}
