package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static ZookeeperWatcher zkWatcher;
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("`path` argument is required");
            return;
        }
        String pathToStartNode = "/";
        String pathToApp = args[0];

        try {
            zkWatcher = new ZookeeperWatcher(pathToStartNode, pathToApp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        zkWatcher.start();
        try {
            inputHandler();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void inputHandler() throws InterruptedException {
        System.out.println("Starting ZookeeperWatcher...");
        while (true) {
            System.out.print("> ");
            String[] choice = scanner.nextLine().split(" ");
            switch (choice[0]) {
                case "create":
                    if (choice.length > 1) {
                        zkWatcher.createNode(choice[1]);
                    } else {
                        System.out.println("create -path");
                    }
                    break;
                case "delete":
                    if (choice.length > 1) {
                        zkWatcher.deleteNode(choice[1], false);
                    } else {
                        System.out.println("delete -path");
                    }
                    break;
                case "deleteall":
                    if (choice.length > 1) {
                        zkWatcher.deleteNode(choice[1], true);
                    } else {
                        System.out.println("deleteall -path");
                    }
                    break;
                case "ls":
                    if (choice.length > 1) {
                        zkWatcher.printTreeStruct(choice[1]);
                    } else {
                        System.out.println("ls -path");
                    }
                    break;
                case "quit":
                    zkWatcher.close();
                    break;
                default:
                    System.out.println("create -path | delete -path | deleteall -path | ls -path | quit");
                    break;
            }
        }
    }
}