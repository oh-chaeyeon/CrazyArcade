package crazyarcade;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int userNumber;
    private Server server;

    public ClientHandler(Socket clientSocket, int userNumber, Server server) {
        this.clientSocket = clientSocket;
        this.userNumber = userNumber;
        this.server = server;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        try {
            sendMessage("현재 참여자는 User" + userNumber + " 입니다.\n");

            while ((message = in.readLine()) != null) {
            	if (message.equals("게임 시작")) {
                    server.broadcastMessage("게임 시작", null);
                } else {
                    server.broadcastMessage("상대방: " + message, this);
                }
            }
        } catch (IOException e) {

        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            server.broadcastMessage("User" + userNumber + "님이 퇴장하셨습니다.", this);
            server.removeClient(this);
        }
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
