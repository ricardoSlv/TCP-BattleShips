package model;

import java.util.Random;

public class game {

    int winner;
    int playersIn;
    grelha[] grelhas;

    public game() {
        this.winner = 0;
        this.grelhas = new grelha[2];
        this.grelhas[0] = new grelha(10);
        this.grelhas[0].fill();
        this.grelhas[1] = new grelha(10);
        this.grelhas[1].fill();
    }

    public int getWinner() {
        return this.winner;
    }

    public synchronized int getPlayerNumber(){
        this.playersIn++;
        return this.playersIn;
    }

    public int getPos(int player, int grelha, int row, int col) {

        int value = this.grelhas[grelha - 1].getPos(row, col);
        if (player != grelha && value >= 1 && value <= 8) {
            value = 100;
        }
        return value;
    }

    public int getPosShot(int grelha, int row, int col) {

        return this.grelhas[grelha - 1].getPos(row, col);
    }

    public void setPos(int grelha, int row, int col, int newValue) {
        this.grelhas[grelha - 1].setPos(row, col, newValue);
    }

    public boolean shoot(int player, int row, int col) {
        int enemy = (player % 2) + 1;

        int value = this.getPosShot(enemy, row, col);
        boolean didShoot = false;

        if (value == 0) {
            didShoot = false;
        } else if (value == 100) {
            this.setPos(enemy, row, col, 0);
            didShoot = true;
        } else if (value > 0) {
            this.setPos(enemy, row, col, -value);
            didShoot = true;
        } else if (value < 0) {
            didShoot = false;
        }

        return didShoot;
    }

    public void randomShoot(int player) {
        int enemy = (player % 2) + 1;
        int randomX, randomY, value;
        Random generator = new Random();

        do {
            randomX = generator.nextInt(10);
            randomY = generator.nextInt(10);
            value = this.getPosShot(enemy, randomX, randomY);

        } while (value == 0 || value < 0);

        this.shoot(player, randomX, randomY);
    }

    public void checkStatus() {
        if (this.grelhas[0].checkStatus() == false) {
            this.winner = 2;
            System.out.println("Player 1 died RIP");
        } else if (this.grelhas[0].checkStatus() == false) {
            this.winner = 1;
            System.out.println("Player 2 died RIP");
        }
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.grelhas[0].getLinha(i) + "                   " + this.grelhas[0].getLinha(i));
        }
    }

}
