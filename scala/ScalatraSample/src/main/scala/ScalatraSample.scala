import org.scalatra._

class ScalatraSample extends ScalatraServlet {
  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
      </body>
    </html>
  }

}
