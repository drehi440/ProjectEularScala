import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by DRehi on 9/30/2016.
  * ProjectEular Problem no. 11: greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?
  * refereces to get through the solution:http://alvinalexander.com/scala/how-to-walk-scala-collections-reduceleft-foldright-cookbook
  */
object LargProdGrid {

  def main(args: Array[String]): Unit = {
    println("THIS IS PROBLEM NO 11")
    var sum = 1
    val readFileGrid = Source.fromFile("C:\\Users\\drehi\\workspace\\untitled\\Grid.txt")
    val calcGridProd = readFileGrid.getLines().map(x=> x.split(" ")).map(y=> y(0)).toList
//    println(calcGridProd.getClass())
//    println(calcGridProd)
//    calcGridProd.map(x=> println(x))

    val readFileGrid1 = Source.fromFile("C:\\Users\\drehi\\workspace\\untitled\\src\\testGrid.txt")//read the file
    val calcGridProd1 = readFileGrid1.getLines().map(x=> x.split(" ")).map(y=> y(2)).toList//create a list with nth place elements only

    calcGridProd1 foreach println

    //FOR LOOPING THE LIST[LIST[STRING]]
    /*var b:List[Long] = List()
    for(i<- 0.toLong to 10.toLong){

    }*/
    var trick = readFileGrid1.getLines().


/*
  val Grid = readFileGrid1.getLines().map(x=> x.split(" ")).map(y
    => y(0)).toList.map(x=> x.toInt).reduceLeft(_ * _)
//    Grid foreach println
*/



    //---------------------------------------------------------------------TEST----------------------------

    //for GRID LIKE
    //FOLLOW THESE STEPS:
    //Prepare the desired List with the required numbers
    //apply sliding
    //then calculate the sum,product or anything that you want as per the convenience

    //TAKE A LOOK AT THE SAMPLE EXAMPLE:


    /*val all = List(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4)
    val sum1 = all.sliding(4).toList
    sum1 foreach println

    var a:List[Long] = List()
    for(x<-sum1){

      a = a ++ List(x.foldLeft(1L)((r,c)=> r.toLong * c.toLong))
    }

    val maxA = a.max
    println(maxA)


    *///---------------------------------------------------------------------TEST----------------------------














  }
}
