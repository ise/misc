def multiplicationRow(n: Int) = {
  for (i <- 1 until 10) yield (i * n).toString
}
//println(multiplicationRow(8))

def multiplicationTable = {
  for (i <- 1 until 10) yield {
    multiplicationRow(i).map(n => {
      val padding = " " * (4 - n.length)
      padding + n
    }).mkString
  }
}

println(multiplicationTable.mkString("\n"))

