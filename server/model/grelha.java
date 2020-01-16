package model;

import java.util.Random;

public class grelha {

    int[][] matrix;
    int size;

    public grelha(int size) {
        this.size = size;
        this.matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.matrix[i][j]=100;
            }
        }
    }

    public int getPos(int x, int y) {
        return this.matrix[x][y];
    }

    public void setPos(int x, int y, int value) {
        this.matrix[x][y] = value;
    }

    public void fill() {
        frota f1 = new frota();
        int randX, randY;
        Random generator = new Random();

        for (barco b : f1.getBarcos()) {
            b.shuffle();
            do {
                randX = generator.nextInt(10);
                randY = generator.nextInt(10);
            } while (this.checkValido(b, randX, randY) == false);

            this.addBarco(b, randX, randY);
        }
    }

    public boolean checkStatus() {

        for (var i = 0; i < this.size; i++) {
            for (var j = 0; j < this.size; j++) {
                if (this.matrix[i][j]!=100&&this.matrix[i][j] > 0) {return true;}
            }
        }
        return false;
    }

    public void print(){
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if(this.matrix[i][j]==100){System.out.print("N ");}
                else{System.out.print(this.matrix[i][j]+" ");}
            }
           System.out.println(""); 
        }
        System.out.println("");
    }

    public String getLinha(int linha){
        StringBuilder sb= new StringBuilder();
        int value=0;
        
        for (int i = 0; i < this.size; i++){
            value=this.matrix[linha][i];
            if(value<0){sb.append("X ");}
            else if(value==100){sb.append("N ");}
            else{sb.append(value+" ");}
        }
        
        return sb.toString();    
    }

    public String getLinha(int player,int grelha,int linha){
        StringBuilder sb= new StringBuilder();
        int value=0;
        
        for (int i = 0; i < this.size; i++){
            value=this.matrix[linha][i];
            if(value<0){sb.append("X ");}
            else if(value>0&&value<=8&&player!=grelha){sb.append("N ");}
            else if(value==100){sb.append("N ");}
            else{sb.append(value+" ");}
        }
        
        return sb.toString();    
    }

    public boolean checkValido(barco b, int randX, int randY) {
        int barcoX = b.getLength();
        int barcoY = b.getHeight();

        // Posicao
        if (barcoX + randX > this.size - 1 || barcoY + randY > this.size - 1)
            return false;

        // Colisao
        for (int i = 0; i < barcoX; i++) {
            for (int j = 0; j < barcoY; j++) {
                if (b.getPos(i, j) != 100 && this.matrix[i + randX][j + randY] != 100) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addBarco(barco b, int randX, int randY) {
        int barcoX = b.getLength();
        int barcoY = b.getHeight();
        int valor;

        for (int i = 0; i < barcoX; i++) {
            for (int j = 0; j < barcoY; j++) {
                valor = b.getPos(i, j);
                if (valor != 100) {
                    this.matrix[i + randX][j + randY] = valor;
                }
            }
        }
    }

}