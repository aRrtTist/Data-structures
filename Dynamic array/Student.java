public class Student {
    private String name;
    private int score;

    public Student(String studentName, int studentScore){
        name = studentName;
        score = studentScore;
    }

    @Override
    public String toString(){
        return String.format("Student(name: %s, score: %d)", name, score);
    }

    public static void main(String[] args){
        Array<Student> arr = new Array<>();
        arr.addLast(new Student("Ruan Tian", 100));
        arr.addLast(new Student("Huang Dezhao", 80));
        arr.addLast(new Student("Wang meirong", 60));
        System.out.println(arr);
    }
}
