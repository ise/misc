import java.io.File
import java.util.HashMap
import org.ho.yaml.Yaml

class UselessPit(target:String) {

  def get(key:String):String = _load.get(target).get(key)

  def getAll:HashMap[String,String] = _load.get(target)

  def set(key:String, value:String):Unit = {
    return
    val config = _load
    val targetConfig = config.get(target)
    targetConfig.put(key,value)
    config.put(target,targetConfig)
    Yaml.dump(config, new File("./out.yaml"))
  }

  private def _load:HashMap[String,HashMap[String,String]] = {
    val pitDir = System.getProperty("user.home") + "/.pit"
    val pit:HashMap[String,String] = (Yaml.load(new File(pitDir + "/pit.yaml"))).asInstanceOf[HashMap[String,String]]
    (Yaml.load(new File(pitDir + "/" + pit.get("profile") + ".yaml"))).asInstanceOf[HashMap[String,HashMap[String,String]]]
  }

}
