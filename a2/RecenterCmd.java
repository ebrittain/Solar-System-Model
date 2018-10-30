package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RecenterCmd extends AbstractAction {

    Camera c;

    RecenterCmd(Camera c){this.c = c;}
    @Override
    public void actionPerformed(ActionEvent e) {
        c.recenter();
    }
}
