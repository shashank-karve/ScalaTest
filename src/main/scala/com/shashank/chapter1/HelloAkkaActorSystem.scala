package com.shashank.chapter1

import akka.actor.ActorSystem

object HelloAkkaActorSystem extends App {
  val actorsystem = ActorSystem("HelloWorld")
  println(actorsystem)
}