package model;

import java.util.Random;

public class barco {

    private int[][] matrix;

    public barco(int i) {
        switch (i) {
        case 1:
            this.matrix = new int[][] { { 1 } };
            break;
        case 2:
            this.matrix = new int[][] { { 2 } };
            break;
        case 3:
            this.matrix = new int[][] { { 3 } };
            break;
        case 4:
            this.matrix = new int[][] { { 4, 4 } };
            break;
        case 5:
            this.matrix = new int[][] { { 5, 5 } };
            break;
        case 6:
            this.matrix = new int[][] { { 6, 6, 6 } };
            break;
        case 7:
            this.matrix = new int[][] { { 7, 7, 7 } };
            break;
        case 8:
            this.matrix = new int[][] { { 8, 8, 8 }, { 100, 8, 100 }, { 100, 8, 100 } };
            break;
        default:
            this.matrix = new int[0][0];
        }
    }

    public int getLength() {
        return this.matrix.length;
    }

    public int getHeight() {
        return this.matrix[0].length;
    }

    public int getPos(int row, int col) {
        return this.matrix[row][col];
    }

    public void shuffle() {

        Random generator = new Random();

        if (generator.nextInt(2) > 0) {
            this.transpose();
        }
        if (generator.nextInt(2) > 0) {
            this.mirrorV();
        }
        if (generator.nextInt(2) > 0) {
            this.mirrorH();
        }

    }

    public void print(){
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(this.matrix[i][j]==100){System.out.print("N ");}
                else{System.out.print(this.matrix[i][j]+" ");}
            }
           System.out.println(""); 
        }
        System.out.println("");
    }

    public void transpose() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;

        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = this.matrix[i][j];
            }
        }
        this.matrix = transposed;
    }

    public void mirrorH() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        int value;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols/2; j++) {
                value=this.matrix[i][j];
                this.matrix[i][j] = this.matrix[i][cols - 1 - j];
                this.matrix[i][cols - 1 - j]=value;
            }
        }
    }

    public void mirrorV() {
        int rows = this.matrix.length;
        int cols = this.matrix[0].length;
        int value;
        for (int i = 0; i < rows/2; i++) {
            for (int j = 0; j < cols; j++) {
                value=this.matrix[i][j];
                this.matrix[i][j] = this.matrix[rows - 1 - i][j];
                this.matrix[rows - 1 - i][j]=value;
            }
        }

    }
}