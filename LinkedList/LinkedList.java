public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyhead;
    private int size;

    public LinkedList(){
        dummyhead = new Node(null, null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){
        if(index < 0 || index >size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
            Node prev = dummyhead;
            for (int i = 0; i < index; i++)
                prev = prev.next;
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
            prev.next = new Node(e, prev.next);
            size++;
    }

    public void addLast(E e){
        add(size, e);
    }

    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        Node cur = dummyhead.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Update failed. Illegal index.");
        Node cur = dummyhead.next;
        for(int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        Node prev = dummyhead;
        for(int i = 0; i < index; i++)
            prev = prev.next;
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public boolean contains(E e){
        Node node = dummyhead.next;
        while(node != null){
            if(node.e.equals(e))
                return true;
            node = node.next;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = dummyhead.next;
        while (cur != null){
            res.append(cur.e + " -> ");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
