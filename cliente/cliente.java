import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class cliente {
    public static void main(String[] args) {
        try {
            Socket clsocket = new Socket("172.29.174.205", 8080);
            System.out.println("Coneção estabelecida");

            BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(clsocket.getInputStream()));
            PrintWriter socketWriter = new PrintWriter(clsocket.getOutputStream());
            socketWriter.flush();
            socketWriter.println("poppoo");
            socketWriter.flush();
        } catch (Exception e) {

        }
    }
}