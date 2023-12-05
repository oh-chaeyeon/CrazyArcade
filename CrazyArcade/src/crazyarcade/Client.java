package crazyarcade;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {
    private Waitroom waitroom;
    private Login loginFrame;
    private PrintWriter out;

    public Client() {
    	showLoginScreen();
    }
    
    private void showLoginScreen() {
        loginFrame = new Login("Login", this::createChatScreen);
    }

    private void createChatScreen() {
    	loginFrame.setVisible(false);

        waitroom = new Waitroom("대기실");

        waitroom.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 54321);
            waitroom.appendText("게임에 접속하였습니다..\n");

            out = new PrintWriter(socket.getOutputStream(), true);

            SwingWorker<Void, String> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        if (message.equals("다른 유저를 기다립니다..")) {
                            SwingUtilities.invokeLater(() -> {
                                waitroom.client1Image("/image/bazzi_front.png");
                            });
                        }
                        
                        else if (message.equals("모든 유저가 접속하였습니다.")) {
                            SwingUtilities.invokeLater(() -> {
                            	waitroom.client1Image("/image/bazzi_front.png");
                                waitroom.client2Image("/image/woonie_front.png");
                            });
                        }
                        publish(message);
                    }
                    return null;
                }

                @Override
                protected void process(List<String> chunks) {
                    for (String message : chunks) {
                        waitroom.appendText(message + "\n");
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
        SwingUtilities.invokeLater(() -> {
            new Client();
        });
    }
}
