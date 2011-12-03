//import scala.collection.JavaConversions._
import java.io.File
import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

import java.io.FileOutputStream
import java.io.ObjectOutputStream


val dir = "/Users/mastakeu/work/tmp/invidx"

// ファイル一覧 読み込み
val files = (new File(dir + "/data")).listFiles
val invIdx = new HashMap[String,HashSet[String]]
files.foreach(f => genInvIdx(f))

// ファイル出力 
val oos = new ObjectOutputStream(new FileOutputStream(dir + "/idx/index"));
oos.writeObject(invIdx);

def genInvIdx(file:File):Unit = {
  // ファイル読み込み, 転置Index作成
  val source = Source.fromFile(file)
  val lines = source.getLines()
  val name = file.getName()
  println("Gen index from " + name)
  try {
    lines.foreach(
      _.split(" ").foreach(w => {
        invIdx.get(w) match {
          case Some(n) => invIdx.update(w, n + name)
          case None => invIdx(w) = HashSet(name)
        }
      }))
  } catch {
    case e: java.nio.charset.MalformedInputException => return
  }
}
