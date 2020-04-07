package SubmarineSimulator;

import com.jogamp.opengl.GL2;

public class BaseSM {
    private double x;
    private double y;
    private double z;
    double xB;
    double yB;
    double zB;

    public BaseSM(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL2 gl) {
        xB = 3;
        yB = 0.5;
        zB = 1;

        /** Face à droite **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,0);
        gl.glVertex3d(this.x - xB, this.y + yB, this.z - zB); //V1
        gl.glVertex3d(this.x - xB,this.y - yB, this.z - zB); //V2
        gl.glVertex3d(this.x + xB,this.y - yB,this.z - zB); //V3
        gl.glVertex3d(this.x + xB, this.y + yB,this.z - zB); //V4
        gl.glEnd();

        /** Face devant **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(1,1,1);
        gl.glVertex3d(this.x + xB, this.y + yB,this.z - zB); //V4
        gl.glVertex3d(this.x + xB,this.y - yB,this.z - zB); //V3
        gl.glVertex3d(this.x + xB, this.y - yB, this.z + zB); //V5
        gl.glVertex3d(this.x + xB,this.y + yB, this.z + zB); //V6
        gl.glEnd();

        /** Face à gauche **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,0);
        gl.glVertex3d(this.x + xB,this.y + yB, this.z + zB); //V6
        gl.glVertex3d(this.x + xB, this.y - yB, this.z + zB); //V5
        gl.glVertex3d(this.x - xB,this.y - yB,this.z + zB); //V7
        gl.glVertex3d(this.x - xB, this.y + yB,this.z + zB); //V8
        gl.glEnd();


        /** Face derrière **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,0);
        gl.glVertex3d(this.x - xB, this.y + yB,this.z + zB); //V8
        gl.glVertex3d(this.x - xB,this.y - yB,this.z + zB); //V7
        gl.glVertex3d(this.x - xB,this.y - yB, this.z - zB); //V2
        gl.glVertex3d(this.x - xB, this.y + yB, this.z - zB); //V1
        gl.glEnd();

        /** Face au dessus **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,0);
        gl.glVertex3d(this.x - xB, this.y + yB,this.z + zB); //V8
        gl.glVertex3d(this.x - xB, this.y + yB, this.z - zB); //V1
        gl.glVertex3d(this.x + xB, this.y + yB,this.z - zB); //V4
        gl.glVertex3d(this.x + xB,this.y + yB, this.z + zB); //V6
        gl.glEnd();

        /** Face en dessous **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,0);
        gl.glVertex3d(this.x - xB,this.y - yB, this.z - zB); //V2
        gl.glVertex3d(this.x - xB,this.y - yB,this.z + zB); //V7
        gl.glVertex3d(this.x + xB, this.y - yB, this.z + zB); //V5
        gl.glVertex3d(this.x + xB,this.y - yB,this.z - zB); //V3
        gl.glEnd();
    }

    public double getxB() {
        return xB;
    }
}
