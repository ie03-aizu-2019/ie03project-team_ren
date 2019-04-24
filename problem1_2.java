import java.util.*;
import java.io.*;

// 各点の座標(x, y)を持つクラス
class N {
    int x, y;
}

// 各道路の情報を持つクラス
class M {
    int p, q;
}

// 最短経路探査に必要な情報を持つクラス
class Q{
    int  d, k, s;
}

public class problem1_2 {

    Scanner sc = new Scanner(System.in);
    int N,M,P,Q;
    double answerX, answerY;
    N[] nList;
    M[] roads;
    Q[] sPath;

    public void input() {
	N = sc.nextInt();
	M = sc.nextInt();
	P = sc.nextInt();
	Q = sc.nextInt();

	String str = new String();

	nList = new N[N+1];
	roads = new M[M];
	sPath = new Q[Q];

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
	for(int i=0; i<Q; i++) {
	    sPath[i] = new Q();
	    str = sc.next();
	    if(isNum(String.valueOf(str.charAt(0))) == true){
		sPath[i].s = Integer.parseInt(str);
	    }
	    else{
		
	    }
	    str = sc.next();
	    if(isNum(String.valueOf(str.charAt(0))) == true){
		sPath[i].d = Integer.parseInt(str);
	    }
	    else{
		
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

	for(int i=0; i<M; i++){
	    for(int j=i+1; j<M; j++){
	    
		double y1 = nList[roads[i].q].y - nList[roads[i].p].y;
		double x2 = nList[roads[j].q].x - nList[roads[j].p].x;
		double x1 = nList[roads[i].q].x - nList[roads[i].p].x;
		double y2 = nList[roads[j].p].y - nList[roads[j].q].y;
		double formulaA = Math.abs(((x1) * (y2)) + ((x2) * (y1)));
		double s, t, xp, yp, yPq;
		
		if(formulaA == 0.0){
		    continue;
		} else {
		    xp = nList[roads[j].p].x - nList[roads[i].p].x;
		    yp = nList[roads[j].p].y - nList[roads[i].p].y;
		    yPq = nList[roads[i].p].y - nList[roads[i].q].y;
		    s = (y2*xp + x2*yp) * 1.0 / formulaA;
		    t = (yPq*xp + x1*yp) * 1.0 / formulaA;
		}
		
		if((s>=0.0 && s<=1.0) && (t>=0.0 && t<=1.0)) {
		    answerX = nList[roads[i].p].x + x1 * s;
		    answerY = nList[roads[i].p].y + y1 * s;
		    cross();
		}
		else {
		    //output("NA");
		    continue;		
		}
	    }
	}
    }
    
    // 交差地点が端点であるかの確認
    public void cross() {
	for(int i=1; i<=N; i++) {
	    if(answerX == nList[i].x && answerY == nList[i].y) {
		//output("NA");
		return ;
	    }
	}
	output(answerX, answerY);
	return ;
    }
    
    public void output(double x, double y) { System.out.printf("%.5f %.5f\n",x,y); }
    //public void output(String message) { System.out.println(message); }

    public void shortPath(){
	
    }

    
    public static void main(String[] args) {
	problem1_2 obj = new problem1_2();
	obj.input();
	obj.compute();
	//小課題2のcompute()を少し書き換えて、3で使えるようにする。
    }
}
