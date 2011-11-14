import java.util.Calendar

if (args.length != 2) {
  println("usage: scala GenHowmDate.scala 2011 12")
  sys.exit()
}

val cal = Calendar.getInstance()
cal.set(args(0).toInt, args(1).toInt - 1, 1)
getHowmDateList(cal).foreach(println)


def getHowmDateList(cal: Calendar): List[String] = {
  val mon = cal.get(Calendar.MONTH)
  val howmDate = "[%tY-%<tm-%<td]@(%<ta)".format(cal.getTime())
  cal.add(Calendar.DATE, 1)
  if (mon != cal.get(Calendar.MONTH)) {
    return howmDate :: Nil
  }
  return howmDate :: getHowmDateList(cal)
}

