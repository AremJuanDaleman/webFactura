/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;

/**
 *
 * @author danie
 */
@Service
@RestController
@RequestMapping(value = "/factura")
public class IntermediarioController {    
    private static int numeracion = 1;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> nuevaFactura(@RequestBody Factura f) {
        JSONObject factura = new JSONObject();
        try{
            factura.put("id", numeracion);
            factura.put("nombre Empleado", f.getNombreEmpleado());  
            factura.put("nombre Cliente", f.getNombreCliente());        
            numeracion++;
            URL link = new URL("https://aremfinalapi.herokuapp.com/cliente?factura="+factura);
            
            try (BufferedReader reader
                    = new BufferedReader(new InputStreamReader(link.openStream()))) {
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println(inputLine);  
                }
            } catch (IOException x) {
                System.err.println(x);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            Logger.getLogger(IntermediarioController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);        }
        
    }
}
