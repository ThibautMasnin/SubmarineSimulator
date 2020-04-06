package SubmarineSimulator;

import com.jogamp.opengl.*;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.newt.event.awt.AWTMouseAdapter;
import com.jogamp.opengl.util.gl2.GLUT;

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
	private float translation = 0f;
	private float deplacement = 0.1f;
	private float positionVerticale = 0.0f;
	private float orientation = 0.0f;
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


		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0);
		gl.glEnable(GL2.GL_CULL_FACE);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
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


	/**
	 * Called when the drawable has been resized
	 */
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
		Repere repere = new Repere(3,3,3);
		repere.draw(gl);

		HeliceSM helice = new HeliceSM(0,0,0);
		helice.draw(gl);

//---------------------------------------------------------------------------------------------
		gl.glPushMatrix();
		gl.glRotatef(orientation,0f,1f,0f);
		gl.glTranslatef(translation, positionVerticale, 0.0f);
		glut.glutSolidTeapot(1);
		gl.glPopMatrix();
//-------------------------------------------------------------------------------------------

	}

	public void droite()
	{
		orientation -= deplacement*5;
	}

	public void gauche()
	{
		orientation += deplacement*5;
	}

	public void monter()
	{
		positionVerticale += deplacement;
	}

	public void descendre()
	{
		positionVerticale -= deplacement;
	}

	public void avancer()
	{
		translation += deplacement;
	}

	public void reculer()
	{
		translation -= deplacement;
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
}
