package adventofcode2018;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author lugoe
 */
public class Chooser {
    JFileChooser fileChooser = new JFileChooser();
    
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
