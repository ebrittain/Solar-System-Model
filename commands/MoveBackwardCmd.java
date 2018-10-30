package commands;

import a2.Camera;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveBackwardCmd extends AbstractAction {

    private Camera c;

    public MoveBackwardCmd(Camera c){this.c = c;}
    @Override
    public void actionPerformed(ActionEvent e) {
        c.moveBackward();
    }
}
