import java.util.*;

public class problem1_1 {

  Scanner sc = new Scanner(System.in);
  int N,M,P,Q;
  int [][] listN;
  int [][] listM;
  double answerX, answerY;

  public void input() {
    N = sc.nextInt();
    M = sc.nextInt();
    P = sc.nextInt();
    Q = sc.nextInt();

    listN = new int[N][N];
    listM = new int[M][M];

    for(int i=0; i<N; i++) {
      listN[i][0] = sc.nextInt();
      listN[i][1] = sc.nextInt();
    }
    for(int i=0; i<M; i++) {
      listM[i][0] = sc.nextInt();
      listM[i][1] = sc.nextInt();
    }

    sc.close();
  }

  public void compute() {
    double y1 = listN[listM[0][1]-1][1] - listN[listM[0][0]-1][1];
    double x2 = listN[listM[1][1]-1][0] - listN[listM[1][0]-1][0];
    double x1 = listN[listM[0][1]-1][0] - listN[listM[0][0]-1][0];
    double y2 = listN[listM[1][0]-1][1] - listN[listM[1][1]-1][1];
    double formulaA = Math.abs( ((x1) * (y2)) + ((x2) * (y1)) );

    double s, t, xp, yp;

    // Step1
    if(formulaA == 0){
      // 交差なし
      output("NA");
      return;
    } else {
      // Step2
      xp = listN[listM[1][0]-1][0] - listN[listM[0][0]-1][0];
      yp = listN[listM[1][0]-1][1] - listN[listM[0][0]-1][1];
      double yPq = listN[listM[0][0]-1][1] - listN[listM[0][1]-1][1];
      s = (y2*xp + x2*yp) * 1.0 / formulaA;
      t = (yPq*xp + x1*yp) * 1.0 / formulaA;
    }

    // Step3
    if((s>=0.0 && s<=1.0) && (t>=0.0 && t<=1.0)) {
      // Step4
      answerX = listN[listM[0][0]-1][0] + x1 * s;
      answerY = listN[listM[0][0]-1][1] + y1 * s;
      for(int i=0; i<N; i++) {
        if(answerX == listN[i][0] && answerY == listN[i][1]){
          output("NA");
          return;
        } else {
          output(answerX, answerY);
        }
      }
    } else {
      // 交差なし
      output("NA");
      return;
    }
  }

  public void output(double x, double y) {
    System.out.printf("%.5f %.5f",x,y);
    System.out.println();
  }

  public void output(String message) { System.out.println(message); }

  public static void main(String[] args) {
    problem1_1 obj = new problem1_1();
    obj.input();
    obj.compute();
  }
}
