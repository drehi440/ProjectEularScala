package com.rehi
import scala.BigInt
//Deepesh Rehi:iiimpulse440@gmail.com


object smallestPostive {
  def main(args: Array[String]): Unit = {

    def check(x:Int): Unit={
        var counter = BigInt(0)
        for(i<- 1 to 20){
            if(x% i == 0){
              
            counter += 1
            }
        }
      
       if(counter !=20){
            check(x+1)
        
        }
        else{
            println(x)
        }
      
    }
    
    check(2520)
    
  }
 }