import java.util.*;

public class problem1_2 extends problem1_1 {
  class Answer {
    double x, y;
    public Answer(double a, double b) {
      x = a;
      y = b;
    }
    public double getX() { return x; }
    public double getY() { return y; }
  }

  ArrayList<Answer> answer = new ArrayList<Answer>();

  public problem1_2() {
    super();
  }

  public void comp() {
    for(int i=0; i<M; i++) {
      for(int j=i+1; j<M; j++) {
        double y1 = nList[roads[i].q].y - nList[roads[i].p].y;
        double x2 = nList[roads[j].q].x - nList[roads[j].p].x;
        double x1 = nList[roads[i].q].x - nList[roads[i].p].x;
        double y2 = nList[roads[j].p].y - nList[roads[j].q].y;
        double formula = Math.abs(((x1) * (y2)) + ((x2) * (y1)));
        double s, t, xp, yp, yPq;

        if(formula != 0.0) {
          xp = nList[roads[j].p].x - nList[roads[i].p].x;
          yp = nList[roads[j].p].y - nList[roads[i].p].y;
          yPq = nList[roads[i].p].y - nList[roads[i].q].y;
          s = (y2*xp + x2*yp) * 1.0 / formula;
          t = (yPq*xp + x1*yp) * 1.0 / formula;
        } else {
          continue;
        }

        if((s>0.0 && s<1.0) && (t>0.0 && t<1.0)) {
          answerX = nList[roads[i].p].x + x1 * s;
          answerY = nList[roads[i].p].y + y1 * s;
          cros();
        }
      }
    }
  }

  public void cros() {
    for(int i=1; i<=N; i++) {
      if(answerX == nList[i].x && answerY == nList[i].y) {
        return;
      } else {
        Answer point = new Answer(answerX, answerY);
        answer.add(point);
        return;
      }
    }
  }

  public void sort() {
  }

  public void out() {
    for(int i=0; i<answer.size(); i++) {
      System.out.printf("%.5f %.5f\n",answer.get(i).getX(), answer.get(i).getY());
    }
  }

  public static void main(String[] args) {
    problem1_2 obj = new problem1_2();
    obj.comp();
    obj.out();
  }
}
