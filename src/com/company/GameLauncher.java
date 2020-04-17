package com.company;

import java.awt.*;

public class GameLauncher {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });
    }

}
