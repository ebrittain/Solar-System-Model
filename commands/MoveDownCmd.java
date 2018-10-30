package commands;

import a2.Camera;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveDownCmd extends AbstractAction {

    private Camera c;

    public MoveDownCmd(Camera c){this.c = c;}
    @Override
    public void actionPerformed(ActionEvent e) {
        c.moveDown();
    }
}