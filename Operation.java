import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Operation{
    Connection conn;
    Statement st;
    ResultSet rs;
    int n;
    float amt;
    String name;
    Scanner sc=new Scanner(System.in);
    Operation(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
            st=conn.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    void CreateAccount(){
        try{
            System.out.println("Enter your name : ");
            name=sc.nextLine();
            System.out.println("Enter Deposit Amount : ");
            amt=sc.nextFloat();
            n=st.executeUpdate("insert into account(name,balance) values('"+name+"',"+amt+")");
            if(n>0){
                rs=st.executeQuery("select * from account where name='"+name+"'");
                rs.next();
                System.out.println("Account Opened\n your Account number is "+rs.getInt("acc_no"));
            }
            else
            {
                System.out.println("Account Not Opened");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    void ShowDetail(){
        try{
            System.out.println("Enter your Account number : ");
            n=sc.nextInt();
            rs=st.executeQuery("select * from account where acc_no="+n);
            if(rs.next())
            {
                System.out.println("Account number is "+rs.getInt("acc_no")+" and Account HolderName is "+rs.getString("name")+" and Available balance is "+rs.getFloat(3));
            }
            else{
                System.out.println("Account is not Exist !");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    void Deposit(){
        try{
            System.out.println("Your Account number : ");
            n=sc.nextInt();
            System.out.println("how many deposit : ");
            amt=sc.nextInt();
            n=st.executeUpdate("update account set balance=balance+"+amt+" where acc_no="+n);
            if(n>0){
                System.out.println("Amount added");
            }
            else{
                System.out.println("Amount not added");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    void withdraw(){
        try{
            System.out.println("Your Account number : ");
            n=sc.nextInt();
            System.out.println("how many Withdraw : ");
            amt=sc.nextInt();
            rs=st.executeQuery("select * from account where acc_no="+n);   
            rs.next();         
            if(rs.getFloat(3)>=amt){
                n=st.executeUpdate("update account set balance=balance-"+amt+" where acc_no="+n);
                if (n>0)
                {
                    System.out.println("withdraw successfull");
                }
                else{
                    System.out.println("withdraw unsuccessfull");
                }
            }
            else{
                System.out.println("you have no available balance");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    void DeleteAccount(){
        try{
            System.out.println("Enter Account number: ");
            n=sc.nextInt();
            rs=st.executeQuery("select * from account where acc_no="+n);            
            if(rs.next()){
                n=st.executeUpdate("delete from account where acc_no="+n);
                if(n>0){
                System.out.println("Deletion successfull");
                }
                else{
                    System.out.println("Deletion unsuccessfull");
                }
            }
            else
            {
                System.out.println("Account not exist !");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}