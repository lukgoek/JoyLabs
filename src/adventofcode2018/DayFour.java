package adventofcode2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DayFour {
    Chooser objChooser = new Chooser();
    TreeMap<Date, String> inputMap = new TreeMap<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    final int GUARD_JOURNEY = 60;
            
    protected void DayFourMenu(){
        System.out.println("**********************************************************");
        System.out.println("*  You are in Day 4: Repose Record ");
        System.out.println("*  Select your file input to continue. ");
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(objChooser.getFile()));
            String inputData;
            
            while((inputData = reader.readLine()) != null){
                inputMap.put(format.parse(inputData.substring(1,17)), inputData.substring(17));
            }
            sortInput();
            System.out.println("**********************************************************");
        }catch(Exception ex){
           Logger.getLogger(DayFour.class.getName()).log(Level.SEVERE, null, ex);
           AdventOfCode2018.createMenu();
        }
    }
    
    protected void sortInput(){
        for(Map.Entry<Date, String> entry : this.inputMap.entrySet()){
           System.out.println("["+format.format(entry.getKey())+""+entry.getValue());
        }
    }
    
    protected void dayFourLogic(){
        
        
        
    }
}
