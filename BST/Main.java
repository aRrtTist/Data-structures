import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);
//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNR();
//        System.out.println();
//
//        bst.inOrder();
//        System.out.println();
//
//        bst.inOrderNR();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();
//
//        bst.levelOrder();
//        System.out.println();
//
//        bst.postOrderNR();
//        System.out.println();
//        System.out.println(bst);

        bst.remove(3);
        System.out.println(bst);
//        bst = new BST<>();
//        Random random = new Random();
//        int n = 1000;
//        for(int i = 0; i < n; i++)
//            bst.add(random.nextInt(100000));
//        ArrayList<Integer> nums1 = new ArrayList<>();
//        while (!bst.isEmpty())
//            nums1.add(bst.removeMin());
//        System.out.println(nums1);
//        for(int i = 1; i < nums1.size(); i++)
//            if (nums1.get(i - 1) > nums1.get(i))
//                throw new IllegalArgumentException("Error");
//        System.out.println("removemax test completed.");
    }
}
