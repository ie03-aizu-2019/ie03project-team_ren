import java.util.*;
import java.io.*;

public class problem1_7 extends problem1_4{

    class Nd{
	double x, y;
	public Nd(double p, double q){
	    x=p;
	    y=q;
	}
    }

    class Md{
	int p, q;
	public Md(int p, int q){
	    this.p =p;
	    this.q =q;
	}
    }
    
    public problem1_7(){
	super();
    }

    ArrayList<Nd> addNList = new ArrayList<Nd>();
    ArrayList<Md> addRoads = new ArrayList<Md>();

   

    double xtm, ytm;
    
    public void addRoad(){
	double d, min, x, y;
	int t=-1;
	M minR = new M();
	 for(int i=1; i<=N; i++){
	addNList.add(new Nd(nList[i].x, nList[i].y));
    }

    for(int i=0; i<M; i++){
	addRoads.add(new Md(roads[i].p, roads[i].q));
    }
	
	for(int i=0;i<P;i++){
	    min = 1000000;
	    x=0;
	    y=0;
	    for(int j=0;j<addRoads.size();j++){
		d = dis3(addNList.get(addRoads.get(j).p-1).x, addNList.get(addRoads.get(j).p-1).y,
			  addNList.get(addRoads.get(j).q-1).x, addNList.get(addRoads.get(j).q-1).y,
			 (double)addN[i].x, (double)addN[i].y);
		if(min > d){
		    min = d;
		    //minR = addRoads.get(j);
		    x = xtm;
		    y = ytm;
		}
	    }
	    System.out.println(String.format("%.5f %.5f", x, y));
	    
	    for(int j=0; j<addNList.size(); j++){
		if(addNList.get(j).x == x && addNList.get(j).y == y){
		    t = j;
		    break;
		}
		t = -1;
	    }

	    addNList.add(new Nd(addN[i].x, addN[i].y));
	    if(t!=-1){
		addRoads.add(new Md(addRoads.size(), t));
	    }
	    else{
		addNList.add(new Nd(x, y));
		addRoads.add(new Md(addRoads.size()-1, addRoads.size()));	
	    }
	}   
    }
    
    public double dis3(double sx, double sy, double ex, double ey, double px, double py){
	double Ax = ex - sx;
	double Ay = ey - sy;
	double Bx = px - sx;
	double By = py - sy;
	double ABdot = Ax * Bx + Ay * By;
	double Asize = Ax * Ax + Ay * Ay;
	double t, x, y;
	
	if(ABdot <= 0){//始点に近い
	    xtm = sx;
	    ytm = sy;
	    return Math.sqrt(Math.pow(Bx, 2.0) + Math.pow(By, 2.0));
	}
	else if(ABdot < Asize){
	    t = ABdot / Asize;
	    x = (ex - sx) * t + sx;
	    y = (ey - sy) * t + sy;
	    xtm = x;
	    ytm = y;
	    return Math.sqrt(Math.pow(px - x, 2.0) + Math.pow(py - y, 2.0));
	}
	else if(ABdot >= Asize){//終点に近い
	    xtm = ex;
	    ytm = ey;
	    return Math.sqrt(Math.pow(ex - px, 2.0) + Math.pow(ey - py, 2.0));
	}
	return 100000000.0;
    }

    public static void main(String[] args){
	problem1_7 obj = new problem1_7();
	obj.compute();
	obj.sort();
	obj.addRoad();
    }
    
}

