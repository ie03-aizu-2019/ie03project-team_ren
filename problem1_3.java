import java.util.*;
import java.io.*;


public class problem1_3 extends problem1_2{
    
    
class check {
    boolean Nfrag;
    int No;
    double dis;
    public check(boolean Nflag, int No, double dis){
	this.Nfrag = Nflag;
	this.No = No;
	this.dis = dis;
    }
}

    class SP extends check{
	public SP(boolean Nflag, int No, double dis){
	    super(Nflag, No, dis);
	}
    }

class TMP {
   boolean Nfrag;
    int No;
    double x;
    double y;

    public TMP(boolean Nflag, int No, double x, double y){
	this.Nfrag = Nflag;
	this.No = No;
	this.x = x;
	this.y = y;
    }
}

 class G{
     ArrayList<check> node;
     public G(){
	 node = new ArrayList<check>();
     }
     public void SetNode(boolean Nflag, int No, double dis){
	 boolean findFlag = false;
	 for(int i=0; i<node.size(); i++){
	     if(node.get(i).Nfrag == Nflag && node.get(i).No == No){
		 findFlag= true;
	     }
	 }

	 if(!findFlag){
	     node.add(new check(Nflag, No, dis));
	 }
     }     
}
    public problem1_3(){ super(); }

    ArrayList<G> NGraph = new ArrayList<G>();
    ArrayList<G> CGraph= new ArrayList<G>();

    //距離を重みとした重みつきグラフ
    public void makeGraph(){
	for(int i=0; i<N+1; i++){
	    NGraph.add(new G());
	}
	for(int i=0; i<answer.size(); i++){
	    CGraph.add(new G());
	}
	for(int i =0; i<M; i++){
	    ArrayList<TMP> tmp = new ArrayList<TMP>();  
	    tmp.add(new TMP(true, roads[i].p,nList[roads[i].p].x ,nList[roads[i].p].y));
	    tmp.add(new TMP(true, roads[i].q,nList[roads[i].q].x ,nList[roads[i].q].y));
	    
	    //交差地点
	    for(int j=0; j<roads[i].pointx.size(); j++){
		for(int k=0; k<answer.size(); k++){
		    if(roads[i].pointx.get(j) == answer.get(k).getX() &&
		       roads[i].pointy.get(j) == answer.get(k).getY()){
			tmp.add(new TMP(false, answer.get(k).getId(),answer.get(k).getX(), answer.get(k).getY()));
		    }
		}
	    }
	    
	    if(nList[roads[i].p].x != nList[roads[i].p].x){
	    Collections.sort(tmp, new Comparator<TMP>(){
		    public int compare(TMP tmp1, TMP tmp2){
			return Double.compare(tmp1.x, tmp2.x);	
		    }
		});
	    }
	    else{
	    Collections.sort(tmp, new Comparator<TMP>(){
		    public int compare(TMP tmp1, TMP tmp2){
			return Double.compare(tmp1.y, tmp2.y);
		    }
		});
	    }

	    /*System.out.println("miti:"+i);
	    for(int j=0; j<tmp.size(); j++){
		System.out.print(tmp.get(j).Nfrag + " " + tmp.get(j).x + " ");
		System.out.println(tmp.get(j).y);
		}*/
	    for(int j=0; j<tmp.size(); j++){
		
		//System.out.println("----------tmp="+ tmp.size());
		if(j==0){
		    //System.out.println("----------sj="+ j);
		    NGraph.get(tmp.get(j).No).SetNode(tmp.get(j+1).Nfrag, tmp.get(j+1).No,
						      distance(tmp.get(j).x,tmp.get(j).y, tmp.get(j+1).x, tmp.get(j+1).y)); 
		}
		else if(j==tmp.size()-1){
		    //  System.out.println("----------ej="+ j +"--"+ NGraph.size() + "--" + tmp.get(j).No);
		    NGraph.get(tmp.get(j).No).SetNode(tmp.get(j-1).Nfrag, tmp.get(j-1).No,
						      distance(tmp.get(j).x,tmp.get(j).y, tmp.get(j-1).x, tmp.get(j-1).y)); 
		}
		else{
		    //System.out.println("----------cj="+ j);
		    CGraph.get(tmp.get(j).No).SetNode(tmp.get(j+1).Nfrag, tmp.get(j+1).No,
						      distance(tmp.get(j).x,tmp.get(j).y, tmp.get(j+1).x, tmp.get(j+1).y)); 
		    CGraph.get(tmp.get(j).No).SetNode(tmp.get(j-1).Nfrag, tmp.get(j-1).No,
						      distance(tmp.get(j).x,tmp.get(j).y, tmp.get(j-1).x, tmp.get(j-1).y)); 
		}
	    }
	}
    }
    
    //道ごとの距離を出す
    public double distance(double x1, double y1, double x2, double y2){
        
	return Math.sqrt(Math.pow((x1 - x2), 2) +
				   Math.pow((y1 - y2), 2));
    }

    //最短経路を求める

    public void shortPath(){
	int s, d;
	for(int i=0; i<Q; i++){
	boolean sflag = false, dflag = false;
	    if(sPath[i].scheck==1){
		sflag = true;
		if(sPath[i].s > N || sPath[i].s < 1){
		    out();
		    continue;
		}
		else{
		s = sPath[i].s;
		}
	    }
	    else{
		if(sPath[i].s - 1 >= answer.size() || sPath[i].s - 1 < 0){
		    out();
		    continue;
		}
		else{
		    s = answer.get(sPath[i].s-1).getId();
		    //dijkstra(s, d, false);
		}
	    }
	    if(sPath[i].dcheck==1){
		dflag = true;
		if(sPath[i].d  > N || sPath[i].d < 1){
		    out();
		    continue;
		}
		else{
		d = sPath[i].d;
		}
	    }
	    else{
		if(sPath[i].d - 1 >= answer.size() || sPath[i].d - 1 < 0){
		    out();
		    continue;
		}
		else{
		    d = answer.get(sPath[i].d-1).getId() + 1;
		}
	    }
	    dijkstra(s, sflag, d, dflag);
	}
    }

    public void dijkstra(int s, boolean sflag, int d, boolean dflag){
	int gs = NGraph.size() + CGraph.size();
	ArrayList<SP> tmp = new ArrayList<SP>();
	ArrayList<SP> ans = new ArrayList<SP>();
	    ans.add(new SP(sflag,s,0));
	if(sflag==true){
	    for(int i=0; i<NGraph.get(s).node.size(); i++){
		tmp.add(new SP(NGraph.get(s).node.get(i).Nfrag,
				  NGraph.get(s).node.get(i).No,
				  NGraph.get(s).node.get(i).dis));
	    }
	}
	else{
	    for(int i=0; i<CGraph.get(s).node.size(); i++){
	    	tmp.add(new SP(CGraph.get(s).node.get(i).Nfrag,
				  CGraph.get(s).node.get(i).No,
				  CGraph.get(s).node.get(i).dis));
	     }
	}

	while(tmp.size() != 0){  
	    int min = -1;
	    for(int j=0; j<tmp.size(); j++){
		    if(min == -1 || tmp.get(min).dis > tmp.get(j).dis){
			min = j;
		    }
		}
	    if(min != -1){
		

		if(tmp.get(min).Nfrag == true){
		    for(int j=0; j<NGraph.get(tmp.get(min).No).node.size(); j++){
			boolean tmpflag = false;
			for(int k=0; k<ans.size(); k++){
			    if(NGraph.get(tmp.get(min).No).node.get(j).No == ans.get(k).No &&
			       NGraph.get(tmp.get(min).No).node.get(j).Nfrag == ans.get(k).Nfrag){
				tmpflag = true;
				break;
			    }
			}
			if(tmpflag == false){
			    tmp.add(new SP(NGraph.get(tmp.get(min).No).node.get(j).Nfrag,
					   NGraph.get(tmp.get(min).No).node.get(j).No,
					   NGraph.get(tmp.get(min).No).node.get(j).dis + tmp.get(min).dis));
			}
		    }
		    ans.add(tmp.get(min));
		    tmp.remove(min);
		}
		else{
		   
		    for(int j=0; j<CGraph.get(tmp.get(min).No).node.size(); j++){
			boolean tmpflag = false;
			for(int k=0; k<ans.size(); k++){
			    if(CGraph.get(tmp.get(min).No).node.get(j).No == ans.get(k).No &&
			       CGraph.get(tmp.get(min).No).node.get(j).Nfrag == ans.get(k).Nfrag){
				tmpflag = true;
				break;
			    }
			}
			if(tmpflag == false){
			    tmp.add(new SP(CGraph.get(tmp.get(min).No).node.get(j).Nfrag,
					   CGraph.get(tmp.get(min).No).node.get(j).No,
					   CGraph.get(tmp.get(min).No).node.get(j).dis + tmp.get(min).dis));
			}
		    }
		    ans.add(tmp.get(min));
		    tmp.remove(min);
		}
	    }
	}
	for(int i=0; i<ans.size(); i++){
	    if(dflag == true){
		if(ans.get(i).No == d && ans.get(i).Nfrag == dflag){
		    System.out.println(ans.get(i).dis);
		    break;
		}
	    }
	    else{
		if(ans.get(i).No == d - 1 && ans.get(i).Nfrag == dflag){
		    System.out.println(ans.get(i).dis);
		    break;
		}
	    }
	}
    }
    

    public void out(){
	System.out.println("NA");
    }
    
    public static void main(String[] args){
	problem1_3 obj = new problem1_3();
	obj.compute();
	obj.sort();
	obj.makeGraph();
	obj.shortPath();
    }
}
