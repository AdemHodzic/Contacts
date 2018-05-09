package com.adem.Commander;

import com.adem.Controller.Controller;

public class LogoutCommand implements Command {

    private final int LOGIN_POSITION = 0;
    private Controller controller;

    @Override
    public void execute() {

        controller = new Controller();
        controller.start(LOGIN_POSITION);

    }
}
