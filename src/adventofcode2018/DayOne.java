package adventofcode2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lugoe
 */
public class DayOne {
    Chooser objChooser = new Chooser();
    int currentFrequency = 0;
    String showMeFrequencies;
    Scanner input = new Scanner(System.in);
    
    protected void dayOneMenu(){
        System.out.println("**********************************************************");
        System.out.println("*  You are in Day 1: Chronal Calibration ");
        System.out.println("*  Enter the starting frequency: ");
        currentFrequency = input.nextInt();
        System.out.println("*  Do you want to see all frequencies? Y/N:");
        showMeFrequencies = input.next();
        
        System.out.println("*  Select you file input to continue. ");
        try {
            BufferedReader reader = new  BufferedReader(new FileReader(objChooser.getFile()));
            String inputData;
            while((inputData = reader.readLine()) != null){
                dayOneLogic(this.currentFrequency, Integer.parseInt(inputData));
            }   
            System.out.println("After all the changes the frequency is: "+currentFrequency);
        } catch (Exception ex) {
            Logger.getLogger(DayOne.class.getName()).log(Level.SEVERE, null, ex);
            AdventOfCode2018.createMenu();
        }
    }

    protected void dayOneLogic(int currentFrequency, int changeOf){
        if(showMeFrequencies.equals("Y") || showMeFrequencies.equals("")){
            System.out.println("Currrent frequency: "+this.currentFrequency+", change of "+changeOf+"; resulting frequency "+(currentFrequency+(changeOf)));  
        }
        this.currentFrequency+=(changeOf);
    }
}
