package util;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import exceptions.FileDialogException;

/**
 *
 * The class for the Save File Dialog.
 * 
 * @author Jim Stanev
 */
public class SaveFileDialog {
    
    
    /**
     * String global variable file.
     */
    private String file;
       
    /**
     * Constructor.
     * 
     * @throws FileDialogException 
     * 
     */
    public SaveFileDialog() throws FileDialogException {
        getFileOrDirectory();  
    }
    
    /**
     * Get the file.
     * 
     * @return the file object 
     */
    public String getFile(){
        return this.file;
    }
    
    /**
     * Private method for opening the file window and passing the results to the <code>file</code>.
     * 
     * @throws FileDialogException 
     * 
     */
    private void getFileOrDirectory() throws FileDialogException{
    	
        JFileChooser fileToOpen = new JFileChooser();
        
        try {
			File dataBaseDirectory = new File(new File(".").getCanonicalPath());
			fileToOpen.setCurrentDirectory(dataBaseDirectory);
			
			fileToOpen.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
			int result = fileToOpen.showSaveDialog(null);
			
			
	        if(result==JFileChooser.APPROVE_OPTION){
	        	file = fileToOpen.getSelectedFile().getAbsolutePath();
	        }else{
	        	throw new FileDialogException("Error in Save");
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}