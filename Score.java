import java.awt.Image;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Score extends JFrame{

    JLabel lImage1,lImage2,lMove,lScore1,lScore2;
    JButton back;

    public Score() {
        
        lImage1 = new JLabel(new ImageIcon((new ImageIcon("pictures4/main.jpg").getImage()).getScaledInstance(200, 250, Image.SCALE_SMOOTH)));

        lImage1.setBounds(150,50,200,250);

        lImage2 = new JLabel(new ImageIcon((new ImageIcon("pictures4/16puzzle.jpg").getImage()).getScaledInstance(200, 250, Image.SCALE_SMOOTH)));

        lImage2.setBounds(150,350,200,250);

        lMove = new JLabel("BEST MOVE COUNT");
        lMove.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lMove.setBounds(500, 30, 300, 50);


        lScore1 = new JLabel("-1000");
        lScore1.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lScore1.setBounds(575, 150, 300, 50);


        lScore2 = new JLabel("-1000");
        lScore2.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lScore2.setBounds(575, 425, 300, 50);


        ArrayList<Integer> scores = HandleCount.getCount();

        lScore1.setText(Integer.toString(scores.get(0)));
        lScore2.setText(Integer.toString(scores.get(1)));

        back = new JButton("< BACK");
        back.setBounds(550, 600, 175, 40);
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 20));

        back.addActionListener( ae -> {
            new PuzzleGame();
            dispose();
        });

        setLayout(null);

        add(lImage1);
        add(lImage2);
        add(lMove);
        add(lScore1);
        add(lScore2);
        add(back);

        setBounds(300, 20, 900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Score();
    }
    
}