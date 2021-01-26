/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect.dicom.query;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.out;
/**
 *
 * @author Varian
 */
public class Util {
    
    
    public enum OS {
            win, lin, mac, sun
        };// Operating systems.
    private static OS os = null;
    
    public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.win;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.lin;
            } else if (operSys.contains("mac")) {
                os = OS.mac;
            } else if (operSys.contains("sunos")) {
                os = OS.sun;
            }
        }
        return os;
    }
    
    public static void teste() throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\Program Files\\Microsoft SQL Server\" && dir");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }
    
    public static String GetNetmask(InetAddress ip) throws SocketException{
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);
            short prflen= networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
            int shft = 0xffffffff<<(32-prflen);
            int oct1 = ((byte) ((shft&0xff000000)>>24)) & 0xff;
            int oct2 = ((byte) ((shft&0x00ff0000)>>16)) & 0xff;
            int oct3 = ((byte) ((shft&0x0000ff00)>>8)) & 0xff;
            int oct4 = ((byte) (shft&0x000000ff)) & 0xff;
            String submask = oct1+"."+oct2+"."+oct3+"."+oct4;
            System.out.println("netmask " +submask );
        return submask;
    }
    
      public static String GetGetway() throws SocketException, IOException{
        String gateway;
        Process p;
        ///netsh interface ipv4 show config pegar dhcp...  startswith dchp.... no Nao ... 
        p = Runtime.getRuntime().exec("netstat -rn");
        BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = output.readLine();
        while(line != null){
            if ( line.trim().startsWith("default") == true || line.trim().startsWith("0.0.0.0") == true )
                break;      
            line = output.readLine();
        }
        if(line==null) //gateway not found;
            return null;

        StringTokenizer st = new StringTokenizer( line );
        st.nextToken();
        st.nextToken();
        gateway = st.nextToken();
        System.out.println("gateway is: "+gateway);

        return gateway;
     
      }
    
    
    public static void DeleteFile(String filename){
        File myObj = new File(filename); 
        if (myObj.delete()) { 
          System.out.println("Deleted the file: " + myObj.getName());
        } else {
          System.out.println("Failed to delete the file.");
        } 
    }
    
   /*
    
    https://stackoverflow.com/questions/26231227/change-computer-ip-address-using-java
    https://jinujawad.com/change-computer-ip-address-using-java/
    http://prabu-lk.blogspot.com/2008/10/java-source-code-to-change-local-ip.html
    
    */
    public  void SetStaticIP() throws IOException{
        InetAddress IpAdress = InetAddress.getLocalHost();

        String ip= IpAdress.getHostAddress();
        String netmask= GetNetmask(IpAdress);
        String getway =  GetGetway();
        String[] command1 = { "netsh", "interface", "ip", "set", "address",
        "name=", "Local Area Connection" ,"source=static", "addr=",ip,
        "mask=", netmask};
        
        //netsh int ip set address name=“Conexão Local” source=static 192.168.10.123 255.255.0.0 192.168.10.1
        //netsh int ip set address name="Ethernet" source=static 192.168.10.123 255.255.0.0 192.168.10.1
        
        /*
        try {
            getInterfaces();
            
            
        } catch (Exception e) {
        }
        */
        



 
    }
    public void runCMD(String command) throws IOException{
        
        ProcessBuilder builder = new ProcessBuilder(
            command
        );
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
       
    }
    
    public void getInterfaces() throws SocketException{
                Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);

    }
    
    //https://docs.oracle.com/javase/tutorial/networking/nifs/listing.html
    public void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            
            
            //  if(netint.getName().contains("lo")){
            if(netint.isLoopback() == true){ 
            }else{
           
                out.printf("Display name: %s\n", netint.getDisplayName());
                out.printf("Name: %s\n", netint.getName());
                out.printf("sub");

                

                out.printf("InetAddress: %s\n", inetAddress);
                out.printf("\n");
                
            }
     
        }
        
     }
    
  
}