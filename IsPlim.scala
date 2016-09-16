package com.rehi
//iiimpulse440@gmail.com
object IsPlim {
  def main(args: Array[String]): Unit = {
       
    def palin(x: String) = x zip (x.reverse) forall(y => y._1 == y._2)
    
    val as = for {
      i <- 100 to 999;
      j <- 100 to 999
    } yield i*j
    
    
    println(as.filter{ z => palin(z.toString) }.max)
  }
}