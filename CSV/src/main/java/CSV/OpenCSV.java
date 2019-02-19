package CSV;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
public class OpenCSV {
    public static void main(String[] args) {

        String csvFile = "students.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {

                System.out.println("Student [id= " + line[0] + ", FirstName= " + line[1] + " , LastName=" + line[2] +
                        " , curs= "+line[3] +" , status= "+ line[4]+"]");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
