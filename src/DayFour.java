

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    final int GUARD_JOURNEY = 59;
    
    int maxTimeAsleep = 0;
    int repeatedMinute;
    int counterMinute;
    String topOffenderGuard;
            
    //Creates a single menu for Day 4, 2018
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
            countFrequentlyAsleep();
            System.out.println("**********************************************************");
        }catch(Exception ex){
           Logger.getLogger(DayFour.class.getName()).log(Level.SEVERE, null, ex);
           AdventOfCode2018.createMenu();
        }
    }
    
    //Prints into a new collection the viaully record to use them later during the counting
    protected void dayFourLogic(){
        StringBuilder timeLabel = new StringBuilder();
        
        for(Map.Entry<Date, String> entry : this.inputMap.entrySet()){
            //Shows the entire chronological order
            if(showMeChronologicalOrder.equals("Y") || showMeChronologicalOrder.equals("y")){
                System.out.println("["+fDate.format(entry.getKey())+""+entry.getValue());
            }
           
            //if the value checked have # means a guard start turn
           if(entry.getValue().contains("#")){
                timeLabel.delete(0, timeLabel.length());
                timeLabel.append("............................................................");
               
                guardInTurn = entry.getValue().substring(entry.getValue().indexOf("#"), entry.getValue().indexOf("b")-1);
                
                //if have already the date added for the current day "Guard arrives early(before 00:00)"
                if(this.outputMap.get(fMonthDay.format(entry.getKey())) == null){
                    this.outputMap.put(fMonthDay.format(entry.getKey()), new StringBuilder(fMonthDay.format(entry.getKey())+" "+guardInTurn+" "+timeLabel));
                }else{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(entry.getKey());
                    cal.add(Calendar.DATE, 1);
                    this.outputMap.put(fMonthDay.format(cal.getTime()), new StringBuilder(fMonthDay.format(cal.getTime())+" "+guardInTurn+" "+timeLabel));
               } 
           }else if(entry.getValue().contains("falls asleep")){
               //System.out.println(guardInTurn+" falls asleep at "+fTime.format(entry.getKey()));
               for(int i = Integer.parseInt(fTime.format(entry.getKey())); i<GUARD_JOURNEY; i++){
                       timeLabel.setCharAt(i, '#');
                       this.outputMap.put(fMonthDay.format(entry.getKey()), new StringBuilder(fMonthDay.format(entry.getKey())+" "+guardInTurn+" "+timeLabel));
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
    
    //Method to show or not the  visually records
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
    
    //Count the time asleep for each guard
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
                
            for(int i = (entry.getValue().length()-GUARD_JOURNEY); i<entry.getValue().length(); i++){
                if(entry.getValue().charAt(i) == '#'){
                    timeAsleep++;
                }
            }
            
            if(this.asleepTime.get(guardInTurn)!= null){
                this.asleepTime.put(guardInTurn, this.asleepTime.get(guardInTurn) + timeAsleep);
            }else{
                this.asleepTime.put(guardInTurn, timeAsleep);
            }
            
        }
        
        //System.out.println(asleepTime.keySet()+"  "+asleepTime.keySet().size());
        //System.out.println(asleepTime.values()+"  "+asleepTime.values().size());
        
        getMax();
        repeatedMinute();
        
        int cleanedGuardID = Integer.parseInt(this.topOffenderGuard.substring(1));
        System.out.println("*  PART A: "+this.topOffenderGuard+" * "+repeatedMinute+" = "+(cleanedGuardID*repeatedMinute));

    }
    
    //Check who has the max time asleep
    protected void getMax(){
        for(Map.Entry<String, Integer> entry:this.asleepTime.entrySet()){
            if(entry.getValue() > maxTimeAsleep){
                maxTimeAsleep = entry.getValue();
                topOffenderGuard = entry.getKey();
            }
        }
    }
    
    //find the most repeated minute asleep from the guard that sleeped more time
    protected void repeatedMinute(){
        TreeMap<String, Integer> minuteRepeadedMoreTimes = new TreeMap<>();
        String regex = "#\\d{2,4}";
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        String methodGuard ="";
        
        for(Map.Entry<String, StringBuilder> entry : this.outputMap.entrySet()){
        Matcher m = p.matcher(entry.getValue());
            while (m.find()) {
                methodGuard = m.group();
            }
            
            if(topOffenderGuard.equals(methodGuard)){
            int initialIndex = entry.getValue().length() - GUARD_JOURNEY;//72-59=13
               for(int i = initialIndex; i<entry.getValue().length(); i++){
                   //System.out.println("number = "+(i-initialIndex)+" CHAR:"+entry.getValue().charAt(i));
                    if(entry.getValue().charAt(i-1) == '#'){
                        //System.out.println((i-initialIndex)+" MINUTE FOUND with :");
                        if(minuteRepeadedMoreTimes.get(""+(i-initialIndex)) != null){
                            minuteRepeadedMoreTimes.put(""+(i-initialIndex), minuteRepeadedMoreTimes.get(""+(i-initialIndex))+1);
                        }else{
                            minuteRepeadedMoreTimes.put(""+(i-initialIndex), 1);
                        }
                        
                    }
                } 
            }
            
        }
        int sum =0;
        for(Map.Entry<String, Integer> entry : minuteRepeadedMoreTimes.entrySet()){
            sum += entry.getValue();
            if(entry.getValue() > counterMinute){
                counterMinute = entry.getValue();
                repeatedMinute = Integer.parseInt(entry.getKey());    
            }
        }
    }
    
    //Count the guard that have the most requently minute asleep
    //iterates around the collections that store the visuall records
    //then saves the results into a new TreeMap and the iterate it to find the
    //guard and minute that was most asleep
    protected void countFrequentlyAsleep(){
        TreeMap<String, TreeMap<Integer, Integer>> mostFrequentlyAsleep = new TreeMap<>();
        
        String regex = "#\\d{2,4}";
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        String guardInTurn = null;
        for(Map.Entry<String, StringBuilder> entry : this.outputMap.entrySet()){  
        TreeMap<Integer, String> minuteASleepCounter = new TreeMap<>();
        
            Matcher m = p.matcher(entry.getValue());
            while (m.find()) {
                guardInTurn = m.group();
            }
            if(mostFrequentlyAsleep.get(guardInTurn) != null){
                
            }else{
                mostFrequentlyAsleep.put(guardInTurn, new TreeMap<Integer, Integer>());
            }
            
             
            int initialIndex = entry.getValue().length() - GUARD_JOURNEY;//72-59=13 
            for(int i = initialIndex-1; i<entry.getValue().length(); i++){
                   
                if(entry.getValue().charAt(i-1) == '#'){
                    if(mostFrequentlyAsleep.get(guardInTurn).get(i-initialIndex) != null){
                        mostFrequentlyAsleep.get(guardInTurn).put(i-initialIndex, 
                                mostFrequentlyAsleep.get(guardInTurn).get(i-initialIndex)+1);
                    }else{
                        mostFrequentlyAsleep.get(guardInTurn).put(i-initialIndex, 1);
                    }
                }
            }
        }
        int mostAsleep = 0;
        int mostRepeateMinute = 0;
        String guard = "";
        for(Map.Entry<String, TreeMap<Integer, Integer>> entry : mostFrequentlyAsleep.entrySet()){
                for(Map.Entry<Integer, Integer> entry2 : entry.getValue().entrySet()){
                    if(entry2.getValue() >= mostAsleep){
                        mostAsleep = entry2.getValue();
                        mostRepeateMinute = entry2.getKey();
                        guard = entry.getKey();
                    }
                }
            }
        
        System.out.println("*  PART B: "+guard+" * "+mostRepeateMinute+" = "+(Integer.parseInt(guard.substring(1))*mostRepeateMinute));
    }
    
}
