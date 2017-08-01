package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("");

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String newElement) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            currentNode.checkChildren();
            if (currentNode.isAvailableToAddChildren()) {
                if (currentNode.availableToAddLeftChildren) {
                    currentNode.leftChild = new Entry<>(newElement);
                    currentNode.leftChild.parent = currentNode;
                    return true;
                } else if (currentNode.availableToAddRightChildren) {
                    currentNode.rightChild = new Entry<>(newElement);
                    currentNode.rightChild.parent = currentNode;
                    return true;
                }
            } else {
                if (currentNode.leftChild != null) {
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null) {
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return false;
    }

    public boolean remove(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            if (currentNode.leftChild != null) {
                if (currentNode.leftChild.elementName.equals(s)) {
                    currentNode.leftChild = null;
                    return true;
                }
                queue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                if (currentNode.rightChild.elementName.equals(s)) {
                    currentNode.rightChild = null;
                    return true;
                }
                queue.offer(currentNode.rightChild);
            }
        }
        return false;
    }

    String getParent(String entryName) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            if (currentNode.elementName.equals(entryName)) {
                return currentNode.parent.elementName;
            } else {
                if (currentNode.leftChild != null) {
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null) {
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return null;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String name) {
            this.elementName = name;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) {
                this.availableToAddLeftChildren = false;
            } else {
                this.availableToAddLeftChildren = true;
            }
            if (rightChild != null) {
                this.availableToAddRightChildren = false;
            } else {
                this.availableToAddRightChildren = true;
            }
        }

        boolean isAvailableToAddChildren() {
            return (availableToAddLeftChildren || availableToAddRightChildren);
        }
    }

    @Override
    public int size() {
        int size = -1;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            size++;
            if (currentNode.leftChild != null) {
                queue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                queue.offer(currentNode.rightChild);
            }
        }
        return size;
    }

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 23; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        System.out.println(list.size());
//        ((CustomTree) list).remove("15");
//        System.out.println(list.size());
        ((CustomTree) list).remove("2");
        System.out.println(list.size());
    }
}