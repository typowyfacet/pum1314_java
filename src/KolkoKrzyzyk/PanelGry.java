package KolkoKrzyzyk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author zp107327 (Przemysław Kasprzak)
 */
public class PanelGry extends JPanel implements MouseListener, MouseMotionListener {

    int pozX;
    int pozY;
    int sze;
    int wys;
    boolean nextStan = false;
    boolean kreska = false;
    String[] tabStan = {"---", "KÓŁKO", "KRZYŻYK"};
    String[] tabWynik = {"brak rozwiązania", ".....", "ZWYCIEZCA!"};
    public int zwyciezca = -1;
    MujButon but;

    //konstruktor z ustawieniem wymiarów i pozycji
    PanelGry(int pozX, int pozY, int sze, int wys) {
        zerujPola();
        setBounds(pozX, pozY, sze, wys);
        this.pozX = pozX;
        this.pozY = pozY;
        this.sze = sze;
        this.wys = wys;
    }
    VPole[][] tabP = new VPole[3][3]; //tabela pól

    public void zerujPola() {
        for (int i1 = 0; i1 < 3; i1++) {
            for (int i2 = 0; i2 < 3; i2++) {
                tabP[i1][i2] = new VPole();
            }
        }

    }

    public void setStanPola(int xX, int yX, int jakiStan) {
        tabP[xX][yX].setStan(jakiStan);
        System.out.println(tabP[xX][yX]);
    }
//********* RYSOWANIE 

    @Override
    public void paintComponent(Graphics g) {
        int a = sze;
        int b = wys;
        int szPola = sze / 3;
        int wPola = wys / 3;
        int odPolaX = szPola / 100 * 15;
        int odPolaY = wPola / 100 * 15;

        Graphics2D g2 = (Graphics2D) g;
//rysuj plansze

        g2.setColor(Color.white);
        g2.fillRect(0, 0, a, b);
        for (int i = 1; i < 3; i++) {
            g2.setColor(Color.BLACK);
            g2.drawLine(a / 3 * i, 0, a / 3 * i, b);
            g2.drawLine(0, b / 3 * i, a, b / 3 * i);

        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                g2.setColor(Color.RED);
                if (tabP[i][j].getStan() == 1) {
                    g2.drawLine(i * szPola + odPolaX, j * wPola + odPolaY, i * szPola + szPola - odPolaX, j * wPola + wPola - odPolaY);
                    g2.drawLine(i * szPola + szPola - odPolaX, j * wPola + odPolaY, i * szPola + odPolaX, j * wPola + wPola - odPolaY);
                } else if (tabP[i][j].getStan() == 0) {
                    g2.drawOval(i * szPola + odPolaX, j * wPola + odPolaY, szPola - 2 * odPolaX, wPola - 2 * odPolaY);
                }
            }
        }
    }

    private int winOption() {
        int powrot = 0;
        for (int i = 1; i < 9; i++) {
            if ((tabP[0][0].getStan() != -1) && (tabP[0][0].getStan() == tabP[1][0].getStan()) && (tabP[0][0].getStan() == tabP[2][0].getStan())) {
                powrot = 1;

                zwyciezca = tabP[0][0].getStan();
            };
            if ((tabP[0][1].getStan() != -1) && (tabP[0][1].getStan() == tabP[1][1].getStan()) && (tabP[0][1].getStan() == tabP[2][1].getStan())) {
                powrot = 2;
                zwyciezca = tabP[0][1].getStan();
            };
            if ((tabP[0][2].getStan() != -1) && (tabP[0][2].getStan() == tabP[1][2].getStan()) && (tabP[0][2].getStan() == tabP[2][2].getStan())) {
                powrot = 3;
                zwyciezca = tabP[0][2].getStan();
            };
            if ((tabP[0][0].getStan() != -1) && (tabP[0][0].getStan() == tabP[0][1].getStan()) && (tabP[0][0].getStan() == tabP[0][2].getStan())) {
                powrot = 4;
                zwyciezca = tabP[0][0].getStan();
            };
            if ((tabP[1][0].getStan() != -1) && (tabP[1][0].getStan() == tabP[1][1].getStan()) && (tabP[1][0].getStan() == tabP[1][2].getStan())) {
                powrot = 5;
                zwyciezca = tabP[1][0].getStan();
            };
            if ((tabP[2][0].getStan() != -1) && (tabP[2][0].getStan() == tabP[2][1].getStan()) && (tabP[2][0].getStan() == tabP[2][2].getStan())) {
                powrot = 6;
                zwyciezca = tabP[2][0].getStan();
            };
            if ((tabP[0][0].getStan() != -1) && (tabP[0][0].getStan() == tabP[1][1].getStan()) && (tabP[0][0].getStan() == tabP[2][2].getStan())) {
                powrot = 7;
                zwyciezca = tabP[0][0].getStan();
            };
            if ((tabP[2][0].getStan() != -1) && (tabP[2][0].getStan() == tabP[1][1].getStan()) && (tabP[2][0].getStan() == tabP[0][2].getStan())) {
                powrot = 8;
                zwyciezca = tabP[2][0].getStan();
            };
        }
        if (powrot == 0) {
            int l = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tabP[i][j].getStan() == -1) {
                        l++;
                    }
                }
            }
            if (l == 0) {
                powrot = -1;
            }
        }

        return powrot;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(), e.getY());
        Point temp = new Point();
        int szer_pola = sze / 3;
        int wys_pola = wys / 3;
        temp.x = p.x / szer_pola;
        temp.y = p.y / wys_pola;
        if (zwyciezca == -1) {

            if (tabP[temp.x][temp.y].getStan() == -1) {
                if (nextStan == false) {
                    nextStan = true;
                    tabP[temp.x][temp.y].setStan(0);
                } else {
                    nextStan = false;
                    tabP[temp.x][temp.y].setStan(1);
                    kreska = true;
                };
            } else {
                System.out.println("pole zawiera: " + tabP[temp.x][temp.y]);
                kreska = false;
            }
            int zw = winOption();
            if ((zw == -1) || (zw > 0)) {
                String kap;
                if (zw > 0) {
                    kap = "ZWYCIEZCA:" + tabStan[zwyciezca + 1] + "   |   kliknij aby zresetowac";
                } else {
                    kap = "REMIS   |   kliknij aby zresetowac";
                }
   //siemanowice slaskie
                but = new MujButon(kap);
                add(but);
                but.setBounds(40, wys / 2 - wys / 4, sze - 60, wys / 3);
                but.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resetujPlansze();
                    }
                });

            //    but.setVisible(true);
            }

            this.repaint();
        }

        //this.repaint();
    }
    Point akt = new Point(0, 0);

    private void resetujPlansze() {
        zwyciezca = -1;
        zerujPola();
        but.setVisible(false);
        repaint();
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       // mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    private class VPole {

        VPole() {

        }

        @Override
        public String toString() {

            return tabStan[stan + 1];
        }
        private int stan = -1; //0 - kolko 1- krzyzyk

        public int getStan() {
            return stan;
        }

        public void setStan(int sStan) {
            this.stan = sStan;
        }

    }
}

class MujButon extends JButton {

    MujButon(String kapszyn) {
        super(kapszyn);
    }
}
