
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import org.ini4j.Wini;
import org.w3c.dom.NameList;
import sun.util.calendar.CalendarUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris2k aka Nhat Tien
 */
public class FruitList {
    ArrayList<Fruit> fruitList = new ArrayList<>();
    ArrayList<Fruit> listChoice;
    ArrayList<String> nameList = new ArrayList<>();
    
    static File fruitData = new File("FruitData.txt");
    
    static Wini voucherData;
    static File voucherDataPath = new File("VoucherData.ini");
    
    Hashtable<String, ArrayList<Fruit>> table = new Hashtable<>();
    
    static Scanner sc = new Scanner(System.in);
    
    public void addFruit(Fruit data){
        fruitList.add(data);
    }
    
    public void addFruit(int numLoop){
        int price, numStorage;
        String ID, name, origin;
        for(int i = 0; i<numLoop; i++){

            while(true){
                try {
                    System.out.println("Enter ID: ");
                    ID = sc.next();
                    if(ID.equalsIgnoreCase("stop")){
                        return;
                    } else {
                        if(!ID.matches("^[0-9]*$")){
                            throw new Exception("IDChar");
                        }
                        for(int j = 0; j<fruitList.size(); j++){
                            if(Integer.parseInt(ID) == fruitList.get(j).getID()){
                                throw new Exception("IDExist");
                            }
                        }
                        break;
                    }
                } catch (Exception e) {
                    if(e.getMessage().contains("IDExist")){
                        System.out.println("ID exist");
                    } else if(e.getMessage().contains("IDChar")){
                        System.out.println("ID must be numbers");
                    }
                }
            }

            while(true){
                try {
                    System.out.println("Enter name: ");
                    name = sc.next();
                    if(name.isEmpty()){
                        throw new Exception("nameEmpty");
                    }
                    break;
                } catch (Exception e) {
                    if(e.getMessage().contains("nameEmpty")){
                        System.out.println("Name must not be empty");
                    }
                }
            }

            while(true){
                try {
                    System.out.println("Enter price: ");
                    price = Integer.parseInt(sc.next());
                    if(!(price+"").matches("^[0-9]*$")){
                        throw new Exception("priceChar");
                    }
                    break;
                } catch (Exception e) {
                    if(e.getMessage().contains("priceChar")){
                        System.out.println("Price must be numbers");
                    }
                }
            }

            while(true){
                try {
                    System.out.println("Enter origin: ");
                    origin = sc.next();
                    if(origin.isEmpty()){
                        throw new Exception("originEmpty");
                    }
                    break;
                } catch (Exception e) {
                    if(e.getMessage().contains("originEmpty")){
                        System.out.println("Origin must not be empty");
                    }
                }
            }

            while(true){
                try {
                    System.out.println("Enter number: ");
                    numStorage = Integer.parseInt(sc.next());
                    if(!(numStorage+"").matches("^[0-9]*$")){
                        throw new Exception("NumChar");
                    }
                    break;
                } catch (Exception e) {
                    if(e.getMessage().contains("NumChar")){
                        System.out.println("Number must be numbers");
                    }
                }
            }

            Fruit temp = new Fruit(Integer.parseInt(ID), price, name, origin, numStorage);
            addFruit(temp);
            System.out.println("Add success ID: "+ID);
        }
    }
    
    public void loadData(){
        if(fruitData.exists()){
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(fruitData));
                String line = reader.readLine();
                while(line!=null){
                    
                    String[] split = line.split("\\s");
                    
                    int ID = Integer.parseInt(split[0]);
                    String name = split[1];
                    int price = Integer.parseInt(split[2]);
                    String origin = split[3];
                    int numberStorage = Integer.parseInt(split[4]);
                    
                    Fruit temp = new Fruit(ID, price, name, origin, numberStorage);
                    
                    addFruit(temp);
                    
                    line = reader.readLine();
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    public void saveData(){
        if(fruitData.exists()){
            fruitData.delete();
            try {
                fruitData.createNewFile();
                FileOutputStream fos = new FileOutputStream(fruitData);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                
                for(int i = 0; i<fruitList.size(); i++){
                    writer.write(fruitList.get(i).getID() + " " 
                                    + fruitList.get(i).getName() + " " 
                                    + fruitList.get(i).getPrice() + " "
                                    + fruitList.get(i).getOrigin() + " "
                                    + fruitList.get(i).getNumStorage());
                    writer.newLine();
                }
                writer.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                fruitData.createNewFile();
                FileOutputStream fos = new FileOutputStream(fruitData);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                
                for(int i = 0; i<fruitList.size(); i++){
                    writer.write(fruitList.get(i).getID() + " " 
                                    + fruitList.get(i).getName() + " " 
                                    + fruitList.get(i).getPrice() + " "
                                    + fruitList.get(i).getOrigin() + " "
                                    + fruitList.get(i).getNumStorage());
                    writer.newLine();
                }
                writer.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void showList(){
        int space01 = 5, space12 = 16, space23 = 35, space34 = 51, space45 = 65;
        String stringSpace12, stringSpace23, stringSpace34, stringSpace45;
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ | ++ Number Storage ++ |");
        for(int i = 0; i<fruitList.size(); i++){
            stringSpace12 = "";
            stringSpace23 = "";
            stringSpace34 = "";
            stringSpace45 = "";
            
            for(int j = 0; j<(space12-(""+fruitList.get(i).getID()).length()-space01); j++){
                stringSpace12 += " ";
            }
            for(int j = 0; j<(space23-fruitList.get(i).getName().length()-space12); j++){
                stringSpace23 += " ";
            }
            for(int j = 0; j<(space34-fruitList.get(i).getOrigin().length()-space23); j++){
                stringSpace34 += " ";
            }
            for(int j = 0; j<(space45-(""+fruitList.get(i).getNumStorage()).length()-space34); j++){
                stringSpace45 += " ";
            }
            
            System.out.println("      "
                    + fruitList.get(i).getID() + stringSpace12
                    + fruitList.get(i).getName() + stringSpace23
                    + fruitList.get(i).getOrigin() + stringSpace34
                    + fruitList.get(i).getPrice()+"$" + stringSpace45
                    + fruitList.get(i).getNumStorage());
        }
    }
    
    public void editFruitByName(String name){
        String newName = null, newOrigin = null;
        int newPrice = 0, numStorage = 0;
        int choice;
        for(int i = 0; i<fruitList.size(); i++){

            if(name.equalsIgnoreCase(fruitList.get(i).getName())){
                int newID = fruitList.get(i).getID();

                do{
                    System.out.println("What you want to edit: ");
                    System.out.println("1. Name");
                    System.out.println("2. Price");
                    System.out.println("3. Origin");
                    System.out.println("4. Num storage");
                    System.out.println("5. Edit done");
                    choice = Integer.parseInt(sc.next());

                    switch(choice){
                        case 1:
                            while(true){
                                try {
                                    System.out.println("Enter new Name: ");
                                    newName = sc.next();
                                    if(newName.isEmpty()){
                                        throw new Exception("emptyName");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("nameEmpty")){
                                        System.out.println("Name must not be empty");
                                    }
                                }
                            }
                            break;
                        case 2:
                            while(true){
                                try {
                                    System.out.println("Enter new Price: ");
                                    newPrice = Integer.parseInt(sc.next());
                                    if(!(newPrice+"").matches("^[0-9]*$")){
                                        throw new Exception("PriceChar");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("priceChar")){
                                        System.out.println("Price must be numbers");
                                    }
                                }
                            }
                            break;
                        case 3:
                            while(true){
                                try {
                                    System.out.println("Enter new Origin: ");
                                    newOrigin = sc.next();
                                    if(newOrigin.isEmpty()){
                                        throw new Exception("emptyOrigin");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("originEmpty")){
                                        System.out.println("Origin must not be empty");
                                    }
                                }
                            }
                            break;
                        case 4:
                            while(true){
                                try {
                                    System.out.println("Enter new Number Storage: ");
                                    numStorage = Integer.parseInt(sc.next());
                                    if(!(numStorage+"").matches("^[0-9]*$")){
                                        throw new Exception("NumChar");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("NumChar")){
                                        System.out.println("Number must be numbers");
                                    }
                                }
                            }
                            break;
                    }
                } while(choice!=5);
                
                if(newPrice == 0){
                    newPrice = fruitList.get(i).getPrice();
                }
                
                if(newName == null){
                    newName = fruitList.get(i).getName();
                }
                
                if(newOrigin == null){
                    newOrigin = fruitList.get(i).getOrigin();
                }
                
                if(numStorage == 0){
                    numStorage = fruitList.get(i).getNumStorage();
                }
                
                
                
                Fruit temp = new Fruit(newID, newPrice, newName, newOrigin, numStorage);
                fruitList.set(i, temp);
                System.out.println("Edit successful");
                break;
            }
        }
    }
    
    public void editFruitByID(int ID){
        String newName = null, newOrigin = null;
        int newPrice = 0, numStorage = 0;
        int choice;
        for(int i = 0; i<fruitList.size(); i++){

            if(ID == fruitList.get(i).getID()){
                int newID = fruitList.get(i).getID();

                do{
                    System.out.println("What you want to edit: ");
                    System.out.println("1. Name");
                    System.out.println("2. Price");
                    System.out.println("3. Origin");
                    System.out.println("4. Num storage");
                    System.out.println("5. Edit done");
                    choice = Integer.parseInt(sc.next());

                    switch(choice){
                        case 1:
                            while(true){
                                try {
                                    System.out.println("Enter new Name: ");
                                    newName = sc.next();
                                    if(newName.isEmpty()){
                                        throw new Exception("emptyName");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("nameEmpty")){
                                        System.out.println("Name must not be empty");
                                    }
                                }
                            }
                            break;
                        case 2:
                            while(true){
                                try {
                                    System.out.println("Enter new Price: ");
                                    newPrice = Integer.parseInt(sc.next());
                                    if(!(newPrice+"").matches("^[0-9]*$")){
                                        throw new Exception("PriceChar");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("priceChar")){
                                        System.out.println("Price must be numbers");
                                    }
                                }
                            }
                            break;
                        case 3:
                            while(true){
                                try {
                                    System.out.println("Enter new Origin: ");
                                    newOrigin = sc.next();
                                    if(newOrigin.isEmpty()){
                                        throw new Exception("emptyOrigin");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("originEmpty")){
                                        System.out.println("Origin must not be empty");
                                    }
                                }
                            }
                            break;
                        case 4:
                            while(true){
                                try {
                                    System.out.println("Enter new Number Storage: ");
                                    numStorage = Integer.parseInt(sc.next());
                                    if(!(numStorage+"").matches("^[0-9]*$")){
                                        throw new Exception("NumChar");
                                    }
                                    break;
                                } catch (Exception e) {
                                    if(e.getMessage().contains("NumChar")){
                                        System.out.println("Number must be numbers");
                                    }
                                }
                            }
                            break;
                    }
                } while(choice!=5);
                
                if(newPrice == 0){
                    newPrice = fruitList.get(i).getPrice();
                }
                
                if(newName == null){
                    newName = fruitList.get(i).getName();
                }
                
                if(newOrigin == null){
                    newOrigin = fruitList.get(i).getOrigin();
                }
                
                if(numStorage == 0){
                    numStorage = fruitList.get(i).getNumStorage();
                }
                
                
                
                Fruit temp = new Fruit(newID, newPrice, newName, newOrigin, numStorage);
                fruitList.set(i, temp);
                System.out.println("Edit successful");
                break;
            }
        }
    }
    
    public void deleteFruit(int ID){
        for(int i = 0; i<fruitList.size(); i++){
            if(ID == fruitList.get(i).getID()){
                fruitList.remove(i);
                fruitList.trimToSize();
                System.out.println("Delete Successful");
            }
        }
    }
    
    public void voucherCreate(){
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String upperChars = lowerChars.toUpperCase();
        SecureRandom numRand = new SecureRandom();
        SecureRandom charAtRand = new SecureRandom();
        
        
        String voucher = "";
        
        try {
            if(!voucherDataPath.exists()){
                voucherDataPath.createNewFile();
                
                voucherData = new Wini(voucherDataPath);
                for(int i = 1; i<4; i++){
                    for(int j = 1; j<6; j++){
                        voucher = "";
                        for(int k = 0; k<10; k++){
                            if(k < 4){
                                voucher += numRand.nextInt(9);
                            } else if(k >= 4 && k<7){
                                voucher += lowerChars.charAt(numRand.nextInt(25));
                            } else {
                                voucher += upperChars.charAt(numRand.nextInt(25));
                            }
                        }
                        voucher = shuffle(voucher);
                        if(i == 1){
                            voucherData.put("10%", "Code"+j, voucher);
                            voucherData.store();
                        } else if(i == 2){
                            voucherData.put("20%", "Code"+j, voucher);
                            voucherData.store();
                        } else {
                            voucherData.put("30%", "Code"+j, voucher);
                            voucherData.store();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void replaceUsedVoucher(String code){
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String upperChars = lowerChars.toUpperCase();
        SecureRandom numRand = new SecureRandom();
        SecureRandom charAtRand = new SecureRandom();
        
        String voucher = "";
        for(int k = 0; k<10; k++){
            if(k < 4){
                voucher += numRand.nextInt(9);
            } else if(k >= 4 && k<7){
                voucher += lowerChars.charAt(numRand.nextInt(25));
            } else {
                voucher += upperChars.charAt(numRand.nextInt(25));
            }
        }
        voucher = shuffle(voucher);
        
        try {
            voucherData = new Wini(voucherDataPath);
            
            for(int i = 1; i<6; i++){
                if(voucherData.get("10%", "Code"+i).equals(code)){
                    voucherData.put("10%", "Code"+i, voucher);
                    voucherData.store();
                } else if(voucherData.get("20%", "Code"+i).equals(code)){
                    voucherData.put("20%", "Code"+i, voucher);
                    voucherData.store();
                } else if(voucherData.get("30%", "Code"+i).equals(code)){
                    voucherData.put("30%", "Code"+i, voucher);
                    voucherData.store();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void giveVoucher(double totalValue){
        try {
            voucherData = new Wini(voucherDataPath);
            
            int randNum;
            
            if(totalValue > 100){
                randNum = getRandomIntegerBetween(1, 5);
                System.out.println("You've got 30% voucher: "+voucherData.get("30%", "Code"+randNum));
            } else if(totalValue > 80){
                randNum = getRandomIntegerBetween(1, 5);
                System.out.println("You've got 20% voucher: "+voucherData.get("20%", "Code"+randNum));
            } else if(totalValue > 50){
                randNum = getRandomIntegerBetween(1, 5);
                System.out.println("You've got 10% voucher: "+voucherData.get("10%", "Code"+randNum));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double discountNow(double totalValue, int discountValue){
        if(totalValue > 100){
            discountValue = 30;
            return (totalValue/100)*70;
        } else if(totalValue > 80){
            discountValue = 20;
            return (totalValue/100)*80;
        } else if(totalValue > 50){
            discountValue = 10;
            return (totalValue/100)*90;
        }
        
        return totalValue;
    }
    
    static int getRandomIntegerBetween(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
    static String shuffle(String input){
           List<Character> characters = new ArrayList<Character>();
           for(char c:input.toCharArray()){
               characters.add(c);
           }
           StringBuilder output = new StringBuilder(input.length());
           while(characters.size()!=0){
               int randPicker = (int)(Math.random()*characters.size());
               output.append(characters.remove(randPicker));
           }
           return output.toString();
       }
    
    public void customerManage(){
        String choice = "N";
        listChoice = new ArrayList<>();
        String cusName = "";
        int quantityChoice = 0, choiceID = 0;
        
        if(fruitList.isEmpty()){
            System.out.println("List of fruit is empty");
        } else {
            showList();
        
            do{
                while(true){
                    try {
                        System.out.println("Enter the number of Fruit you want to order: ");
                        choiceID = Integer.parseInt(sc.next());
                        if(!(choiceID+"").matches("^[0-9]*$")){
                            throw new Exception("choiceIDChar");
                        }
                        for(int i = 0; i<fruitList.size(); i++){
                            if(fruitList.get(i).getID() == choiceID){
                                if(fruitList.get(i).getNumStorage() == 0){
                                    throw new Exception("outStorage");
                                }
                            }
                        }
                        System.out.println("Enter quantity: ");
                        quantityChoice = Integer.parseInt(sc.next());
                        if(!(quantityChoice+"").matches("^[0-9]*$")){
                            throw new Exception("quantityChar");
                        }
                        for(int i = 0; i<fruitList.size(); i++){
                            if(fruitList.get(i).getID() == choiceID){
                                if(fruitList.get(i).getNumStorage() < quantityChoice){
                                    throw new Exception("notEnough");
                                }
                            }
                        }
                        break;
                    } catch (Exception e) {
                        if(e.getMessage().contains("choiceIDChar")){
                            System.out.println("ChoiceID must be Number");
                        } else if(e.getMessage().contains("outStorage")){
                            System.out.println("Out of Storage!");
                        } else if(e.getMessage().contains("notEnough")){
                            System.out.println("Storage is not enough!");
                        } else if(e.getMessage().contains("quantityChar")){
                            System.out.println("Quantity must be numbers");
                        }
                    }
                }

                for(int i = 0; i<fruitList.size(); i++){
                    if(fruitList.get(i).getID() == choiceID){
                        Fruit temp = new Fruit(fruitList.get(i).getID(), fruitList.get(i).getPrice(), fruitList.get(i).getName(), fruitList.get(i).getOrigin(), fruitList.get(i).getNumStorage());
                        listChoice.add(temp);
                        listChoice.get(listChoice.size()-1).setQuantity(quantityChoice);
                        break;
                    }
                }

                System.out.println("Do you want to order now (Y/N) ? ");
                choice = sc.next();

                if(choice.equalsIgnoreCase("Y")){
                    for(int i = 0; i<listChoice.size(); i++){
                        for(int j = 0; j<fruitList.size(); j++){
                            if(listChoice.get(i).getName().equalsIgnoreCase(fruitList.get(j).getName())){
                                int newNumStorage = fruitList.get(j).getNumStorage() - listChoice.get(i).getQuantity();

                                Fruit temp = new Fruit(fruitList.get(j).getID(), fruitList.get(j).getPrice(), fruitList.get(j).getName(), fruitList.get(j).getOrigin(), newNumStorage);
                                fruitList.set(j, temp);
                            }
                        }
                    }
                    saveData();

                    System.out.println("Product | Quantity | Price | Amount");
                    double total= 0;
                    for(int i = 0; i<listChoice.size(); i++){
                        System.out.println(listChoice.get(i).getName() + "     "
                                            + listChoice.get(i).getQuantity() + "     "
                                            + listChoice.get(i).getPrice()+"$     "
                                            + listChoice.get(i).getAmount()+"$");
                        total += listChoice.get(i).getAmount();
                    }
                    
                    System.out.println("Total: "+total+"$");
                    System.out.println("Do you have voucher ? (Y/N)");
                    String isVoucher = sc.next();
                    if(isVoucher.equalsIgnoreCase("y")){
                        try {
                            voucherData = new Wini(voucherDataPath);
                            
                            boolean isExist = false;
                            int valueVoucher = 0;
                            System.out.println("Enter your voucher");
                            String voucherCode = sc.next();

                            for(int i = 0; i<3; i++){
                                for(int j = 1; j<6; j++){
                                    if(i == 0){
                                        if(voucherCode.equals(voucherData.get("10%", "Code"+j))){
                                            isExist = true;
                                            valueVoucher = 10;
                                        }
                                    } else if(i == 1){
                                        if(voucherCode.equals(voucherData.get("20%", "Code"+j))){
                                            isExist = true;
                                            valueVoucher = 20;
                                        }
                                    } else {
                                        if(voucherCode.equals(voucherData.get("30%", "Code"+j))){
                                            isExist = true;
                                            valueVoucher = 30;
                                        }
                                    }
                                }
                            }
                            
                            if(isExist == false){
                                System.out.println("This voucher is not exist !");
                            } else {
                                System.out.println("You used "+valueVoucher+"% voucher !");
                                total = (total/100)*(100-valueVoucher);
                                for(int i = 0; i<listChoice.size(); i++){
                                    listChoice.get(i).setAmount(total);
                                }
                            }
                            
                            System.out.println("Total: "+total+"$");
                            replaceUsedVoucher(voucherCode);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if(isVoucher.equalsIgnoreCase("n")){
                        int choiceDiscount;
                        boolean isDone = false;
                        
                        do {
                            System.out.println("===================================");
                            System.out.println("1. Get voucher for the next time");
                            System.out.println("2. Discount this time");
                            System.out.println("3. Exit");
                            System.out.println("===================================");
                            
                            choiceDiscount = Integer.parseInt(sc.next());
                            
                            
                            
                            switch(choiceDiscount){
                                case 1:
                                    giveVoucher(total);
                                    isDone = true;
                                    break;
                                case 2:
                                    int discountValue = 0;
                                    
                                    if(total > 100){
                                        discountValue = 30;
                                    } else if(total > 80){
                                        discountValue = 20;
                                    } else if(total > 50){
                                        discountValue = 10;
                                    }
                                    total = discountNow(total, discountValue);
                                    
                                    for(int i = 0; i<listChoice.size(); i++){
                                        listChoice.get(i).setAmount(total);
                                    }
                                    
                                    System.out.println("New bill: "+total+"$ (-"+discountValue+"%)");
                                    
                                    isDone = true;
                                    break;
                            }
                        } while (!isDone);
                    }
                    
                    System.out.println("Enter your name: ");
                    cusName = sc.next();
                }
            } while(!choice.equalsIgnoreCase("Y"));

            table.put(cusName, listChoice);
            nameList.add(cusName);
        }
    }
    
    public void viewOrders(){
        Set<String> name = table.keySet();
        Iterator<String> it = name.iterator();

        double total = 0; 

        while(it.hasNext()){
            String nameTmp = it.next();
            ArrayList<Fruit> temp = table.get(nameTmp);

            System.out.println("Customer Name: "+nameTmp);
            System.out.println("Product | Quantity | Price | Amount");
            
            for(Fruit fruitTemp : temp){
                String space1 = "", space2 = "", space3 = "";
                
                int numSpace1 = 10 - fruitTemp.getName().length();
                for(int i = 0; i<numSpace1; i++){
                    space1+=" ";
                }
                
                int numSpace2 = 11 - (fruitTemp.getQuantity()+"").length();
                for(int i = 0; i<numSpace2; i++){
                    space2+=" ";
                }
                
                int numSpace3 = 7 - (fruitTemp.getPrice()+"").length();
                for(int i = 0; i<numSpace3; i++){
                    space3+=" ";
                }
                
                System.out.println(fruitTemp.getName()+space1
                                    +fruitTemp.getQuantity()+space2
                                    +fruitTemp.getPrice()+"$"+space3
                                    +fruitTemp.getAmount()+"$");
                total = fruitTemp.getTotal();
            }
            System.out.println("Total: "+total+"$");
        }
    }
}
