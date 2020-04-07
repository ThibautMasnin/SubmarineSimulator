package SubmarineSimulator;

import com.jogamp.opengl.GL2;

public class Repere {
    private double x, y, z;

    public Repere(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_LINES);

        // rouge : axe X
        gl.glColor3d(1,0,0);
        gl.glVertex3d(0,0,0);
        gl.glVertex3d(this.x,0,0);

        // vert : axe Y
        gl.glColor3d(0,1,0);
        gl.glVertex3d(0,0,0);
        gl.glVertex3d(0,this.y = y,0);

        // bleu : axe Z
        gl.glColor3d(0,0,1);
        gl.glVertex3d(0,0,0);
        gl.glVertex3d(0,0,this.z = z);

        gl.glEnd();
    }
}
