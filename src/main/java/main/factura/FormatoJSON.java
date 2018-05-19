/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.factura;

import org.json.JSONObject;

/**
 *
 * @author danie
 */
public class FormatoJSON implements Factura {

    @Override
    public JSONObject convertir(String f) {        
        JSONObject json = new JSONObject();
        String factura = f.substring(f.indexOf("?")+1, f.length());        
        factura += "&fin";        
        while(!factura.equals("fin")){            
            json.append(factura.substring(0, factura.indexOf("=")),factura.substring(factura.indexOf("=")+1,factura.indexOf("&")));
            factura = factura.substring(factura.indexOf("&")+1, factura.length());            
        }                                      
        return json;
        
    }
    
}
