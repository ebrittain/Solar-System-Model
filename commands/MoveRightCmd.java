package commands;

import a2.Camera;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveRightCmd extends AbstractAction {

    private Camera c;

    public MoveRightCmd(Camera c){this.c = c;}
    @Override
    public void actionPerformed(ActionEvent e) {
        c.moveRight();
    }
}
