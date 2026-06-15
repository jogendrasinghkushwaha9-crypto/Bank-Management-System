import java.util.Scanner;
import java.sql.*;

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
                    break;
                case 2:
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
                    break;
                case 3:
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
                    break;
                case 4:
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
                    break;
                case 5:
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