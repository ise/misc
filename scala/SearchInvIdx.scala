import java.io.FileInputStream
import java.io.ObjectInputStream
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.io.Source

val dir = "/Users/mastakeu/work/tmp/invidx"

// index読み込み
val ois = new ObjectInputStream(new FileInputStream(dir + "/idx/index"));
val invIdx = ois.readObject().asInstanceOf[HashMap[String,HashSet[String]]];


val pat = "search (.+)".r

// 検索 
while (true) {
  print("> ")
  val input = readLine match {
    case "exit" => sys.exit
    case pat(s) => search(s)
    case _ => "error"
  }
}

def search(q:String) = {
  val result = invIdx.get(q) match {
    case Some(res) => "Hit!\n" + res.mkString(",")
    case None => "Not found"
  }
  println(result)
}

// val in = Source.fromInputStream(System.in)
// while (true) {
//   print("> ")
//   in.getLines.foreach(println)
// }
//val lines = in.getLines.collect

//println(in.getLines)


