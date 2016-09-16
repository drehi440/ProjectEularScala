package com.rehi
//Code: Bala:  balamanikandant2016@gmail.com and Deepesh iiimpulse440@gmail.com
import scala.collection.mutable.ListBuffer

object largPalim {
  def main(args: Array[String]): Unit = {
    
    
    var palimList:List[(Int, String)] = List.empty[(Int, String)]
    
    
      for(i <- (900 to 999).reverse){
    palim(999,i)
      }
    
      def palim(x: Int,i: Int): Unit={
        if(x > 899){
            var as = (i.toString() ++ i.toString().reverse).toInt
            if(as % x == 0){
               val z:List[(Int, String)] = List((x.toInt, as.toString()))
              palimList = palimList ++ z //Map(as -> x )
              }
            palim(x-1,i)
        }
      }  
  
     var e:Map[String, String] = Map() 
       palimList.map { r =>
               //println(r._1)
               //println(r._2)s
               val z2 = palimList.filter(p => p._2 == r._2)
               if(z2.size == 2){
                 z2.map{ k =>
                   e = e ++ Map(k._1.toString() -> k._2.toString())                   
                 }
               }
               
             }
       
       e.map{q => println("Dup:"+ q._1 +"\tVal:"+ q._2)}

     
     
  }
  
}

