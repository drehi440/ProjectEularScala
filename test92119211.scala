import java.io.{ BufferedWriter, FileWriter }
import scala.io.Source
import util.control.Breaks._



object TestDemo_UniqueRecords {

  def main(args: Array[String]): Unit = {

    var UpdatedFileData = ""
    
    // Reading the CSV File
    
    val readFile = Source.fromFile("C:\\Users\\gagrawal2\\workspace\\Scala_demo\\SCALA_DEMO\\src\\testTable")
    
    // Writing the data in New CSV File
    
    val file = new FileWriter("C:\\Users\\gagrawal2\\workspace\\Scala_demo\\SCALA_DEMO\\src\\testTable1.txt")
    var LineAsString = ""

    val readFileLines = readFile.getLines.map(x => x.split("\t"))

    var vuff = new BufferedWriter(file)
    vuff.append("phl_phi_id\tphi_description\tphl_id\tphl_level_node_char\tphl_level_node_total_char\n")
    
    // Declaring the list for maintaing the unique records
    
    var data: List[String] = List()
    var uniqueCombination: List[Any] = List()
      // Iterating over the CSV File Data
    for (e <- readFileLines) {

     LineAsString = e mkString "\t"
        
      // Checking the Columns and updating the data in another/New column
      
      if (e(3) == "null")
        UpdatedFileData = "SUPERCATEGORY"
      else
        UpdatedFileData = UpdatedFileData + " | " + e(3)
         
        //Updating the file with Unique records
        
        
      breakable {
        if (uniqueCombination.contains(UpdatedFileData))
          break
        else {
          data = data ::: List(LineAsString + "\t" + UpdatedFileData)
          uniqueCombination = uniqueCombination ::: List(UpdatedFileData)
        }
      }

    }
    data = data.distinct

    // Iterating the new File
    for (x <- data)
      vuff.append(x + "\n")
// closing the file
    vuff.close()

  }
}
