import java.io.File
import java.util.HashMap
import org.ho.yaml.Yaml

class UselessPit(topKey:String) {
  private val _pitDir = System.getProperty("user.home") + "/.pit"
  private val _pitConfig:HashMap[String,String]= (Yaml.load(new File(_pitDir + "/pit.yaml"))).asInstanceOf[HashMap[String,String]]

  private val _profileConfig:HashMap[String,Any] = (Yaml.load(new File(_pitDir + "/" + _pitConfig.get("profile") + ".yaml"))).asInstanceOf[HashMap[String,Any]]
  private val _targetConfig:HashMap[String,String] = (_profileConfig.get(topKey)).asInstanceOf[HashMap[String,String]]

  def get(key:String):String = _targetConfig.get(key)

  def getAll:HashMap[String,String] = _targetConfig

  def set(key:String, value:String) = {
    _targetConfig.put(key,value)
    println(_targetConfig)
  }

}
