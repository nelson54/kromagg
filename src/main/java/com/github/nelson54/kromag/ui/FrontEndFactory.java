package com.github.nelson54.kromag.ui;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class FrontEndFactory {

    WindowFactory windowFactory;
    AudioTagFormFactory audioTagFormFactory;
    TableModel tableModel;



    @Inject
    public FrontEndFactory(WindowFactory windowFactory, AudioTagFormFactory audioTagFormFactory){
        this.windowFactory = windowFactory;
        this.audioTagFormFactory = audioTagFormFactory;
    }

    public BorderLayout getLayout(){
        return new BorderLayout();
    }

    public void setTableModel(TableModel tableModel){
        this.tableModel = tableModel;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public JFrame getFrontEnd(){
        JFrame window = windowFactory.getWindow();
        Container pane = window.getContentPane();

        Container form = audioTagFormFactory.getAudioTagForm();
        form.setPreferredSize(new Dimension(300, 200));

        Container table = getTable();
        table.setPreferredSize(new Dimension(400, 200));

        pane.setLayout(getLayout());
        pane.add(form, BorderLayout.LINE_START);
        pane.add(table, BorderLayout.CENTER);


        return window;
    }

    JTable getTable(){
        JTable table = new JTable();

        table.setModel(getTableModel());

        return table;
    }
}
