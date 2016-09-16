package com.rehi
//Deepesh Rehi: iiimpulse440@gmail.com  
import scala.BigInt
import scala.collection.mutable.ListBuffer

object nthPrime {
 
  
  
  
var listPrime = new ListBuffer[BigInt]
  def main(args: Array[String]): Unit = {
            def isPrime(n: BigInt) = (BigInt(2) until n) forall (n % _ != 0)
            
            for (i <- 1 to 10 if isPrime(i)) listPrime += i


            println(listPrime.length)//lift(10001))
  }
}



/*object NthPrime extends App {

    def isPrime(num: Int): Boolean = {
      !(2 to math.sqrt(num).toInt).exists(num % _ == 0)
    }

  def nextPrimeFrom(num: Int): Int = {
      Iterator.from(num + 1).find(isPrime).get
  }

  val primes: Iterator[Int] = Iterator.iterate(2)(nextPrimeFrom)

  val droppedPrimes = primes.drop(10000)
  println(droppedPrimes.next())
}*/