import java.util.Scanner;

public class StudentGreadeCalculator {
   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the number of subject: ");
      int n = sc.nextInt();
      int total = 0;
      for (int i = 0; i < n; i++) {
         System.out.println("Entr the mark of subject " + i + " out of 100");
         int mark = sc.nextInt();
         // validiate marks
         if (mark < 0 || mark > 100) {
            System.out.println("Invalild number please enter again !!");
            i--;
            continue;
         }
         total += mark;
      }

      double percentage = (double) total / n;
      String gread;

      if (percentage >= 90) {
         gread = "A";
      } else if (percentage < 90 || percentage >= 75) {
         gread = "B";
      } else if (percentage < 75 || percentage >= 60) {
         gread = "C";
      } else if (percentage < 60 || percentage >= 45) {
         gread = "D";
      } else {
         gread = "Fail";
      }

      System.out.println("Total Marks = " + total);
      System.out.println("Average percentage = " + percentage + " %");
      System.out.println("Grade = " + gread);
      sc.close();

   }
}
