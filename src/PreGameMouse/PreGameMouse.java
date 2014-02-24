package PreGameMouse;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Klasa obiektu odpowiedzalnego za zmiane współrzednych strzału.
 */
class NewStrzal extends Thread {

    PreGameMouse okno;
    int predkosc = 15;

    int kierunek_x = okno.ustalKierunek();

    int kierunek_y = -1;

    NewStrzal(PreGameMouse okno) {
        this.okno = okno;
    }

    @Override
    public void run() {
        while (okno.y_strzal > 0 && okno.y_strzal<okno.h-okno.wysokosc/2) {
            //if  
            System.out.println(okno.wykryjKolizje());
            if (okno.wykryjKolizje() == 1 || okno.wykryjKolizje() == 2) {
                kierunek_x = 0 - kierunek_x;
            }
            if (okno.wykryjKolizje()==3 || okno.wykryjKolizje()==4) {
                kierunek_y=0-kierunek_y;
            }
            okno.y_strzal += predkosc * kierunek_y;
            okno.x_strzal += predkosc * kierunek_x;
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            };
        }
        okno.jestStrzal = false;
    }
}

public class PreGameMouse extends JFrame implements KeyListener, Runnable, MouseListener, MouseMotionListener {

    int w = 500, h = 500; //pole gry
    int x = 200, y = 450, szerokosc = 75, wysokosc = 10; // dolna
    int x2 = 200, y2 = 50, szerokosc2 = 75, wysokosc2 = 10; //gorna
    int predkosc = 5;
    int predkosc2 = 28;

    static int x_strzal, y_strzal, h_strzal = 10;
    static boolean jestStrzal = false;

    public static int ustalKierunek() {
        Random r = new Random();
        int p = r.nextInt(10);System.out.println("---"+p);
        if ((p % 2) == 0) {
            return 1;
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        PreGameMouse okno = new PreGameMouse("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);
        new Thread(okno).start();

        okno.addKeyListener(okno);
        okno.addMouseListener(okno);
        okno.addMouseMotionListener(okno);
    }

    PreGameMouse(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setBackground(Color.yellow);
        setSize(w, h);
        setResizable(false);
    }

    /**
     * Inicjalizacja strzalu - uruchomienie watka zmieniajacego wspolrzedne
     * strzalu.
     */
    void strzal() {
        if (!jestStrzal) {
            x_strzal = x + szerokosc / 2 - h_strzal / 2;
            y_strzal = y - h_strzal;
            jestStrzal = true;
            new NewStrzal(this).start();
        }
    }

    /**
     *
     */
    //Point wspPilki{
    //Point p=new point();
//}
    public int wykryjKolizje() {
        // sciany
        int powrot = 0;
        int t = h_strzal;
// kolizja lewa sciana
        if (x_strzal <= t) {
            powrot = 1;
            System.out.println("LEWA SCIANA");
        }
        //kolizja prawa sciana
        if (x_strzal >= (w - h_strzal)) {
            powrot = 2;
            System.out.println("PRAWA ŚCIANA");
        }
        // kolizja gorna paletka   //???
        if (y_strzal >= 50 && y_strzal <=50+wysokosc2+h_strzal && x_strzal>=x2 && x_strzal<=x2+szerokosc2) {
            System.out.println("GÓRNA PALETKA");
            powrot = 3;
        }
        //kolizja dolna paletka
        if (y_strzal <= h+wysokosc && y_strzal >=h-50+wysokosc-h_strzal && x_strzal>=x && x_strzal<=x+szerokosc) {
            System.out.println("DOLNA PALETKA");
            powrot = 4;
        }

    //0 brak kolizji
        return powrot;
    }

    void przesun(int kierunek) {
        x += (kierunek * predkosc);
    }

    void przesun2(int kierunek) {
        x2 += (kierunek * predkosc2);
    }

    void ustawPozycje(int xPos) {
        x = xPos;
    }

    void ustawPozycje2(int xPos) {
        x2 = xPos;
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran
        g.clearRect(0, 0, w, h);
        g.fillRect(x, y, szerokosc, wysokosc);
        g.fillRect(x2, y2, szerokosc2, wysokosc2);

        if (jestStrzal) {
            g.fillOval(x_strzal, y_strzal, h_strzal, h_strzal);
        }
    }

    public void run() { // metoda watka odswierzajacego ekran
        while (true) {
            repaint();
            try {
                Thread.sleep(15);
            } catch (Exception e) {
            };
        }
    }
//obsluga zdarzen z klawiatury

    public void keyTyped(KeyEvent e) {
        // te metode zostawie jako pusta
    }

    public void keyReleased(KeyEvent e) {
        // te metode zostawie jako pusta
    }

    public void keyPressed(KeyEvent e) { // reaguje jedynie na przycisniecie klawisza
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                przesun2(-1);
                break;
            case KeyEvent.VK_RIGHT:
                przesun2(1);
                break;
            case KeyEvent.VK_SPACE:
                strzal();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        strzal();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ustawPozycje(e.getX());
// /throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
