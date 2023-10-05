import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class user {
    public String name;
    public String username;
    public String password;
    public String accountNo;
    float balance = 0;
    ArrayList<String> transactionHistory = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);

    public Boolean check_password(String password) {
        if (password.length() < 8)
            return false;
        int character_count = 0;
        int num_count = 0;
        int special_ch = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (isNum(ch))
                num_count++;
            else if (isCharacter(ch))
                character_count++;
            else if (isSpecialCh(password))
                special_ch++;
            else
                return false;

        }
        return (character_count >= 1 && num_count >= 1 && special_ch >= 1);
    }

    public static Boolean isNum(Character ch) {
        return (ch >= '0' && ch <= '9');
    }

    public static Boolean isCharacter(Character ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static Boolean isSpecialCh(String password) {
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher isSpecial = special.matcher(password);
        if (isSpecial.find())
            return true;
        else
            return false;

    }

    public Boolean checkName(String name) {
        int character_count = 0;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (isCharacter(ch))
                character_count++;
            else
                return false;

        }
        return ((character_count >= 1));
    }

    public Boolean check_accountNo(String accountNo) {
        int num_count = 0;
        for (int i = 0; i < accountNo.length(); i++) {
            char ch = accountNo.charAt(i);
            if (isNum(ch))
                num_count++;
            else
                return false;

        }
        return (num_count == 12);
    }

    public void setName() {
        while (true) {
            System.out.print("Enter the name :");
            name = sc.nextLine();
            System.out.println();
            if (checkName(name)) {
                break;
            } else {
                System.out.println("Invalid Name");
            }
        }
    }

    public void setPassword() {
        while (true) {
            System.out.print("Enter the password :");
            this.password = sc.nextLine();
            System.out.println();
            if (check_password(password)) {
                System.out.println("Password Set Successfully !");
                break;
            } else
                System.out.println("Password not follow the Criteria...");
        }
    }

    public void setAccountNo() {
        while (true) {
            System.out.print("Enter the Account No : ");
            this.accountNo = sc.nextLine();
            if (check_accountNo(accountNo)) {
                break;
            } else {
                System.out.println("Invalid AccountNo.. ! AccountNo Must be 12 digits...");
            }
        }
    }

    public void sign_up() {
        setName();
        System.out.print("Enter your Username: ");
        username = sc.nextLine();
        System.out.println();
        System.out.print(
                "Password Criteria ::\n" +
                        "1. A password must have at least eight characters.\n" +
                        "2. A password consists of only letters, Special Characters and digits.\n" +
                        "3. A password must contain at least one digits \n" +
                        "4. A password must contain at least one Special Character \n");
        System.out.println();
        setPassword();
        System.out.println();
        setAccountNo();

        System.out.println();

    }

    public Boolean sign_in() {
        System.out.println();
        System.out.println("Login Using Your Username and Password..!");
        Boolean isLogin = false;
        while (!isLogin) {
            System.out.print("Enter your username: ");
            String user = sc.nextLine();
            System.out.print("Enter your Password: ");
            String pass = sc.nextLine();

            if (user.equals(username) && pass.equals(password)) {
                System.out.println();
                System.out.println("Successfully Login..!");
                isLogin = true;
            } else {
                System.out.println();
                System.out.println("Invalid Username OR Password");
                isLogin = false;
            }
        }
        return isLogin;
    }

}

class atm extends user {
    public float checkBalance() {
        return balance;

    }

    public void withdraw() {
        System.out.println();
        System.out.print("Enter the Amount :");
        float enteredAmount = sc.nextFloat();
        if (checkBalance() < enteredAmount) {
            System.out.println();
            System.out.println("Your Account have not Sufficient Balance..");
        } else {
            System.out.println();
            System.out.println("Successfully Withdraw...!");
            balance = balance - enteredAmount;
            String history = "Rs." + enteredAmount + " is Withdraw";
            transactionHistory.add(history);
        }

    }

    public void trans_History() {
        System.out.println();
        int j = 1;
        System.out.println("Your Account Transaction History :");
        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.println(j + ") " + transactionHistory.get(i));
            j++;
        }
        System.out.println();
    }

    public void deposit() {
        System.out.println();
        System.out.print("Enter the Amount:");
        float enteredAmount = sc.nextFloat();
        balance += enteredAmount;
        System.out.println();
        System.out.println("SuccessFully Deposited...!");
        String history = "Rs." + enteredAmount + " is Deposited";
        transactionHistory.add(history);

    }

    public void transfer() {
        System.out.println();
        sc.nextLine();
        System.out.print("Enter Recipient's Name: ");
        String recipient = sc.nextLine();
        System.out.print("\nEnter amount to transfer : ");
        float enteredAmount = sc.nextFloat();

        if (balance < enteredAmount) {
            System.out.println();
            System.out.println("Transfer is Failed");
            System.out.println("Not Sufficient Balance to Transfer");
        } else {
            System.out.println();
            balance -= enteredAmount;
            System.out.println("SuccessFully transferred to " + recipient);
            String history = "Rs." + enteredAmount + " is transferred to " + recipient;
            transactionHistory.add(history);

        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        user atmUser = new user();
        System.out.println();
        System.out.println("Welcome To ATM");
        System.out.println("Register To YourSelf");
        atmUser.sign_up();
        atmUser.sign_in();
        atm ATM = new atm();
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("1. Check Your Balance ");
            System.out.println("2. Withdraw  ");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History ");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("Enter Your Choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n Your Account Balance : " + ATM.checkBalance());
                    break;
                case 2:
                    ATM.withdraw();
                    break;
                case 3:
                    ATM.deposit();
                    break;
                case 4:
                    ATM.transfer();
                    break;
                case 5:
                    ATM.trans_History();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Incorrect Choice, Please Enter Correct Choice");
                    break;
            }

        }

    }
}