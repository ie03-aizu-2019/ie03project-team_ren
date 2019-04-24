import java.util.*;
import java.io.*;

// 各点の座標(x, y)を持つクラス
class N {
    double x, y;
}

// 各道路の情報を持つクラス
class M {
    int p, q;
    //交差地点の座標を保持
    ArrayList<Double> pointx;
    ArrayList<Double> pointy;
    //距離を重みとして保持
    ArrayList<Double> disp;   //地点→交差地点
    ArrayList<Double> pdis;   //交差地点→地点
    ArrayList<Double> dis;
}

// 最短経路探査に必要な情報を持つクラス
class Q{
    int  s, d, k;
    int scheck, dcheck;
}

public class problem1_3 {

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
	    roads[i].pointx = new ArrayList<Double>();
	    roads[i].pointy = new ArrayList<Double>();
	    roads[i].disp = new ArrayList<Double>();
	    roads[i].pdis = new ArrayList<Double>();
	    roads[i].dis = new ArrayList<Double>();
	    roads[i].p = sc.nextInt();
	    roads[i].q = sc.nextInt();
	}
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
		str = str.replace("C", "");
		sPath[i].d = Integer.parseInt(str);
	    }
	    else{
		sPath[i].dcheck = 0;
		//sPath[i].d = Integer.parseInt(str);
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

	for(int i=0; i<M-1; i++){
	    for(int j=i+1; j<M; j++){
	    
		double y1 = nList[roads[i].q].y - nList[roads[i].p].y;
		double x2 = nList[roads[j].q].x - nList[roads[j].p].x;
		double x1 = nList[roads[i].q].x - nList[roads[i].p].x;
		double y2 = nList[roads[j].p].y - nList[roads[j].q].y;
		double formulaA = Math.abs(((x1) * (y2)) + ((x2) * (y1)));
		double s, t, xp, yp, yPq;
		
		if(formulaA == 0.0){
		    roads[i].pointx.add(-1.0);
		    roads[j].pointx.add(-1.0);
		    roads[i].pointy.add(-1.0);
		    roads[j].pointy.add(-1.0);
		    distance();
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
		    cross(i, j);
		}
		else {
		    roads[i].pointx.add(-1.0);
		    roads[j].pointx.add(-1.0);
		    roads[i].pointy.add(-1.0);
		    roads[j].pointy.add(-1.0);
		    //output("NA");
		    distance();
		    continue;		
		}
	    }
	}
    }
    
    // 交差地点が端点であるかの確認
    public void cross(int I, int J) {
	for(int i=1; i<=N; i++) {
	    if(answerX == nList[i].x && answerY == nList[i].y) {
		roads[I].pointx.add(-1.0);
		roads[J].pointx.add(-1.0);
		roads[I].pointy.add(-1.0);
		roads[J].pointy.add(-1.0);
		distance();  //ばぐ可能性
		return ;
	    }
	}
	roads[I].pointx.add(answerX);
	roads[J].pointx.add(answerX);
	roads[I].pointy.add(answerY);
	roads[J].pointy.add(answerY);
	distance();  //ばぐ可能性
	//output(answerX, answerY);
	return ;
    }
    
    //public void output(double x, double y) { System.out.printf("%.5f %.5f\n",x,y); }
    //public void output(String message) { System.out.println(message); }

    public void shortPath(){
	
    }

    public void distance(){
	int num = 0;
	for(int i=0;i<M;i++){
	    if(roads[i].pointx.get(num) == -1.0){
		roads[i].dis.add(Math.sqrt(Math.pow((nList[roads[i].p].x - nList[roads[i].q].x), 2) +
					   Math.pow((nList[roads[i].p].y - nList[roads[i].q].y), 2)));
		roads[i].disp.add(-1.0);
		roads[i].pdis.add(-1.0);
	    }
	    else{
		roads[i].disp.add(Math.sqrt(Math.pow((nList[roads[i].p].x - roads[i].pointx.get(num)), 2) +
					    Math.pow((nList[roads[i].p].y - roads[i].pointy.get(num)), 2)));
	    roads[i].disp.add(Math.sqrt(Math.pow((roads[i].pointx.get(num) - nList[roads[i].q].x), 2) +
					Math.pow((roads[i].pointy.get(num) - nList[roads[i].q].y), 2)));

		num++;
	    }
	}
    }
    
    public static void main(String[] args) {
	problem1_3 obj = new problem1_3();
	obj.input();
	obj.compute();
	obj.shortPath();
    }
}
