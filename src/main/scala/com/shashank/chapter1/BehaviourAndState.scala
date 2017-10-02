package com.shashank.chapter1

import akka.actor.{Actor, ActorSystem, Props}

class SummingActor extends Actor {
  // state inside the Actor
  var sum = 0
  //behaviour which is applied to the state
  override def receive: Receive = {
    //receives an interger message
    case x:Int => sum = sum + x
    println(s"My state as Sum is $sum")
    // receives default message
    case _ => println("Unknown!!")

  }
}

object BehaviourAndState extends App{
  val actorSystem = ActorSystem("HelloAkka")
  //creating an actor inside the Actor System
  val actor = actorSystem.actorOf(Props[SummingActor])
  //print actor path
  println(actor.path)

}
