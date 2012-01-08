object Main extends App {
  def boom(x: Int): Int = 
    if (x == 0) throw new Exception("boom!")
    else boom(x-1) + 1

  def bang(x: Int): Int =
    if (x == 0) throw new Exception("boom!")
    else bang(x-1)
/*
 * classファイルをdecompileしてみると下記のようになってた
 *
public int bang(int x) {
  while (true) { if (x == 0) throw new Exception("boom!");
    x -= 1;
  }
}
 */

  //boom(10)
  bang(10)
}
