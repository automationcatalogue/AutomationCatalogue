package utilities;
import java.util.Scanner;

public class Demo {
    public static void main(String args[]) {
        // Your code goes here
		/* * You are given T(number of test cases) integer arrays.
		* For each array A, you have to find the value of absolute difference between
		 the counts of * even and odd elements in the array. * */

        System.out.println("Enter Number of Arrays");
        Scanner s1 = new Scanner(System.in);
        int numberArray = s1.nextInt();

        for(int i=0;i<numberArray;i++){
            System.out.println("Enter the Size of the Array");
            Scanner s2 = new Scanner(System.in);
            int size = s2.nextInt();

            int a[] = new int[size];
            System.out.println("Enter the Array Values");

            for(int j=0;j<a.length;j++){
                Scanner s3 = new Scanner(System.in);
                a[j] = s3.nextInt();
            }
            //To Read data from array
            System.out.println("Array Data is:");
            for(int k=0;k<a.length;k++){
                System.out.print(a[k]+",");
            }
            //To get the difference sum of Even numbers and Sum of Odd Numbers
            int evenCount=0, oddCount=0, diff=0;
            for(int k=0;k<a.length;k++){
                if(a[k]%2==0){
                    evenCount++;
                }else {
                    oddCount++;
                }
            }
            System.out.println("Difference is:"+ (oddCount-evenCount));
        }
    }
}
