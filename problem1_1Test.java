public class problem1_1Test {

  @Test
  public void test() {
    final Generator<List<Integer>> generator = lists(integers());
    IntStream.range(0, 4).forEach(i -> System.out.println(generator.next()));
  }

  public static void main(String[] args) {
    new problem1_1Test();
    this.test();
  }
}
