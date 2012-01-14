object Main extends Application {
  val e1 = Element.elem(Array("aaa", "bbb", "ccc"))
  val e2 = Element.elem('*', 10, 2)
  println(e1 beside e2)

  val vertical = Element.elem('*', 10, 1)
  val horizontal = Element.elem('*', 1, 1) beside Element.elem(' ', 8, 1) beside Element.elem('*', 1, 1)

  println(func(vertical) above vertical)

  def func(e:Element): Element = {
    if (e.height == 9) e else func(e above horizontal)
  }
}
