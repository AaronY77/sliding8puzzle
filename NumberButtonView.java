package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NumberButtonView extends JFrame implements ActionListener
{
    private Puzzle p;
    private JButton[][] buttons;
    private JButton b0, b1,b2,b3,b4,b5,b6,b7,b8,next;
    private JLabel directions,movesReport;

    public NumberButtonView(Puzzle p)
    {
        super("MixedUp Tile Game");
        this.p = p;

        //Print directions
        directions = new JLabel("Directions:  Shift the numbers until they are in order.");
        directions.setBounds(5, 0,300,20+(5*(p.dim-3)));
        add(directions);

        //Report moves
        movesReport = new JLabel("Moves: " + p.getNumMoves());
        movesReport.setBounds(25*p.dim,70+40*p.dim,200,40);
        add(movesReport);

        //Make new buttons with blank labels (optional: could put labels directly on them)
        JButton[][] buttonArr = new JButton[p.dim][p.dim];
        for(int i=0;i<p.dim*p.dim;i++){
            buttonArr[i/p.dim][i%p.dim]= new JButton("hello");
        }
        this.buttons = buttonArr;

        //Labels the buttons to match the 2D array (converting the 0 to a " " to make it look blank)
        updateLabels();


        //Set positions and sizes of buttons in the window
        int hor= 50;
        int vert= 40;
        for(int i=0;i<p.dim*p.dim;i++){
            buttonArr[i/p.dim][i%p.dim].setBounds(10+hor*(i%p.dim),30+vert*(i/p.dim),hor,vert);
            buttonArr[i/p.dim][i%p.dim].setFont(new Font("Arial",Font.PLAIN,9));
            add(buttonArr[i/p.dim][i%p.dim]);
            buttonArr[i/p.dim][i%p.dim].addActionListener(this);

        }
        updateLabels();



        //Add buttons to the JFrame


        //Create and color the "NEXT" button
        next=new JButton("NEXT");
        next.setBounds(-50+25*p.dim,100+40*p.dim,100,40);
        next.addActionListener(this);
        next.setBackground(Color.black);
        next.setForeground(Color.green);
        add(next);

        //Set attributes of the frame itself
        setSize(20+50*p.dim, 200+40*p.dim);  //size of window
        setLayout(null);
        setVisible(true);   //it's set up, so now you can make it visible!
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //hit X to close the window
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<p.dim*p.dim;i++){
            if(e.getSource()==buttons[i/p.dim][i%p.dim]){
                System.out.println("hi"+i);
                p.move(i);
                updateLabels();
                return;
            }
            else if(e.getSource()==next){
                p=new Puzzle(p.dim);
                updateLabels();
            }
        }
        //Code to shuffle when someone hits "Next"
		/*if(e.getSource()==next)
		{
			//reset move counter
			//call a Puzzle method to change the state of the puzzle
			next.setText("Just kidding!");
		}


		//Code to update text on buttons
		else if(e.getSource()==b0)
		{
      p.move(0);

			//If they click the first button, move the appropriate tile

		}
		else if(e.getSource()==b1)
		{
			p.move(1);
		}
		else if(e.getSource()==b2)
		{
      p.move(2);

		}
		else if(e.getSource()==b3)
		{
      p.move(3);
		}
		else if(e.getSource()==b4)
		{
      p.move(4);
		}
		else if(e.getSource()==b5)
		{
      p.move(5);
		}
		else if(e.getSource()==b6)
		{
      p.move(6);
		}
		else if(e.getSource()==b7)
		{
      p.move(7);
		}
		else if(e.getSource()==b8)
		{
      p.move(8);
		}*/


    }

    public void updateLabels() {
        for (int r = 0; r < p.dim; r++) {
            for (int c = 0; c < p.dim; c++) {
                if (p.getGrid()[r][c] == 0)
                    buttons[r][c].setText(" ");
                else
                    buttons[r][c].setText("" + p.getGrid()[r][c]);
            }
        }
        if (p.won()) {
            movesReport.setText("You won in " + p.getNumMoves() + " moves");
        } else {
            movesReport.setText("" + p.getNumMoves());
        }
    }


    public static void main(String[] args)
    {
        Puzzle p = new Puzzle(3);
        new NumberButtonView(p);

    }

}

