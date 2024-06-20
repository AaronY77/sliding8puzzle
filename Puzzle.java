package com.company;


import java.util.Scanner;
import java.util.*;

public class Puzzle
{
    private int[][] grid;
    private int moves;
    public final int dim;



    public Puzzle(int num)
    {
        dim=num;
        moves=0;


        grid = new int [num][num];
        for(int i=0;i<num*num;i++){
            grid[i/num][i%num]=i;
        }
        int inv=1;
        while(inv%2!=0) {
            inv=0;
            for (int i = 0; i < num*num; i++) {
                swap(i, (int)(Math.random() * num * num));
            }
            System.out.println(this);
            for (int i = 0; i < num * num - 1; i++) {
                if(grid[i/num][i%num]==0)i++;
                for (int c = i + 1; c < num * num; c++) {
                    if(grid[c/num][c%num]==0)c++;
                    if (c<num*num&&(grid[c / num][c % num] < grid[i / num][i % num])) {
                        System.out.println(grid[i / num][i % num] +"is inverted with " + grid[c / num][c % num]+"\ntotal invesrsions:"+(inv+1));
                        inv++;
                    }
                }
            }
        }



    }
    public boolean won(){
        for(int i=0;i<-1+grid.length*grid.length;i++){
            if(grid[i/grid.length][i%grid.length]!=i+1)return false;
        }
        return true;
    }


    private void swap(int i, int c){
        int temp = grid[i/grid.length][i%grid.length];
        grid[i/grid.length][i%grid.length]=grid[c/grid.length][c%grid.length];
        grid[c/grid.length][c%grid.length]=temp;
    }

    public int[][] getGrid()
    {
        return grid;
    }
    public int coor(int l){
        for(int i=0;i<grid.length*grid.length;i++){
            if(grid[i/grid.length][i%grid.length]==l){
                return i;
            }
        }
        return -1;
    }
    public String toString(){
        String s ="{";
        for(int i=0;i<grid.length*grid.length;i++){
            if(grid[i/grid.length][i%grid.length]==0){for(int c=0;c<Math.log10(grid.length*grid.length);c++)s+=" ";s+=",";}
            else{
                String num = String.valueOf(grid[i/grid.length][i%grid.length]);

                s+=String.format("%1$"+(int)(-1*Math.log10(10*grid.length*grid.length))+"s",num)+",";
            }
            if((i+1)%grid.length==0){
                s+="\b}\n{";
            }
        }
        return s.substring(0,s.length()-1);
    }

    public void move(int coo){
        System.out.println("moving");
        int r= coo/grid.length,c=coo%grid.length;
        if(r-1>=0 && grid[r-1][c]==0)

        {
            moves++;
            swap(grid.length*r+c,grid.length*(r-1)+c);

        }
        else if(r+1<grid.length && grid[r+1][c]==0)
        {
            moves++;
            swap(grid.length*r+c,grid.length*(r+1)+c);

        }
        else if(c-1>=0 && grid[r][c-1]==0)
        {
            moves++;
            swap(grid.length*r+c,grid.length*r+c-1);

        }
        else if(c+1<grid.length && grid[r][c+1]==0)
        {
            swap(grid.length*r+c,grid.length*r+c+1);
        }
    }
    public void moveTile(int l)
    {
        int coo=coor(l);
        move(coo);
    }
    public int getNumMoves(){
        return moves;
    }
}


//Given row and column, move value if possible


