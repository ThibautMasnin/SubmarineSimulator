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

        if(kc == 77) {
            myGLEventListener.deplacementManuel();
        }

        if(kc == 65) {
            myGLEventListener.deplacementAuto();
        }

        if(kc == 81) {
            //Q est tapé, on tourne à gauche
            myGLEventListener.gauche();
            if(myGLEventListener.getPivH()>-10) {
                myGLEventListener.changePivH(-2);
            }
        }
        else if(kc == 68) {
            //D est tapé, on tourne à droite
            myGLEventListener.droite();
            if(myGLEventListener.getPivH()<10) {
                myGLEventListener.changePivH(2);
            }
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
            if(myGLEventListener.getPivH()>0) {
                myGLEventListener.changePivH(-2);
            }
            else if(myGLEventListener.getPivH()<0) {
                myGLEventListener.changePivH(2);
            }
        }
        else if(kc == 90) {
            //Z est tapé, on avance
            myGLEventListener.avancer();
            if(myGLEventListener.getPivH()>0) {
                myGLEventListener.changePivH(-1);
            }
            else if(myGLEventListener.getPivH()<0) {
                myGLEventListener.changePivH(1);
            }
        }
        else if(kc == 32) {
            //Espace est tapé, on tire
            myGLEventListener.tirer();
        }
        else if(kc == 140) {
            //+ est tapé, on zoome
            zoom += 0.1;
        }
        else if(kc == 139) {
            //- est tapé, on dézoome
            zoom -= 0.1;
        }
        else if(kc == KeyEvent.VK_LEFT) {
            //Left est tapé, on se déplace vers la gauche
            view_roty += 1;
        }
        else if(kc == KeyEvent.VK_RIGHT) {
            //Right est tapé, on se déplace vers la droite
            view_roty -= 1;
        }
        else if(kc == KeyEvent.VK_UP) {
            //Up est tapé, on se déplace vers le haut
            view_rotx += 1;
        }
        else if(kc == KeyEvent.VK_DOWN) {
            //Down est tapé, on se déplace vers le bas
            view_rotx -= 1;
        }
        else {
            System.out.println(e.getKeyCode());
        }

        myGLEventListener.setView_rotx(view_rotx);
        myGLEventListener.setView_roty(view_roty);
        myGLEventListener.setScale(zoom);
    }
}