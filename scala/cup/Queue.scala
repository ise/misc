abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) { println("BasicIntQueue");buf += x }
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) { println("Doubling");super.put(2 * x) }
}

trait Incrementing extends IntQueue {
  abstract override def put (x: Int) { println("Incrementing");super.put(x + 1) }
}

//class MyQueue extends BasicIntQueue with Doubling
class MyQueue extends BasicIntQueue with Doubling with Incrementing

object Main extends App {
  //val queue = new BasicIntQueue
  val queue = new MyQueue
  queue.put(10)
  queue.put(20)
  println(queue.get())
  println(queue.get())
}

