import java.util.Arrays;

public class MergeSort {

    private MergeSort(){}

    public static void sort(Comparable[] arr){
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Comparable[] arr, int l, int r){
        if(r - l <= 15) {
            insertionSort(arr, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if(arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r);
    }

    private static void merge(Comparable[] arr, int l, int mid, int r){
        Comparable[] t = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;
        for(int k = l; k <= r; k++){
            if(i >mid){
                arr[k] = t[j - l];
                j++;
            }
            else if( j > r){
                arr[k] = t[i - l];
                i++;
            }
            else if(t[i - l].compareTo(t[j - l]) < 0){
                arr[k] = t[i - l];
                i++;
            }
            else {
                arr[k] = t[j - l];
                j++;
            }
        }
    }

    private static void insertionSort(Comparable[] arr, int l, int r){
        for(int i = l + 1; i <= r; i++){
            Comparable cur = arr[i];
            int j;
            for(j = i; j > l; j--) {
                if (cur.compareTo(arr[j - 1]) < 0)
                    arr[j] = arr[j - 1];
                else
                    break;
            }
            arr[j] = cur;
        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 1000000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, Integer.MAX_VALUE);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
//        SortTestHelper.testSort("InsertionSort", arr1);
//        SortTestHelper.testSort("SelectionSort", arr2);
        SortTestHelper.testSort("ShellSort", arr3);
        SortTestHelper.testSort("MergeSort", arr4);
    }
}
