package CSV;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
public class CommonsCSV {
    private static final String SAMPLE_CSV_FILE_PATH = "students.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("Id", "FirstName", "LastName", "Curs" , "Status")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                String id=csvRecord.get("Id");
                String firstname=csvRecord.get("FirstName");
                String lastname = csvRecord.get("LastName");
                String curs = csvRecord.get("Curs");
                String status = csvRecord.get("Status");


                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Id : " + id);
                System.out.println("FirstName : " + firstname);
                System.out.println("LastName : " + lastname);
                System.out.println("Curs : " + curs);
                System.out.println("Status : " + status);
                System.out.println("---------------\n\n");
            }
        }
    }
}
