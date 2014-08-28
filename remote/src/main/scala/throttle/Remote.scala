package throttle


import akka.actor._
import akka.routing._
import common.ACK

object RemoteApp extends App {
  val system = ActorSystem("throttleRemote")
  val actor : ActorRef = system.actorOf(Props[Remote].withRouter(
              RoundRobinRouter(nrOfInstances = 5)), name="remote") 
              
}

class Remote extends Actor with ActorLogging{
   def receive = {
      case msg : String => 
        log.info( msg )
        sender ! ACK
   }
}