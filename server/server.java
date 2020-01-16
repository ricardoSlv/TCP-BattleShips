import model.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) throws IOException {

        ServerSocket ssSock = new ServerSocket(8080);
        game serverGame = new game();
        serverGame.print();

        // try {
        //     while (true) {
        //         Socket clSock = ssSock.accept();
        //         new Thread(new serverWorker(clSock, serverModel)).start();
        //         System.out.println("Accepted Connection Request");
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     System.out.println("Server error");
        // }

    }
}