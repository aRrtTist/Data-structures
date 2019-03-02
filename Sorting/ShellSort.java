import java.util.Arrays;

public class ShellSort {

    private ShellSort(){}

    public static void sort(Comparable[] arr){
        int n = arr.length;
        // 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;
        while (h < n/3) h = 3*h + 1;
        while(h > 0){
            for(int i = h; i < n; i ++){
                Comparable cur = arr[i];
                int j;
                for(j = i; j >= h; j -= h){
                    if(cur.compareTo(arr[j - h]) < 0)
                        arr[j] = arr[j - h];
                    else
                        break;
                }
                arr[j] = cur;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0, 10000);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        SortTestHelper.testSort("InsertionSort", arr1);
        SortTestHelper.testSort("SelectionSort", arr2);
        SortTestHelper.testSort("BubbleSort", arr3);
        SortTestHelper.testSort("ShellSort", arr4);
    }
}
