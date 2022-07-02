import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NowySamochodGUI extends JFrame {
    private JTextField Nr_rejstracyjny;
    private JTextField Model;
    private JTextField Marka;
    private JTextField Prędkość_max;
    private JButton dodajButton;
    private JButton anulujButton;
    private JPanel panel;
    private JRadioButton Benzyna;
    private JRadioButton Diesel;
    private JRadioButton Biegow5;
    private JRadioButton Biegow6;

    public NowySamochodGUI(JComboBox comboBox){
      setContentPane(panel);
      setResizable(false);

      dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pozycja pozycja = new Pozycja(0,0);

                Silnik silnik = null;
                Silnik benzyna = new Silnik("Benzyna", 7000, 0, false, 5000, 20000);
                Silnik diesel = new Silnik("Diesel", 8000, 0, false, 7500, 50000);
                if(Benzyna.isSelected()){
                    silnik = benzyna;
                } else if (Diesel.isSelected()){
                    silnik = diesel;
                }

                SkrzyniaBiegow skrzynia = null;
                SkrzyniaBiegow manual5 = new SkrzyniaBiegow(0, 5, 1500, "manual-5", 25, new Sprzeglo("normalne", 1000, 10, false));
                SkrzyniaBiegow manual6 = new SkrzyniaBiegow(0, 6, 3500, "manual-6", 50, new Sprzeglo("normalne", 1000, 10, false));
                if(Biegow5.isSelected()){
                    skrzynia = manual5;
                } else if (Biegow6.isSelected()){
                    skrzynia = manual6;
                }

                comboBox.addItem(new Samochód(false, Nr_rejstracyjny.getText(), Marka.getText(), Model.getText(), Integer.parseInt(Prędkość_max.getText()), pozycja, silnik, skrzynia));
                dispose();
            }
        });

      anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}


