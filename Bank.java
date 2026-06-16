import java.util.Scanner;
import java.sql.*;
class BankOperation {
    connection con=new connection();
    Scanner sc=new Scanner(System.in);
    void OpenAccount(){
        sc.nextLine();
        System.out.println("Enter Account holder name : ");
        con.accountHolderName=sc.nextLine();
        System.out.println("Enter Deposit Amount : ");
        con.tmpamount=sc.nextDouble();
        try{
            con.n=con.st.executeUpdate("insert into account(name,balance) values('"+con.accountHolderName +"',"+con.tmpamount+")");
            if(con.n>0)
            {
                System.out.println("Account Opened Succefully");
            }
            else{
                System.out.println("Account Opened UnSuccefully");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void ShowDetail(){
        sc.nextLine();
        System.out.println("Enter Account Holder Name : ");
        con.accountHolderName=sc.nextLine();
        try{
            con.rs=con.st.executeQuery("select * from account where name='"+con.accountHolderName+"'");
            if(con.rs.next())
            {
                System.out.println(con.rs.getInt(1)+" "+con.rs.getString(2)+" "+con.rs.getDouble(3));
            }
            else{
                System.out.println("Account does not exist");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void Deposit(){
        sc.nextLine();
        System.out.println("Enter Account Holder Name : ");
        con.accountHolderName=sc.nextLine();
        System.out.println("Enter Amount of Deposit : ");
        con.tmpamount=sc.nextDouble();
        try{
            con.n=con.st.executeUpdate("update account set balance =balance+"+con.tmpamount+" where name='"+con.accountHolderName+"'");
            if(con.n>0)
            {
                System.out.println("Amount Added Successfully");
            }
            else{
                System.out.println("Amount Added UnSuccefully");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void WithDraw(){
        sc.nextLine();
        System.out.println("Enter Account Holder Name : ");
        con.accountHolderName=sc.nextLine();
        System.out.println("Enter withdraw Amount : ");
        con.tmpamount=sc.nextDouble();
        try{
            con.rs=con.st.executeQuery("select * from account where name='"+con.accountHolderName+"'");
            if(con.rs.next()){
                if(con.rs.getDouble(3)>=con.tmpamount)
                {
                    con.st.executeUpdate("update account set balance =balance-"+con.tmpamount+" where name='"+con.accountHolderName+"'");
                    System.out.println("Amount withdraw Successfully");
                }
                else{
                    System.out.println("Balance is not available");
                }
            }
            else{
                System.out.println("Amount withdraw UnSuccefully");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    void DeleteAccount(){
        sc.nextLine();
        System.out.println("Enter Account Holder Name : ");
        con.accountHolderName=sc.nextLine();
        try{
            con.n=con.st.executeUpdate("delete from account where name='"+con.accountHolderName+"'");
            if(con.n>0)
            {
                System.out.println("Acount deleted Successfully");
            }
            else{
                System.out.println("Account deleted UnSuccefully");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
class connection{
    Connection conn;
    Statement st;
    ResultSet rs;
    int n,choice;
    double tmpamount;
    String accountHolderName;
    connection(){
        try{
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
        st=conn.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
public class Bank{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        connection con=new connection();
        BankOperation bo=new BankOperation();
        while(true)
        {
            System.out.println("Bank Management System\n");
            System.out.println("1 Create Bank Account ....");
            System.out.println("2 View Account Detail ....");
            System.out.println("3 Deposit ....");
            System.out.println("4 Withdraw ....");
            System.out.println("5 Delete Account ....");
            System.out.println("6 Exit ....\n");
            System.out.println("what operation do you perform : ");
            con.choice=sc.nextInt();
            switch (con.choice) {
                case 1:
                    bo.OpenAccount();
                    break;
                case 2:
                    bo.ShowDetail();;
                    break;
                case 3:
                    bo.Deposit();
                    break;
                case 4:
                    bo.WithDraw();
                    break;
                case 5:
                   bo.DeleteAccount();;
                    break;
                case 6:
                    System.exit(0);
                    break;
            
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }
    }
}