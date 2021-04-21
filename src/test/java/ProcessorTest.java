
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;


public class ProcessorTest extends TestCase {
	    private PrintStream _output; 
	    private BufferedReader _input;
	   @Test  
	    public void testFindMax(){  
		   String result1="Lisa:$-93\n" + 
		   				  "Quincy:error\n" + 
		   				  "Tom:$500\n";
			File file=new File("/Users/pramod/Documents/workspace1-copy/PayPalPe/src/test/java/store.txt"); 
	        InputStream in = null; String res="";
	        try {
				in = new FileInputStream(file);
				Processor bs= new Processor();
				_input = new BufferedReader(new InputStreamReader(in));
		        _output = System.out; 
				 String line = _input.readLine();
			        while (line != null && line.length() > 0) {
			            if (line.equals("END")) {	               
			               res=bs.summary();
			                break;
			                }
			            bs.process_command(line);
			            line = _input.readLine();
			        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println(res);
		  assertEquals(result1+"END",res );
		  assertTrue((result1+"END").equals(res));			  
		 
	         
	        
	    }
	   
	   public void process () throws IOException {
	       
	    }
}

