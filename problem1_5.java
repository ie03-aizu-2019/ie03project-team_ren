import java.util.*;

public class problem1_5 extends problem1_3 {
  class Distance {
    int v;
    double distance;
    public Distance(int v) { this.v = v; }
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }
  }

  public problem1_5() { super(); }

  ArrayList<Distance> s_west = new ArrayList<Distance>();
  ArrayList<Distance> t_west = new ArrayList<Distance>();
  int idx;
  int[] visited = new int[10];

  public static void main(String[] args) {
    problem1_5 obj = new problem1_5();
    obj.compute();
    obj.sort();
    obj.makeGraph();
    obj.kShortestPath();
    obj.outWest();
  }

  public void outWest() {
    for(int i=0; i<idx; i++) { System.out.println("id: " + i + ", sum: " + (s_west.get(i).getDistance() + t_west.get(i).getDistance())); }
  }

  public double Dijkstra(int s, boolean sflag, int t, boolean dflag) {
    int gs = NGraph.size() + CGraph.size();
    ArrayList<SP> tmp = new ArrayList<SP>();
    ArrayList<SP> ans = new ArrayList<SP>();
    ans.add(new SP(sflag, s, 0));
    if(sflag == true) {
      for(int i=0; i<NGraph.get(s).node.size(); i++){
        tmp.add(new SP(NGraph.get(s).node.get(i).Nfrag,
        NGraph.get(s).node.get(i).No,
        NGraph.get(s).node.get(i).dis));
      }
    }

    else {
      for(int i=0; i<CGraph.get(s).node.size(); i++){
        tmp.add(new SP(CGraph.get(s).node.get(i).Nfrag,
        CGraph.get(s).node.get(i).No,
        CGraph.get(s).node.get(i).dis));
      }
    }

    while(tmp.size() != 0){
      int min = -1;
      for(int j=0; j<tmp.size(); j++) {
        if(tmp.get(j).Nfrag == false) { if(min == -1 || tmp.get(min).dis > tmp.get(j).dis) { min = j; } }
      }

      if(min != -1) {
        if(tmp.get(min).Nfrag == true) {
          for(int j=0; j < NGraph.get(tmp.get(min).No).node.size(); j++) {
            boolean tmpflag = false;
            for(int k=0; k<ans.size(); k++){
              if(NGraph.get(tmp.get(min).No).node.get(j).No == ans.get(k).No &&
              NGraph.get(tmp.get(min).No).node.get(j).Nfrag == ans.get(k).Nfrag) {
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

        else {
          for(int j=0; j<CGraph.get(tmp.get(min).No).node.size(); j++) {
            boolean tmpflag = false;
            for(int k=0; k<ans.size(); k++) {
              if(CGraph.get(tmp.get(min).No).node.get(j).No == ans.get(k).No &&
              CGraph.get(tmp.get(min).No).node.get(j).Nfrag == ans.get(k).Nfrag){
                tmpflag = true;
                break;
              }
            }
            if(tmpflag == false) {
              tmp.add(new SP(CGraph.get(tmp.get(min).No).node.get(j).Nfrag,
              CGraph.get(tmp.get(min).No).node.get(j).No,
              CGraph.get(tmp.get(min).No).node.get(j).dis + tmp.get(min).dis));
            }
          }
          ans.add(tmp.get(min));
          tmp.remove(min);
        } // End else.
      }
    }

    for(int i=0; i<ans.size(); i++) {
      System.out.println(ans.get(i).No + "--" + ans.get(i).Nfrag + "--" +ans.get(i).dis);
      if(dflag == true) {
        if(ans.get(i).No == t && ans.get(i).Nfrag == dflag) {
          System.out.println(ans.get(i).dis);
          return ans.get(i).dis;
        }
      }

      else {
        if(ans.get(i).No == t - 1 && ans.get(i).Nfrag == dflag) {
          System.out.println(ans.get(i).dis);
          return ans.get(i).dis;
        }
      }

    } // End for loop.

    return 0.0;
  } // End dijkstra().

  // For v,
  public void WorkerN(int s, int t, boolean sflag) {
    int v;
    for(int i=0; i<N; i++) {
      for(int j=0; j<NGraph.get(i).node.size(); j++) {
        if(NGraph.get(i).node.get(j).Nfrag) { // 座標
          v = NGraph.get(i).node.get(j).No;
          if(v != s && visited[v] == 0 && v != t) {
            visited[v] = 1;
            // System.out.println("Log-s, v: " + s + ", " + v);
          }
        } else { // 交差地点
          v = answer.get(NGraph.get(i).node.get(j).No).getId();
          if(visited[v+N+1] == 0) {
            visited[v+N+1] = 1;
            // System.out.println("Cross point-Log-s, v: " + s + ", " + v);
          }
        }
        double s_path = Dijkstra(s, sflag, v, false);
        s_west.add(new Distance(v));
        s_west.get(idx++).setDistance(s_path);
      }
    }
  }
  public void WorkerC(int s, int t, boolean sflag) {
    int v;
    for(int i=0; i<answer.size(); i++) {
      for(int j=0; j<CGraph.get(i).node.size(); j++) {
        if(CGraph.get(i).node.get(j).Nfrag) { // 座標
          v = CGraph.get(i).node.get(j).No;
          if(v != s && visited[v] == 0 && v!= t) {
            visited[v] = 1;
            // System.out.println("Log-s, v: " + s + ", " + v);
          }
        } else { // 交差地点
          v = answer.get(CGraph.get(i).node.get(j).No).getId();
          if(visited[v+N+1] == 0) {
            visited[v+N+1] = 1;
            // System.out.println("Cross point-Log-s, v: " + s + ", " + v);
          }
        }
        double s_path = Dijkstra(s, sflag, v, true);
        s_west.add(new Distance(v));
        s_west.get(idx++).setDistance(s_path);
      }
    }
  }

  public void kShortestPath() {
    int s, t, v;
    for(int i=0; i<Q; i++) {
      // For s,
      boolean sflag = false, tflag = false; // sflag, tflag: 交差地点であるかを示す
      if(sPath[i].scheck == 1) {
        sflag = true;
        if(sPath[i].s > N || sPath[i].s < 1) {
          out();
          continue;
        } else {
          s = sPath[i].s;
        }
      }
      else {
        if(sPath[i].s - 1 >= answer.size() || sPath[i].s - 1 < 0) {
          out();
          continue;
        } else {
          s = answer.get(sPath[i].s - 1).getId();
        }
      } // Value of s is completed.

      // For t,
      if(sPath[i].dcheck == 1) {
        tflag = true;
        if(sPath[i].d > N || sPath[i].d < 1) {
          out();
          continue;
        } else {
          t = sPath[i].d;
        }
      }
      else {
        if(sPath[i].d - 1 >= answer.size() || sPath[i].d - 1 < 0) {
          out();
          continue;
        } else{
          t = answer.get(sPath[i].d - 1).getId() + 1;
        }
      } // Value of t is completed.

      //From s,
      idx = 0;
      WorkerN(s, t, sflag); //NGraph
      WorkerC(s, t, sflag); // CGraph
      // From t,
      idx = 0;
      visited = new int[10];
      WorkerN(t, s, tflag); // NGraph
      WorkerC(t, s, tflag); // CGraph
    }
  }
}
