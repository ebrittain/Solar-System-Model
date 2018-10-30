package a2;

import graphicslib3D.Matrix3D;
import graphicslib3D.Point3D;
import graphicslib3D.Vector3D;


//camera class handles all movements of the camera as well as computing view matrix
public class Camera {
    private Point3D camLocation;
    private Vector3D u, v, n;
    private int degree = 0;
    private Point3D target;


    public Camera(Point3D camLocation) {
        this.camLocation = camLocation;
        target = new Point3D();

        recenter();

    }



    public Point3D getCamLocation() {
        return camLocation;
    }

    public void setCamLocation(Point3D p) {
        camLocation = p;
    }

    public void moveLeft() {
        camLocation = camLocation.add(new Point3D(u.normalize().mult(-0.25)));
    }

    public void moveRight() { camLocation = camLocation.add(new Point3D(u.normalize().mult(0.25))); }

    public void moveUp() { camLocation = camLocation.add(new Point3D(v.normalize().mult(0.25))); }

    public void moveDown() {
        camLocation = camLocation.add(new Point3D(v.normalize().mult(-0.25)));
    }

    public void moveForward() {
        camLocation = camLocation.add(new Point3D(n.normalize().mult(-0.25)));
    }

    public void moveBackward() { camLocation = camLocation.add(new Point3D(n.normalize().mult(0.25))); }


    public void setTarget(Point3D target){this.target = target;}



    public void recenter(){
        Vector3D y = new Vector3D(0,1,0);
            n = (new Vector3D(camLocation.minus(target))).normalize();
            u = (n.mult(-1).cross(y)).normalize();
            v = (u.cross(n.mult(-1))).normalize();

    }
    public void pitchUp() {
        Matrix3D vRot = new Matrix3D();
        vRot.rotate(1, u);
        v = v.mult(vRot);
        n = n.mult(vRot);

    }

    public void pitchDown() {
        Matrix3D vRot = new Matrix3D();
        vRot.rotate(-1, u);
        v = v.mult(vRot);
        n = n.mult(vRot);

    }

    public void panRight() {
        Matrix3D vRot = new Matrix3D();
        vRot.rotate(-1, v);
        u = u.mult(vRot);
        n = n.mult(vRot);

    }

    public void panLeft() {
        Matrix3D vRot = new Matrix3D();
        vRot.rotate(1, v);
        u = u.mult(vRot);
        n = n.mult(vRot);

    }


    public Matrix3D computeView() {

        Matrix3D m = new Matrix3D();
        Vector3D cam = new Vector3D(camLocation).mult(-1);
        m.translate(cam.getX(),cam.getY(),cam.getZ());
        Matrix3D view = new Matrix3D();
        view.setRow(0, u);
        view.setRow(1, v);
        view.setRow(2, n);
        view.concatenate(m);

        return view;

    }


}
