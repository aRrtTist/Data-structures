import java.util.Arrays;

public class QuickSort {

    private QuickSort(){}

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void insertionSort(Comparable[] arr, int l, int r){
        for(int i = l + 1; i <= r; i++){
            Comparable cur = arr[i];
            int j;
            for(j = i; j > l; j--){
                if(cur.compareTo(arr[j - 1]) < 0)
                    arr[j] = arr[j - 1];
                else
                    break;
            }
            arr[j] = cur;
        }
    }

    private static int partition(Comparable[] arr, int l, int r){
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        Comparable v = arr[l];
        int low = l, high = r;
        while(low < high){
            while(low < high && arr[high].compareTo(v) >= 0)
                high--;
            arr[low] = arr[high];
            while(low < high && arr[low].compareTo(v) <= 0)
                low++;
            arr[high] = arr[low];
        }
        arr[low] = v;
        return low;
    }

    private static void quickSort(Comparable[] arr, int l, int r){
        if( l >= r)
            return;
        if( r - l <= 15){
            insertionSort(arr, l, r);
            return;
        }
        int mid = partition(arr, l, r);
        quickSort(arr, l, mid- 1);
        quickSort(arr,mid + 1, r);
    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 10000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, 0);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("MergeSort", arr1);
        SortTestHelper.testSort("QuickSort", arr2);
    }
}
