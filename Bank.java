import java.util.Scanner;

public class Bank{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Operation op=new Operation();
        while (true) {
            System.out.println("-----Bank Management System-----");            
            System.out.println("1 Create Bank Account ....");
            System.out.println("2 View Account Detail ....");
            System.out.println("3 Deposit ....");
            System.out.println("4 Withdraw ....");
            System.out.println("5 Delete Account ....");
            System.out.println("6 Exit ....\n");
            System.out.println("what operation do you perform : "); 
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    op.CreateAccount();
                    break;
                case 2:
                    op.ShowDetail();
                    break;
                case 3:
                    op.Deposit();
                    break;
                case 4:
                    op.withdraw();
                    break;
                case 5:
                    op.DeleteAccount();
                    break;
                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Inavalid Choice");

            }
        }
    }
}
