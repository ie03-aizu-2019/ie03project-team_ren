import java.io.IOException;
import java.util.*;

//交差地点の情報クラス

class Info{
    int x;
    int y;
    int number;
    int weight;
    double d;
}

class Line{
    ArrayList ;
}


public class ShortestPath{
    public static void main(String[] args){

	//宣言処理、初期化
	Tool t = new Tool();
	Scanner scanner = new Scanner(System.in);
	int N = scanner.nextInt();
        int M = scanner.nextInt();
	int P = scanner.nextInt();
	int Q = scanner.nextInt();
	
	Info[] arr_info = new Info[N];
	//仮置き
	Info[] det_info = new Info[3];

	for(int i = 0; i < arr_info.length; i++){
	    arr_info[i] = new Info();
	    arr_info[i].x = scanner.nextInt();
	    arr_info[i].y = scanner.nextInt();
	}
	    det_info[0] = new Info();
	    det_info[0].x = 3.66667;
	    det_info[0].y = 3.66667;
	    det_info[0].number = 1;
	    
	    det_info[1] = new Info();
	    det_info[1].x = 4.86885;
	    det_info[1].y = 2.70492;
	    det_info[0].number = 2;

	    det_info[2] = new Info();
	    det_info[2].x = 5.86957;
	    det_info[2].y = 3.26087;
	    det_info[0].number = 3;

	for(int i = 0; i < arr_info.length; i++){
	    arr_info[i].number = t.num(i);
	}

	
	for(int i = 0; i < arr_info.length){
	t.Distance();
	}
    }  
}
class Tool{
 public void InfoReader(){
	
    }

    public int num(int i){
        return i+1;
	
    }
    
    public double Distance(int s, int d,int k){
	//sは始点,dは終点,kはk番目までの最短経路の問い合わせを表す。
	
	
    }
