import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

class RoundBorder implements Border {

    private int radius;

    RoundBorder(int radius) {
        this.radius = radius;
    }
   
    @Override
    public Insets getBorderInsets(Component c){
        return new Insets(radius, radius, radius,radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1,radius,radius);
    }
}

public class PuzzleGame extends JFrame {

    JLabel background,labelStart;
    JButton start1,start2,score,exit;


    public PuzzleGame() {

        background = new JLabel(new ImageIcon((new ImageIcon("pictures4/Puzzle.jpg").getImage()).getScaledInstance(900, 700, Image.SCALE_SMOOTH)));

        background.setBounds(0, 0, 900, 700);


        labelStart = new JLabel();
        labelStart.setBounds(500,350,300,300);


        start1 = new JButton("IMAGE PUZZLE");
        start1.setBounds(25,10,250,40);
        start1.setBackground(Color.BLUE);
        start1.setForeground(Color.WHITE);
        start1.setFont(new Font("Arial", Font.BOLD, 26));
        start1.setBorder(new RoundBorder(10));

        start1.addActionListener( ae -> {
            new HardPuzzle();
            dispose();
        });

        labelStart.add(start1);


        start2 = new JButton("NUMBER PUZZLE");
        start2.setBounds(25,70,250,40);
        start2.setBackground(Color.BLUE);
        start2.setForeground(Color.WHITE);
        start2.setFont(new Font("Arial", Font.BOLD, 26));
        start2.setBorder(new RoundBorder(10));

        start2.addActionListener( ae -> {
            new Puzzle16();
            dispose();
        });

        labelStart.add(start2);


        score = new JButton("SCORE");
        score.setBounds(25,130,250,40);
        score.setBackground(Color.BLUE);
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Arial", Font.BOLD, 26));
        score.setBorder(new RoundBorder(10));

        score.addActionListener( ae -> {
            new Score();
            dispose();
        });

        labelStart.add(score);



        exit = new JButton("EXIT");
        exit.setBounds(25,190,250,40);
        exit.setBackground(Color.BLUE);
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Arial", Font.BOLD, 26));
        exit.setBorder(new RoundBorder(10));

        exit.addActionListener( ae -> {
            dispose();
        });

        labelStart.add(exit);



        setLayout(null);
        add(labelStart);
        add(background);
        

        setBounds(300, 20, 900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new PuzzleGame();
    }
    
}