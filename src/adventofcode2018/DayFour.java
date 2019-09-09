package adventofcode2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFour {
    Scanner input = new Scanner(System.in);
    Chooser objChooser = new Chooser();
    TreeMap<Date, String> inputMap = new TreeMap<>();
    TreeMap<String, StringBuilder> outputMap = new TreeMap<>();
    TreeMap<String, Integer> asleepTime = new TreeMap<>();
    
    SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat fMonthDay = new SimpleDateFormat("MM-dd");
    SimpleDateFormat fTime = new SimpleDateFormat("mm");
    
    String showMeChronologicalOrder;
    String showMeVisuallyRecords;
    
    String guardInTurn;
    final int GUARD_JOURNEY = 60;
    
    int maxAsleep = 0;
    String topOffenderGuard;
            
    protected void DayFourMenu(){
        System.out.println("**********************************************************");
        System.out.println("*  You are in Day 4: Repose Record ");
        System.out.println("*  Do you want to see the chronological order? Y/N:");
        showMeChronologicalOrder = input.next();
        System.out.println("*  Do you want to see the visually records? Y/N:");
        showMeVisuallyRecords = input.next();
        System.out.println("*  Select your file input to continue. ");
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(objChooser.getFile()));
            String inputData;
            
            while((inputData = reader.readLine()) != null){
                inputMap.put(fDate.parse(inputData.substring(1,17)), inputData.substring(17));
            }
            dayFourLogic();
            countTimeAsleep();
            System.out.println("**********************************************************");
        }catch(Exception ex){
           Logger.getLogger(DayFour.class.getName()).log(Level.SEVERE, null, ex);
           AdventOfCode2018.createMenu();
        }
    }
    
    protected void dayFourLogic(){
        StringBuilder timeLabel = new StringBuilder();
        
        for(Map.Entry<Date, String> entry : this.inputMap.entrySet()){
            //Shows the entire chronological order
            if(showMeChronologicalOrder.equals("Y") || showMeChronologicalOrder.equals("y")){
                System.out.println("["+fDate.format(entry.getKey())+""+entry.getValue());
            }
           
           if(entry.getValue().contains("#")){
               timeLabel.delete(0, timeLabel.length());
               timeLabel.append("............................................................");
               
               //System.out.println(".....####################.....#########################.....");
               guardInTurn = entry.getValue().substring(entry.getValue().indexOf("#"), entry.getValue().indexOf("b")-1);
               if(this.outputMap.containsKey(fMonthDay.format(entry.getKey()))){
                   Calendar cal = Calendar.getInstance();
                    cal.setTime(entry.getKey());
                    cal.add(Calendar.DATE, 1);
                 this.outputMap.put(fMonthDay.format(entry.getKey()), new StringBuilder(fMonthDay.format(entry.getKey())+" "+guardInTurn+" "+timeLabel));
               }else{
                   this.outputMap.put(fMonthDay.format(entry.getKey()), new StringBuilder(fMonthDay.format(entry.getKey())+" "+guardInTurn+" "+timeLabel));
               }
               
           }else if(entry.getValue().contains("falls asleep")){
               //System.out.println(guardInTurn+" falls asleep at "+fTime.format(entry.getKey()));
               for(int i = Integer.parseInt(fTime.format(entry.getKey())); i<GUARD_JOURNEY; i++){
                 timeLabel.setCharAt(i, '#'); 
               }
           }else if(entry.getValue().contains("wakes up")){
               //System.out.println(guardInTurn+" wakes up at "+fTime.format(entry.getKey()));
               for(int i = Integer.parseInt(fTime.format(entry.getKey())); i<GUARD_JOURNEY; i++){
                timeLabel.setCharAt(i, '.');  
                    if(i==GUARD_JOURNEY-1){
                        this.outputMap.put(fMonthDay.format(entry.getKey()), new StringBuilder(fMonthDay.format(entry.getKey())+" "+guardInTurn+" "+timeLabel));
                    } 
                }
            }
        }
        
        if(showMeVisuallyRecords.equals("Y") || showMeVisuallyRecords.equals("y")){
            showVisuallyRecords();
        }
        
    }
    
    protected void showVisuallyRecords(){
        System.out.println("DATE    ID  MINUTE");
        System.out.println("            000000000011111111112222222222333333333344444444445555555555");
        System.out.println("            012345678901234567890123456789012345678901234567890123456789");
        for(Map.Entry<String, StringBuilder> entry : this.outputMap.entrySet()){
            
            //if guard ID have N digits
            if(entry.getValue().length() == 70){
                //adds two spaces
                System.out.println(entry.getValue().replace(9, 10, "   "));
            }else if(entry.getValue().length() == 71){
                //adds two spaces
                System.out.println(entry.getValue().replace(10, 11, "  "));
            }else if(entry.getValue().length() == 72){
                //do not add space
                System.out.println(entry.getValue());
            }
        }
    }
    
    protected void countTimeAsleep(){
        String regex = "#\\d{2,4}";
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        String guardInTurn = null;
        
        for(Map.Entry<String, StringBuilder> entry : this.outputMap.entrySet()){
            int timeAsleep=0;
            Matcher m = p.matcher(entry.getValue());
            while (m.find()) {
                guardInTurn = m.group();
            }
                
            for(int i = entry.getValue().length()-GUARD_JOURNEY; i<entry.getValue().length(); i++){
                if(entry.getValue().charAt(i) == '#'){
                    timeAsleep++;
                }
            }
            
            if(asleepTime.get(guardInTurn)!= null){
                asleepTime.put(guardInTurn, asleepTime.get(guardInTurn)+timeAsleep);
            }else{
                asleepTime.put(guardInTurn, timeAsleep);
            }
            
        }
        
        //System.out.println(asleepTime.keySet()+"  "+asleepTime.keySet().size());
        //System.out.println(asleepTime.values()+"  "+asleepTime.values().size());
        
        getMax();
        int cleanedGuardID = Integer.parseInt(this.topOffenderGuard.substring(1));
        System.out.println(this.topOffenderGuard+" * "+maxAsleep+" = "+(cleanedGuardID*maxAsleep));

    }
    
    
    protected void getMax(){
        for(Map.Entry<String, Integer> entry:this.asleepTime.entrySet()){
            if(entry.getValue()>maxAsleep){
                maxAsleep = entry.getValue();
                topOffenderGuard = entry.getKey();
            }
        }
    }
    
}
