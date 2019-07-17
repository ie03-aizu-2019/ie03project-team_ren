// This code is to create test cases for problem1.
// case1.txt is output file.
// case1.txt grammer
// ----------------------------------------------
//   Case No.
//   Value_of_N Value_of_M Value_of_P Value_of_Q
//   x0 y0
//   .  .
//   x_n-1 y_n-1
//   b0 e0
//   .  .
//   b_n-1 e_n-1
//   Case . . .
//   .
//   .
//   .
// -----------------------------------------------

import java.util.*;
import java.io.*;

public class Case1_1 {
  public static void main(String[] args) {
    Case1_1 obj = new Case1_1();

    System.out.println("N=3-------");
    obj.makeCase(3, 2, 0, 0);
    System.out.println("N=4-------");
    obj.makeCase(4, 2, 0, 0);
  }

  public void makeCase(int N, int M, int P, int Q) {
    int x, y;
    for(int i=0; i<5; i++) {
      System.out.println("Case" + (i+1));
      System.out.println(N + " " + M + " " + P + " " + Q);
      for(int j=0; j<N; j++) {
        x = (int)(Math.random()*1001);
        y = (int)(Math.random()*1001);
        System.out.println(x + " " + y);
      }

      ArrayList<Integer> list_M = new ArrayList<Integer>();
      for(int j=1; j<=N; j++) { list_M.add(j); }
      Collections.shuffle(list_M);
      if(N == 3) {
        System.out.println(list_M.get(0) + " " + list_M.get(1));
        System.out.println(list_M.get(1) + " " + list_M.get(2));
      } else {
        System.out.println(list_M.get(0) + " " + list_M.get(1));
        System.out.println(list_M.get(2) + " " + list_M.get(3));
      }
    }
  }
}
