

import java.io.File;
import javax.swing.JFileChooser;

public class Chooser {
    JFileChooser fileChooser = new JFileChooser();
    
    //@return the happy path of a file selected 
    protected File getFile() throws Exception{
        File selectedFile = null;
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile();
        }else{
            System.out.println("***********************************");
            System.out.println("* File was not selected or valid. *");
            System.out.println("***********************************");
            System.out.println("");
        }
        return selectedFile = new File(selectedFile.getAbsolutePath());
    }
}
