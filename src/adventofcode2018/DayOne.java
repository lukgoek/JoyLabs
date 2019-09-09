package adventofcode2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    ArrayList<String> inputArray = new ArrayList<>();
    
    boolean hasBeenSeen = false;
    HashMap<String, Integer> usedFrequencies = new HashMap<String, Integer>();
    String firstRunLastFrequency = null;
    String firstRepeatedFrequency = null;
    
    protected void dayOneMenu(){
        System.out.println("**********************************************************");
        System.out.println("*  You are in Day 1: Chronal Calibration ");
        System.out.println("*  Enter the starting frequency: ");
        currentFrequency = input.nextInt();
        System.out.println("*  Do you want to see all frequencies? Y/N(faster option):");
        showMeFrequencies = input.next();
        
        System.out.println("*  Select you file input to continue. ");
        try {
            BufferedReader reader = new  BufferedReader(new FileReader(objChooser.getFile()));
            String inputData;
            while((inputData = reader.readLine()) != null){
                inputArray.add(inputData);
            }
            int pilot =0;
            while(pilot < inputArray.size() && firstRepeatedFrequency ==  null){
                dayOneLogic(this.currentFrequency, Integer.parseInt(inputArray.get(pilot)));
                pilot++;
                if(pilot >= inputArray.size()){
                    pilot = 0;
                    if(firstRunLastFrequency == null){
                        firstRunLastFrequency = ""+this.currentFrequency;
                    }
                    
                }
            }
            
            System.out.println("*  After all the changes in the list one time the frequency is: "+firstRunLastFrequency);
            System.out.println("*  First repeated frequency for over and over: "+firstRepeatedFrequency);
        } catch (Exception ex) {
            Logger.getLogger(DayOne.class.getName()).log(Level.SEVERE, null, ex);
            AdventOfCode2018.createMenu();
        }
    }

    protected void dayOneLogic(int currentFrequency, int changeOf){
        if(showMeFrequencies.equals("Y") || showMeFrequencies.equals("y")){
            System.out.println("Currrent frequency: "+this.currentFrequency+", change of "+changeOf+"; resulting frequency "+(currentFrequency+(changeOf)));  
        }
        this.currentFrequency+=(changeOf);
        
        if(hasBeenSeen == false){
            if(usedFrequencies.containsKey(""+this.currentFrequency)){
                hasBeenSeen = true;
                firstRepeatedFrequency = ""+this.currentFrequency;
                
            }
            
        }
        usedFrequencies.put(""+this.currentFrequency, 1);   
    }
}
