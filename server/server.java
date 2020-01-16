import model.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) throws IOException {

        ServerSocket ssSocket = new ServerSocket(8080);
        Socket socket1,socket2;
        game serverGame;
        
        try {
            while (true) {
                serverGame = new game();
                socket1 = ssSocket.accept();
                System.out.println("Player 1 joined, waiting on Player 2");
                socket2= ssSocket.accept();         
                System.out.println("Player 2 joined, starting game");
                Thread gameRun = new Thread(new serverWorker(socket1,socket2, serverGame));
                gameRun.start();
                gameRun.join();
                System.out.println("Game Ended");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server error");
            ssSocket.close();
        }

    }
}