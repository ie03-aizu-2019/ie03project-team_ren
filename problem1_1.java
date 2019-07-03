import java.util.*;
import java.io.*;

public class problem1_1 {

  // 各点の座標(x, y)を持つクラス
  class N { int x, y; }
  // 各道路の情報を持つクラス
  class M { int p, q;
    //交差地点の座標を保持
    ArrayList<Double> pointx;
    ArrayList<Double> pointy;
  }
  // 最短経路探査に必要な情報を持つクラス
  class Q{
    int  s, d, k;
    int scheck, dcheck;
  }
  // 交差地点を持つクラス
  class Answer {
    double x, y;
    int id;
    public Answer(double a, double b) {
      id = 0;
      x = a;
      y = b;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public void setId(int number) { id = number; }
    public int getId() {return id;}
  }

  Scanner sc = new Scanner(System.in);
  int N,M,P,Q;
  double answerX, answerY;
  double x1, x2, y1, y2;

  N[] nList;
  M[] roads;
  Q[] sPath;
  ArrayList<Answer> answer = new ArrayList<Answer>();

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
      //1_3用追加
      roads[i].pointx = new ArrayList<Double>();
      roads[i].pointy = new ArrayList<Double>();
    }

    //1_3用の追加
    String str = new String();

    sPath = new Q[Q];

    for(int i=0; i<Q; i++) {
      sPath[i] = new Q();
      str = sc.next();
      if(isNum(String.valueOf(str.charAt(0))) == true){
        sPath[i].scheck = 1;
        sPath[i].s = Integer.parseInt(str);
      }
      else{
        sPath[i].scheck = 0;
        str = str.replace("C", "");
        sPath[i].s = Integer.parseInt(str);
      }
      str = sc.next();
      if(isNum(String.valueOf(str.charAt(0))) == true){
        sPath[i].dcheck = 1;
        sPath[i].d = Integer.parseInt(str);
      }
      else{
        sPath[i].dcheck = 0;
        str = str.replace("C", "");
        sPath[i].d = Integer.parseInt(str);
      }
      sPath[i].k = sc.nextInt();
    }
    sc.close();
  }

  public boolean isNum(String number) {
    try {
      Integer.parseInt(number);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public void compute() {
    for(int i=0; i<M-1; i++) {
      for(int j=i+1; j<M; j++) {
        y1 = nList[roads[i].q].y - nList[roads[i].p].y;
        x2 = nList[roads[j].q].x - nList[roads[j].p].x;
        x1 = nList[roads[i].q].x - nList[roads[i].p].x;
        y2 = nList[roads[j].p].y - nList[roads[j].q].y;
        double formula = Math.abs(((x1) * (y2)) + ((x2) * (y1)));
        double s, t, xp, yp, yPq;

        if(formula != 0.0) {
          xp = nList[roads[j].p].x - nList[roads[i].p].x;
          yp = nList[roads[j].p].y - nList[roads[i].p].y;
          yPq = nList[roads[i].p].y - nList[roads[i].q].y;
          s = (y2*xp + x2*yp) * 1.0 / formula;
          t = (yPq*xp + x1*yp) * 1.0 / formula;
          recordAnswer(i, s, t, j);
        }
      }
    }
  }

  public void recordAnswer(int i, double s, double t, int j) {
    if((s>0.0 && s<1.0) && (t>0.0 && t<1.0)) {
      answerX = nList[roads[i].p].x + x1 * s;
      answerY = nList[roads[i].p].y + y1 * s;
      answer.add(new Answer(answerX, answerY));
      roads[i].pointx.add(answerX);
      roads[j].pointx.add(answerX);
      roads[i].pointy.add(answerY);
      roads[j].pointy.add(answerY);
    }
  }

  public void output() {
    if(answer.size() == 0) { System.out.println("NA"); }
    for(int i=0; i<answer.size(); i++) {
      System.out.printf("%.5f %.5f\n",answer.get(i).getX(), answer.get(i).getY());
    }
  }
  
  public static void main(String[] args) {
    problem1_1 obj = new problem1_1();
    obj.compute();
    obj.output();
  }
}
