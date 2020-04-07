package SubmarineSimulator;

import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;

class SceneKeyAdapter extends KeyAdapter
{

    private float view_rotx, view_roty;
    private float zoom = 1;

    private MyGLEventListener myGLEventListener;

    public SceneKeyAdapter (MyGLEventListener _myGLEventListener) {
        myGLEventListener = _myGLEventListener;
        view_rotx = _myGLEventListener.getView_rotx();
        view_roty = _myGLEventListener.getView_roty();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        view_rotx = myGLEventListener.getView_rotx();
        view_roty = myGLEventListener.getView_roty();

//        Ctrl = 17 -> descendre
//        Maj = 15 -> monter
//        Z = 90 -> avancer
//        S = 83 -> reculer
//        Q = 81 -> gauche
//        D = 68 -> droite

        if(kc == 81) {
            //Q est tapé, on tourne à gauche
            myGLEventListener.gauche();
        }
        else if(kc == 68) {
            //D est tapé, on tourne à droite
            myGLEventListener.droite();
        }
        else if(kc == 15) {
            //Maj est tapé, on monte
            myGLEventListener.monter();
        }
        else if(kc == 17) {
            //Ctrl est tapé, on descend
            myGLEventListener.descendre();
        }
        else if(kc == 83) {
            //S est tapé, on recule
            myGLEventListener.reculer();
        }
        else if(kc == 90) {
            //Z est tapé, on avance
            myGLEventListener.avancer();
        }
        else if(kc == 32) {
            //Espace est tapé, on tire
            myGLEventListener.tirer();
        }
        else if(kc == 140) {
            zoom += 0.1;
            //System.out.println("Key pressed: zoom in="+zoom);
        }
        else if(kc == 139) {
            zoom -= 0.1;
            //System.out.println("Key pressed: zoom out");
        }
        else if(kc == KeyEvent.VK_LEFT) {
            view_roty -= 1;
            //System.out.println("Key pressed: view_roty="+view_roty);
        }
        else if(kc == KeyEvent.VK_RIGHT) {
            view_roty += 1;
            //System.out.println("Key pressed: view_roty="+view_roty);
        }
        else if(kc == KeyEvent.VK_UP) {
            view_rotx -= 1;
            //System.out.println("Key pressed: view_rotx="+view_rotx);
        }
        else if(kc == KeyEvent.VK_DOWN) {
            view_rotx += 1;
            //System.out.println("Key pressed: view_rotx="+view_rotx);
        }
        else {
            System.out.println(e.getKeyCode());
        }

        myGLEventListener.setView_rotx(view_rotx);
        myGLEventListener.setView_roty(view_roty);
        myGLEventListener.setScale(zoom);
    }

    public float getScale() {
        return zoom;
    }

    public void setScale(float scale) {
        this.zoom = scale;
    }

}