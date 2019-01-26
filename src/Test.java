import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int num01, num02, times = 0;
        System.out.println("请分别输入两个数组的长度：");
        Scanner in = new Scanner(System.in);
        num01 = in.nextInt();
        num02 = in.nextInt();
        System.out.println("请分别输入两个数组元素：");
        int[] array1 = new int[num01];
        int[] array2 = new int[num02];
        for (int N1 = 0; N1 < num01; N1++) {
            System.out.println("请输入数组1的元素：");
            array1[N1] = in.nextInt();
        }
        for (int N2 = 0; N2 < num02; N2++) {
            System.out.println("请输入数组2的元素：");
            array2[N2] = in.nextInt();
        }
        in.close();
        for (int count01 = 0; count01 < num01; count01++) {
            for (int count02 = 0; count02 < num02; count02++) {
                if (array1[count01] == array2[count02])
                    times += 1;
            }
        }
        if (times == 0) {
            System.out.println("两个数组交集的交集的元素的个数为：None");
        } else {
            System.out.println("两个数组交集的交集的元素的个数为：" + times);
        }
    }

}
