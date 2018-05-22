/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.factura;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import java.net.URL;
import main.factura.api.Formato;
import main.factura.api.FormatoCliente;
import main.factura.api.FormatoDIAN;
import main.factura.envio.EnvioCorreoFacturas;

/**
 *
 * @author danie
 */

public class IntermediarioController implements Runnable {    
    private static int numeracion = 1;        
    private ServerSocket socket;    
    //private Api api;
    

    IntermediarioController(ServerSocket socket) {
        this.socket = socket;        
    }

    @Override
    public void run() {
        while (true) {
            try {
                init();
            } catch (IOException ex) {
                Logger.getLogger(IntermediarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void init() throws IOException{
        Socket clientSocket = null;
        try {            
            clientSocket = socket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if(inputLine.startsWith("GET")){
                String path = inputLine.split(" ")[1];
                if(path.equals("/") || path.equals("/index.html")){                                        
                    //File indexFile =new File("classes/index.html");                    
                    File indexFile = new File("src/main/resources/index.html");                    
                    String output="";
                    String text;                    
                    try {                        
                        FileReader input = new FileReader(indexFile);                        
                        BufferedReader br = new BufferedReader(input);
                        
                        while((text = br.readLine())!=null) {
                            output+=text;
                        }                        
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(IntermediarioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n\r\n" + output;
                    
                    out.println(outputLine);
                }
                else if(path.contains("solicitud")){                                                            
                    
                    
                    //Recibir enforma de texto la factura
                    String datos = String.valueOf(path.split("/")[3]);                                                                                                
                      
                    
                    Formato f = new FormatoCliente();
                    Formato d= new FormatoDIAN();
                    String facturaCliente = f.getResult(datos);
                    String facturaDian = d.getResult(datos);
                    
                    
                    EnvioCorreoFacturas ev = new EnvioCorreoFacturas();
                    ev.send(facturaCliente);
                    ev.send(facturaDian);
                    
                    try{                                                                       
                        outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n\r\n"                                        
                        + "<h1> FACTURA ENVIADA EXITOSAMENTE\n </h1></br>"
                        + facturaCliente + "\n";                                                      
                        out.println(outputLine);
                    
                    }catch(Exception e){
                        System.out.println("ENVIO FALLIDO DE LA FACTURA");
                    }                                                            
                }
            }
            
            if (!in.ready()) {
                break;
            }
            
            if (inputLine.equals("")) break;
        }
        
        out.close();
        in.close();
        clientSocket.close();
    }
}
