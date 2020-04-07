package SubmarineSimulator;

import com.jogamp.opengl.GL2;

public class Rectangle {
    private double x, y, z, xB, yB, zB;

    public Rectangle(double x, double y, double z, double xb, double yb, double zb) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xB = xb;
        this.yB = yb;
        this.zB = zb;
    }

    public void draw(GL2 gl) {
        /** Face devant **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(1, 1, 1);
        gl.glVertex3d(this.x - xB, this.y + yB, this.z - zB); //V1
        gl.glVertex3d(this.x - xB, this.y - yB, this.z - zB); //V2
        gl.glVertex3d(this.x + xB, this.y - yB, this.z - zB); //V3
        gl.glVertex3d(this.x + xB, this.y + yB, this.z - zB); //V4
        gl.glEnd();
    }
}