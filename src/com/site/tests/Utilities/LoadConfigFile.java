package com.site.tests.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class LoadConfigFile {

    private static LoadConfigFile instance = null;
    private Properties properties;
    private Properties propertiesLender;

    protected LoadConfigFile() throws IOException{

       properties = new Properties();
       propertiesLender = new Properties();
       String path = new File("globalConfig.properties")
		.getAbsolutePath();

        String Lenderpath = new File("lenders.properties")
                .getAbsolutePath();

        FileInputStream inFile = new FileInputStream(path);
        FileInputStream lendernFile = new FileInputStream(Lenderpath);

        properties.load(inFile);
        propertiesLender.load(lendernFile);

        properties.putAll(propertiesLender);

    }

    public static LoadConfigFile getInstance() {
        if(instance == null) {
            try {
               synchronized (LoadConfigFile.class) {
                   if ( instance == null){
                       instance = new LoadConfigFile();
                   }
               }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key).trim();
    }
    
    public void setValue(String key,String value) {
         properties.setProperty(key, value);
         OutputStream out=null;
         try {
			 out= new FileOutputStream(new File("lenders.properties"));
		     properties.store(out, "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
        
         if(out != null){

             try {

                 out.close();
             } 
             catch (IOException ex) {

                 System.out.println("IOException: Could not close  LoadConfig setValue output stream; " + ex.getMessage());
                 ex.printStackTrace();
             }
         }
		}
    }

    
  

}