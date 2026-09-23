package JDBCCC.com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeService {
	
	
    private static final String URL = "jdbc:mysql://localhost:3306/Test";
    private static final String USER = "root";
    private static final String PASSWORD = "Example@2024";
    private static final String INSERT_SQL = "INSERT INTO employees (name, email, salary) VALUES (?, ?, ?)";

      static Employee e = new Employee();
      static Scanner sc = new Scanner(System.in);
      
      //Save Employee
      public void saveEmployee() throws SQLException, ClassNotFoundException{
	  
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	  
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Database connected Successfully .........");
        
        e.setEmail("p@gmail.com");
        e.setName("Prathamesh"); 
        e.setSalary(25000);
        
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
        

        preparedStatement.setString(1, e.getName());
        preparedStatement.setString(2, e.getEmail());
        preparedStatement.setInt(3, e.getSalary());

    
        int rowsInserted = preparedStatement.executeUpdate();
        System.out.println("Rows inserted: " + rowsInserted);


        preparedStatement.close();
        connection.close();
        
        System.out.println("Employee record saved successfully!");
        
    }
      
      
      //Get Employee Retrive Employee
      public void getEmployee() throws SQLException {
    	  Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
    	  PreparedStatement ps = con.prepareStatement("Select * from employees");
    	  ResultSet rs= ps.executeQuery();
    	  while(rs.next()) {
    		  System.out.println(rs.getString("Id")+" | "+rs.getString("name")+" | "+rs.getString("email")+" | "+rs.getString("salary"));
    	  }
    	  ps.close();
          con.close();
      }
      
      
      //Update Employee 
      public void updateEmployee() throws SQLException {
    	  Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
    	  PreparedStatement ps =con.prepareStatement("update employees set name=?,email=?,salary=? where id=? ");
    	  
    	  
    	  System.out.print("Update Employee Id:");
    	  int upId=sc.nextInt();
    	  e.setId(upId);
    	  ps.setInt(1, e.getId());
    	  
    	  System.out.print("Enter Name:");
    	  String name=sc.next();
    	  System.out.print("Enter Email:");
    	  String email=sc.next();
    	  System.out.print("Enter Salary:");
    	  int salary=sc.nextInt();
    	  
    	  e.setName(name);
    	  e.setEmail(email);
    	  e.setSalary(salary);
    	  
    	  ps.setString(1, name);
    	  ps.setString(2, email);
    	  ps.setInt(3, salary);
    	  ps.setInt(4, upId);
    	  
    	  int update= ps.executeUpdate();
    	  System.out.println("Updated Record ..."+upId);
    	 
    	  
    	 
    	  
    	  
    	  ps.close();
    	  con.close();
      }
      
      
      
      //Delete Employee
      public void deleteEmployee() throws SQLException {
    	  Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
    	  System.out.print("Enter Id :");
    	  int delId=sc.nextInt();
    	  e.setId(delId);
    	  PreparedStatement ps= con.prepareStatement("delete from employees where id=?");
    	  
    	  ps.setInt(1, e.getId());
    	  
    	  int delrecord = ps.executeUpdate();
    	  System.out.println("Deleted Record Sucessfully .."+delId);
    	  
    	  ps.close();
          con.close();
    	  
      } 
      
      
      
      
      
      public static void main(String[] args) throws SQLException, ClassNotFoundException {
		EmployeeService e = new EmployeeService();
		
		
		while(true) {
			System.out.println("1.Save Employee \n2.View Employee \n3.Remove Employee \n4.Update Employee \n5.Exit");
			System.out.print("\n\nEnter Your Choice :");
			int ch=sc.nextInt();
			
			switch(ch) {
			case 1:
				e.saveEmployee();
				break;
				
			case 2:
				e.getEmployee();
				break;
				
			case 3:
				e.updateEmployee();
				break;
				
			case 4:
				e.deleteEmployee();
				break;
				
			case 5:
				System.out.println("Exit The JDBC.......");
				return;
				
			default :
				System.out.println("Invalid Choice , Please Try Again ........");
				
			}
		}
	}
}