package SubmarineSimulator;

import com.jogamp.opengl.GL2;

public class FirstPerson {

    public void draw(GL2 gl) {
        Rectangle rectangle = new Rectangle(0,5.1,0,11, 0.5, 1);
		rectangle.draw(gl);

		Rectangle rectangle2 = new Rectangle(0,-4.1,0,11, 3, 1);
		rectangle2.draw(gl);

		Rectangle rectangle3 = new Rectangle(-9.5,0,0,1, 6, 1);
		rectangle3.draw(gl);

		Rectangle rectangle4 = new Rectangle(9.5,0,0,1, 6, 1);
		rectangle4.draw(gl);
    }
}
