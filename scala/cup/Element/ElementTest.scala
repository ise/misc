/*
 * テスト実行
 * scala -cp ~/lib/scalatest-1.6.1/scalatest-1.6.1.jar org.scalatest.tools.Runner -p . -s ElementTest2
 * -oオプション付けるとテストレポートが標準出力へ
 */

import org.scalatest.Suite
import Element.elem

class ElementTest extends Suite {
  def testUniformElement() {
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
  }
}

import org.scalatest.FunSuite

class ElementTest2 extends FunSuite {
  test("elem result should have passed width") {
    val ele = elem('x', 2, 3)
    assert(ele.width == 2)
  }
}

object ExecTest extends App {

  (new ElementTest).execute()

}

