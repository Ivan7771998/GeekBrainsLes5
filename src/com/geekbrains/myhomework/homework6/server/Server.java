package com.geekbrains.myhomework.homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Server {

    public static Thread sendClient = null;
    public static Thread answerClient = null;

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream in;
        DataOutputStream out;


        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запустился");

            socket = server.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            answerClient = new Thread(() -> {
                while (true) {
                    String str = null;
                    try {
                        str = in.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Клиент отключился");
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                    System.out.println("Клиент: " + str);
                }
            });

            sendClient = new Thread(() -> {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String str = scanner.nextLine();
                    try {
                        if (!str.trim().equals(""))
                        out.writeUTF(str);
                        if (str.equals("/end")) {
                            System.out.println("Server отключен!");
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            answerClient.start();
            sendClient.start();

            try {
                answerClient.join();
                sendClient.join();
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
            try {
                Objects.requireNonNull(server).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
