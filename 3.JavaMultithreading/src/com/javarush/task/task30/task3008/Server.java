package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        System.out.println("Введите порт сервера.");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            System.out.println("Сервер запущен.");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Ошибка получения серверного сокета.");
        }

    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name = null;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if (entry.getKey() != userName) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                StringBuffer textMessage = new StringBuffer();
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    textMessage.append(userName + ": " + message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, textMessage.toString()));
                } else {
                    System.out.println("Ошибка: в сообщении должен быть текст.");
                }
            }
        }

        public void run() {
            ConsoleHelper.writeMessage("Соединение установлено: " + socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException a) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом." + a.getMessage());
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
            ConsoleHelper.writeMessage("Соединение закрыто.");
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Сообщения не отправлены.");
            }
        }
    }
}
