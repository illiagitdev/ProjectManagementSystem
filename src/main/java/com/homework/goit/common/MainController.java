package com.homework.goit.common;

public class MainController {
    private View view;

    public MainController(View view) {
        this.view = view;
    }

    public void process(){
        view.write("Welcome to Project Management System!\n");
        while (true){
            String command = view.read();
            switch (command){
                case "developers":{
                    view.write("Developers team!");
                    break;
                }
                case "projects":{
                    view.write("Current projects!");
                    break;
                }
                case "companies":{
                    view.write("Companies!");
                    break;
                }
                case "customers":{
                    view.write("Current customers!");
                    break;
                }
                default:{
                    view.write("No command found!");
                    break;
                }
            }
        }
    }
}
