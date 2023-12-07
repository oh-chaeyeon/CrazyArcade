package crazyarcade;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private static Server instance;
    private Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());
    private int userCounter = 0;
    private ServerSocket serverSocket;

    private Server() {
        startServer();
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(54321);
            System.out.println("서버가 시작되었습니다.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("새로운 User가 입장하셨습니다.");

                userCounter++;
                ClientHandler clientHandler = new ClientHandler(clientSocket, userCounter, this);
                clients.add(clientHandler);

                if (clients.size() == 1) {
                    clientHandler.sendMessage("다른 유저를 기다립니다..");
                } else if (clients.size() == 2) {
                    broadcastMessage("모든 유저가 접속하였습니다.", null);
                    broadcastMessage("", null);
                }

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            closeServerSocket();
        }
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("User" + clientHandler.getUserNumber() + " 연결이 종료되었습니다.");
    }

    private void closeServerSocket() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message, ClientHandler excludeClient) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != excludeClient) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = Server.getInstance();
    }
}
