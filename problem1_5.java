import java.util.*;

public class problem1_5 {
  int n; // 頂点の数
  // int n = N + answer().size();

  // For dijkstra
  double[][] matrix;
  double[] d; // 距離
  int[] visit; // 訪問の状態を記録
  int white =0, gray = 1, black = 2; // 訪問の状態を示す
  final int inf = Integer.MAX_VALUE; // 定数（∞として扱う）
  int indexU;
  double min;

  // query用配列
  String[][] query = new String[n][2];

  Scanner sc = new Scanner(System.in);

  public problem1_5() {
    // matrixはスーパークラスのものを使用
    // ダイクストラに必要な配列などはここで初期化する
    // query[][]のInputを行う
    // Arrays.fill(d,inf);
    // Arrays.fill(visit, 0);
  }

  // s-t 双方向ダイクストラ
  public double bidirectionalDijkstra(int s, int t) {
    double MAX_W = DBL_MAX / 2;

    Vector<vector<double>> d(2, Vector<double>(n, MAX_W));
    Vector<vector<boolean>> visit(2, Vector<boolean>(n, false));
    Vector<priority_queue<P, Vector<P>, greater<P>>> pq(2);

    pq[0].emplace(d[0][s] = 0, s);
    pq[1].emplace(d[1][t] = 0, t);

    boolean loop_continue = true;
    while(loop_continue && !pq[0].empty() && !pq[1].empty()) {
      for(int i=0; i<2; i++) {
        if(pq[i].empty()) { continue; }

        int u;
        double w;

        tie(w, u) = pq[i].top();
        pq[i].pop();

        if(w <= d[i][u]) {
          visit[i][u] = true;

          if(w != 0 && visit[0][u] && visit[1][u]) {
            loop_continue = false;
            break;
          }

          for(int v=0; v < n; ++v) {
            if(u == v) { continue; }

            double cost = Weight(p[u], p[v]) + v;

            if(cost < d[i][v]) {
              d[i][v] = cost;
              pq[i].emplace(d[i][v] = cost, v);
            }
          }
        }
      }
    }

    double ans = MAX_W;
    for(int v=0; v < n; ++v) { ans = min(ans, d[0][v] + d[1][v]); }

    return ans;
  }

  // 頂点番号と最短距離の出力
  public void showOutput() {
    for(int i=0; i<n; i++) if(d[i] != inf) { System.out.println(i + " " + d[i]); }
  }

  // ドライバ
  // matrix(隣接リスト)の作成
  public void input() {
    n = sc.nextInt();
    matrix = new double[n][n];
    d = new double[n];
    visit = new int[n];
    Arrays.fill(d,inf);
    Arrays.fill(visit, 0);
    for(int i=0; i<n; i++) { for(int j=0; j<n; j++) { matrix[i][j] = inf; } }
    for(int i=0; i<n; i++) {
      int u = sc.nextInt();
      int k = sc.nextInt();
      //出字数分だけ
      for(int j=0; j<k; j++) {
        int v = sc.nextInt();
        double c = sc.nextDouble();
        matrix[u][v] = c;
      }
    }
  }

  // 頂点0から各点への最短経路を求めるdijkstraアルゴリズム
  public void dijkstra() {
    d[0] = 0;
    visit[0] = gray;
    while( true ) {
      min = inf;
      int indexU = -1;
      for(int i=0; i<n; i++) {
        if(min > d[i] && visit[i] != black) {
          indexU = i;
          min = d[i];
        }
      }
      if(indexU == -1) break;
      visit[indexU] = black; //訪問済みに変更
      for(int v=0; v<n; v++) {
        if(visit[v]!=black && matrix[indexU][v]!=inf) {
          if(d[v] > d[indexU] + matrix[indexU][v]) {
            d[v] = d[indexU] + matrix[indexU][v];
            visit[v] = gray;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    problem1_5 obj = new problem1_5();
    obj.input();
    obj.dijkstra();
    obj.bidirectionalDijkstra(1, 4);
    obj.showOutput();
  }
}
