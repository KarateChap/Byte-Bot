package MusicPlayer;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.DefaultListModel;

/**
 * This class will serve as MP3 Player for the chat bot
 * extends Swing JFrame
 * @author Bugay, Maglinao, Mariano, Olivar, Robles, Susara
 * @since 2019-03-28
 * @version 1.0
 */
public class mp3Player extends javax.swing.JFrame {

    /**
     * Constructor.
     * Initializes and displays all the components of the Main Frame.
     * Used to set the position of the Main Frame to the center.
     */
    PlayList playList = new PlayList();
    ArrayList updateList = new ArrayList();
    javazoom.jl.player.Player player;

    public mp3Player() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
    *This method records the updated song list of MP3 Player.
    */
    public void updateList() {
        updateList = playList.getListSong();
        DefaultListModel model = new DefaultListModel();
        for (int count = 0; count < updateList.size(); count++) {
            int count2 = count + 1;
            model.add(count, count2 + " | " + ((File) updateList.get(count)).getName());
        }
        jPlaylist.setModel(model);

    }
    /**
    *This method adds new song in the MP3 player song list.
    */
    public void add() {
        playList.add(this);
        updateList();
    }
     /**
    *This method removes a song in the MP3 player song list.
    */
   public void remove() {
        try {
            int selection = jPlaylist.getLeadSelectionIndex();
            playList.listSong.remove(selection);
            updateList();
        } catch (Exception e) {
        }
    }

    File play1;
    static int localCount = 0;
    /**
    *This method starts playing a song that is selected at the MP3 player song list.
    */
   public void play() {
        if (localCount == 0) {
            try {
                int p1ay = jPlaylist.getSelectedIndex();
                play1 = (File) this.updateList.get(p1ay);
                FileInputStream fis = new FileInputStream(play1);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                localCount = 1;
            } catch (Exception e) {
                System.out.println("Problem playing file");
                System.out.println(e);
            }

            new Thread() {
                @Override
             
                public void run() {
                    try {
                        player.play();

                    } catch (Exception e) {
                    }
                }
            }.start();
        } else {
            player.close();

            localCount = 0;
            play();
        }
    }

    File newFile;
    /**
    *This method stops the current playing song and starts playing the next song in the play list.
    */
    public void next() {
        if (localCount == 0) {
            try {
                int s1 = jPlaylist.getSelectedIndex() + 1;
                newFile = (File) this.playList.listSong.get(s1);
                FileInputStream fis = new FileInputStream(newFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                localCount = 1;
                jPlaylist.setSelectedIndex(s1);
            } catch (Exception e) {
                System.out.println("Problem playing file");
                System.out.println(e);
            }

            new Thread() {
                @Override
       
                public void run() {
                    try {
                        player.play();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            player.close();
            localCount = 0;
            next();
        }

    }
    /**
    *This method stops the current playing song and starts playing the previous song in the play list.
    */
    public void previous() {
        if (localCount == 0) {
            try {
                int s1 = jPlaylist.getSelectedIndex() - 1;
                newFile = (File) this.playList.listSong.get(s1);
                FileInputStream fis = new FileInputStream(newFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                localCount = 1;
                jPlaylist.setSelectedIndex(s1);
            } catch (Exception e) {
                System.out.println("Problem playing file");
                System.out.println(e);
            }

            new Thread() {
                @Override
                public void run() {
                    try {
                        player.play();

                    } catch (Exception e) {
                    }
                }
            }.start();

        } else {
            player.close();
            localCount = 0;
            previous();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPlaylist = new javax.swing.JList<>();
        addBTN = new javax.swing.JButton();
        playBTN = new javax.swing.JButton();
        prevBTN = new javax.swing.JButton();
        nextBTN = new javax.swing.JButton();
        stopBTN = new javax.swing.JButton();
        removeBTN = new javax.swing.JButton();
        volumeUpBTN = new javax.swing.JButton();
        volumeDownBTN = new javax.swing.JButton();
        mute = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jPlaylist);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 430, 190));

        addBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Music_Folder_60px.png"))); // NOI18N
        addBTN.setText("ADD TO PLAYLIST");
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });
        getContentPane().add(addBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 230, 70));

        playBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Circled_Play_60px.png"))); // NOI18N
        playBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBTNActionPerformed(evt);
            }
        });
        getContentPane().add(playBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 80, 70));

        prevBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Sort_Left_60px.png"))); // NOI18N
        prevBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBTNActionPerformed(evt);
            }
        });
        getContentPane().add(prevBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 70, 70));

        nextBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Sort_Right_60px.png"))); // NOI18N
        nextBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBTNActionPerformed(evt);
            }
        });
        getContentPane().add(nextBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 70, 70));

        stopBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Stop_60px.png"))); // NOI18N
        stopBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopBTNActionPerformed(evt);
            }
        });
        getContentPane().add(stopBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 130, 100));

        removeBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Trash_60px.png"))); // NOI18N
        removeBTN.setText("REMOVE");
        removeBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBTNActionPerformed(evt);
            }
        });
        getContentPane().add(removeBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 230, 80));

        volumeUpBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Sort_Up_60px.png"))); // NOI18N
        volumeUpBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumeUpBTNActionPerformed(evt);
            }
        });
        getContentPane().add(volumeUpBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 130, 60));

        volumeDownBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Sort_Down_60px.png"))); // NOI18N
        volumeDownBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumeDownBTNActionPerformed(evt);
            }
        });
        getContentPane().add(volumeDownBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 130, 60));

        mute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/icons8_Mute_60px.png"))); // NOI18N
        mute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteActionPerformed(evt);
            }
        });
        getContentPane().add(mute, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 130, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MusicPlayer/mainFrame.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    *This method runs the add method.
    *@param evt
    */
    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTNActionPerformed
        add();
    }//GEN-LAST:event_addBTNActionPerformed
    /**
    *This method runs the play method.
    *@param evt Sets the background color of playBTN to green while stopBTN to white.
    */
    private void playBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBTNActionPerformed
        play();
        playBTN.setBackground(Color.GREEN);
        stopBTN.setBackground(Color.white);
    }//GEN-LAST:event_playBTNActionPerformed
     /**
    *This method runs the previous method.
    *@param evt
    */
    private void prevBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBTNActionPerformed
        previous();
    }//GEN-LAST:event_prevBTNActionPerformed
     /**
    *This method runs the next method.
    *@param evt
    */
    private void nextBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBTNActionPerformed
        next();
    }//GEN-LAST:event_nextBTNActionPerformed
     /**
    *This method runs the close method.
    *@param evt
    */
    private void stopBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopBTNActionPerformed
        player.close();
        stopBTN.setBackground(Color.red);
        playBTN.setBackground(Color.white);
    }//GEN-LAST:event_stopBTNActionPerformed
     /**
    *This method runs remove method.
    *@param evt
    */
    private void removeBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBTNActionPerformed
        remove();
    }//GEN-LAST:event_removeBTNActionPerformed
     /**
    *This method runs the volumeUpControl method.
    *@param evt
    */
    private void volumeUpBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumeUpBTNActionPerformed
        volumeUpControl(0.2);
        mute.setBackground(Color.WHITE);
        volumeUpBTN.setBackground(Color.WHITE);
        
    }//GEN-LAST:event_volumeUpBTNActionPerformed
      /**
    *This method runs the volumeDownControl method.
    *@param evt
    */
    private void volumeDownBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumeDownBTNActionPerformed
        volumeDownControl(0.2);
    }//GEN-LAST:event_volumeDownBTNActionPerformed
      /**
    *This method runs the volumeControl method.
    *@param evt
    */
    private void muteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteActionPerformed
        
        volumeControl(0.0);
        volumeUpBTN.setBackground(Color.GREEN);
        mute.setBackground(Color.ORANGE);
    }//GEN-LAST:event_muteActionPerformed

    /**
     * This method stops the music from playing when the window is close.
     * @param evt 
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try{
            player.close();
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mp3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mp3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mp3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mp3Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mp3Player().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jPlaylist;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mute;
    private javax.swing.JButton nextBTN;
    private javax.swing.JButton playBTN;
    private javax.swing.JButton prevBTN;
    private javax.swing.JButton removeBTN;
    private javax.swing.JButton stopBTN;
    private javax.swing.JButton volumeDownBTN;
    private javax.swing.JButton volumeUpBTN;
    // End of variables declaration//GEN-END:variables
// Let's Set Volume Down Method It's not necessary to remeber this code.
    private void volumeDownControl(Double valueToPlusMinus){
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for(Mixer.Info mixerInfo : mixers){
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for(Line.Info lineInfo : lineInfos){
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if(!opened){
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float)currentVolume-(double)volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    // Close Line If it opened
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    
    // Let's Set Volume Up Method It's not necessary to remeber this code.
    private void volumeUpControl(Double valueToPlusMinus){
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for(Mixer.Info mixerInfo : mixers){
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for(Line.Info lineInfo : lineInfos){
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if(!opened){
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float)currentVolume+(double)volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    // Close Line If it opened
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
    
    // Let's Set Volume Method It's not necessary to remeber this code.
    private void volumeControl(Double valueToPlusMinus){
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for(Mixer.Info mixerInfo : mixers){
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for(Line.Info lineInfo : lineInfos){
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try{
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if(!opened){
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((double)volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);
                    
                }catch (LineUnavailableException lineException){
                }catch (IllegalArgumentException illException){
                }finally{
                    // Close Line If it opened
                    if(line != null && !opened){
                        line.close();
                    }
                }
            }
        }
    }
}
