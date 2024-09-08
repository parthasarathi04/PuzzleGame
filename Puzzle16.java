import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Puzzle16 extends JFrame {

    List<JButton> buttons = new ArrayList<>();//16
    JButton reset,back;
    Icon specialIcon;
    JLabel label,cText,count,bgBorder;
    final String voidText = "";
    final Color voidColor = new Color(218,218,218);
    final Color fillColor = Color.ORANGE;

    public Puzzle16() {


        label = new JLabel(new ImageIcon((new ImageIcon("pictures4/16puzzle.jpg").getImage()).getScaledInstance(200, 265, Image.SCALE_SMOOTH)));

        label.setBounds(600,160,200,265);


        bgBorder = new JLabel();
        bgBorder.setBounds(95,25,419,539);
        bgBorder.setBorder(new LineBorder(Color.BLACK,10));
        add(bgBorder);


        for(int i=0;i<16;i++) {
            buttons.add(new JButton(""+(i+1)));
            buttons.get(i).setBounds(100+(i%4)*100+4, 30+(i/4)*130+4, 100, 130);
            buttons.get(i).setBackground(fillColor);
            buttons.get(i).setForeground(Color.BLACK);
            buttons.get(i).setBorder(new LineBorder(Color.RED,2));
            buttons.get(i).setFont(new Font("Times New Roman", Font.BOLD, 36));
        }

        buttons.get(15).setText("");
        buttons.get(15).setBackground(voidColor);

        suffle();


        reset = new JButton("RESET");
        reset.setBounds(240, 600, 120, 32);
        reset.setBackground(Color.RED);
        reset.setForeground(Color.WHITE);
        reset.setFont(new Font("Arial", Font.BOLD, 16));

        reset.addActionListener( ae -> {
            count.setText("0");
            suffle();
        });

        back = new JButton("< BACK");
        back.setBounds(650, 600, 120, 32);
        back.setBackground(Color.BLUE);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 16));

        back.addActionListener( ae -> {
            new PuzzleGame();
            dispose();
        });


        cText = new JLabel("MOVES : ");
        cText.setBounds(650,500,100,30);
        cText.setFont(new Font("Arial", Font.BOLD, 18));

        count = new JLabel("0");
        count.setBounds(750,500,150,28);
        count.setFont(new Font("Times New Roman", Font.BOLD, 35));

        for(int i=0;i<16;i++) {
            int j = i;
            buttons.get(i).addActionListener( ae -> {
                if(j-4>=0 && buttons.get(j-4).getText().equalsIgnoreCase(voidText)) {
                    buttons.get(j-4).setText(buttons.get(j).getText());
                    buttons.get(j).setText(voidText);

                    buttons.get(j).setBackground(voidColor);
                    buttons.get(j-4).setBackground(fillColor);
                    
                    int c = Integer.parseInt(count.getText());
                    c++;
                    count.setText(""+c);
                }
                else if(j+4<16 && buttons.get(j+4).getText().equalsIgnoreCase(voidText)) {
                    buttons.get(j+4).setText(buttons.get(j).getText());
                    buttons.get(j).setText(voidText);

                    buttons.get(j).setBackground(voidColor);
                    buttons.get(j+4).setBackground(fillColor);

                    int c = Integer.parseInt(count.getText());
                    c++;
                    count.setText(""+c);
                }
                else if(j-1>=0 && (j-1)%4!=3  && buttons.get(j-1).getText().equalsIgnoreCase(voidText)) {
                    buttons.get(j-1).setText(buttons.get(j).getText());
                    buttons.get(j).setText(voidText);

                    buttons.get(j).setBackground(voidColor);
                    buttons.get(j-1).setBackground(fillColor);

                    int c = Integer.parseInt(count.getText());
                    c++;
                    count.setText(""+c);
                }
                else if(j+1<16 && (j+1)%4!=0 && buttons.get(j+1).getText().equalsIgnoreCase(voidText)) {
                    buttons.get(j+1).setText(buttons.get(j).getText());
                    buttons.get(j).setText(voidText);

                    buttons.get(j).setBackground(voidColor);
                    buttons.get(j+1).setBackground(fillColor);

                    int c = Integer.parseInt(count.getText());
                    c++;
                    count.setText(""+c);
                }

                isComplete();
            });
        }



        setLayout(null);

        for(int i=0;i<16;i++) {
            add(buttons.get(i));
        }
        add(label);
        add(reset);
        add(back);
        add(cText);
        add(count);

        setBounds(300, 20, 900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void suffle() {

        Random random = new Random();

        List<Integer> bList = new ArrayList<Integer>();
        List<Integer> tracker = new ArrayList<Integer>();

        for(int i=0;i<16;i++) {
            bList.add(i);
            tracker.add(i);
        }

        while(bList.size()>0) {
            int i = bList.get(Math.abs(random.nextInt()) % bList.size());
            bList.remove(Integer.valueOf(i));

            int j = bList.get(Math.abs(random.nextInt()) % bList.size());
            bList.remove(Integer.valueOf(j));

            buttons.get(i).setText(""+(j+1));
            buttons.get(j).setText(""+(i+1));

            if(i == 15 ) {
                buttons.get(j).setText(voidText);
            }
            if(j == 15 ) {
                buttons.get(i).setText(voidText);
            }

            tracker.set(i, j);
            tracker.set(j, i);
        }

        if(!solvable(tracker)) {
            String str = buttons.get(0).getText();
            buttons.get(0).setText(buttons.get(1).getText());
            buttons.get(1).setText(str);
        }

        for(JButton b : buttons) {
            if(b.getText().equals(voidText))
                b.setBackground(voidColor);
             else
                b.setBackground(fillColor);
        }

    }

    private boolean solvable(List<Integer> arr) {
        int pos = -1;
        int inversion = 0;

        for(int i=0;i<16;i++) {
            if(arr.get(i)==15) pos = i;
            for(int j = i+1;j<16;j++) {
                if(arr.get(i)>arr.get(j)) inversion++;
            }
        }

        if((pos/4)%2 == 0) return inversion%2 == 0;
        else return inversion%2 == 1;

    }

    private boolean isComplete() {
        for(int i =0;i<15;i++) {
            if( ! buttons.get(i).getText().equals(new String(""+(i+1)))) return false;
        }

        buttons.get(15).setText("16");
        buttons.get(15).setBackground(fillColor);

        HandleCount.updateCount(2, Integer.parseInt(count.getText()));

        return true;

    }

    public static void main(String[] args) {
        new Puzzle16();
    }
    
}