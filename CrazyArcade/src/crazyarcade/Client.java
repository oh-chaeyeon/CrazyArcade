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
    private String selectedMap;
    
    public Client() {
        showLoginScreen();
    }

    private void showLoginScreen() {
        loginFrame = new Login("Login", this::createChatScreen);
    }

    private void createChatScreen() {
    	Point loginWindowLocation = loginFrame.getLocation();
        loginFrame.setVisible(false);

        waitroom = new Waitroom("대기실");
        waitroom.setLocation(loginWindowLocation);
        
        waitroom.getMap1Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedMap = "Map1";
                waitroom.getMap1Button().setBackground(Color.BLUE);
                waitroom.getMap2Button().setBackground(Color.WHITE);
                sendMessageToServer("맵1 선택");
            }
        });

        waitroom.getMap2Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedMap = "Map2";
                waitroom.getMap2Button().setBackground(Color.BLUE);
                waitroom.getMap1Button().setBackground(Color.WHITE);
                sendMessageToServer("맵2 선택");
            }
        });

        waitroom.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        
        waitroom.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSelectedMap();
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
                        } else if (message.equals("모든 유저가 접속하였습니다.")) {
                            SwingUtilities.invokeLater(() -> {
                                waitroom.client1Image("/image/bazzi_front.png");
                                waitroom.client2Image("/image/woonie_front.png");
                            });
                        } else if (message.endsWith("맵1 선택")) {
                            SwingUtilities.invokeLater(() -> {
                                waitroom.getMap1Button().setBackground(Color.BLUE);
                                waitroom.getMap2Button().setBackground(Color.WHITE);
                                selectedMap = "Map1";
                            });
                        } else if (message.endsWith("맵2 선택")) {
                            SwingUtilities.invokeLater(() -> {
                                waitroom.getMap2Button().setBackground(Color.BLUE);
                                waitroom.getMap1Button().setBackground(Color.WHITE);
                                selectedMap = "Map2";
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
                        if (message.equals("게임 시작")) {
                        	startGame();
                        }
                    }
                }
            };
            worker.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendMessageToServer(String message) {
        out.println(message);
    }

    private void sendMessage() {
        String message = waitroom.getInputBox().getText();
        if (!message.isEmpty()) {
            waitroom.appendText("나: " + message + "\n");
            out.println(message);
            waitroom.getInputBox().setText("");
        }
    }
    
    private void startSelectedMap() {
        if (selectedMap != null) {
        	sendMessageToServer("게임 시작");
        }
    }

    private void startGame() {
    	Point location = waitroom.getLocationOnScreen();
        waitroom.setVisible(false);
        switch(selectedMap) {
        case "Map1":
            new Map1(location);
            break;
        case "Map2":
            new Map2(location);
            break;
        default:
            JOptionPane.showMessageDialog(waitroom, "맵을 선택하지 않았습니다.");
            waitroom.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Client();
        });
    }
}
