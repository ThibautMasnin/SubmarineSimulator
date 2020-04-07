package SubmarineSimulator;

import com.jogamp.opengl.*;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.newt.event.awt.AWTMouseAdapter;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.Random;

//Applications implement the GLEventListener interface to perform OpenGL drawing via callbacks.
public class MyGLEventListener implements GLEventListener
{
	private float view_rotx = 0.0f, view_roty = 0.0f;
	private float scale = 1.0f;
	private boolean zoomModified = false;
	private float aspect;
	private GLUT glut;
	SceneMouseAdapter objectMouse;
	SceneKeyAdapter objectKeys;

	//////////////////////////////////////////////////////////////////////////////////////////////:
	// TO FILL
	private float vitesse = 0.1f;

	private float rotH;
	private float pivH;

	private float x = 0f;
	private float y = 0.0f;
	private float z = 0.0f;
	private float o = 0.0f;

	private boolean tire = false;
	private float p;
	private float op;
	private float q;
	private float oq;
	private float tempx;
	private float tempy;
	private float tempz;

	private boolean mouvementAuto = false;
	private int nbDeplacement=0;
	private int choixDeplacement;
	private boolean vueConducteur = false;
	//...

	/**
	 * The init() method is called when a new OpenGL context is created for the given GLAutoDrawable.
	 * Any display lists or textures used during the application's normal rendering loop can be safely
	 * initialized in init(). The GLEventListener's init() method may be called more than once during
	 * the lifetime of the application. The init() method should therefore be kept as short as possible
	 * and only contain the OpenGL initialization required for the display() method to run properly.
	 */
	public void init(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };
		float red[] = { 0.8f, 0.1f, 0.0f, 0.7f };


		gl.glClearColor(0.4f, 0.6f, 0.9f, 1f);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0);
//		gl.glEnable(GL2.GL_CULL_FACE);
//		gl.glEnable(GL2.GL_LIGHTING);
//		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_DEPTH_TEST);

		objectMouse = new SceneMouseAdapter(this);
		objectKeys = new SceneKeyAdapter(this);

		if (drawable instanceof Window) {
			Window window = (Window) drawable;
			window.addMouseListener(objectMouse);
			window.addKeyListener(objectKeys);
		}

		else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
			java.awt.Component comp = (java.awt.Component) drawable;
			new AWTMouseAdapter(objectMouse, drawable).addTo(comp);
			new AWTKeyAdapter(objectKeys, drawable).addTo(comp);
		}

		gl.glEnable(GL2.GL_NORMALIZE);


		/////////////////////////////////////////////////////////////////////////////////////
		//TO FILL

		glut = new GLUT();
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		//...
	}

	// Called when the drawable has been resized
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		GL2 gl = drawable.getGL().getGL2();

		aspect = (float)height / (float)width;

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustum(-scale, scale, -scale*aspect, scale*aspect, 5.0f, 60.0f);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}


	@Override
	public void dispose(GLAutoDrawable drawable) {

	}


	/**
	 * Called to perform per-frame rendering.
	 */
	public void display(GLAutoDrawable drawable) {

		// Get the GL corresponding to the drawable we are animating
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		// Rotate the entire assembly of gears based on how the user dragged the mouse around
		gl.glPushMatrix();

		updateScaleAndRotation(gl,aspect,view_rotx,view_roty);

		/////////////////////////////////////////////////////////////////////////////////
		//TO FILL


//---------------------------------------------------------------------------------------------

		if(vueConducteur == true)
		{
			FirstPerson fp = new FirstPerson();
			fp.draw(gl);
		}
		else
		{
			if(mouvementAuto) {
				deplacerAuto();
			}
			gl.glPushMatrix();
			gl.glPushMatrix();
			gl.glTranslatef(x, y, z);
			gl.glRotatef(o,0f,1f,0f);
			/** Repere **/
			Repere repere = new Repere(10,10,10);
			repere.draw(gl);
			/** Sous marin **/
			BaseSM base = new BaseSM(0,0,0);
			base.draw(gl);
			/** Helice **/
			HeliceSM helice = new HeliceSM(-(base.xB + 0.25),0,0);
			gl.glRotatef(pivH,0f,1f,0f);
			gl.glRotatef(rotH,1f,0f,0f);
			helice.draw(gl);
			gl.glPopMatrix();

			/** Torpille **/
			gl.glPushMatrix();
			if(tire) {
				p+=op*2;
				q+=oq*2;
				gl.glTranslatef(tempx+p, tempy, tempz+q);
			}
			else {
				gl.glTranslatef(x, y, z);
				gl.glRotatef(o,0f,1f,0f);
			}
			gl.glColor3d(0,0,0);
			glut.glutSolidSphere(0.2,5,5);
			gl.glPopMatrix();
		}

		/** Poissons **/
		gl.glPushMatrix();
		gl.glTranslatef(5f, 0f, 0f);
		gl.glColor3d(0.3,0.8,0.2);
		glut.glutSolidSphere(0.1,5,5);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(3f, 0f, 4f);
		gl.glColor3d(1,1,0);
		glut.glutSolidSphere(0.1,5,5);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(-3f, 0f, 2f);
		gl.glColor3d(1,0,0);
		glut.glutSolidSphere(0.1,5,5);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, -3f);
		gl.glColor3d(0,0,1);
		glut.glutSolidSphere(0.1,5,5);
		gl.glPopMatrix();
		gl.glPopMatrix();
	}

	public void deplacerAuto()
	{
		System.out.println(nbDeplacement);
		if(nbDeplacement==0) {
			choixDeplacement = new Random().nextInt(5);
			nbDeplacement = new Random().nextInt(50)+10;
		}
		if(choixDeplacement == 0) {
			avancer();
		}
		else if(choixDeplacement == 1) {
			reculer();
		}
		else if(choixDeplacement == 2) {
			monter();
		}
		else if(choixDeplacement == 3) {
			descendre();
		}
		else if(choixDeplacement == 4) {
			droite();
		}
		else if(choixDeplacement == 5) {
			gauche();
		}
		nbDeplacement--;
	}

	public void droite() {
		avancer();
		rotH += 100*vitesse;
		o -= vitesse*15;
		if(pivH<10) {
			pivH+=2;
		}
	}

	public void gauche() {
		avancer();
		rotH += 100*vitesse;
		o += vitesse*15;
		if(pivH>-10) {
			pivH-=2;
		}
	}

	public void monter() {
		y += vitesse;
		redresser();
	}

	public void descendre() {
		y -= vitesse;
		redresser();
	}

	public void avancer() {
		rotH += 100*vitesse;
		redresser();
		if((o%360)/90>=-4 && (o%360)/90<=-3) {
			x = x-vitesse*(3+(o%360)/90);
			z = z-vitesse*(4+(o%360)/90);
		}
		else if((o%360)/90>=-3 && (o%360)/90<=-2) {
			x = x-vitesse*(3+(o%360)/90);
			z = z+vitesse*(2+(o%360)/90);
		}
		else if((o%360)/90>=-2 && (o%360)/90<=-1) {
			x = x+vitesse*(1+(o%360)/90);
			z = z+vitesse*(2+(o%360)/90);
		}
		else if((o%360)/90>=-1 && (o%360)/90<=0) {
			x = x+vitesse*(1+(o%360)/90);
			z = z-vitesse*((o%360)/90);
		}
		else if((o%360)/90>=0 && (o%360)/90<=1) {
			x = x+vitesse*(1-(o%360)/90);
			z = z-vitesse*((o%360)/90);
		}
		else if((o%360)/90>=1 && (o%360)/90<=2) {
			x = x+vitesse*(1-(o%360)/90);
			z = z-vitesse*(2-(o%360)/90);
		}
		else if((o%360)/90>=2 && (o%360)/90<=3) {
			x = x-vitesse*(3-(o%360)/90);
			z = z-vitesse*(2-(o%360)/90);
		}
		else if((o%360)/90>=3 && (o%360)/90<=4) {
			x = x-vitesse*(3-(o%360)/90);
			z = z+vitesse*(4-(o%360)/90);
		}
	}

	public void reculer() {
		rotH -= 100*vitesse;
		redresser();
		if((o%360)/90>=-4 && (o%360)/90<=-3) {
			x = x+vitesse*(3+(o%360)/90);
			z = z+vitesse*(4+(o%360)/90);
		}
		else if((o%360)/90>=-3 && (o%360)/90<=-2) {
			x = x+vitesse*(3+(o%360)/90);
			z = z-vitesse*(2+(o%360)/90);
		}
		else if((o%360)/90>=-2 && (o%360)/90<=-1) {
			x = x-vitesse*(1+(o%360)/90);
			z = z-vitesse*(2+(o%360)/90);
		}
		else if((o%360)/90>=-1 && (o%360)/90<=0) {
			x = x-vitesse*(1+(o%360)/90);
			z = z+vitesse*((o%360)/90);
		}
		else if((o%360)/90>=0 && (o%360)/90<=1) {
			x = x-vitesse*(1-(o%360)/90);
			z = z+vitesse*((o%360)/90);
		}
		else if((o%360)/90>=1 && (o%360)/90<=2) {
			x = x-vitesse*(1-(o%360)/90);
			z = z+vitesse*(2-(o%360)/90);
		}
		else if((o%360)/90>=2 && (o%360)/90<=3) {
			x = x+vitesse*(3-(o%360)/90);
			z = z+vitesse*(2-(o%360)/90);
		}
		else if((o%360)/90>=3 && (o%360)/90<=4) {
			x = x+vitesse*(3-(o%360)/90);
			z = z-vitesse*(4-(o%360)/90);
		}
	}

	public void redresser() {
		if(pivH>0) {
			pivH-=2;
		}
		else if(pivH<0) {
			pivH+=2;
		}
	}

	public void tirer() {
		if(!tire) {
			tire=true;
			tempx=x;
			tempy=y;
			tempz=z;
			if((o%360)/90>=-4 && (o%360)/90<=-3) {
				op = -vitesse*(3+(o%360)/90);
				oq = -vitesse*(4+(o%360)/90);
			}
			else if((o%360)/90>=-3 && (o%360)/90<=-2) {
				op = -vitesse*(3+(o%360)/90);
				oq = vitesse*(2+(o%360)/90);
			}
			else if((o%360)/90>=-2 && (o%360)/90<=-1) {
				op = vitesse*(1+(o%360)/90);
				oq = vitesse*(2+(o%360)/90);
			}
			else if((o%360)/90>=-1 && (o%360)/90<=0) {
				op = vitesse*(1+(o%360)/90);
				oq = -vitesse*((o%360)/90);
			}
			else if((o%360)/90>=0 && (o%360)/90<=1) {
				op = vitesse*(1-(o%360)/90);
				oq = -vitesse*((o%360)/90);
			}
			else if((o%360)/90>=1 && (o%360)/90<=2) {
				op = vitesse*(1-(o%360)/90);
				oq = -vitesse*(2-(o%360)/90);
			}
			else if((o%360)/90>=2 && (o%360)/90<=3) {
				op = -vitesse*(3-(o%360)/90);
				oq = -vitesse*(2-(o%360)/90);
			}
			else if((o%360)/90>=3 && (o%360)/90<=4) {
				op = -vitesse*(3-(o%360)/90);
				oq = vitesse*(4-(o%360)/90);
			}
		}
	}

	/**
	 * Method to update the scene wrt the rotation angles and the zoom factor
	 * @param gl
	 * @param aspect
	 * @param view_rotx
	 * @param view_roty
	 */
	public void updateScaleAndRotation(GL2 gl, float aspect, float view_rotx, float view_roty) {
		if(zoomModified) {
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glFrustum(-scale, scale, -scale*aspect, scale*aspect, 5.0f, 60.0f);
			zoomModified = false;
			gl.glMatrixMode(GL2.GL_MODELVIEW);
		}
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -50.0f);
		gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
	}

	//GETTER AND SETTER
	//*************************************************************
	public float getView_rotx() {
		return view_rotx;
	}

	public void setView_rotx(float view_rotx) {
		this.view_rotx = view_rotx;
	}

	public float getView_roty() {
		return view_roty;
	}

	public void setView_roty(float view_roty) {
		this.view_roty = view_roty;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale2) {
		this.scale = scale2;
		zoomModified = true;
	}

	public void deplacementAuto() {
		mouvementAuto=!mouvementAuto;
	}

	public void isVueConducteur()
	{
		vueConducteur =! vueConducteur;
	}
}
