package KolkoKrzyzyk;
import java.awt.EventQueue;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
/**
 *
 * @author zp107327 (Przemysław Kasprzak)
 */
public class KolkoKrzyzyk {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MojaForma plansza = new MojaForma("Kułko i kżyrzyk");
                plansza.init();
                //plansza.addActionListener(plansza);
                plansza.setResizable(false);
                plansza.setDefaultCloseOperation(EXIT_ON_CLOSE);
                plansza.setVisible(true);         
            }
        });
    };
};
