/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect.dicom.query;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author RTMEDICAL
 */
public class SendDICOM extends javax.swing.JFrame {

    public Thread backgroundWorker = null;
    public Process p = null;
    public Properties prop = null;
    String DcmBinFolder = "dcm4che-5.22.6";
  
    
    /**
     * Creates new form SendDICOM
     */
    public SendDICOM(String ipaddr, String ae, String pt, String dest, Properties prop2) {
        initComponents();
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            System.out.println("Unable to load Windows look and feel");
        }
        jTextField1.setText(ipaddr);
        jTextField2.setText(ae);
        jTextField3.setText(pt);
        jTextField4.setText(dest);
        prop = prop2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setTitle("Envio DICOM ...");
        setAlwaysOnTop(true);

        jLabel1.setText("IP:");

        jLabel2.setText("AE Title:");

        jLabel3.setText("Porta:");

        jLabel4.setText("Pasta:");

        jButton1.setText("Pesquisar ...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enviar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Fechar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jButton2)
                .addGap(33, 33, 33)
                .addComponent(jButton3)
                .addContainerGap(144, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = j.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            jTextField4.setText(j.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
 
        prop.setProperty("SD_IP", jTextField1.getText().trim());
        prop.setProperty("SD_AETitle", jTextField2.getText().trim());
        prop.setProperty("SD_port", jTextField3.getText().trim());
        prop.setProperty("SD_destination", jTextField4.getText().trim());
        try {
            prop.store(new FileOutputStream("C:\\ProgramData\\config.properties"), null);
        } catch (Exception e) {
            System.out.println("Error Saving Configuration Properties - " + e.toString());
        }


        jTextArea1.setText("");
        jScrollPane1.getHorizontalScrollBar().setValue(0);
        backgroundWorker = new Thread() {
            public void run() {
            Util util = new Util(); 
            String os = util.getOS().toString(); 
                try {
                    
                    String extension = (os == "win") ? ".bat" : "" ;
                    if(DcmBinFolder == "dcm4che-5.22.6"){
                        
                        ProcessBuilder pb = new ProcessBuilder();
                        pb.redirectErrorStream(true);
                        String[] cmd = new String[5];
                        cmd[0] = "cmd";
                        cmd[1] = "/c";
                        cmd[2] = "lib\\dcm\\"+os+"\\"+DcmBinFolder+"\\bin\\storescu"+extension+" -c";
                        cmd[3] = jTextField2.getText() + "@" + jTextField1.getText().trim() + ":" + jTextField3.getText();
                        cmd[4] = jTextField4.getText();
                        pb.command(cmd);
                        //pb.command("cmd /c DcmBinFolder\\bin\\dcmrcv.bat " + ap.AETitle + "@"+ ipaddr +":"+ ap.port + " -dest \""+ jTextField1.getText() +"\"");
                        p = pb.start();
                        
                    }else if(DcmBinFolder == "dcm4che-2.0.27"){
                        ProcessBuilder pb = new ProcessBuilder();
                        pb.redirectErrorStream(true);
                        String[] cmd = new String[5];
                        cmd[0] = "cmd";
                        cmd[1] = "/c";
                        cmd[2] = "lib\\dcm\\"+os+"\\"+DcmBinFolder+"\\bin\\dcmsnd"+extension+" ";
                        cmd[3] = jTextField2.getText() + "@" + jTextField1.getText().trim() + ":" + jTextField3.getText();
                        cmd[4] = jTextField4.getText();
                        pb.command(cmd);
                        //pb.command("cmd /c DcmBinFolder\\bin\\dcmrcv.bat " + ap.AETitle + "@"+ ipaddr +":"+ ap.port + " -dest \""+ jTextField1.getText() +"\"");
                        p = pb.start();
                    
                    }
                    

                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        final String lineF = line;
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                jTextArea1.append(lineF.trim() + "\r\n");
                                //jTextArea1.append(fLine.trim() + "\r\n");
                                jScrollPane1.getHorizontalScrollBar().setValue(0);

                            }
                        });

                    }
                } catch (final Exception e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            jTextArea1.append(e.toString() + "\r\n");
                            jScrollPane1.getHorizontalScrollBar().setValue(0);
                        }
                    });
                }


                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        jButton1.setEnabled(true);
                        jButton2.setEnabled(true);

                        jTextArea1.append("Stopped by user.\r\n");
                        jScrollPane1.getHorizontalScrollBar().setValue(0);

                    }
                });


            }
        };

        backgroundWorker.start();
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);


    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
