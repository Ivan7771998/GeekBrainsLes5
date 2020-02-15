package com.geekbrains.myhomework.homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    final static String IP_ADDRESS = "localhost";
    final static int PORT = 8189;

    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream in;
        DataOutputStream out;

        try {

            socket = new Socket(IP_ADDRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread answerServer = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            break;
                        }
                        System.out.println("Сервер: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread sendServer = new Thread(() -> {
                try {
                    while (true) {
                        Scanner scanner = new Scanner(System.in);
                        String str = scanner.nextLine();
                        if (str.equals("/end")) {
                            out.writeUTF(str);
                            break;
                        }
                        if (!str.trim().equals(""))
                            out.writeUTF(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            answerServer.start();
            sendServer.start();

            try {
                sendServer.join();
                answerServer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(socket).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
