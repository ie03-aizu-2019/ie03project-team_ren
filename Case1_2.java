// This code is to create test cases for problem2.
// case2.txt is output file.
// case2.txt grammer
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

public class Case1_2 {
  public static void main(String[] args) {
    Case1_2 obj = new Case1_2();
    int N = (int)(Math.random()*199)+2;
    int M = (int)(Math.random()*100)+1;
    obj.makeCase(N, M, 0, 0);
  }

  public void makeCase(int N, int M, int P, int Q) {
    int x, y;
    for(int i=0; i<2; i++) {
      System.out.println("Case" + (i+1));
      System.out.println(N + " " + M + " " + P + " " + Q);
      for(int j=0; j<N; j++) {
        x = (int)(Math.random()*1001);
        y = (int)(Math.random()*1001);
        System.out.println(x + " " + y);
      }
      ArrayList<Integer> list_M = new ArrayList<Integer>();
      for(int j=1; j<=N; j++) { list_M.add(j); }
      int co = 0;
      while(true) {
        if(co == M*2) break;
        int idx = (int)(Math.random()*N);
        Collections.shuffle(list_M);
        if(co%2 == 0)
          System.out.print(list_M.get(idx) + " ");
        else
          System.out.println(list_M.get(idx));
        co++;
      }
      System.out.println();
    }
  }
}
