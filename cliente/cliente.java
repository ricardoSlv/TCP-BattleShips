import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class cliente {
    public static void main(String[] args) throws IOException {
        String line = "";
        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
        ;
        BufferedReader socketReader;
        PrintWriter socketWriter;
        String input;

        while (line.equals("Quit") == false) {
            try {
                Socket clsocket = new Socket("172.29.174.205", 8080);
                System.out.println("Connected successfully, waiting on Game");

                socketReader = new BufferedReader(new InputStreamReader(clsocket.getInputStream()));
                socketWriter = new PrintWriter(clsocket.getOutputStream());
                socketWriter.flush();

                while ((input = socketReader.readLine()).equals("GAME OVER") == false && input != null) {
                    processInput(input, socketReader, socketWriter, stdinReader);
                }
                clsocket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Write anything to replay, 'Quit' to close the game.");
            line = stdinReader.readLine();
        }
    }

    public static void processInput(String input, BufferedReader socketReader, PrintWriter socketWriter,
            BufferedReader stdinReader) throws IOException {
        if (input.equals("PLAY")) {
            String s;
            String[] coords;

            do {
                System.out.println("Insere as coordenadas para o disparo");
                s = stdinReader.readLine();
                coords = s.split(" ");
            } while (coords.length != 2);
            socketWriter.println((Integer.parseInt(coords[0]) - 1) + " " + (Integer.parseInt(coords[1]) - 1));
            socketWriter.flush();

        } else if (input.equals("BOARD")) {
            while ((input = socketReader.readLine()).equals("BOARD END") == false) {
                System.out.println(input);
            }
        }

        else if (input.equals("WINNER")) {
            System.out.println("Game ended, you Won !!!!");
        } else if (input.equals("LOSER")) {
            System.out.println("Game ended, you Lost !!!!");
        }
    }
}