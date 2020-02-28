package com.homework.goit;

import com.homework.goit.common.Console;
import com.homework.goit.common.MainController;
import com.homework.goit.common.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        MainController controller = new MainController(view);
        controller.process();
    }
}
