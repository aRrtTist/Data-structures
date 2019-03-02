import java.util.Arrays;

public class MergeSortBU {

    private MergeSortBU(){}

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

    public static void sort(Comparable[] arr){
        int n = arr.length;
        for(int sz = 1; sz < n; sz += sz){
            for(int i = 0; i  + sz < n; i += sz + sz){
                if(sz + sz <= 15) {
                    insertionSort(arr, i, Math.min(i + sz + sz - 1, n - 1));
                    continue;
                }
                if(arr[i + sz - 1].compareTo(arr[i + sz]) > 0)
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
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
        SortTestHelper.testSort("MergeSortBU", arr2);
        SortTestHelper.testSort("ShellSort", arr3);
        SortTestHelper.testSort("MergeSort", arr4);
    }
}
