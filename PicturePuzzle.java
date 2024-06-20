package com.company;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

import java.io.*;

public class PicturePuzzle extends JFrame implements ActionListener
{
    private JButton next;
    private JButton[][] buttons;
    private Icon[] icons;
    private JLabel directions,movesReport;
    private Puzzle p;
    private BufferedImage img;
    private int change;
    private final int size= 100;

    public PicturePuzzle(Puzzle puzz) throws IOException {
        super("MixedUp Tile Game");

        this.p = puzz;

        //Print directions
        directions = new JLabel("Directions:  Shift the numbers until they are in order.");
        directions.setBounds(5, 0,400,20+(5*(p.dim-3)));
        add(directions);

        //Report moves
        movesReport = new JLabel("Moves: " + p.getNumMoves());
        movesReport.setBounds(-10+25*p.dim,70+100*p.dim,200,40);
        add(movesReport);

        //Make icons from a split up picture.
        //Here, the image files are numbered 1-9 and
        //live in a folder called pic that I dragged
        //into the project folder
        icons = new Icon[p.dim*p.dim];
        icons[0]=new ImageIcon("");
        File file = new File("src/com/company/10lebron-voters-mediumSquareAt3X.jpg");
        img = (ImageIO.read(file));
        change = img.getHeight()/p.dim;
        System.out.println(img.getHeight());
        for(int i=0;i<-1+p.dim*p.dim;i++){
            BufferedImage sub = img.getSubimage((i%p.dim)*change,(i/p.dim)*change,change,change);
            Image sub2= sub.getScaledInstance(size,size,Image.SCALE_DEFAULT);
            icons[i+1]= new ImageIcon(sub2);
        }
        // Icon ic0=new ImageIcon("");
        // Icon ic1=new ImageIcon("src/pictures/pic-1.png");
        // Icon ic2=new ImageIcon("src/pictures/pic-2.png");
        // Icon ic3=new ImageIcon("src/pictures/pic-3.png");
        // Icon ic4=new ImageIcon("src/pictures/pic-4.png");
        // Icon ic5=new ImageIcon("src/pictures/pic-5.png");
        // Icon ic6=new ImageIcon("src/pictures/pic-6.png");
        // Icon ic7=new ImageIcon("src/pictures/pic-7.png");
        // Icon ic8=new ImageIcon("src/pictures/pic-8.png");

        // Icon[] ic = {ic0,ic1,ic2,ic3,ic4,ic5,ic6,ic7,ic8};
        // this.icons = ic;

        //Create buttons using each icon
        buttons= new JButton[p.dim][p.dim];
        for(int i=0;i<p.dim*p.dim;i++){
            buttons[i/p.dim][i%p.dim]=new JButton("");
        }
        // b0=new JButton("");
        // b1=new JButton("");
        // b2=new JButton("");
        // b3=new JButton("");
        // b4=new JButton("");
        // b5=new JButton("");
        // b6=new JButton("");
        // b7=new JButton("");
        // b8=new JButton("");
        //next=new JButton("NEXT");
        //JButton[][] buttonArr = { {b0, b1, b2}, {b3, b4, b5}, {b6, b7, b8} };
        //this.buttons = buttonArr;
        updateIcons();

        //Set positions and sizes of buttons in the window
        int hor= size;
        int vert= size;
        for(int i=0;i<p.dim*p.dim;i++){
            buttons[i/p.dim][i%p.dim].setBounds(10+hor*(i%p.dim),30+vert*(i/p.dim),hor,vert);
            buttons[i/p.dim][i%p.dim].setFont(new Font("Arial",Font.PLAIN,13));
            add(buttons[i/p.dim][i%p.dim]);
            buttons[i/p.dim][i%p.dim].addActionListener(this);
        }

        //next.setBounds(110,440,100,40);
        next=new JButton("NEXT");
        next.setBounds(-50+25*p.dim,100+40*p.dim,100,40);
        next.addActionListener(this);
        next.setBackground(Color.black);
        next.setForeground(Color.green);
        add(next);

        //Add buttons to the JFrame
        // add(b0);add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8); add(next);
        // b0.addActionListener(this);
        // b1.addActionListener(this);
        // b2.addActionListener(this);
        // b3.addActionListener(this);
        // b4.addActionListener(this);
        // b5.addActionListener(this);
        // b6.addActionListener(this);
        // b7.addActionListener(this);
        // b8.addActionListener(this);
        //next.addActionListener(this);

        //Color the "NEXT" button
        next.setBackground(Color.black);
        next.setForeground(Color.green);


        //Set attributes of the frame itself
        setSize(20+size*p.dim, 200+size*p.dim);
        setLayout(null);
        setVisible(true);   //it's set up, so now you can make it visible!
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //hit X to close the window
    }


    //Code to shuffle when someone hits "Next"
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<p.dim*p.dim;i++){
            if(e.getSource()==buttons[i/p.dim][i%p.dim]){

                p.move(i);
                updateIcons();
                System.out.println(p);
                return;
            }
            else if(e.getSource()==next){
                p=new Puzzle(p.dim);
                updateIcons();
            }
        }

    }

    public void updateIcons()
    {
        for(int r=0; r<p.dim; r++)
        {
            for(int c=0; c<p.dim; c++)
            {
                if(p.getGrid()[r][c] == 0)
                    buttons[r][c].setIcon(new ImageIcon("") );
                else
                {
                    int iconIdx = p.getGrid()[r][c];
                    buttons[r][c].setIcon(icons[iconIdx]);
                }
            }
        }
        if(p.won()){
            BufferedImage corner = img.getSubimage((p.dim-1)*change,(p.dim-1)*change,change,change);
            icons[p.dim*p.dim-1]= new ImageIcon(corner.getScaledInstance(size,size,Image.SCALE_DEFAULT));
            buttons[p.dim-1][p.dim-1].setIcon(icons[p.dim*p.dim-1]);
            movesReport.setText("Won in: " + p.getNumMoves());
        }

        else{movesReport.setText("Moves: " + p.getNumMoves());}
    }



}
