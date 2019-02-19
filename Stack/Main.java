import java.util.Random;

public class Main {

    private static double testStack(Stack<Integer>stack, int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0; i < opCount; i++)
            stack.pop();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 10000000;
        ArrayStack<Integer> stack =  new ArrayStack<>();
        double time = testStack(stack, opCount);
        LinkedListStack<Integer> stack1 =  new LinkedListStack<>();
        double time1 = testStack(stack1, opCount);
        System.out.println("ArrayStack time: " + time + "s");
        System.out.println("LinkedListStack time: " + time1 + "s");
    }
}
