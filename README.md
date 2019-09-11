# Advent Of Code Y=2018
### by HLA.

## How to run the project from Command Prompt(Windows)
1. Clone/download the project and copy the happy path of the src folder:
    mine srcrHappyPath: C:\Users\<userName>\Documents\NetBeansProjects\AdventOfCode2018\src
2. Open CMD
    Having problems with Java Environment Variables go to [stackoverflow](https://stackoverflow.com/questions/7709041/javac-is-not-recognized-as-an-internal-or-external-command-operable-program-or)
3. Use the cd [srcHappyPath] to go to the folder where the java files of theproject are.
4. (You can skip this step they are already compiled)
    Use  "javac *.java" command to compile all the java files.
5. className.class file will be updated/created.
6. Run the main class with **"java AdventOfCode2018"** command.


## How the program works:
    Note: all the day results will be displayed ain the following way:
        PART A: [result here]
        PART B: [result here]
        
### Day 1:
    Will ask you for **"Enter the starting frequency: "** 
        0 will be the best option ;D
    Then **"Do you want to see all frequencies? Y/N(faster option):"**
        Y will show you a message like this **"Currrent frequency: 0, change of -1; resulting frequency -1"** until a resulting frequency is repeated.
    Now a **"JChooserDialog"** will appear select the file **"Day1Input.txt"** from the **"..\src\inputs"** folder. 
    Results will be displayed in the screen

### Day 2 Inventory Management System:
    This is the most easy one to use just select your file input like **"Day2Input.txt"** from **"..\src\inputs"** folder and see the magic.
    
### Day 4 Repose Record:
    Will ask **"Do you want to see the chronological order? Y/N:"**
        if you selected Y, then on the screen all the input sorted will appear.
    Second question is "Do you want to see the visually records? Y/N:"
        Basically you will see the records in a visually way like in the AdventOfCode page.
        DATE    ID  MINUTE
                    000000000011111111112222222222333333333344444444445555555555
                    012345678901234567890123456789012345678901234567890123456789
        02-01 #137  .......................................################.....
                and more and more rows...
    last thing to do here is to select the input file like **"Day2Input.txt"** from **"..\src\inputs"** folder.
