package sample;

import javax.swing.*;
import java.io.File;

public class Setting  {

    public String newPath;

    /**
     * The constructor generates a new window (using Swing) to select a new folder.
     * Chart screenshots will be saved in this folder
     * @param path
     */
    Setting( String path) {
        JFileChooser directoryPath = new JFileChooser();
        directoryPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = directoryPath.showDialog(null, "Обрати папку");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = directoryPath.getSelectedFile();
            newPath=file.getPath();
            Main m=new Main();
            m.globalPath=newPath+"\\";
        }
    }
}
