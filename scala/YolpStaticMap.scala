/**
 * Created by IntelliJ IDEA.
 * User: mastakeu
 * Date: 12/02/15
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */

import scala.collection.mutable.ArrayBuffer
import java.awt.Color

class YolpStaticMap(
  private val appid: String,
  private val lat: String,
  private val lon: String,
  private val width: Int,
  private val height: Int
) {
  private val baseUrl = "http://map.olp.yahooapis.jp/OpenLocalPlatform/V1/static"
  private var pins = new ArrayBuffer[String]
  private var circles = new ArrayBuffer[String]
  private val default = "scalebar=off&logo=off&output=png&quality=40&maxzoom=17&mode=map&style=base:vivid"
  def addPin(lat:String, lon:String, style:String = "", label:String = "", color:String = "") = {
    pins += "pin%s=%s,%s,%s,%s".format(style, lat, lon, label, color)
  }
  def addCircle(borderColor:Color, borderAlpha:Int = 0, borderWidth:Int = 1,
                innerColor:Color, innerAlpha:Int = 0,
                lat:String, lon:String, dist:Int) = {
    var tmp = new ArrayBuffer[String]
    tmp += borderColor.getRed().toString()
    tmp += borderColor.getGreen().toString()
    tmp += borderColor.getBlue().toString()
    tmp += borderAlpha.toString()
    tmp += borderWidth.toString()
    tmp += innerColor.getRed().toString()
    tmp += innerColor.getGreen().toString()
    tmp += innerColor.getBlue().toString()
    tmp += innerAlpha.toString()
    tmp += lat
    tmp += lon
    tmp += dist.toString()
    circles += tmp.mkString(",")
  }
  def getParameterString = {
    var tmp = new ArrayBuffer[String]
    tmp += "appid=" + appid
    tmp += "lat=" + lat
    tmp += "lon=" + lon
    tmp += "width=" + width
    tmp += "height=" + height
    tmp += default
    val pin = pins.mkString("&")
    if (pin != "") {
      tmp += pin
    }
    val circle = circles.mkString(":")
    if (circle != "") {
      tmp += "e=" + circle
    }
    tmp.mkString("&")
  }
  def getUrl = baseUrl + "?" + getParameterString
}

object YolpStaticMap {
  def main(args: Array[String]) {
    val ysm = new YolpStaticMap("cV8qsbmxg67L0Z7MV1B7vtwGTL5uf2wHPQhZPkam8Wfjp_.7SpgzAEn9cID00NXUcpqY",
                                "35.658619279712", "139.74553000746",
                                200, 200)
    ysm.addPin("35.658619279712", "139.74553000746", "")
    ysm.addCircle(new Color(255, 0, 0), 0, 1, new Color(255, 0, 0), 127, "35.658619279712", "139.74553000746", 3000)
    ysm.addPin("35.758619279812", "139.74553000750", "")
    ysm.addCircle(new Color(255, 0, 0), 0, 1, new Color(255, 0, 0), 127, "35.758619279812", "139.74553000750", 3000)
    println(ysm.getUrl)
  }
}
