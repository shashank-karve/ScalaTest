package com.shashank.chapter1

import akka.actor.Actor

class FibonacciActor extends Actor{
  override def receive: Receive = {
    case num: Int =>
      val fibonacciNumber = fib(num)
      sender ! fibonacciNumber

  }

  def fib( n : Int) : Int = n match {
    case 0 | 1 => n
    case _ => fib( n-1 ) + fib( n-2 )

  }
}

import akka.actor.{Props, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Await

object FibonacciActorApp extends App {
  implicit val timeout = Timeout(10 seconds)
  val actorSystem = ActorSystem("Fibonacci_Akka")
  val actor = actorSystem.actorOf(Props[FibonacciActor])

  //asking the result from actor
  val future = (actor ? 10).mapTo[Int]
  val fibonacciNumber = Await.result(future, 10 seconds)
  println(fibonacciNumber)
}
