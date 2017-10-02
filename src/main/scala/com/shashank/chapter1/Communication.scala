package com.shashank.chapter1

import akka.actor.{ActorRef, Props}

object Messages{
  case class Done(randomwNumber: Int)
  case object GiveMeRandomNumber
  case class Start(actorref: ActorRef)
}

import akka.actor.Actor
import scala.util.Random._

class RandomNumberGeneratorActor extends Actor {
  import Messages._
  override def receive: Receive ={
    case GiveMeRandomNumber =>
      println("received a message to generate a random integer")
      val randomNumber = nextInt
      sender ! Done(randomNumber)
  }
}
class QueryActor  extends Actor {
  import Messages._
  override def receive: Receive = {
    case Start(actorRef) => println(s"Send me the next random integer")
    actorRef ! GiveMeRandomNumber

    case Done(randomNumber) =>
      println(s"received a random number $randomNumber")
  }
}
import akka.actor.ActorSystem
object Communication extends App {
  import Messages._
  val actorSystem = ActorSystem("HelloAkka")
  val randomNumberGenerator =
    actorSystem.actorOf(Props[RandomNumberGeneratorActor],"randomnumbergeneratoractor")
  val queryActor = actorSystem.actorOf(Props[QueryActor], "queryActor")
  queryActor ! Start(randomNumberGenerator)
}
