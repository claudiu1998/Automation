
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
public class Parse {
    public static void main(String[] args) {

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("lucratori.json"));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            JSONObject company = (JSONObject)jsonObject.get("company");
            JSONArray departmentArray = (JSONArray) company.get("department");



            for(int i = 0 ; i < departmentArray.size(); i++)
            {
                JSONObject department = (JSONObject) departmentArray.get(i);
                JSONArray employeesArray  = (JSONArray)department.get("employee");
                System.out.println("Department name : "+department.get("name"));
                System.out.println("Department ID : "+department.get("depId") + "\n");
                for(int j = 0; j < employeesArray.size();j++) {
                    JSONObject employee = (JSONObject) employeesArray.get(j);
                    System.out.println("First name : " + employee.get("firstName"));
                    System.out.println("Last name : " + employee.get("lastName"));
                    System.out.println("Position : " + employee.get("position"));
                    System.out.println("Manager ID : " + employee.get("managerId"));
                    System.out.println("Birth Date : " + employee.get("birthDate"));


                    JSONObject skills = (JSONObject) employee.get("skills");
                    JSONArray skillsarray = (JSONArray) skills.get("skill");
                    System.out.println("Skills : ");
                    for(int k = 0 ; k < skillsarray.size();k++)
                        System.out.println(skillsarray.get(k));

                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
