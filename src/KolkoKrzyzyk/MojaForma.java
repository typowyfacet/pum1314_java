package KolkoKrzyzyk;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author zp107327 (Przemysław Kasprzak)
 */
public class MojaForma extends JFrame {

    MojaForma(String tytulFormy) {
        super(tytulFormy);
    }

public PanelGry panelGry;

    void init() {
        setLayout(null);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int wielkoscOkna;
        if (screenSize.width > screenSize.height) {
            wielkoscOkna = screenSize.height / 2 + screenSize.height / 4;
        } else {
            wielkoscOkna = screenSize.width / 2 + screenSize.width / 4;
        }
        setSize(wielkoscOkna, wielkoscOkna);
        Point location = new Point(screenSize.width / 2 - wielkoscOkna / 2, screenSize.height / 2 - wielkoscOkna / 2);
        setLocation(location);
        panelGry = new PanelGry(30,30,wielkoscOkna-60,wielkoscOkna-60);
        add(panelGry);
        panelGry.addMouseListener(panelGry);
        panelGry.addMouseMotionListener(panelGry);
        panelGry.repaint();
        panelGry.setVisible(true);
    }

    

    
}
