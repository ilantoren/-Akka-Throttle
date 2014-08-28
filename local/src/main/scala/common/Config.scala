package common

/*
http://typesafe.com/activator/template/akka-scala-spring
 */
import akka.util.Timeout
import org.specs2.io.Path

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Config {

  implicit val ec = ExecutionContext.Implicits.global
  implicit val timeOut = Timeout(5 seconds)
  implicit val duration = timeOut.duration
  implicit val path :Path = Path("test")
}

