package by.bsuir.app.controller.command;

import by.bsuir.app.controller.command.admin.AddRoomCommand;
import by.bsuir.app.controller.command.admin.DeoccupyRoomCommand;
import by.bsuir.app.controller.command.admin.ShowRoomsCommand;
import by.bsuir.app.controller.command.common.*;
import by.bsuir.app.controller.command.user.MainPageCommand;
import by.bsuir.app.controller.command.user.MakeOrderCommand;

public class CommandFactory {
    private static final CommandFactory INSTANCE = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    public Command getCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand();
            case "showRooms":
                return new ShowRoomsCommand();
            case "mainPage":
                return new MainPageCommand();
            case "changeLanguage":
                return new ChangeLanguageCommand();
            case "addRoom":
                return new AddRoomCommand();
            case "makeOrder":
                return new MakeOrderCommand();
            case "deoccupyRoom":
                return new DeoccupyRoomCommand();
            case "signOut":
                return new LogOutCommand();
            case "startPage":
                return new StartPageCommand();
            case "signUp":
                return new SignUpCommand();
            case "startLogin":
                return new StartLoginCommand();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
