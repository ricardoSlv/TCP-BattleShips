import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import model.game;

public class serverWorker implements Runnable {

    private int playerNumber;
    private Socket socket;
    private game Game;

    public serverWorker(Socket cSock, game serverGame) {
        this.Game = serverGame;
        this.socket = cSock;
        this.playerNumber=this.Game.getPlayerNumber();
    }

    public void run() {
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            System.out.println(socketReader.readLine());
        } catch (Exception e) {
        }
    }
}