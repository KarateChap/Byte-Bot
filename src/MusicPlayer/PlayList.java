package MusicPlayer;

/**
 * This class will serve as play list of MP3 player.
 * extends Swing JFrame
 * @author Bugay, Maglinao, Mariano, Olivar, Robles, Susara
 * @since 2019-03-28
 * @version 1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


/**
*This class serve as music selector and list multiple songs to be added on the MP3 player.
*/
public class PlayList {

    JFileChooser fileChooser = new JFileChooser();
    ArrayList listSong = new ArrayList();

    /**
    *This method checks the chosen music file if it is valid or not, valid files will be added to song list and if not it will not be added. 
    *@param frame
    */
    
    public void add(JFrame frame) {
        fileChooser.setMultiSelectionEnabled(true);
        int fileValid = fileChooser.showOpenDialog(frame);
        if (fileValid == javax.swing.JFileChooser.CANCEL_OPTION) {
            
        } else if (fileValid == javax.swing.JFileChooser.APPROVE_OPTION) {
            File[] file = fileChooser.getSelectedFiles();
            listSong.addAll(Arrays.asList(file));

        }
    }
    /**
    *This method gets song list.
    *@return listSong.
    *
    */
    
    public ArrayList getListSong() {
        return listSong;
    }
    FileOutputStream fileOutputStream;
    ObjectOutputStream objetOutputStream;
    
    /*
    *This method saves the chosen valid music file to the play list.  
    *@param frame
    */
    
    public void saveAsPlaylist(JFrame frame) {
        fileChooser.setMultiSelectionEnabled(true);
        int Valid = fileChooser.showOpenDialog(frame);
        if (Valid == javax.swing.JFileChooser.CANCEL_OPTION) {
            
        } else if (Valid == javax.swing.JFileChooser.APPROVE_OPTION) {
            File[] pls = fileChooser.getSelectedFiles();
            try { 
                fileOutputStream = new FileOutputStream(pls + ".tgr");
                objetOutputStream = new ObjectOutputStream(fileOutputStream);
                for (int i = 0; i < listSong.size(); i++) {
                    File tmp = (File) listSong.get(i);
                    objetOutputStream.writeObject(tmp);
                }
                objetOutputStream.close();
            } catch (Exception e) {
            }
        }
    }
    FileInputStream fileInputStream;
    ObjectInputStream objetInputStream;
}
