import java.util.*;

public class problem1_2 extends problem1_1 {
  public problem1_2() { super(); }

  // ソートをArraysクラスのsortメソッドで実装
  public void sort() {
    Collections.sort(answer, new Comparator<Answer>() {
      public int compare(Answer a, Answer b) {
        if(a.getX() != b.getX()) { return Double.compare(a.getX(), b.getX()); }
        else { return Double.compare(a.getY(), b.getY()); }
      }
    });

    // 交差地点に番号を割り当てる
    for(int i=0; i<answer.size(); i++) { answer.get(i).setId(i); }
  }

  public static void main(String[] args) {
    problem1_2 obj = new problem1_2();
    obj.compute();
    obj.sort();
    obj.output();
  }
}
