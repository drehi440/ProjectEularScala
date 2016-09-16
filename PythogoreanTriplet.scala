package com.rehi
//There exists exactly one Pythagorean triplet for which a + b + c = 1000.
//Find the product abc.

object PythogoreanTriplet {
  def main(args: Array[String]): Unit = {
   
    
            def Triplets(x: Long): Unit={
              if(x%2 != 0){
                      var y = (x*x/2)
                      var z = (y+1)
                      var sum = (x*25+y*25+z*25)//the triplet multiple of 25 which is c^2 for the 1st pythagoreaN triplet!
   
                  if(sum == 1000L){
                      println(sum)    
                      println(x*25)
                      println(y*25)
                      println(z*25)
                      println(x*y*z*25*25*25)
                  }
              }
              
              else{
    
                      var yInter = x/2
                      var y = (yInter*yInter-1)
                      var z = (yInter*yInter+1)
                      var sum = ((x*25)+(y*25)+(z*25))
    
    if(sum == 1000L){
                      println(sum)    
                      println(x*25)
                      println(y*25)
                      println(z*25)
                      println(x*y*z*25*25*25)
    }
              
              }

            }
  
  
  for(i <- 1L to 10L){
  Triplets(i)
  }
  
   

  
  

 
  }  
}