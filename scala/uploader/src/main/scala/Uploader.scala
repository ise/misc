package com.example

import unfiltered.request._
import unfiltered.response._
import unfiltered.filter.request._

class Uploader extends unfiltered.filter.Plan {
  def intent = {
    case GET(Path("/upload")) => Ok ~> Html(
<html>
    <head>
        <meta charset="utf-8" />
        <title>HTML5 and jQuery and Unfiltered Uploader</title>
        <link rel="stylesheet" href="assets/css/base.css" type="text/css" /> 
    </head>
    <body>
	<header><h1>HTML5 and jQuery and Unfiltered Uploader</h1></header>
	<div id="dropbox">
		<span class="box">Drop here</span>
	</div>
	<script src="assets/js/jquery-1.7.2.min.js"></script>
	<script src="assets/js/jquery.filedrop.js"></script>
	<script src="assets/js/script.js"></script>
    </body>
</html>)
    case POST(Path("/upload") & MultiPart(req)) =>
      MultiPartParams.Streamed(req).files("file") match {
        case Seq(file, _*) if !file.name.isEmpty => {
          val tmpfile = new java.io.File("hoge")
          file.write(tmpfile)
          Ok ~> JsonContent ~> ResponseString("""{"status":"ok"}""")
        }
        case f => BadRequest ~> JsonContent ~> ResponseString("""{"status":"error"}""")
      }
  }
}

object Server {
  def main(args: Array[String]) {
    unfiltered.jetty.Http(19830).context("/assets") {
      _.resources(new java.net.URL(getClass().getResource("/www/css"), "."))
    }.filter(new Uploader).run
  }
}
