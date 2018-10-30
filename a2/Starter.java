package a2;

import commands.*;
import graphicslib3D.Point3D;

import javax.swing.*;


//main entry point, sets up all key bindings
public class Starter extends JFrame {

    private Camera c;
    private View v;

    public Starter() {
        c = new Camera(new Point3D(0,0,10));

        v = new View(c);
        setTitle("Assignment 2");
        setSize(1920, 1200);
        getContentPane().add(v);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        createKeyBinding("Q","moveUp", new MoveUpCmd(c));
        createKeyBinding("E","moveDown", new MoveDownCmd(c));
        createKeyBinding("A","moveLeft", new MoveLeftCmd(c));
        createKeyBinding("D","moveRight", new MoveRightCmd(c));
        createKeyBinding("W","moveForward", new MoveForwardCmd(c));
        createKeyBinding("S","moveBackward", new MoveBackwardCmd(c));
        createKeyBinding("LEFT","panLeft", new PanLeftCmd(c));
        createKeyBinding("RIGHT","panRight", new PanRightCmd(c));
        createKeyBinding("UP","pitchUp", new PitchUpCmd(c));
        createKeyBinding("DOWN","pitchDown", new PitchDownCmd(c));
        createKeyBinding("R","recenter", new RecenterCmd(c));
        createKeyBinding("SPACE","toggleAxis", new ShowAxisCmd(v));

    }

    private void createKeyBinding(String key, String keyDescription, AbstractAction cmd){
        JComponent pane = (JComponent) this.getContentPane();
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;

        pane.getInputMap(mapName).put(KeyStroke.getKeyStroke(key), keyDescription);
        pane.getActionMap().put(keyDescription, cmd);
    }


    public static void main(String[] args) {
        new Starter();
    }
}
