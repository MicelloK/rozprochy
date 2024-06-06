package org.example;

import org.apache.zookeeper.*;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class ZookeeperWatcher implements Watcher {
    private final ZooKeeper zk;
//    private final Map<String, Process> externalProcesses = new LinkedHashMap<>();
    private final Map<String, JLabel> labels = new LinkedHashMap<>();
    private final Map<String, JFrame> frames = new LinkedHashMap<>();
    private final Map<String, Integer> descendants = new LinkedHashMap<>();
    private final String pathToStartNode;
//    private final String pathToApp;

    public ZookeeperWatcher(String pathToStartNode, String pathToApp) throws IOException {
        this.pathToStartNode = pathToStartNode;
//        this.pathToApp = pathToApp;
        String ZK_HOST = "localhost:2181";
        zk = new ZooKeeper(ZK_HOST, 3000, this);
    }

    public void start() {
        try {
            zk.addWatch(pathToStartNode, AddWatchMode.PERSISTENT_RECURSIVE);
        } catch (KeeperException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case NodeCreated:
                createNodeHandler(event.getPath());
                break;
            case NodeDeleted:
                deleteNodeHandler(event.getPath());
                break;
            default:
                break;
        }
    }

    private void createNodeHandler(String path) {
        String[] nodes = path.split("/");
        if (path.startsWith(pathToStartNode) && Objects.equals(nodes[nodes.length - 1], "a")) {
//            try {
//                Process externalAppProcess = Runtime.getRuntime().exec(pathToApp);
                JFrame frame = new JFrame("ZooKeeper Watcher");
                JLabel label = new JLabel("PATH: " + path + " | Number of children: 0", SwingConstants.CENTER);
                labels.put(path, label);
                frames.put(path, frame);
                descendants.put(path, 0);
                frame.add(label);
                frame.setSize(300, 200);
                frame.setVisible(true);
//                externalProcesses.put(path, externalAppProcess);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
        }
        String currentPath = "";
        for (String node : nodes) {
            if (currentPath.equals("/")) {
                currentPath += node;
            } else {
                currentPath += "/" + node;
            }
            if (labels.containsKey(currentPath) && descendants.containsKey(currentPath) && !currentPath.equals(path)) {
                descendants.put(currentPath, descendants.get(currentPath) + 1);
                labels.get(currentPath).setText("PATH " + currentPath + " | Number of descendants: " + descendants.get(currentPath));
            }
        }
    }

    private void deleteNodeHandler(String path) {
//        if (externalProcesses.get(path) != null) {
//            externalProcesses.get(path).destroy();
//        }
        if (frames.containsKey(path)) {
            frames.get(path).setVisible(false);
            frames.remove(path);
            labels.remove(path);
            descendants.remove(path);
        }

        String[] nodes = path.split("/");
        String currentPath = "";
        for (String node : nodes) {
            if (currentPath.equals("/")) {
                currentPath += node;
            } else {
                currentPath += "/" + node;
            }
            if (labels.containsKey(currentPath) && descendants.containsKey(currentPath)) {
                descendants.put(currentPath, descendants.get(currentPath)-1);
                labels.get(currentPath).setText("PATH " + currentPath + "\nNumber of descendants: " + descendants.get(currentPath));
            }
        }

    }

    private void printTree(String path, int level) throws InterruptedException, KeeperException {
        List<String> children = zk.getChildren(path, false);
        for (String child : children) {
            String childPath;
            if (path.equals("/")) {
                childPath = path + child;
            } else {
                childPath = path + "/" + child;
            }
            System.out.println(" ".repeat(level) + child);
            printTree(childPath, level + 1);
        }
    }

    public void printTreeStruct(String path) {
        try {
            System.out.println("--- Tree structure for '" + path + "' ---");
            printTree(path, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNode(String path) {
        try {
            zk.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("--- Node " + path + " created ---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteNode(String path, boolean deleteAll) {
        try {
            if (deleteAll) {
                List<String> children = zk.getChildren(path, true);
                for (String child : children) {
                    deleteNode(path + "/" + child, true);
                }
            }
            zk.delete(path, -1);
            System.out.println("--- Node " + path + " deleted ---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
