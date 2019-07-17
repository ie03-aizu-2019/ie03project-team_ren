// This code is to create test cases for problem7.
// case7.txt is output file.

import java.util.*;
import java.io.*;

public class Case2_7 {
  public static void main(String[] args) {
    Case2_7 obj = new Case2_7();
    int N = (int)(Math.random()*999)+2;
    int M = (int)(Math.random()*(N-2))+1;
    int P = (int)(Math.random()*101);
    obj.makeCase(N, M, P, 0);
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

      for(int j=0; j<P; j++) {
        x = (int)(Math.random()*10001);
        y = (int)(Math.random()*10001);
        System.out.println(x + " " + y);
      }
      System.out.println();
    }
  }
}
