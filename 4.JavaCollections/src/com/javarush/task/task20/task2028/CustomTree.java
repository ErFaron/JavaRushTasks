package com.javarush.task.task20.task2028;

        import java.io.Serializable;
        import java.util.*;
        import java.util.function.Consumer;
        import java.util.function.Predicate;
        import java.util.function.UnaryOperator;
        import java.util.stream.Stream;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable{
    Entry<String> root = new Entry<>(null);

    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i <= 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Size: " + ((CustomTree) list).size());
        list.remove("2");
        System.out.println("Size: " + ((CustomTree) list).size());

        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        //list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public boolean add(String s) {
        if (s == null)
            return false;
        Entry<String> rootElement;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            rootElement = queue.poll();
            rootElement.checkChildren();
            if(rootElement.availableToAddLeftChildren){
                rootElement.leftChild = new Entry<>(s);

                //System.out.println(rootElement.leftChild.elementName);
                return true;
            }
            else
                queue.offer(rootElement.leftChild);

            if(rootElement.availableToAddRightChildren){
                rootElement.rightChild = new Entry<>(s);
                //System.out.println(rootElement.rightChild.elementName);
                return true;
            }
            else
                queue.offer(rootElement.rightChild);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            return false;

        String s = (String) o;

        Entry<String> rootElement;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            rootElement = queue.poll();
            rootElement.checkChildren();
            if(!rootElement.availableToAddLeftChildren) {
                if (rootElement.leftChild.elementName.equals(s)) {
                    rootElement.leftChild = null;
                    return true;
                } else
                    queue.offer(rootElement.leftChild);
            }

            if(!rootElement.availableToAddRightChildren) {
                if (rootElement.rightChild.elementName.equals(s)) {
                    rootElement.rightChild = null;
                    return true;
                } else
                    queue.offer(rootElement.rightChild);
            }
        }
        return false;
    }

    public String getParent(String s) {
        if (root == null || s == null || s.equals(root.elementName))
            return null;

        Entry<String> rootElement;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            rootElement = queue.poll();
            rootElement.checkChildren();
            if(rootElement.leftChild != null) {
                if (rootElement.leftChild.elementName.equals(s)) {
                    return rootElement.elementName;
                } else
                    queue.offer(rootElement.leftChild);
            }

            if(rootElement.rightChild != null) {
                if (rootElement.rightChild.elementName.equals(s)) {
                    return rootElement.elementName;
                } else
                    queue.offer(rootElement.rightChild);
            }
        }
        return null;
    }

    @Override
    public int size() {
        Entry<String> rootElement;
        Queue<Entry<String>> queue = new LinkedList<>();

        queue.offer(root);
        int count = 0;

        while (!queue.isEmpty()) {
            rootElement = queue.poll();
            rootElement.checkChildren();
            if(rootElement.leftChild != null) {
                queue.offer(rootElement.leftChild);
                count++;
            }

            if(rootElement.rightChild != null) {
                queue.offer(rootElement.rightChild);
                count++;
            }
        }
        return count;
    }



    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren(){
            //availableToAddLeftChildren = leftChild != null ? false : true;
            //availableToAddRightChildren = rightChild != null ? false : true;

            if(leftChild != null)
                availableToAddLeftChildren = false;
            if(rightChild != null)
                availableToAddRightChildren = false;
        }

        boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator operator) {

    }

    @Override
    public void sort(Comparator c) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return false;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
