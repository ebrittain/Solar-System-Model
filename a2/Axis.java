package a2;

public class Axis extends SolarSystemObject {


    Axis(String texPath) {
        this.setTexture(texPath);
        setupVerts();
    }

    public void setupVerts() {
        float[] pVal = {
                -1.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f, 1.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,
                0.0f, -1.0f, 0.0f,0.0f,0.0f,0.0f, 0.0f, 1.0f, 0.0f,0.0f,0.0f,0.0f,
                0.0f, 0.0f, -1.0f,0.0f,0.0f,0.0f, 0.0f, 0.0f, 1.0f,0.0f,0.0f,0.0f
        };
        float[] tVal = {
                0.0f, 0.0f, 0.0f,0,0f,0.0f, 0.0f,0.0f,0f,
                0.5f, 0.5f, 0.5f, 0.5f,0.5f, 0.5f,0.5f,0.5f,
                1.0f, 1.0f, 1.0f, 1.0f,1.0f, 1f,1f,1f
        };
        float[] nVal = {
                0.0f
        };
        this.setBuffers(pVal, tVal, nVal);
        this.setNumVerts(12);
    }
}
