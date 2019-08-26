package com.detzerg.rdfa;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Detzer
 */
public class RFAController {
    private final String HTTP = "https://www.w3.org/2012/pyRdfa/extract";
    private Http multipartForm;
                  
    public boolean processFile() {
       File f = new File("web.html");
       if(!f.exists()) {
           System.out.println("El archivo web.html no existe");           
           return false;
       } else {
           try {
                System.out.println("Iniciando conversión, espere....");
                this.multipartForm = new Http(HTTP, "UTF-8");
                this.multipartForm.addHeaderField("Content-Type", "application/rdf+xml;");              
                this.multipartForm.addFormField("format", "xml");
                this.multipartForm.addFormField("host_language", "html");
                this.multipartForm.addFilePart("uploaded", f);
                this.multipartForm.finish();                
                return true;
           } catch (IOException ex) {
               Logger.getLogger(RFAController.class.getName()).log(Level.SEVERE, null, ex);
               return false;
           }
       }
    }
}
