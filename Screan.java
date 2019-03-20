package sudoku.DecipherSudoku;

import sudoku.Algoritm;
import sudoku.Decipher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screan implements ActionListener {
    JFrame f;
    JTextField[][] tf = new JTextField[9][9];
    JButton cheak, decipher, clean, neww;
    JTextField t;
    Algoritm al = new Algoritm(34);

    public Screan(){

        f = new JFrame("Sudoku !!!");
        cheak = new JButton("CHEAK");
        decipher = new JButton("DEC");
        clean = new JButton("CLEAN");
        neww = new JButton("NEW");
        createTF();
        t = new JTextField();
        t.setBounds(400, 400, 10, 10);
        cheak.setBounds(350, 80, 80, 35);
        decipher.setBounds(350, 120, 80, 35);
        clean.setBounds(350, 160, 80, 35);
        neww.setBounds(350, 200, 80, 35 );
        f.add(decipher);
        f.add(cheak);
        f.add(clean);
        f.add(neww);
        f.add(t);
        f.setSize(500, 350);
        cheak.addActionListener(this);
        decipher.addActionListener(this);
        clean.addActionListener(this);
        neww.addActionListener(this);
        f.setVisible(true);
        f.setLayout(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cheak) {
            for (int i = 0; i < 9; i++) {
                for (int l = 0; l < 9; l++) {
                    if (tf[i][l].getText().equals("")) {
                        tf[i][l].setBackground(Color.red);
                    } else {
                        if (al.getElementOld(i, l) != Integer.valueOf(tf[i][l].getText())) {
                            tf[i][l].setBackground(Color.red);
                        } else {
                            tf[i][l].setBackground(Color.white);
                        }
                    }
                }
            }
        }
        if (e.getSource() == decipher) {
            dec();
        }
        if (e.getSource() == clean) {
            for (int i = 0; i < tf.length; i++) {
                for (int l = 0; l < tf[i].length; l++) {
                    tf[i][l].setText("");
                    tf[i][l].setBackground(Color.white);
                }
            }
        }
        if(e.getSource() == neww){
            al.creatNew();
            vstavka();
        }
    }

    public void dec() {
        Decipher d = new Decipher();
        for (int i = 0; i < tf.length; i++) {
            for (int l = 0; l < tf[i].length; l++) {
                int k = 0;
                if(tf[i][l].getText().equals("")) {
                } else{
                    k = Integer.valueOf(tf[i][l].getText());
                }

                d.setElement(i, l, k);
            }
        }
        d.decipherFull();
        System.out.println(d.getNumberDecipher());
        for (int i = 0; i < tf.length; i++) {
            for (int l = 0; l < tf[i].length; l++) {
                tf[i][l].setText(String.valueOf(d.getElement(i, l)));
            }
        }

    }

    public void createTF() {
        for (int i = 0; i < tf.length; i++) {
            for (int l = 0; l < tf[i].length; l++) {
                tf[i][l] = new JTextField();
                tf[i][l].setBounds((25 * (l + 1)), (25 * (i + 1)), 24, 24);
                f.add(tf[i][l]);
            }
        }
    }

    public void vstavka() {
        for (int i = 0; i < tf.length; i++) {
            for (int l = 0; l < tf[i].length; l++) {
                String str = "" + al.getElementNew(i, l);
                tf[i][l].setBackground(Color.white);
                if (!str.equals("0"))
                    tf[i][l].setText(str);
                else
                    tf[i][l].setText("");
            }
        }
    }
}
