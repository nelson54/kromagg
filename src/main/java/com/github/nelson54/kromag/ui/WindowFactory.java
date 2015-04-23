package com.github.nelson54.kromag.ui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowFactory {

    JFrame getWindow(){
        JFrame window = new JFrame();

        addCloseListener(window);
        return window;
    }

    void addCloseListener(JFrame window){
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

}
