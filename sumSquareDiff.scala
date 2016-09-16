package com.rehi
//Deepesh Rehi:iiimpulse440@gmail.com
import scala.collection.mutable.ListBuffer

object sumSquareDiff {
  def main(args: Array[String]): Unit = {
    
    val sqListOfNatNum = new ListBuffer[BigInt]
    val sumListOfNatNum = new ListBuffer[BigInt]
    
    
    for(i<- 1 to 100){
        sqListOfNatNum += i*i
        sumListOfNatNum += i
      }
 
    def Diff(x: BigInt, y:BigInt):Unit={
        println(x-y)
      }
      
      Diff(sumListOfNatNum.sum*sumListOfNatNum.sum,sqListOfNatNum.sum)
      
      
  }
}