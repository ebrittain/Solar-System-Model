package a2;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import graphicslib3D.*;

import java.nio.*;

import static com.jogamp.opengl.GL4.*;

import com.jogamp.opengl.*;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GLContext;


//class that handles all gl calls
public class View extends GLCanvas implements GLEventListener {



    private int rendering_program;
    private int vao[] = new int[1];
    private int vbo[] = new int[87];
    private GLSLUtils util = new GLSLUtils();

    private boolean axisFlag;

    private Planet sun;
    private Planet mercury;
    private Planet venus;
    private Planet earth;
    private Planet mars;
    private Planet jupiter;
    private Planet moon;


    private Axis axis;

    private Camera c;

    private MatrixStack mvStack = new MatrixStack(20);

    public View(Camera c) {
        addGLEventListener(this);
        FPSAnimator animator = new FPSAnimator(this, 60);
        animator.start();
        this.c = c;
    }


    private void addPlanet(Planet p) {

        GL4 gl = (GL4) GLContext.getCurrentGL();
        int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
        double amt = (double) (System.currentTimeMillis()) / 1000.0;


        mvStack.pushMatrix();
        mvStack.translate(Math.sin(amt * p.getSpeed()) * p.getDistFromOrigin(), 0.0f, Math.cos(amt * p.getSpeed()) * p.getDistFromOrigin());
        mvStack.pushMatrix();
        mvStack.rotate((System.currentTimeMillis()) / 15.0, 0.0, 1.0, 0.0);
        mvStack.pushMatrix();
        mvStack.scale(p.getRadius(), p.getRadius(), p.getRadius());
        gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
        gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(1);

        gl.glActiveTexture(GL_TEXTURE0);
        gl.glBindTexture(GL_TEXTURE_2D, p.getTexNumber());




        gl.glDrawArrays(GL_TRIANGLES, 0, p.getNumVerts());

        mvStack.popMatrix();
        mvStack.popMatrix();
        mvStack.popMatrix();
    }

    private void addPlanetWithMoon(Planet p, Planet m) {

        GL4 gl = (GL4) GLContext.getCurrentGL();
        int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
        double amt = (double) (System.currentTimeMillis()) / 1000.0;


        mvStack.pushMatrix();
        mvStack.translate(Math.sin(amt * p.getSpeed()) * p.getDistFromOrigin(), 0.0f, Math.cos(amt * p.getSpeed()) * p.getDistFromOrigin());
        mvStack.pushMatrix();
        mvStack.rotate((System.currentTimeMillis()) / 15.0, 0.0, 1.0, 0.0);
        mvStack.pushMatrix();
        mvStack.scale(p.getRadius(), p.getRadius(), p.getRadius());
        gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
        gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(1);

        gl.glActiveTexture(GL_TEXTURE0);
        gl.glBindTexture(GL_TEXTURE_2D, p.getTexNumber());
        gl.glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_NEAREST);
        gl.glGenerateMipmap(GL_TEXTURE_2D);




        gl.glDrawArrays(GL_TRIANGLES, 0, p.getNumVerts());

        mvStack.popMatrix();
        mvStack.popMatrix();

        mvStack.pushMatrix();
        mvStack.translate(Math.sin(amt * m.getSpeed()) * (m.getDistFromOrigin()+ earth.getRadius()), 0.0f, Math.cos(amt * m.getSpeed()) * (m.getDistFromOrigin()+ earth.getRadius()));
        mvStack.pushMatrix();
        mvStack.rotate((System.currentTimeMillis()) / 15.0, 0.0, 1.0, 0.0);
        mvStack.pushMatrix();
        mvStack.scale(m.getRadius(), m.getRadius(), m.getRadius());
        gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
        gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(1);

        gl.glActiveTexture(GL_TEXTURE0);
        gl.glBindTexture(GL_TEXTURE_2D, m.getTexNumber());


        gl.glDrawArrays(GL_TRIANGLES, 0, p.getNumVerts());

        mvStack.popMatrix();
        mvStack.popMatrix();
        mvStack.popMatrix();
        mvStack.popMatrix();


    }

    private void showAxis() {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
        mvStack.pushMatrix();
        mvStack.scale(10000, 10000, 10000);

        gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[3]);
        gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[4]);
        gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        gl.glEnableVertexAttribArray(1);

        gl.glActiveTexture(GL_TEXTURE0);
        gl.glBindTexture(GL_TEXTURE_2D, axis.getTexNumber());

        gl.glDrawArrays(GL_LINES, 0, axis.getNumVerts());


        mvStack.popMatrix();
    }


    public void display(GLAutoDrawable drawable) {

        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glClear(GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL_DEPTH_TEST);
        float bkg[] = {0.0f, 0.0f, 0.0f, 1.0f};
        FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
        gl.glClearBufferfv(GL_COLOR, 0, bkgBuffer);

        gl.glUseProgram(rendering_program);

        int proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");

        float aspect = (float) this.getWidth() / (float) this.getHeight();
        Matrix3D pMat = perspective(60.0f, aspect, 0.1f, 1000.0f);
        gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);


        mvStack.pushMatrix();
        mvStack.loadMatrix(c.computeView());


        if (axisFlag) showAxis();
        addPlanetWithMoon(sun,mercury);
        addPlanet(venus);
        addPlanet(earth);

        addPlanetWithMoon(earth, moon);
        addPlanetWithMoon(mars, moon);
        addPlanet(jupiter);

        mvStack.popMatrix();

    }

    public void init(GLAutoDrawable drawable) {

        GL4 gl = (GL4) GLContext.getCurrentGL();
        rendering_program = createShaderProgram();

        sun = new Planet(0, 0.7, 1.5, "src/textures/sun.jpg");
        mercury = new Planet(4, 0.6, 0.2, "src/textures/mercury.jpg");
        venus = new Planet(6, 0.5, 0.5, "src/textures/venus.jpg");
        earth = new Planet(8, 0.4, 0.6, "src/textures/earth.jpg");
        mars = new Planet(10, 0.3, 0.7, "src/textures/mars.jpg");
        jupiter = new Planet(12, 0.2, 0.9, "src/textures/jupiter.jpg");
        moon = new Planet(0.2, 3, 0.2, "src/textures/moon.jpg");

        axis = new Axis("src/textures/rgb.png");



        setupVertices();

    }

    private void setupVertices() {

        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);
        gl.glGenBuffers(10, vbo, 0);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
        gl.glBufferData(GL_ARRAY_BUFFER, sun.getVertBuf().limit() * 4, sun.getVertBuf(), GL_STATIC_DRAW);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
        gl.glBufferData(GL_ARRAY_BUFFER, sun.getTexBuf().limit() * 4, sun.getTexBuf(), GL_STATIC_DRAW);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[2]);
        gl.glBufferData(GL_ARRAY_BUFFER, sun.getNorBuf().limit() * 4, sun.getNorBuf(), GL_STATIC_DRAW);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[3]);
        gl.glBufferData(GL_ARRAY_BUFFER, axis.getVertBuf().limit() * 4, axis.getVertBuf(), GL_STATIC_DRAW);

        gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[4]);
        gl.glBufferData(GL_ARRAY_BUFFER, axis.getTexBuf().limit() * 4, axis.getTexBuf(), GL_STATIC_DRAW);

    }

    private Matrix3D perspective(float fovy, float aspect, float n, float f) {
        float q = 1.0f / ((float) Math.tan(Math.toRadians(0.5f * fovy)));
        float A = q / aspect;
        float B = (n + f) / (n - f);
        float C = (2.0f * n * f) / (n - f);
        Matrix3D r = new Matrix3D();
        r.setElementAt(0, 0, A);
        r.setElementAt(1, 1, q);
        r.setElementAt(2, 2, B);
        r.setElementAt(3, 2, -1.0f);
        r.setElementAt(2, 3, C);
        return r;
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void dispose(GLAutoDrawable drawable) {
        getAnimator().stop();
    }

    private int createShaderProgram() {
        GL4 gl = (GL4) GLContext.getCurrentGL();

        String vshaderSource[] = util.readShaderSource("src/shaders/vert.shader");
        String fshaderSource[] = util.readShaderSource("src/shaders/frag.shader");

        int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
        int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

        gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
        gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);

        gl.glCompileShader(vShader);
        gl.glCompileShader(fShader);

        int vfprogram = gl.glCreateProgram();
        gl.glAttachShader(vfprogram, vShader);
        gl.glAttachShader(vfprogram, fShader);
        gl.glLinkProgram(vfprogram);
        return vfprogram;
    }


    public void setAxisFlag() {
        axisFlag = !axisFlag;
    }

}
