package a2;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.nio.FloatBuffer;

//top level class for all objects in solar system
public abstract class SolarSystemObject {


    private FloatBuffer vertBuf;
    private FloatBuffer texBuf;
    private FloatBuffer norBuf;

    private int numVerts;
    private Texture tex;
    private int texNumber;


    public abstract void setupVerts();


    public Texture getTex() {
        return tex;
    }

    public int getTexNumber() {
        return texNumber;
    }

    public int getNumVerts() {
        return numVerts;
    }

    public FloatBuffer getVertBuf() {
        return vertBuf;
    }

    public FloatBuffer getTexBuf() {
        return texBuf;
    }

    public FloatBuffer getNorBuf() {
        return norBuf;
    }

    public void setTexNumber(int texNumber){this.texNumber = texNumber;}


    public void setTexture(String path) {
        try {
            tex = TextureIO.newTexture(new File(path), false);
            texNumber = tex.getTextureObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNumVerts(int numVerts) {
        this.numVerts = numVerts;
    }

    public void setBuffers(float[] pval, float[] tval, float[] nval) {
        vertBuf = Buffers.newDirectFloatBuffer(pval);
        texBuf = Buffers.newDirectFloatBuffer(tval);
        norBuf = Buffers.newDirectFloatBuffer(nval);
    }

}
