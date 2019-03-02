import java.util.ArrayList;

public class MaxHeap<E extends Comparable> {

    private ArrayList<E> data;

    public MaxHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MaxHeap(){
        data = new ArrayList<>();
    }

    public MaxHeap(E[] arr){
        data = new ArrayList<>();
        for(int i = 0; i < arr.length; i++)
            data.add(arr[i]);
        for(int i = parent(data.size() - 1); i >= 0; i--)
            siftDown(i);
    }

    public int getSize(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    private void siftUp(int index){
        int parentIndex = parent(index);
        while(index > 0 && data.get(index).compareTo(data.get(parentIndex)) > 0){
            E temp = data.get(index);
            data.set(index, data.get(parentIndex));
            data.set(parentIndex, temp);
            index = parentIndex;
        }
    }

    private void siftDown(int k){
        while(leftChild(k) < data.size()) {
            int childIndex = leftChild(k);
            if (childIndex + 1 < data.size() &&data.get(childIndex).compareTo(data.get(childIndex + 1)) < 0)
                childIndex++;
            if(data.get(k).compareTo(data.get(childIndex)) < 0){
                E temp = data.get(k);
                data.set(k, data.get(childIndex));
                data.set(childIndex, temp);
                k = childIndex;
            }
            else
                return;
        }
    }

    public void add(E e){
        data.add(e);
        siftUp(data.size() - 1);
    }

    public E findMax(){
        if(data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    public E extractMax(){
        E ret = findMax();
        data.set(0, data.get(data.size() -1));
        data.remove(data.size() - 1);
        siftDown(0);
        return ret;
    }

    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
