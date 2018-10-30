package a2;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowAxisCmd extends AbstractAction {

    View v;

    ShowAxisCmd(View v){this.v = v;}
    @Override
    public void actionPerformed(ActionEvent e) {
        v.setAxisFlag();
    }
}
