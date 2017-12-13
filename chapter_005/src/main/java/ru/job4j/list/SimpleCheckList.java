package ru.job4j.list;

public class SimpleCheckList<T> {


    public boolean hasCycle(Node<T> root) {
        Node<T>[] nodes = new Node[100];
        int index = 0;
        nodes[index++] = root;
        boolean isCycling = false;
        Node<T> tmpNode = root;
        while (!isCycling && tmpNode.next != null) {
            for (int i = 0; i < index; i++) {
                if (tmpNode.next == nodes[i]) {
                    isCycling = true;
                    return isCycling;
                }
            }
            nodes[index++] = tmpNode.next;
            tmpNode = tmpNode.next;
            if (index >= nodes.length - 1) {
                nodes = nodeListGroover(nodes);
            }

        }
        return isCycling;
    }
    /*
    Make more room for testing data
     */
    private Node<T>[] nodeListGroover(Node<T>[] list) {
        Node<T>[] newlist = new Node[list.length + 100];
        for (int index = 0; index < list.length; index++) {
            newlist[index] = list[index];
        }
        return newlist;
    }
}
