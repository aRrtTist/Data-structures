import java.util.Arrays;

public class HeapSort {

    private HeapSort(){}

    private static int leftChild(int k){
        return 2 * k + 1;
    }

    private static void siftDown(Comparable[] arr, int n, int k){
        while(leftChild(k) < n) {
            int childIndex = leftChild(k);
            if (childIndex + 1 < n &&arr[childIndex].compareTo(arr[childIndex + 1]) < 0)
                childIndex++;
            if(arr[k].compareTo(arr[childIndex]) < 0){
                Comparable temp = arr[k];
                arr[k]  = arr[childIndex];
                arr[childIndex] = temp;
                k = childIndex;
            }
            else
                return;
        }
    }

    public static void sort(Comparable[] arr){
//        MaxHeap<Comparable> heap = new MaxHeap<>(arr);
//        for(int i = arr.length - 1; i >=0; i--)
//            arr[i] = heap.extractMax();

        for(int i = (arr.length - 2) / 2; i >= 0; i--)
            siftDown(arr, arr.length, i);
        for(int i = arr.length - 1; i >= 0; i--){
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    private static void swap(Object[] arr, int i, int j){
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 10000;
        // 存在大量相同元素时三路快排明显快于普通快排
//        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, 0);
        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, Integer.MAX_VALUE);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("QuickSort", arr1);
        SortTestHelper.testSort("QuickSort3Ways", arr2);
        SortTestHelper.testSort("HeapSort", arr3);
    }
}
