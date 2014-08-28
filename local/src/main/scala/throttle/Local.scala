package throttle


import akka.actor.{ActorRef,Actor,ActorLogging,ActorSelection,ActorSystem,Props}
import akka.routing._
import scala.concurrent._
import akka.util.Timeout
import scala.collection.mutable
import common._




object LocalApp extends App { 
 
  val system = ActorSystem("throttleLocal")
  val actor : ActorRef = system.actorOf(Props[Local])
   for ( i <- 1 to 10000)
       actor ! MSG(s"message $i")
              
}

class Local extends Actor with ActorLogging{
   import LocalApp._
   import common.Config._
   import common._
   
   val window = 10
   var pending = 0
   val queue = mutable.Queue.empty[Any]
   val remotePath =
	  system.actorSelection("akka.tcp://throttleRemote@127.0.0.1:2552/user/remote")
   val future = remotePath.resolveOne();
   val remote: ActorRef = Await.result(future, duration).asInstanceOf[ActorRef]
   var cnt = 1
   def receive = {
       case ACK  =>
          if ( pending > 0) pending = pending - 1
          if ( queue.size > 0) {
             self ! queue.dequeue() 
           }
       case  msg: MSG =>
          if ( pending == window) {
              queue enqueue msg.x
              log.info( s" queue size ${queue.size}" )
              pending = pending - 1
          }
          else {
            pending += 1
            self ! msg.x
         }
       case msg : String => 
        log.info(s"$cnt local $msg" )
        cnt+=1
        remote ! msg
    }
}