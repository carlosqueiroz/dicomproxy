/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect.dicom.query;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.SwingUtilities;
import java.util.Arrays;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
/**
 *      
 * //https://stackoverflow.com/questions/362473/how-to-upload-files-to-password-protected-https-site-using-curl       
 * //https://stackoverflow.com/questions/20885989/post-4gb-file-from-shell-using-curl
 * // curl -X POST --data-binary @1.dcm https://192.168.10.195/api/uploadtest --insecure
 * //curl -X POST -F "userid=1" -F "filecomment=This is an image file"  -F "file=@1.dcm"  https://192.168.10.195/api/uploadtest --insecure
 * //curl  --insecure -H "Accept: application/xml" -H "Content-Type: application/xml" -X GET https://192.168.10.195/api/uploadtest
 * @author RT MEDICAL
 */
public class UploadCloud {
     Thread backgroundWorker = new Thread();
     String os = null;
 
     Process p = null;
     Properties prop = new Properties();
     AdvancedPreferences ap = new AdvancedPreferences();
     String DcmBinFolder = "dcm4che-5.22.6";
 
 
     public String UploadDicom(String filename) throws Exception{
        // Detect OS SYSTEM
        Util util = new Util(); 
        os = util.getOS().toString(); 
        
        
         System.out.println("connect.dicom.query.UploadCloud.UploadDicom()");
         System.out.println(filename);
        
        prop.load(new FileInputStream("C:\\ProgramData\\config.properties"));
        ap.URL = prop.getProperty("URL");
        ap.CLIENT = prop.getProperty("CLIENT");
        ap.USER = prop.getProperty("USER");
        ap.PASSWORD = prop.getProperty("PASSWORD");
        ap.TOKEN = prop.getProperty("TOKEN");
        ap.STOWRS = prop.getProperty("STOWRS");
         
        
        String AuthStow = "";
         if (ap.PASSWORD != "") {
            AuthStow = "--user "+ap.USER+":"+ap.PASSWORD;
         }
        
        //curl -X POST -F "userid=1" -F "filecomment=This is an image file"  -F "file=@1.dcm"  https://192.168.10.195/api/uploadtest --insecure
        
        /*
        String[] cmd = new String[4];
        cmd[0] = "cmd";
        cmd[1] =  "/c lib\\curl\\"+os+"\\bin\\curl.exe/";
        cmd[2] = "curl/";
        cmd[3] = "-X POST -F \"userid=1\" -F \"filecomment=This is an image file\"  -F \"file=@"+filename+"\"  https://192.168.10.195/api/uploadtest --insecure";
       */
        
        //System.out.println(Arrays.toString(cmd));
        
        ProcessBuilder builder;
        
        if ( prop.getProperty("STOWRS").equals("1") == true) {
            // ENVIO VIA STOW
         System.out.println("STOW");
         builder = new ProcessBuilder(
            "cmd " ,
            "/c lib\\dcm\\"+os+"\\"+DcmBinFolder+"\\bin\\stowrs.bat "+" --url "+ap.URL+" \""+filename+"\""
           );
            System.out.println(
                "cmd " + "/c lib\\dcm\\"+os+"\\"+DcmBinFolder+"\\bin\\stowrs.bat "+" --url "+ap.URL+"  \""+filename+"\""
            );
        }else{
             System.out.println("CURL");
            // ENVIO VIA CURL
           builder = new ProcessBuilder(
            "cmd" ,
            "/c lib\\curl\\"+os+"\\bin\\curl.exe/",
            "curl/",
            " -o -L -s -X POST -F \"client="+ap.CLIENT+"\" -F \"file=@"+filename+"\"  "+ap.URL+" --insecure"
           );
           
           System.out.println(            
            "cmd" +
            "/c lib\\curl\\"+os+"\\bin\\curl.exe/"+
            "curl/"+
            " -o -L -s -X POST -F \"client="+ap.CLIENT+"\" -F \"file=@"+filename+"\"  "+ap.URL+" --insecure");
        }

 
     
         
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            boolean sucess = line.indexOf("200") !=-1? true: false; //true
            if(sucess == true){
                System.out.println("ENVIADO");
                util.DeleteFile(filename);
                
            }else{
                System.out.println(line); 
                Main main = new Main();
                main.setTextArea(line);
            
          
                 System.out.println("ERRO");
                 System.err.println(line);
            }
            
        }
 
         return null;   
     }
 
     
     public String SendNotSyncFiles(File directory){
         // Lista todos os diretórios de uma pasta
        if(directory.isDirectory()) {
            //Pastas
           // System.out.println(directory.getPath());
            
            String ListFilesInDirectory = ListFilesInDirectory(directory.getPath());
             
            String[] subDirectory = directory.list();
            if(subDirectory != null) {
                for(String dir : subDirectory){
                    SendNotSyncFiles(new File(directory + File.separator  + dir));
                }
            }
        }
                 
         return "s";
     }
     public boolean IsDicom(String file) throws IOException{
         boolean ret = false;
         Util util = new Util(); 
         String os = util.getOS().toString();
           System.out.println("cmd "+ "/c "+ "lib\\dcm\\"+os+"\\"+DcmBinFolder+"\\bin\\dcmdump.bat \""+file+"\"");        
          ProcessBuilder builder = new ProcessBuilder(
            "cmd ", "/c ", "lib\\dcm\\"+os+"\\"+DcmBinFolder+"\\bin\\dcmdump.bat  \""+file+"\"");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            
            ret = line.indexOf("0020") !=-1? true: false; //true
            
        } 
        
        
         return ret;
     }
     public String ListFilesInDirectory(String directory){
        Util util = new Util(); 
        String os = util.getOS().toString();
         
        File folder = new File(directory);
 
        File[] files = folder.listFiles();
         try {
            for (File file : files)
            {
                if (file.isFile())
                {
                    //ARQUIVO RODANDO EM UM THREAD
                    
                     System.out.println(directory+"\\"+file.getName());
                     
                    if (IsDicom(directory+"\\"+file.getName()) == true){
                     
                        UploadDicom(directory+"/"+file.getName());
                    }  
                     
                     ////
                      
                    
                }

            }
         } catch (Exception e) {
              System.out.println(e);
         }

        
     return "";
     }
     
}
