package com.site.runners;

import static com.site.tests.Utilities.CommonFunctions.generateAndSendEmailConsumerSite;
import static com.site.tests.Utilities.CommonFunctions.tearDownProcess;
import static com.site.tests.Utilities.CommonFunctions.UpdateDBExcel;
import static com.site.tests.Utilities.CommonFunctions.selectDBExcel_RowCount;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.testng.TestNG;



public class ConsumerSite_TestRunner {

	public static String invoker = null;
	public static void main(String[] args) throws Exception {
		
		
		String s = "Eclipse";

        for (String arg : args) {
            if( arg.equalsIgnoreCase("")){
            }else{
                s = arg;
            }
        }
        
        invoker=s;
		
        // Create object of TestNG Class
        TestNG runner = new TestNG();

        // Create a list of String
        List<String> suitefiles=new ArrayList<String>();

        // Add xml file which you have to execute
        suitefiles.add("testng.xml");

        // now set xml file for execution
        runner.setTestSuites(suitefiles);

        try{
            // finally execute the runner using run method
            runner.run();
            generateAndSendEmailConsumerSite();
           }catch(Exception e){
            tearDownProcess(e);
        }
		

	}


}
