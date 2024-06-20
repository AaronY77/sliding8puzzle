package com.company;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the dimension of the grid you wish to play on.");
        Scanner scanner= new Scanner(Syststem.in);
        int dimension  = scanner.nextInt();
        Puzzle p = new Puzzle(dimension);
        new PicturePuzzle(p);
    
    }
}


