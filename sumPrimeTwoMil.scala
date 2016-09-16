package com.rehi

import scala.collection.mutable.ListBuffer
import scala.BigInt
// PROBLEM : 10The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
//Find the sum of all the primes below two million.

object sumPrimeTwoMil {
 
  var r = (2 to 1999993)
  
var listPrime = new ListBuffer[BigInt]

  
  def main(args: Array[String]): Unit = {
   val sdf = Test
 
    var last = 2
            def isPrime(n: BigInt) = (BigInt(2) until n) forall (n % _ != 0)
           var one =1
           one +=1
            for (i <- 3 to 100000 if(i%2 !=0) if isPrime(i)) listPrime += i
    //for (i <- List.range(3, 12) if i % 2 != 0 && isPrime(i)) yield last+=i

           var st = 454396535
           
            
            println(listPrime.sum)//lift(10001))
  }
}
  
