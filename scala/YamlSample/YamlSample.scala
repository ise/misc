import java.util.HashMap
import scala.collection.JavaConversions._

object YamlSample extends Application {
  val pit = new UselessPit("twitter.com")
  val conf:HashMap[String,String] = (pit.getAll).asInstanceOf[HashMap[String,String]]
  for (key <- conf.keySet()) {
    println(key + ":" + conf.get(key))
  }
  pit.set("key", "value");
  println(pit.get("consumer_secret"))
}
