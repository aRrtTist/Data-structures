public class BubbleSort {

    //算法类不允许产生任何实例
    private BubbleSort(){}

    public static void sort(Comparable[] arr){
        int n = arr.length;
        boolean swaped = true;
        while(n > 1){
            for(int j = 0; j < n - 1; j++){
                if(arr[j].compareTo(arr[j + 1]) > 0){
                    swap(arr, j, j + 1);
                    swaped = false;
                }
            }
            if(swaped)
                return;
            n--;
        }
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("BubbleSort", arr);
    }
}
