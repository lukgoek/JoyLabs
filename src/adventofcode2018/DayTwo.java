package adventofcode2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DayTwo {
    Chooser objChooser = new Chooser();
    ArrayList<String> inputArray = new ArrayList<String>(); 
    int haveTwo = 0;
    int haveThree = 0;
    
    int pairCount = 0;
    int tertiaryCount =0;
    
    protected void dayTwoMenu(){
        System.out.println("**********************************************************");
        System.out.println("*  You are in Day 2: Inventory Management System ");
        System.out.println("*  Select your file input to continue. ");
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(objChooser.getFile()));
            System.out.println("*  ");
            String inputData;
            while((inputData = reader.readLine()) != null){
                dayTwoLogic(inputData);
                inputArray.add(inputData);
            }
            
            System.out.println("*  CHECKSUM RESULT: "+ pairCount+ " * "+tertiaryCount+" = "+(pairCount*tertiaryCount));
            System.out.println("*  COMMON LETTERS BETWEEN THE TWO CORRECT BOX IDS: ["+dayTwoSimilars()+"]");
            System.out.println("**********************************************************");
        }catch(Exception ex){
           Logger.getLogger(DayTwo.class.getName()).log(Level.SEVERE, null, ex);
           AdventOfCode2018.createMenu();
        }
    }
        
    protected void dayTwoLogic(String data) throws IOException{
        haveTwo = 0;
        haveThree = 0;
        
        HashMap<String, Integer> counterMap = new HashMap<>();
        String letterInTurn;
        
        for(int i=0; i<data.length(); i++){
            letterInTurn = String.valueOf(counterMap.get(data.substring(i, i+1)));
            if(letterInTurn.equals("null")){
                counterMap.put(data.substring(i, i+1), 1);
            }else{
                counterMap.put(data.substring(i, i+1), counterMap.get(data.substring(i, i+1))+1);
            }
        }
        
        //count if the String contains 2 or 3 letters
        for(Map.Entry<String, Integer> entry : counterMap.entrySet()) {
            if(entry.getValue() == 2 ){
                haveTwo++;
            }else if(entry.getValue() == 3){
                haveThree++;
            }else if(entry.getValue() > 3){//Looks like a non-valid scenario with the input provided
                System.out.println("A letter appeared more than 3 times in the String!");
            }
	}
        
        //Checks if the letter appears more that two times.
        if(haveTwo >= 2){
            haveTwo = 1;
        }else if( haveThree >= 2){
            haveThree = 1;
        }
            
        
        //Sum pairs and tertiaries 
        pairCount += haveTwo;
        tertiaryCount += haveThree;
    }
    
    
    protected String dayTwoSimilars(){
        int counter=0;
        int maxcounter =0;
        String haveSimilar = "";
        
        for(int i=0; i<this.inputArray.size(); i++){
            for(int j=0; j<this.inputArray.size(); j++){
                for(int k=0; k<this.inputArray.get(i).length(); k++){
                    if(!this.inputArray.get(i).equals(this.inputArray.get(j))){
                        if(this.inputArray.get(i).charAt(k) == this.inputArray.get(j).charAt(k)){
                            counter++;
                            if(counter > maxcounter){
                                maxcounter = counter;
                                haveSimilar = this.inputArray.get(i);
                                //System.out.println("THIS ONE HAVE HIGEST SIMILITUDES "+haveSimilar+"]["+this.inputArray.get(j));
                            }
                        } 
                    }
                }
                counter = 0;
            }
        }
        return haveSimilar.substring(0, maxcounter);
    }
}
