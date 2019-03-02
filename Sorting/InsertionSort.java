import java.util.Arrays;

public class InsertionSort {

    private InsertionSort(){}

    public static void sort(Comparable[] arr){
        int n = arr.length;
        for(int i = 1; i < n; i++){
            Comparable cur = arr[i];
            int j;
            for(j = i; j > 0; j--) {
                if(arr[j - 1].compareTo(cur) > 0)
                    arr[j] = arr[j - 1];
                else
                    break;
            }
            arr[j] = cur;
        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, 10000);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("InsertionSort", arr1);
        SortTestHelper.testSort("SelectionSort", arr2);
    }
}
