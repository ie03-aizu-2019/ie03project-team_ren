import java.util.*;

public class problem1_1 {

  // 各点の座標(x, y)を持つクラス
  class N { int x, y; }

  // 各道路の情報を持つクラス
  class M { int p, q; }

  Scanner sc = new Scanner(System.in);
  int N,M,P,Q;
  double answerX, answerY;
  N[] nList;
  M[] roads;

  public problem1_1() {
    N = sc.nextInt();
    M = sc.nextInt();
    P = sc.nextInt();
    Q = sc.nextInt();

    nList = new N[N+1];
    roads = new M[M];

    for(int i=1; i<=N; i++) {
      nList[i] = new N();
      nList[i].x = sc.nextInt();
      nList[i].y = sc.nextInt();
    }
    for(int i=0; i<M; i++) {
      roads[i] = new M();
      roads[i].p = sc.nextInt();
      roads[i].q = sc.nextInt();
    }

    sc.close();
  }

  public void compute() {

    double y1 = nList[roads[0].q].y - nList[roads[0].p].y;
    double x2 = nList[roads[1].q].x - nList[roads[1].p].x;
    double x1 = nList[roads[0].q].x - nList[roads[0].p].x;
    double y2 = nList[roads[1].p].y - nList[roads[1].q].y;
    double formulaA = Math.abs(((x1) * (y2)) + ((x2) * (y1)));
    double s, t, xp, yp, yPq;

    if(formulaA == 0.0){
      output("NA");
      return;
    } else {
      xp = nList[roads[1].p].x - nList[roads[0].p].x;
      yp = nList[roads[1].p].y - nList[roads[0].p].y;
      yPq = nList[roads[0].p].y - nList[roads[0].q].y;
      s = (y2*xp + x2*yp) * 1.0 / formulaA;
      t = (yPq*xp + x1*yp) * 1.0 / formulaA;
    }

    if((s>=0.0 && s<=1.0) && (t>=0.0 && t<=1.0)) {
      answerX = nList[roads[0].p].x + x1 * s;
      answerY = nList[roads[0].p].y + y1 * s;
      cross();
    } else {
      output("NA");
      return;
    }
  }

  // 交差地点が端点であるかの確認
  public void cross() {
    for(int i=1; i<=N; i++) {
      if(answerX == nList[i].x && answerY == nList[i].y) {
        output("NA");
        return;
      } else {
        output(answerX, answerY);
        return;
      }
    }
  }

  public void output(double x, double y) { System.out.printf("%.5f %.5f\n",x,y); }
  public void output(String message) { System.out.println(message); }

  public static void main(String[] args) {
    problem1_1 obj = new problem1_1();
    obj.compute();
  }
}
