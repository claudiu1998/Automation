
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Write {
    public static void main(String[] args) {
          JSONObject company = new JSONObject();
//          JSONObject obj = new JSONObject();
//           obj.put("name", "Development");
//        obj.put("depId", new Integer(1));

//
        JSONObject dep = new JSONObject();
        dep.put("name"," Development ");
        dep.put("depId","1 ");
       // obj.put("department", dep);

        JSONArray skil= new JSONArray();
        skil.add(0,"skill: Communication ");
        skil.add(1,"skill: Java ");
      //  obj.put("skills", skil);

        JSONObject emp= new JSONObject();
        emp.put("empId","001 ");
        emp.put("lastName","Eminiscu ");
        emp.put("firstName","Mihai ");
        emp.put("birthDate","10.01.1998 ");
        emp.put("position","Department Manager ");
        emp.put("managerId","002 ");

      //  obj.put("employee", emp);
        JSONObject emp1= new JSONObject();
        JSONObject department = new JSONObject();
        emp.put("skills",skil);
        emp1.put("employee",emp);
        //dep.put("department",emp1);
        //Aici trebuie pe dep sal pun ca Jason Array dar nu pot sal introduc ....nustiu cum dep.put sal pun cind e array
        dep.put("employee",emp);
        department.put("department",dep);
        company.put("company",department);





        try (FileWriter file = new FileWriter("lucratori1.json")) {

            file.write(company.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(company);

    }




//
//    public static void main(String[] args) {
//
//        JSONObject company = new JSONObject();
//        JSONObject department = new JSONObject();
//        JSONObject employee1 = new JSONObject();
//        JSONObject employee = new JSONObject();
//        JSONArray skills = new JSONArray();
//
//        skills.add(0,"Creativity");
//        skills.add(1,"Strong attention to detail");
//        skills.add(2,"Good communication skills");
//
//        employee1.put("firstName","Armasula");
//        employee1.put("lastName","Alecsandru");
//        employee1.put("position","Marketing Director");
//        employee1.put("brithDate","03.04.1995");
//        employee1.put("managerId",0);
//
//        employee1.put("skills",skills);
//        employee.put("employee",employee1);
//        department.put("department",employee);
//        company.put("company",department);
//
//
//        try (FileWriter file = new FileWriter("companytest.json")) {
//
//            file.write(company.toJSONString());
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.print(company);
//
//    }
}
