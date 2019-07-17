// This code is to create test cases for problem3.
// case3.txt is output file.
// case3.txt grammer
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

public class Case1_3 {
  public static void main(String[] args) {
    Case1_3 obj = new Case1_3();
    int N = (int)(Math.random()*999)+2;
    int M = (int)(Math.random()*500)+1;
    int Q = (int)(Math.random()*101);
    obj.makeCase(N, M, 0, Q);
  }

  public void makeCase(int N, int M, int P, int Q) {
    int x, y;
    for(int i=0; i<2; i++) {
      System.out.println("Case" + (i+1));
      System.out.println(N + " " + M + " " + P + " " + Q);
      for(int j=0; j<N; j++) {
        x = (int)(Math.random()*10001);
        y = (int)(Math.random()*10001);
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

      for(int j=0; j<Q/2; j++) {
        int i1 = (int)(Math.random()*N);
        int i2 = (int)(Math.random()*N);
        Collections.shuffle(list_M);
        System.out.println(list_M.get(i1) + " " + list_M.get(i2) + " " + 1);
      }
      for(int j=Q/2; j<Q; j++) {
        int i1 = (int)(Math.random()*N);
        int i2 = (int)(Math.random()*N);
        Collections.shuffle(list_M);
        System.out.println("C" + list_M.get(i1) + " " + list_M.get(i2) + " " + 1);
      }

      System.out.println();
    }
  }
}
