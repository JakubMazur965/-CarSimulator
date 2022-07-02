import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class samochodGUI extends Thread {
    private JPanel panel1;
    private JButton włączButton;
    private JButton wyłączButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton dodajNowyButton;
    private JButton usuńButton;
    private JComboBox comboBox1;
    private JTextField sbNazwa;
    private JTextField sbCena;
    private JTextField sbWaga;
    private JTextField sbBieg;
    private JTextField sNazwa;
    private JTextField sCena;
    private JTextField sWaga;
    private JTextField sObroty;
    private JTextField spNazwa;
    private JTextField spCena;
    private JTextField spWaga;
    private JTextField spStan;
    private JButton ZwiększBieg;
    private JButton ZmniejszBieg;
    private JButton DodajGazu;
    private JButton UjmijGazu;
    private JButton Naciśnij;
    private JButton Zwolnij;
    private JPanel mapa;
    private JLabel samIcon;

    private Samochód samochód;

    public samochodGUI(Samochód s) {
        samochód = s;
        refresh();
        włączButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.wlacz();
                refresh();
            }
        });

        wyłączButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.wylacz();
                refresh();
            }
        });

        ZwiększBieg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.zwiekszBieg();
                refresh();
            }
        });

        ZmniejszBieg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.zmniejszBieg();
                refresh();
            }
        });

        DodajGazu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.dodajGazu(100);
                refresh();
            }
        });

        UjmijGazu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.silnik.zmniejszObroty(100);
                refresh();
            }
        });

        Naciśnij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.sprzeglo.wcisnij();
                refresh();
            }
        });

        Zwolnij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.skrzynia.sprzeglo.zwolnij();
                refresh();
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód = (Samochód) comboBox1.getSelectedItem();
            }
        });

        dodajNowyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new NowySamochodGUI(comboBox1);
                f.pack();
                f.setVisible(true);
                refresh();
            }
        });

        usuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samochód.interrupt();
                comboBox1.removeItem(samochód);
                refresh();
            }
        });

        mapa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pozycja cel = new Pozycja(0,0);
                cel.setX(e.getX());
                cel.setY(e.getY());
                samochód.jedźDo(cel.getX(), cel.getY());
            }
        });
        start();
    }

    public void run(){
        while (true) {
            refresh();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        // silnik do ferrari
        Silnik v8 = new Silnik("benzyna", 8000, 0, false, 2000, 100000);
        // skrzynia biegów dla ferrari
        SkrzyniaBiegow skrzynia_ferrari = new SkrzyniaBiegow(0, 6, 1000, "manual", 500, new Sprzeglo("SPF", 25000, 150, false));
        // startowe pozycje
        Pozycja pozycja_f = new Pozycja(0,0);
        // gotowy samochód ferrari
        Samochód ferrari = new Samochód(false, "KRA1234", "FERRARI", "458", 350, pozycja_f, v8, skrzynia_ferrari);

        JFrame frame = new JFrame("samochodGUI");
        frame.setContentPane(new samochodGUI(ferrari).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void refresh(){
        if(comboBox1.getItemCount() > 0) {
            textField1.setText(samochód.getModel());
            textField2.setText(samochód.getNrRejestr());
            textField3.setText(String.valueOf(samochód.waga()));
            textField4.setText(String.valueOf(samochód.getAktPredkosc()));

            sbNazwa.setText(samochód.getSkrzynia().getNazwa());
            sbCena.setText(String.valueOf(samochód.getSkrzynia().getCena()));
            sbWaga.setText(String.valueOf(samochód.getSkrzynia().getWaga()));
            sbBieg.setText(String.valueOf(samochód.getSkrzynia().getAktualnyBieg()));

            sNazwa.setText(samochód.getSilnik().getNazwa());
            sCena.setText(String.valueOf(samochód.getSilnik().getCena()));
            sWaga.setText(String.valueOf(samochód.getSilnik().getWaga()));
            sObroty.setText(String.valueOf(samochód.getSilnik().getObroty()));

            spNazwa.setText(samochód.getSkrzynia().getSprzeglo().getNazwa());
            spCena.setText(String.valueOf(samochód.getSkrzynia().getSprzeglo().getCena()));
            spWaga.setText(String.valueOf(samochód.getSkrzynia().getSprzeglo().getWaga()));
            spStan.setText(String.valueOf(samochód.getSkrzynia().getSprzeglo().isStanSprzegla()));

            samIcon.show();
            samIcon.setLocation((int) samochód.aktPozycja.getX(), (int) samochód.aktPozycja.getY());
        }
        else {
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");

            sbNazwa.setText("");
            sbCena.setText("");
            sbWaga.setText("");
            sbBieg.setText("");

            sNazwa.setText("");
            sCena.setText("");
            sWaga.setText("");
            sObroty.setText("");

            spNazwa.setText("");
            spCena.setText("");
            spWaga.setText("");
            spStan.setText("");

            samIcon.hide();
        }
    }
}
