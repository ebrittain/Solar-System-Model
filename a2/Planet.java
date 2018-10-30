package a2;


import graphicslib3D.Point3D;
import graphicslib3D.Vertex3D;
import graphicslib3D.shape.Sphere;

public class Planet extends SolarSystemObject {


    private Sphere sphere;
    private double radius;
    private double speed;
    private double distFromOrigin;


    Planet(double distFromOrigin, double speed, double radius, String texPath) {
        sphere = new Sphere(24);
        this.setTexture(texPath);
        this.distFromOrigin = distFromOrigin;
        this.radius = radius;
        this.speed = speed;
        setupVerts();

    }

    public double getRadius() {
        return radius;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistFromOrigin() {
        return distFromOrigin;
    }


    public void setupVerts() {
        Vertex3D[] vertices = sphere.getVertices();
        int[] indices = sphere.getIndices();

        float[] pvalues = new float[indices.length * 3];
        float[] tvalues = new float[indices.length * 2];
        float[] nvalues = new float[indices.length * 3];

        for (int i = 0; i < indices.length; i++) {
            pvalues[i * 3] = (float) (vertices[indices[i]]).getX();
            pvalues[i * 3 + 1] = (float) (vertices[indices[i]]).getY();
            pvalues[i * 3 + 2] = (float) (vertices[indices[i]]).getZ();
            tvalues[i * 2] = (float) (vertices[indices[i]]).getS();
            tvalues[i * 2 + 1] = (float) (vertices[indices[i]]).getT();
            nvalues[i * 3] = (float) (vertices[indices[i]]).getNormalX();
            nvalues[i * 3 + 1] = (float) (vertices[indices[i]]).getNormalY();
            nvalues[i * 3 + 2] = (float) (vertices[indices[i]]).getNormalZ();
        }

        this.setBuffers(pvalues, tvalues, nvalues);
        this.setNumVerts(indices.length);
    }

}
