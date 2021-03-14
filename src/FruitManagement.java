
import java.io.File;
import java.util.Scanner;
import org.ini4j.Ini;
import org.ini4j.Wini;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class FruitManagement {
    static Scanner sc = new Scanner(System.in);
    
    static FruitList fruitList = new FruitList();
    
    static Wini staffData;
    
    static String fileName = "StaffData.ini";
    
    static File staffDataPath = new File(fileName);
    
    static void entryStaffMenu(){
        
        int choice;
        
        do {            
            System.out.println("============");
            System.out.println("1. Login as Staff");
            System.out.println("2. Logup new Staff");
            System.out.println("3. Exit");
            System.out.println("============");
            
            while(true){
                try {
                    choice = Integer.parseInt(sc.next());
                    if(!(choice+"").matches("^[0-9]*$")){
                        throw new Exception("choiceChar");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Choice must be numbers");
                }
            }
            
            switch(choice){
                case 1:
                    loginFrame();
                    break;
                case 2:
                    logupFrame();
                    break;
                case 3:
                    mainMenu();
                    break;
                default:
                    break;
            }
            
        } while (choice != 3);
    }
    
    static void loginFrame(){
        if(staffDataPath.exists()){
            System.out.println("Login Frame");
            System.out.println("Enter account:");
            String account = sc.next();
            System.out.println("Enter password: ");
            String password = sc.next();
            try {
                staffData = new Wini(staffDataPath);
                
                if(staffData.get("StaffInfo", "Account").equals(account) && staffData.get("StaffInfo", "Password").equals(password)){
                    menuStaff();
                } else {
                    System.out.println("Wrong login infomation !");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Database Empty");
            logupFrame();
        }
    }
    
    static void logupFrame(){
        try {
            if(!staffDataPath.exists()){
                staffDataPath.createNewFile();
                staffData = new Wini(staffDataPath);
                System.out.println("Logup Frame");
                System.out.println("Enter account: ");
                String account = sc.next();
                System.out.println("Enter password: ");
                String password = sc.next();

                staffData.put("StaffInfo", "Account", account);
                staffData.put("StaffInfo", "Password", password);
                staffData.store();
            }else {
                System.out.println("Acount have been created yet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void menuStaff(){
        int choice;
        int numLoop = 0;
        
        do {         
            System.out.println("===================");
            System.out.println("1. Add Fruit");
            System.out.println("2. Show Fruit List");
            System.out.println("3. Edit Fruit");
            System.out.println("4. Delete Fruit");
            System.out.println("5. View Orders");
            System.out.println("6. Save and exit");
            System.out.println("7. Exit");
            System.out.println("=======================");
            
            while(true){
                try {
                    choice = Integer.parseInt(sc.next());
                    if(!(choice+"").matches("^[0-9]*$")){
                        throw new Exception("choiceChar");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Choice must be numbers");
                }
            }
            
            switch(choice){
                case 1:
                    while(true){
                        try {
                            System.out.println("Number of Fruit to add: ");
                            numLoop = Integer.parseInt(sc.next());
                            if(!(numLoop+"").matches("^[0-9]*$")){
                                throw new Exception("numLoopChar");
                            }
                            break;
                        } catch (Exception e) {
                            if(e.getMessage().contains("numLoopChar")){
                                System.out.println("Number of Fruit must be numbers");
                            }
                        }
                    }
                    fruitList.addFruit(numLoop);
                    break;
                case 2:
                    fruitList.showList();
                    break;
                case 3:
                    System.out.println("======================");
                    System.out.println("1. Edit by Fruit Name");
                    System.out.println("2. Edit by Fruit ID");
                    System.out.println("3. Exit");
                    System.out.println("======================");
                    int editOptions = Integer.parseInt(sc.next());
                    
                    switch(editOptions){
                        case 1:
                            System.out.println("Enter FruitName to Edit: ");
                            String nameEdit = sc.next();
                            fruitList.editFruitByName(nameEdit);
                            break;
                        case 2:
                            System.out.println("Enter FruitID to Edit");
                            int idEdit = Integer.parseInt(sc.next());
                            fruitList.editFruitByID(idEdit);
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Enter ID to delete Fruit");
                    int idDelete = Integer.parseInt(sc.next());
                    fruitList.deleteFruit(idDelete);
                    break;
                case 5:
                    if(!fruitList.table.isEmpty()){
                        fruitList.viewOrders();
                    } else {
                        System.out.println("Orders is Empty");
                    }
                    break;
                case 6:
                    fruitList.saveData();
                    System.out.println("Saved!");
                    mainMenu();
                    break;
                case 7:
                    mainMenu();
                    break;
            }
        } while (choice != 7);
    }
    
    static void menuCustomer(){
        int choice;
        
        do {            
            System.out.println("=============");
            System.out.println("1. Order Fruit");
            System.out.println("2. Exit");
            System.out.println("=============");
            
            while(true){
                try {
                    choice = Integer.parseInt(sc.next());
                    if(!(choice+"").matches("^[0-9]*$")){
                        throw new Exception("choiceChar");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Choice must be numbers");
                }
            }
            
            switch(choice){
                case 1:
                    fruitList.customerManage();
                    break;
            }
            
        } while (choice!=2);
    }
    
    static void mainMenu(){
        int choice = 0;
        
        do {
            System.out.println("=======================");
            System.out.println("1. Login as Staff");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            System.out.println("=======================");
            
            while(true){
                try {
                    choice = Integer.parseInt(sc.next());
                    if(!(choice+"").matches("^[0-9]*$")){
                        throw new Exception("choiceChar");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Choice must be numbers");
                }
            }
            
            switch(choice){
                case 1:
                    entryStaffMenu();
                    break;
                case 2:
                    menuCustomer();
                    break;
            }
        } while (choice != 3);
    }
    
    public static void main(String[] args) {
        fruitList.voucherCreate();
        fruitList.loadData();
        mainMenu();
    }
}
