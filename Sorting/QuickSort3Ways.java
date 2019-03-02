import java.util.Arrays;

public class QuickSort3Ways {

    private QuickSort3Ways(){}

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void insertionSort(Comparable[] arr, int l, int r){
        for(int i = l + 1; i <= r; i++){
            Comparable v = arr[i];
            int j;
            for(j = i; j > l; j--){
                if(arr[j - 1].compareTo(v) > 0)
                    arr[j] = arr[ j - 1];
                else
                    break;
            }
            arr[j] = v;
        }
    }

    private static void quickSort3Ways(Comparable[] arr, int l, int r){
        if(r - l <= 15){
            insertionSort(arr, l, r);
            return;
        }
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        Comparable v = arr[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while( i < gt){
            if(arr[i].compareTo(v) == 0)
                i++;
            else if(arr[i].compareTo(v) < 0){
                swap(arr, i, lt + 1);
                lt++;
                i++;
            }
            else {
                swap(arr, gt - 1, i);
                gt--;
            }
        }
        swap(arr, l, lt);
        quickSort3Ways(arr, l, lt - 1);
        quickSort3Ways(arr, gt, r);
    }

    public static void sort(Comparable[] arr){
        quickSort3Ways(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 10000;
        // 存在大量相同元素时三路快排明显快于普通快排
//        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, 0);
        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, Integer.MAX_VALUE);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("MergeSort", arr1);
        SortTestHelper.testSort("QuickSort", arr2);
        SortTestHelper.testSort("QuickSort3Ways", arr3);
    }
}
