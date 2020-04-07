package SubmarineSimulator;


import com.jogamp.opengl.GL2;

public class HeliceSM {
    private double x;
    private double y;
    private double z;

    public HeliceSM(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL2 gl) {

        /** HELICE 1 **/
        double xH1 = 0.15;
        double yH1 = 0.4;
        double zH1 = 0.1;

        double xH = this.x;


        /** Face devant **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH1, this.y + yH1, this.z - zH1); //V1
        gl.glVertex3d(xH - xH1,this.y - yH1, this.z - zH1); //V2
        gl.glVertex3d(xH + xH1,this.y - yH1,this.z - zH1); //V3
        gl.glVertex3d(xH + xH1, this.y + yH1,this.z - zH1); //V4
        gl.glEnd();

        /** Face à droite **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH + xH1, this.y + yH1,this.z - zH1); //V4
        gl.glVertex3d(xH + xH1,this.y - yH1,this.z - zH1); //V3
        gl.glVertex3d(xH + xH1, this.y - yH1, this.z + zH1); //V5
        gl.glVertex3d(xH + xH1,this.y + yH1, this.z + zH1); //V6
        gl.glEnd();

        /** Face derrière **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH + xH1,this.y + yH1, this.z + zH1); //V6
        gl.glVertex3d(xH + xH1, this.y - yH1, this.z + zH1); //V5
        gl.glVertex3d(xH - xH1,this.y - yH1,this.z + zH1); //V7
        gl.glVertex3d(xH - xH1, this.y + yH1,this.z + zH1); //V8
        gl.glEnd();

        /** Face à gauche **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH1, this.y + yH1,this.z + zH1); //V8
        gl.glVertex3d(xH - xH1,this.y - yH1,this.z + zH1); //V7
        gl.glVertex3d(xH - xH1,this.y - yH1, this.z - zH1); //V2
        gl.glVertex3d(xH - xH1, this.y + yH1, this.z - zH1); //V1
        gl.glEnd();

        /** Face au dessus **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH1, this.y + yH1,this.z + zH1); //V8
        gl.glVertex3d(xH - xH1, this.y + yH1, this.z - zH1); //V1
        gl.glVertex3d(xH + xH1, this.y + yH1,this.z - zH1); //V4
        gl.glVertex3d(xH + xH1,this.y + yH1, this.z + zH1); //V6
        gl.glEnd();

        /** Face au dessus **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH1,this.y - yH1, this.z - zH1); //V2
        gl.glVertex3d(xH - xH1,this.y - yH1,this.z + zH1); //V7
        gl.glVertex3d(xH + xH1, this.y - yH1, this.z + zH1); //V5
        gl.glVertex3d(xH + xH1,this.y - yH1,this.z - zH1); //V3
        gl.glEnd();



        /** HELICE 1 **/
        double xH2 = 0.1;
        double yH2 = 0.1;
        double zH2 = 0.4;


        /** Face devant **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH2, this.y + yH2, this.z - zH2); //V1
        gl.glVertex3d(xH - xH2,this.y - yH2, this.z - zH2); //V2
        gl.glVertex3d(xH + xH2,this.y - yH2,this.z - zH2); //V3
        gl.glVertex3d(xH + xH2, this.y + yH2,this.z - zH2); //V4
        gl.glEnd();

        /** Face à droite **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH + xH2, this.y + yH2,this.z - zH2); //V4
        gl.glVertex3d(xH + xH2,this.y - yH2,this.z - zH2); //V3
        gl.glVertex3d(xH + xH2, this.y - yH2, this.z + zH2); //V5
        gl.glVertex3d(xH + xH2,this.y + yH2, this.z + zH2); //V6
        gl.glEnd();

        /** Face derrière **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH + xH2,this.y + yH2, this.z + zH2); //V6
        gl.glVertex3d(xH + xH2, this.y - yH2, this.z + zH2); //V5
        gl.glVertex3d(xH - xH2,this.y - yH2,this.z + zH2); //V7
        gl.glVertex3d(xH - xH2, this.y + yH2,this.z + zH2); //V8
        gl.glEnd();

        /** Face à gauche **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH2, this.y + yH2,this.z + zH2); //V8
        gl.glVertex3d(xH - xH2,this.y - yH2,this.z + zH2); //V7
        gl.glVertex3d(xH - xH2,this.y - yH2, this.z - zH2); //V2
        gl.glVertex3d(xH - xH2, this.y + yH2, this.z - zH2); //V1
        gl.glEnd();

        /** Face au dessus **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH2, this.y + yH2,this.z + zH2); //V8
        gl.glVertex3d(xH - xH2, this.y + yH2, this.z - zH2); //V1
        gl.glVertex3d(xH + xH2, this.y + yH2,this.z - zH2); //V4
        gl.glVertex3d(xH + xH2,this.y + yH2, this.z + zH2); //V6
        gl.glEnd();

        /** Face au dessus **/
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3d(0,0,1);
        gl.glVertex3d(xH - xH2,this.y - yH2, this.z - zH2); //V2
        gl.glVertex3d(xH - xH2,this.y - yH2,this.z + zH2); //V7
        gl.glVertex3d(xH + xH2, this.y - yH2, this.z + zH2); //V5
        gl.glVertex3d(xH + xH2,this.y - yH2,this.z - zH2); //V3
        gl.glEnd();

    }
}
