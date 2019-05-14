import java.util.*;

public class problem1_3 extends problem1_2 {
  int listNumber = answer.size() + N; // 隣接リストのサイズ
  int[][] list = new int[listNumber][listNumber]; // 隣接リスト

  final int inf = Integer.MAX_VALUE; // 定数
	int[] d = new int[n]; // 最短経路の記録

  int[] visit = new int[n]; //頂点の訪問を記録
	int white = 0, gray = 1, black = 2; // 頂点の状態

  public problem1_3() {
    // 配列を全て定数で初期化
    Arrays.fill(list, inf);
    Arrays.fill(d, inf);
  }

  public void setList() {
    for(int i=0; i<listNumber; i++) {
      list[i][0] = i;
      setK(i);
    }
  }

  public void setK(int idx) {
  }

  public static void main(String[] args) {
    new problem1_3();
  }
}
