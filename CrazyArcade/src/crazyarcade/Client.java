package crazyarcade;


import javax.swing.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client {
    private JFrame loginFrame;
    private Waitroom waitroom;
    private PrintWriter out;
    private BufferedReader in;

    public Client() {
        loginFrame = new Login("Login", this::createChatScreen);
    }

    private void createChatScreen() {
        Point location = loginFrame.getLocation();
        loginFrame.setVisible(false);

        waitroom = new Waitroom("대기실 (User2)");
        waitroom.setLocation(location);
        waitroom.getSendButton().addActionListener(e -> sendMessage());
        startClient();
    }

    private void startClient() {
        try {
            Socket socket = new Socket("localhost", 9999);
            waitroom.appendText("게임에 접속하였습니다..\n\n");

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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
        SwingUtilities.invokeLater(Client::new);
    }
}
