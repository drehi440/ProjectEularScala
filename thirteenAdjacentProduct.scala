package com.rehi



//Deepesh Rehi:iiimpulse440@gmail.com
//stored the 1000 digit number in a file and read the file saved each digit to a LIST

object thirteenAdjacentProduct {
  def main(args: Array[String]): Unit = {
    
    val digit = """([0-9])""".r
    
    val all = io.Source.fromFile("C:\\Users\\429553\\Desktop\\asd.txt").getLines.flatMap(digit.findAllIn).toList
println(all)
    var sum = all.sliding(4).toList.head
    println(sum)
    var a:List[Long] = List()
   for(e <- sum){
     
//     a.filter(s => s != 0)
      a = a ++ List(e.foldLeft(1L)((r,c) => r.toLong + c.toLong))
    }
    println(a.filter(s => s != 0))
  }
}