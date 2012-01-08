import java.io.File
import java.io.PrintWriter

object PrintWriterTest {
  def withPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  def withPrintWriter2(file: File)(op: PrintWriter => Unit) {
    withPrintWriter(file, op)
  }

}

object Main extends App {
  // PrintWriterTest.withPrintWriter(
  //   new File("date.txt"),
  //   writer => writer.println(new java.util.Date)
  // )
  val f = new File("date.txt")
  PrintWriterTest.withPrintWriter2(f) {
    writer => writer.println(new java.util.Date)
  }
}

