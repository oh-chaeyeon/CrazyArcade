package crazyarcade;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private JFrame loginFrame;
    private Waitroom waitroom;
    private PrintWriter out;
    private BufferedReader in;

    public Server() {
        loginFrame = new Login("Login", this::createChatScreen);
    }

    private void createChatScreen() {
        Point location = loginFrame.getLocation();
        loginFrame.setVisible(false);

        waitroom = new Waitroom("대기실 (User1)");
        waitroom.setLocation(location);
        waitroom.getSendButton().addActionListener(e -> sendMessage());
        startServer();
    }

    private void startServer() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(9999);
                SwingUtilities.invokeLater(() ->
                    waitroom.appendText("게임에 접속하였습니다. 다른 유저를 기다립니다..\n"));

                Socket clientSocket = serverSocket.accept();
                SwingUtilities.invokeLater(() ->
                    waitroom.appendText("User2가 입장하셨습니다.\n\n"));

                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                SwingWorker<Void, String> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        String message;
                        while ((message = in.readLine()) != null) {
                            publish(message);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<String> chunks) {
                        for (String message : chunks) {
                            waitroom.appendText("상대방: " + message + "\n");
                        }
                    }
                };
                worker.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void sendMessage() {
        String message = waitroom.getInputBox().getText();
        if (!message.isEmpty()) {
            waitroom.appendText("나: " + message + "\n");
            out.println(message);
            waitroom.getInputBox().setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }
}
