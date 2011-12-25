import org.scalatra._
import scalate.ScalateSupport
import java.sql.{DriverManager, Connection, Statement, ResultSet,SQLException}

class ScalateSample extends ScalatraServlet with ScalateSupport {
  get("/") {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    val con = DriverManager.getConnection("jdbc:mysql://localhost/under_hair?user=under_hair&password=uhadm1n")
    val stmt = con.createStatement()
    val rs = stmt.executeQuery("select * from porn_words_of_wisdom limit 10")
    var resultList: List[String] = Nil
    //var resultList: Map[String,String] = 
    while (rs.next()){
      resultList = (rs.getString(2) + " " + rs.getString(3)) :: resultList
    }
    stmt.close()
    con.close()

    contentType = "text/html"
    templateEngine.layout("WEB-INF/layouts/default.ssp", Map("title" -> "title!!", "resultList" -> resultList))
  }

  get("/scalatra/") {
    <html>
    <body>
    <h1>Hello, scalatra!</h1>
    </body>
    </html>
  }

}
