public class IndexMinHeap<Item extends Comparable> {

    private Item[] data;
    private int[] indexes;
    private int[] reverse;
    private int count;
    private int capacity;

    public IndexMinHeap(int capacity){
        data = (Item[])new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        count = 0;
        this.capacity =capacity;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void insert(int i, Item item){
        assert count + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;

        // 再插入一个新元素前,还需要保证索引i所在的位置是没有元素的。
        assert !contains(i);
        i++;
        data[i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1;
        count++;
        shiftUp(count);
    }

    public Item extractMin(){
        assert count > 0;
        Item ret = data[indexes[1]];
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);
        return ret;
    }

    public int extractMinIndex(){
        assert count > 0;

        int ret = indexes[1] - 1;
        swapIndexes( 1 , count );
        reverse[indexes[count]] = 0;
        count --;
        shiftDown(1);

        return ret;
    }

    public Item getMin(){
        assert count > 0;
        return data[indexes[1]];
    }

    public int getMinIndex(){
        assert count > 0;
        return indexes[1]-1;
    }

    public void change(int i, Item newItem){
        assert contains(i);
        i++;
        data[i] = newItem;
        shiftUp(reverse[i]);
        shiftDown(reverse[i]);
    }

    public boolean contains(int i){
        assert  i + 1 >= 1 && i + 1 <= capacity;
        return reverse[i + 1] != 0;
    }

    private void swapIndexes(int i, int j){
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;

        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;
    }

    private void shiftUp(int k){
        while(k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) > 0){
            swapIndexes(k, k / 2);
        }
    }

    private void shiftDown(int k){
        while(2 * k <= count){
            int j = 2 * k;
            if(j + 1 <= count && data[indexes[j]].compareTo(data[indexes[j + 1]]) < 0)
                j++;
            if(data[indexes[k]].compareTo(data[indexes[j]]) <= 0)
                break;
            swapIndexes(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {

        int N = 1000000;
        IndexMinHeap<Integer> indexMinHeap = new IndexMinHeap<Integer>(N);
        for( int i = 0 ; i < N ; i ++ )
            indexMinHeap.insert( i , (int)(Math.random()*N) );

    }
}
