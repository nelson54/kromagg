package com.github.nelson54.kromag.ui;

import javax.swing.*;
import java.awt.*;

public class AudioTagFormFactory {

    LayoutManager getLayout(){
        return new SpringLayout();
    }

    JPanel getBase(){
        return new JPanel();
    }

    Container getAudioTagForm(){
        Container form = getBase();

        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));

        Container trackField = getFieldContainer("Track: ");
        Container titleField = getFieldContainer("Title: ");
        Container artistField = getFieldContainer("Artist: ");

        form.add(trackField);
        form.add(Box.createVerticalGlue());
        form.add(titleField);
        form.add(Box.createVerticalGlue());
        form.add(artistField);
        form.add(Box.createVerticalGlue());
        return form;
    }

    private Container getFieldContainer(String label) {
        Container field = getBase();

        SpringLayout layout = new SpringLayout();
        field.setLayout(layout);

        JLabel trackLabel = new JLabel(label);
        JTextField trackField = new JTextField("Text field");

        layout.putConstraint(SpringLayout.NORTH, trackField,
                10,
                SpringLayout.SOUTH, trackLabel);

        field.add(trackLabel);
        field.add(trackField);

        return field;
    }

    Container getEmptyAudioTagForm(){
        Container emptySelection = getBase();

        SpringLayout layout = new SpringLayout();

        emptySelection.setLayout(layout);

        JLabel label = new JLabel("No selection");

        emptySelection.add(label);

        return emptySelection;
    }

    void addComponent(Container base, Component component, SpringLayout layout){

        base.add(component);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, component,
                5,
                SpringLayout.HORIZONTAL_CENTER, base);
    }

}
