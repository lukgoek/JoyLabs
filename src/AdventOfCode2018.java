

import java.util.Scanner;

public class AdventOfCode2018 {
    static Scanner input = new Scanner(System.in);
    static DayOne objDayOne = new DayOne();
    static DayTwo objDayTwo = new DayTwo();
    static DayFour objDayFour = new DayFour();
    
    public static void main(String[] args) {
        //run main menu
        AdventOfCode2018 objAdvent = new AdventOfCode2018();
        objAdvent.createMenu();
    }
    
    //Creates a single menu for Advent Of Code 2018
    static void createMenu(){
        System.out.println("");
        System.out.println("-- Advent Of Code 2018 --");
        System.out.println("**********************************************************");
        System.out.println("*  Day 1: Chronal Calibration press (1)                  *");
        System.out.println("*  Day 2: Inventory Management System press (2)          *");
        System.out.println("*  Day 4: Repose Record press (4)                        *");
        System.out.println("*  Close (x)                                             *");
        System.out.println("**********************************************************");
        switch(input.nextLine()){
            case "1":
                objDayOne.dayOneMenu();
                createMenu();
                break;
            case "2":
                objDayTwo.dayTwoMenu();
                createMenu();
                break;
            case "4":
                objDayFour.DayFourMenu();
                createMenu();
                break;
            case "x":
                System.out.println("Good bye :) ");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
                createMenu();
        }
    }
    
}
