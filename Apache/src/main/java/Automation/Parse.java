package Automation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.util.List;
public class Parse {
    public static void main(String[] args) {
        try  {
            OutputStream fileOut = new FileOutputStream("exercitiu.xls");
            Workbook wb = new HSSFWorkbook();

            File inputFile = new File("lucratori.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element company = document.getRootElement();
            //punem scrisul bold
            CellStyle styleBold = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            styleBold.setFont(font);


            List<Element> departmentsList = company.getChildren();
            for (int i = 0; i < departmentsList.size(); i++) {
                Element department = departmentsList.get(i);
               //atributul nume si depId punem la sheet
                Attribute depId = department.getAttribute("depId");
                Attribute depName = department.getAttribute("name");
                Sheet sheet =  wb.createSheet(depName.getValue() +" "+  depId.getValue());
                Row rindul  = sheet.createRow(0);

                Cell empid = rindul.createCell(0);
                Cell lastname = rindul.createCell(1);
                Cell firstname= rindul.createCell(2);
                Cell birthdate = rindul.createCell(3);
                Cell managerid = rindul.createCell(4);
                Cell skills = rindul.createCell(5);

                empid.setCellStyle(styleBold);
                lastname.setCellStyle(styleBold);
                firstname.setCellStyle(styleBold);
                birthdate.setCellStyle(styleBold);
                managerid.setCellStyle(styleBold);
                skills.setCellStyle(styleBold);

                empid.setCellValue("empId");
                lastname.setCellValue("lastName");
                firstname.setCellValue("firstName");
                birthdate.setCellValue("birthDate");
                managerid.setCellValue("manager id");
                skills.setCellValue("Skills ");

                List<Element> employeeList = department.getChildren();
                for (int j = 0; j < employeeList.size(); j++) {
                    Element employee = employeeList.get(j);
                    Attribute attribute = employee.getAttribute("empId");
                    Row currentRow = sheet.createRow(j+1);


                    currentRow.createCell(0).setCellValue(attribute.getValue());
                    currentRow.createCell(2).setCellValue(employee.getChild("lastName").getText());
                    currentRow.createCell(1).setCellValue(employee.getChild("firstName").getText());
                    currentRow.createCell(3).setCellValue(employee.getChild("birthDate").getText());
                    currentRow.createCell(4).setCellValue(employee.getChild("managerId").getText());


                    List<Element> skillsList = employee.getChildren("skills").get(0).getChildren();
                    String skilllist = "";

                    for (Element skill : skillsList) {
                        skilllist += skill.getText();
                        skilllist +="\n";
                    }
                    Cell skill = currentRow.createCell(5);
                    CellStyle newline = wb.createCellStyle();
                    newline.setWrapText(true);
                    skill.setCellStyle(newline);
                    skill.setCellValue(skilllist);
                }
                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.autoSizeColumn(3);
                sheet.autoSizeColumn(4);
                sheet.autoSizeColumn(5);
            }
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

}
