import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if(node == null) {
            size++;
            return new Node(key, value);
        }
        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }
        else {
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node min = minimum(node.right);
            min.left = node.left;
            min.right = removeMin(node.right);
            node.left = null;
            node.right = null;
            return min;
        }
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        else
            return minimum(node.left);
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        if(node == null)
            return null;
        else
            return node.value;
    }

    private Node getNode(Node node, K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else
            return node;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exists!");
        else
            node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args){
        System.out.println("Pride and predjudice");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)){
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for(String word: words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequence of PRIDE: " + map.get("pride"));
            System.out.println("Frequence of PREJUDICE: " + map.get("prejudice"));
        }
    }
}
