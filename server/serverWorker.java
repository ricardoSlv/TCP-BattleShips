import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import model.game;

public class serverWorker implements Runnable {

    private Socket socket1, socket2;
    private game Game;

    public serverWorker(Socket cSock1, Socket cSock2, game serverGame) {
        this.Game = serverGame;
        this.socket1 = cSock1;
        this.socket2 = cSock2;
    }

    public void playerTurn(int player, BufferedReader socketReader, PrintWriter socketWriter) throws IOException {
        String[] coords = { "0", "0" };
        String s;

        do {
            socketWriter.println("PLAY");
            socketWriter.flush();
            s = socketReader.readLine();

            coords = s.split(" ");
        } while (this.Game.shoot(player, Integer.parseInt(coords[0]), Integer.parseInt(coords[1])) == false);
    }

    public void sendBoards(PrintWriter socketWriter1, PrintWriter socketWriter2) {
        ArrayList<String> lines1 = this.Game.getLines(1);
        ArrayList<String> lines2 = this.Game.getLines(2);
        socketWriter1.println("BOARD");
        socketWriter2.println("BOARD");
        for (String s : lines1) {
            socketWriter1.println(s);
        }
        for (String s : lines2) {
            socketWriter2.println(s);
        }

        socketWriter1.println("BOARD END");
        socketWriter2.println("BOARD END");
        socketWriter1.flush();
        socketWriter2.flush();
    }

    public void run() {
        try {
            BufferedReader socketReader1 = new BufferedReader(new InputStreamReader(this.socket1.getInputStream()));
            BufferedReader socketReader2 = new BufferedReader(new InputStreamReader(this.socket2.getInputStream()));
            PrintWriter socketWriter1 = new PrintWriter(this.socket1.getOutputStream());
            PrintWriter socketWriter2 = new PrintWriter(this.socket2.getOutputStream());
            socketWriter1.flush();
            socketWriter2.flush();

            while (this.Game.getWinner() == 0) {
                this.sendBoards(socketWriter1, socketWriter2);
                this.playerTurn(1, socketReader1, socketWriter1);
                this.sendBoards(socketWriter1, socketWriter2);
                this.playerTurn(2, socketReader2, socketWriter2);
                this.Game.checkStatus();
            }
            this.sendBoards(socketWriter1, socketWriter2);

            if (Game.getWinner() == 1) {
                socketWriter1.println("WINNER");
                socketWriter2.println("LOSER");
            } else if (Game.getWinner() == 2) {
                socketWriter1.println("LOSER");
                socketWriter2.println("WINNER");
            }

            socketWriter1.println("GAME OVER");
            socketWriter2.println("GAME OVER");
            socketWriter1.flush();
            socketWriter2.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}