package Lab2;

import com.thoughtworks.xstream.XStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class BNM {
    public static void main(String[] args) {

        try {
            //Read dates from file
            File dates = new File("data.txt");
            FileReader filerider = new FileReader(dates);
            BufferedReader buferrider = new BufferedReader(filerider);
            List<String> inputDates = new LinkedList<>();
            String line;
            while((line = buferrider.readLine()) != null)
            {
                inputDates.add(line);
            }
            filerider.close();
            buferrider.close();
            //create workbook
            OutputStream fileOut = new FileOutputStream("bnm.xls");
            Workbook workbook = new HSSFWorkbook();

            for(int i = 0;i < inputDates.size(); i++)
            {
                String urldate = "https://bnm.md/en/official_exchange_rates?get_xml=1&date=";
                urldate += inputDates.get(i);

                URL url = new URL(urldate);
                //make connection
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();

                //post mode
                connect.setDoOutput(true);
                connect.setAllowUserInteraction(false);
                //get xml from bnm site
                BufferedReader buferrider_get = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String xml = new String();
                String l;
                while((l = buferrider_get.readLine()) != null)
                {
                    xml += l;
                }
                buferrider_get.close();


                XStream xstream = new XStream();
                xstream.processAnnotations(ValCurs.class);
                xstream.processAnnotations(Valute.class);

                ValCurs valCurs = (ValCurs)xstream.fromXML(xml);



                //create sheet with date as sheet name
                Sheet sheet = workbook.createSheet(inputDates.get(i));
                //create first row with tag names from request
                Row row = sheet.createRow(0);
                //5 columns NumCode,CharCode,Nominal,Name,Valute
                Cell numCode = row.createCell(0);
                Cell charCode = row.createCell(1);
                Cell nominal = row.createCell(2);
                Cell name = row.createCell(3);
                Cell valute = row.createCell(4);

                numCode.setCellValue("NumCode");
                charCode.setCellValue("CharCode");
                nominal.setCellValue("Nominal");
                name.setCellValue("Name");
                valute.setCellValue("Valute");

                //next rows for actual values
                int rowCount = 1;
                for(Valute currentVal : valCurs.getValutes())
                {
                    Row valuteRow  = sheet.createRow(rowCount);
                    valuteRow.createCell(0).setCellValue(currentVal.getNumCode());
                    valuteRow.createCell(1).setCellValue(currentVal.getCharCode());
                    valuteRow.createCell(2).setCellValue(currentVal.getNominal());
                    valuteRow.createCell(3).setCellValue(currentVal.getName());
                    valuteRow.createCell(4).setCellValue(currentVal.getValue());
                    System.out.println("Current row in sheet " + valuteRow.getRowNum());
                    System.out.println(currentVal);
                    Thread.sleep(300);
                    rowCount++;
                }

                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);

                xml = "";
                rowCount = 1;
                System.out.println("\n");

            }
            workbook.write(fileOut);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


    }


}
