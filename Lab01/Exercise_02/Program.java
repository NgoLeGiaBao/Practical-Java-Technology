import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;

public class Program {
    public static void main(String[] args) {
        int[] a = {5, 2, 9, 1};
        int[] b = {8, 3, 7, 4};

        System.out.println("Array a:");
        ArrayOutput.print(a);
        System.out.println("Array b:");
        ArrayOutput.print(b);

        int[] c = ArrayHandler.merge(a, b);
        System.out.println("Merged array c:");
        ArrayOutput.print(c);

        ArrayHandler.sort(c);
        System.out.println("Sorted array c:");
        ArrayOutput.print(c);

        ArrayOutput.write(c, "output.txt");
        System.out.println("Array c has been written to output.txt");
    }
}
