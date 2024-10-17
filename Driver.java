import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * Driver class - Stock System 
 */
public class Driver
{
    /** global variables */
    ArrayList<Shoes> stockList; 

    /** constructor */
    public Driver()
    {
        // declare array list 
        stockList = new ArrayList<Shoes>();

        System.out.print("\f");

        // check to see if data file exsists 
        if (readFromFile())
        {
            System.out.println("System is populated with stock");
        }
        else 
        {
            System.out.println("There is no stock in the system");
        }
        
        //menu 
        processMainMenu();

        //save items into file  
        saveToFile(); 
        
    }// end constructor

    public void saveToFile()
    {
        ObjectOutputStream fileOut;

        try
        {
            fileOut = new ObjectOutputStream (new FileOutputStream("shoes.dat"));
            for (Shoes aShoes : stockList)
            {
                fileOut.writeObject(aShoes);
            }
            fileOut.close();
            System.out.println("All shoe stock have been saved");
        }
        catch (IOException e)
        {
            System.out.println("IO Error : " + e.getMessage());
        }

    }//end saveToFile

    public boolean readFromFile()
    {
        int index = 0;
        ObjectInputStream fileIn;
        Shoes s;

        try
        {
            fileIn = new ObjectInputStream(new FileInputStream("shoes.dat"));
            System.out.println("Opened file successfully");
            s = (Shoes) fileIn.readObject();
            index = 1; 
            while (s != null)
            {
                stockList.add(s);
                s = (Shoes) fileIn.readObject();
                index++;
            }
            fileIn.close();
            return true;
        }
        catch (IOException e)
        {
            if (index > 0)
            {
                return true;
            }
            else 
            {
                System.out.println("Data file does not exsist\n");
                return false;
            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class Error : " + e.getMessage());
            return false; 
        }
    }// end saveToFile 

    /** main menu : 
     * customer menu
     * main menu 
     * exit system
     */
    public void displayMainMenu()
    {
        System.out.println("********************************");
        System.out.println("MAIN MENU");
        System.out.println("\n1. Customer Menu");
        System.out.println("2. Staff Menu");
        System.out.println("3. Exit System");
        System.out.println("********************************");
    }// end displayMainMenu

    public int getMainMenuOption()
    {
        Scanner scan = new Scanner(System.in);
        int mainMenuOption;

        do 
        {
            System.out.println("Please chose menu option (1-3) : ");
            mainMenuOption = scan.nextInt(); scan.nextLine();

            if (mainMenuOption < 1 || mainMenuOption > 3)
            {
                System.out.println("Error invalid input, please try again : ");
                mainMenuOption = scan.nextInt(); scan.nextLine();
            }

        }while (mainMenuOption < 1 || mainMenuOption > 3);

        return mainMenuOption;
    }// end getMainMenuOption 

    public void processMainMenu()
    {
        int mainMenuOption;

        do 
        {
            displayMainMenu();

            mainMenuOption = getMainMenuOption();

            if (mainMenuOption == 1)
            {
                processCustomerMenu();
            }
            else if (mainMenuOption == 2) 
            {
                processPassword();
            }
            else 
            {
                System.out.println("Goodbye!");
            }

        }while (mainMenuOption != 3);

    }// end processMainMenu

    /** staff menu :
     * hardwire staff password
     * ask to input password to access staff menu, validate.
    1. method - create sneaker 
    2. method - create heels 
    3. method - update stock
    4. back to main menu */

    // Display staff menu 
    public void displayStaffMenu()
    {
        System.out.println("********************************");
        System.out.println("STAFF MENU");
        System.out.println("\n1. Add new sneaker to stock list.");
        System.out.println("2. Add new heels to stock list.");
        System.out.println("3. Update stock list for exsisting shoes");
        System.out.println("4. Back to main menu.");
        System.out.println("********************************");
    }// end displayStaffMenu 

    public int getStaffMenuOption()
    {
        Scanner scan = new Scanner(System.in);
        int staffMenuOption;

        do 
        {
            System.out.println("Please input menu option (1-4) : ");
            staffMenuOption = scan.nextInt(); scan.nextLine();

            if (staffMenuOption < 1 || staffMenuOption > 4)
            {
                System.out.println("Error invalid input, please try again : ");
                staffMenuOption = scan.nextInt(); scan.nextLine();
            }

        }while (staffMenuOption < 1 || staffMenuOption > 4);

        return staffMenuOption;
    }// end getStaffMenuOption

    public void processStaffMenu()
    {
        int staffMenuOption;

        do
        {
            displayStaffMenu();

            staffMenuOption = getStaffMenuOption();

            if (staffMenuOption == 1)
            {
                addSneaker();
            }
            else if (staffMenuOption == 2)
            {
                addHeels();
            }
            else if (staffMenuOption == 3)
            {
                updateStock();
            }
            else 
            {
                processMainMenu();
            }

        }while (staffMenuOption != 4);

    }// end processStaffMenu

    /** User hardwire a staff password */
    public void processPassword()
    {
        Scanner scan = new Scanner (System.in);
        String enterPassword, processPassword;

        System.out.println("Please hardwire a staff password : ");
        processPassword = scan.nextLine();

        System.out.println("Enter password to enter staff menu : ");
        enterPassword = scan.nextLine();

        if (enterPassword.equals(processPassword))
        {
            processStaffMenu();
        }
        else 
        {
            for (int count = 0; count < 3; count++)
            {
                System.out.println("Wrong password, please try again : ");
                processPassword = scan.nextLine();
            }

            System.out.println("Error, Invalid Input");
            processMainMenu();
        }

    }// end processPassword 

    /** Staff menu opt 1 - add sneakers to stocklist */
    public void addSneaker()
    {
        Scanner scan = new Scanner (System.in);

        String size, colour, brand, model, lacesAns;
        double price; 
        int stock, add;
        boolean laces;

        Shoes sneakers;

        System.out.println("\nHow many sneakers would you like to add to the stock list?");
        add = scan.nextInt(); scan.nextLine();

        for (int count = 0; count < add; count++)
        {
            /** ask user to input sneaker details */
            System.out.println("\nEnter new sneaker details : ");
            System.out.println("******************************");

            System.out.println("Sneaker size : ");
            size = scan.nextLine();

            System.out.println("Sneaker colour : ");
            colour = scan.nextLine();

            System.out.println("Brand : ");
            brand = scan.nextLine();

            System.out.println("Sneaker Model : ");
            model = scan.nextLine();

            System.out.println("Price : ");
            price = scan.nextDouble(); scan.nextLine();

            do 
            {
                System.out.println("Do these sneakers have laces (yes/no) : ");
                lacesAns = scan.nextLine();

                if (!lacesAns.equalsIgnoreCase("yes") && !lacesAns.equalsIgnoreCase("no"))
                {
                    System.out.println("Error, invalid input please try again ");
                }

            }while(!lacesAns.equalsIgnoreCase("yes") && !lacesAns.equalsIgnoreCase("no"));

            if (lacesAns.equalsIgnoreCase("yes"))
            {
                laces = true;
            }
            else 
            {
                laces = false;
            }

            System.out.println("How many of these sneakers are in stock?");
            stock = scan.nextInt(); scan.nextLine();

            sneakers = new Sneakers(size, colour, brand, price, stock, model, laces);
            stockList.add(sneakers);

            System.out.println("these sneakers have successfully been added to the stock list!");

        }// end for 

    }//end addSneaker 

    /** Staff menu opt 2 - add heels to stocklist */
    public void addHeels()
    {
        Scanner scan = new Scanner(System.in);

        String size, colour, brand, type;
        int stock, add;
        double height, price; 

        Shoes heels;

        System.out.println("How many heels would you like to add to the stock list?");
        add = scan.nextInt(); scan.nextLine();

        for (int count = 0; count < add; count++)
        {
            //ask user to input heel details 
            System.out.println("\nEnter new heel details : ");
            System.out.println("******************************");

            System.out.println("Heel size : ");
            size = scan.nextLine();

            System.out.println("Heel colour : ");
            colour = scan.nextLine();

            System.out.println("Brand : ");
            brand = scan.nextLine();

            System.out.println("Heel type : ");
            type = scan.nextLine();

            System.out.println("Heel height : "); 
            height = scan.nextDouble(); scan.nextLine();

            System.out.println("Price : ");
            price = scan.nextDouble(); scan.nextLine();

            System.out.println("How many of these heels are in stock?");
            stock = scan.nextInt(); scan.nextLine();

            //create heel object 
            heels = new Heels(size, colour, brand, price, stock, type, height);
            stockList.add(heels);

            System.out.println("These heels have successfully been added to the stock list!");

        }// end for 
    }// end add heels 

    /** Staff menu opt 3 - update stock of exsisting shoes */
    public void updateStock()
    {
        Scanner scan = new Scanner(System.in);

        String update;

        System.out.println("Update Sneakers or Heels stock? (Sneakers/Heels) : ");
        update = scan.nextLine();

        do
        {
            if (update.equalsIgnoreCase("Sneakers"))
            {
                updateSneakerStock();
            }
            else if (update.equalsIgnoreCase("Heels"))
            {
                updateHeelStock();
            }
            else
            {
                System.out.println("Error, invalid input please try again.");
                update = scan.nextLine();
            }

        }while(!update.equalsIgnoreCase("Sneakers") && !update.equalsIgnoreCase("Heels"));

    }// end update stock

    public void updateSneakerStock()
    {
        Scanner scan = new Scanner (System.in); 

        // variables 
        String size, colour, brand, model; 
        int stock; 

        Sneakers s;

        System.out.println("Enter the size(UK) you would like to restock : ");
        size = scan.nextLine();

        System.out.println("Enter the colour you would like to restock : ");
        colour = scan.nextLine();

        System.out.println("Enter the brand you would like to restock : ");
        brand = scan.nextLine(); 

        System.out.println("Enter the sneaker model you would like to restock : ");
        model = scan.nextLine();

        for (Shoes a : stockList)
        {
            if (a instanceof Sneakers)
            {
                s = (Sneakers) a; 

                if (size.equalsIgnoreCase(a.getSize()) && colour.equalsIgnoreCase(a.getColour()) && brand.equalsIgnoreCase(a.getBrand()) && model.equalsIgnoreCase(s.getModel()))
                {
                    System.out.println("How many units would you like to restock? : ");
                    stock = scan.nextInt(); scan.nextLine();

                    a.setStock(stock);

                    System.out.println("\nSuccessfully updated stock to " + a.getStock());

                }// end if 
            }// end if 

        }// end for

    }// end update sneaker stock

    public void updateHeelStock()
    {
        Scanner scan = new Scanner (System.in); 

        // variables 
        String size, colour, brand, type; 
        int stock; 

        Heels h;

        System.out.println("Enter the size(UK) you would like to restock : ");
        size = scan.nextLine();

        System.out.println("Enter the colour you would like to restock : ");
        colour = scan.nextLine();

        System.out.println("Enter the brand you would like to restock : ");
        brand = scan.nextLine(); 

        System.out.println("Enter the heel type you would like to restock : ");
        type = scan.nextLine();

        for (Shoes a : stockList)
        {
            if (a instanceof Heels)
            {
                h = (Heels) a; 

                if (size.equalsIgnoreCase(a.getSize()) && colour.equalsIgnoreCase(a.getColour()) && brand.equalsIgnoreCase(a.getBrand()) && type.equalsIgnoreCase(h.getType()))
                {
                    System.out.println("How many units would you like to restock? : ");
                    stock = scan.nextInt(); scan.nextLine();

                    a.setStock(stock);

                    System.out.println("\nSuccessfully updated stock to " + a.getStock());

                }// end if 
            }// end if 

        }// end for

    }// end update heels stock

    /** 
     * customer menu : 
    1. method - display sneakers
    2. method - diplay heels 
    3. method - buy sneakers 
    4. method - buy heels
    5. method - sizing guide 
    6. back to main menu 
     */

    public void displayCustomerMenu()
    {
        System.out.println("\n********************************");
        System.out.println("CUSTOMER MENU");
        System.out.println("\n1. Display all sneakers");
        System.out.println("2. Display all heels");
        System.out.println("3. Buy sneakers");
        System.out.println("4. Buy heels");
        System.out.println("5. Sizing guide");
        System.out.println("6. Back to main menu.");
        System.out.println("********************************\n");
    }// end displayCustomerMenu 

    public int getCustomerMenuOption()
    {
        Scanner scan = new Scanner(System.in);
        int customerMenuOption;

        do 
        {
            System.out.println("Please input menu option (1-6) : ");
            customerMenuOption = scan.nextInt(); scan.nextLine();

            if (customerMenuOption < 1 || customerMenuOption > 6)
            {
                System.out.println("Error invalid input, please try again : ");
                customerMenuOption = scan.nextInt(); scan.nextLine();
            }

        }while (customerMenuOption < 1 || customerMenuOption > 6);

        return customerMenuOption;
    }// end getCustomerMenuOption

    public void processCustomerMenu()
    {
        int customerMenuOption;

        do
        {
            displayCustomerMenu();

            customerMenuOption = getCustomerMenuOption();

            if (customerMenuOption == 1)
            {
                displaySneakers();
            }
            else if (customerMenuOption == 2)
            {
                displayHeels();
            }
            else if (customerMenuOption == 3)
            {
                buySneakers();
            }
            else if (customerMenuOption == 4) 
            {
                buyHeels();
            }
            else if (customerMenuOption == 5) 
            {
                sizingGuide();
            }
            else 
            {
                processMainMenu();
            }

        }while (customerMenuOption != 6);

    }// end processCustomerMenu

    /** Customer menu opt 1 - display all sneakers */
    public void displaySneakers()
    {
        Sneakers s;
        System.out.println("\nDisplaying all sneakers...");
        for (Shoes a : stockList)
        {
            if (a instanceof Sneakers)
            {
                s = (Sneakers) a;
                System.out.print(a.toString());
            }
        }// end for 

    }// end displaySneakers 

    /** Customer menu opt 2 - display all heels */
    public void displayHeels()
    {
        Heels h;
        System.out.println("\nDisplaying all heels...");
        for (Shoes b : stockList)
        {
            if (b instanceof Heels)
            {
                h = (Heels) b;
                System.out.print(b.toString());
            }
        }// end for 
    }// end displayHeels 

    /** Customer menu opt 3 - buy sneakers */
    public void buySneakers()
    {
        Scanner scan = new Scanner(System.in);

        //variables 
        String size, colour, model, answer, answer1, discount;
        double buy, total, total1, discount1; 
        boolean found = true;

        Sneakers s; 
        for (Shoes a : stockList)
        {
            if (a instanceof Sneakers)
            {
                s = (Sneakers) a; 

                System.out.println("Enter your size (UK) : ");
                size = scan.nextLine();

                System.out.println("Enter desired colour : ");
                colour = scan.nextLine();

                System.out.println("Enter desired model : ");
                model = scan.nextLine();

                if (size.equalsIgnoreCase(a.getSize()) && colour.equalsIgnoreCase(a.getColour()) && model.equalsIgnoreCase(s.getModel()))
                {
                    System.out.println("The price of these sneakers is " + a.getPrice());

                    System.out.println("How many do you wish to purchase? : ");
                    buy = scan.nextDouble(); scan.nextLine();

                    System.out.println("Do you have a discount code?(yes/no)");
                    answer1 = scan.nextLine(); 

                    if (buy <= a.getStock())
                    {
                        total = buy * a.getPrice();
                        System.out.println("Your total is €" + total);

                        System.out.println("Do you have a discount code?(yes/no)");
                        answer1 = scan.nextLine(); 

                        do
                        {
                            if (answer1.equalsIgnoreCase("yes"))
                            {
                                System.out.println("Enter code :");
                                discount = scan.nextLine();
                                if (discount.equals("20OFF"))
                                {
                                    discount1 = total - (total * 0.2); 
                                    System.out.println("Thank you for purchasing!");
                                }
                            }
                            else
                            {
                                System.out.println("Thank you for purchasing!");
                            }
                        }while(!answer1.equalsIgnoreCase("yes") && !answer1 .equalsIgnoreCase("no"));

                    }
                    else 
                    {
                        System.out.println("Sorry there is not enough available sneakers for your request.");
                        System.out.println("There are " + a.getStock() + " sneakers left.");

                        do 
                        {
                            System.out.println("Would you like to purchase the remaining stock (yes/no) : ");
                            answer = scan.nextLine();

                            if (answer.equalsIgnoreCase("yes"))
                            {
                                total1 = a.getStock() * a.getPrice();
                                System.out.println("Your total is €" + total1); 

                                System.out.println("Do you have a discount code?(yes/no)");
                                answer1 = scan.nextLine(); 

                                do
                                {
                                    if (answer1.equalsIgnoreCase("yes"))
                                    {
                                        System.out.println("Enter code :");
                                        discount = scan.nextLine();
                                        if (discount.equals("20OFF"))
                                        {
                                            discount1 = total1 - (total1 * 0.2); 
                                            System.out.println("Thank you for purchasing!");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Thank you for purchasing!");
                                    }
                                }while(!answer1.equalsIgnoreCase("yes") && !answer1 .equalsIgnoreCase("no"));

                            }
                            else
                            {
                                processCustomerMenu();
                            }

                        }while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
                    }// end if 

                    found = true;
                }// end if

                if (found == false)
                {
                    System.out.println("Sorry these sneakers are not in stock");
                }// end if 

            }// end if 
        }// end for 

    }// end buy sneakers 

    /** Customer menu opt 4 - buy heels */
    public void buyHeels()
    {
        Scanner scan = new Scanner(System.in);

        //variables 
        String size, colour, type, answer; 
        double buy, total, total1; 
        boolean found = true;

        Heels h; 
        for (Shoes b : stockList)
        {
            if (b instanceof Heels)
            {
                h = (Heels) b; 

                System.out.println("Enter your size (UK) : ");
                size = scan.nextLine();

                System.out.println("Enter desired colour : ");
                colour = scan.nextLine();

                System.out.println("Enter desired type of heel : ");
                type = scan.nextLine();

                if (size.equalsIgnoreCase(b.getSize()) && colour.equalsIgnoreCase(b.getColour()) && type.equalsIgnoreCase(h.getType()))
                {
                    System.out.println("The price of these heels is " + b.getPrice());

                    System.out.println("How many do you wish to purchase? : ");
                    buy = scan.nextDouble(); scan.nextLine();

                    if (buy <= b.getStock())
                    {
                        total = buy * b.getPrice();
                        System.out.println("Your total is €" + total);
                    }
                    else 
                    {
                        System.out.println("Sorry there is not enough available sneakers for your request.");
                        System.out.println("There are " + b.getStock() + " sneakers left.");

                        do 
                        {
                            System.out.println("Would you like to purchase the remaining stock (yes/no) : ");
                            answer = scan.nextLine();

                            if (answer.equalsIgnoreCase("yes"))
                            {
                                total1 = b.getStock() * b.getPrice();
                                System.out.println("Your total is €" + total1); 
                            }
                            else
                            {
                                processCustomerMenu();
                            }

                        }while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));

                    }// end if 

                    found = true;
                }// end if

                if (found == false)
                {
                    System.out.println("Sorry these heels are not in stock");
                }// end if 

            }// end if 
        }// end for 

    }// end buy heels 

    public void sizingGuide()
    {

        System.out.println("\n********************************");
        System.out.println("SIZING GUIDE : ");
        System.out.println("|  UK  |  US  |  EU  |  CM  |");
        System.out.println("   1      4      34    20.6");
        System.out.println("   2      5      35    21.4");
        System.out.println("   3      6      36    22.2");
        System.out.println("   4      7      37    23.0");
        System.out.println("   5      8      38    23.8");
        System.out.println("   6      9      39    24.6");
        System.out.println("   7      10     40    25.4");
        System.out.println("   8      11     41    26.2");
        System.out.println("   9      12     42    27.0");
        System.out.println("   10     13     43    27.8");
        System.out.println("********************************\n");

    }// end sizing guide 

    public static void main(String[]args)
    {
        new Driver();
    }

}// end class 
