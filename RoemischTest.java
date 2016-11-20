import junit.framework.*;
import java.lang.NumberFormatException;
import org.junit.Test;

public class RoemischTest extends TestCase{
    
    /*
    *   Test das kontrolliert das jede Zahl zwischen 1 und 4999 fuer beides 
    *   arabisch in roemisch und roemisch in arabisch verwandeln
    */	
    public void testRightNumber(){
    	for(int i=1;i<5000;i++)
    	{
    	    try
    	    {
    	    assertEquals(i,new Roemisch(new Roemisch(i+"").arabischNachRoemisch())
    		.roemischNachArabisch());
    		}
    	    catch(Exception e)
    	    {
    	    	System.out.println(e.getMessage()+" bei "+i);
    	    }
    	}
      }
      
    /*
    *   Test das kontrolliert das eine Exception ausgegeben wird von arabischNachRoemisch
    *    wenn die eingabe 0 ist
    */
    public void testZero(){
    	boolean thrown=false;
    	
    	try{
    		new Roemisch("0").arabischNachRoemisch();
    	}
    	catch(Exception e)
    	{
    		thrown = true;
    	}
    	assertTrue(thrown);
    }
      
    /*
    *   Test das kontrolliert das eine Exception ausgegeben wird von arabischNachRoemisch
    *   wenn die eingabe keine Zahl ist
    */
    public void testNoNumberArabisch(){
    	boolean thrown=false;
    	
    	try{
    		new Roemisch("p").arabischNachRoemisch();
    	}
    	catch(Exception e)
    	{
    		thrown = true;
    	}
    	assertTrue(thrown);
    }
      
    /*
    *   Test das kontrolliert das eine Exception ausgegeben wird von roemischNachArabisch
    *   wenn die eingabe keine Zahl ist
    */
    public void testNoNumberRoemisch(){
    	boolean thrown=false;
    	
    	try{
    		new Roemisch("p").roemischNachArabisch();
    	}
    	catch(Exception e)
    	{
    		thrown = true;
    	}
    	assertTrue(thrown);
    }
    
      
    /*
    *   Test das kontrolliert das eine Exception ausgegeben wird von roemischNachArabisch
    *   wenn die eingabe eine falsche roemische Zahl ist
    */
    public void testFalscheRoemisch(){
    	boolean thrown=false;
    	
    	try{
    		new Roemisch("XXXX").roemischNachArabisch();
    	}
    	catch(Exception e)
    	{
    		thrown = true;
    	}
    	assertTrue(thrown);
    }
    
      
    /*
    *   Test das kontrolliert das eine Exception ausgegeben wird von roemischNachArabisch
    *   wenn die eingabe eine arabische Zahl ist
    */
    public void testNumberRoemisch(){
    	boolean thrown=false;
    	
    	try{
    		new Roemisch("1").roemischNachArabisch();
    	}
    	catch(Exception e)
    	{
    		thrown = true;
    	}
    	assertTrue(thrown);
    }
    
    /*
    *   Test das kontrolliert das jede arabische Zahl zwischen 1 und 4999 fuer zahlKontrollieren 
    *   eine a ausgibt
    */	
    public void testArabischKontrollieren(){
    	for(int i=1;i<5000;i++)
    	{
    	    try
    	    {
    	    assertEquals('a',new Roemisch(i+"").zahlKontrollieren());
    		}
    	    catch(Exception e)
    	    {
    	    	System.out.println(e.getMessage()+" bei "+i);
    	    }
    	}
    }
      
    /*
    *   Test das kontrolliert das eine Exception ausgegeben wird von zahlKontrollieren
    *   wenn die eingabe keine Zahl ist
    */
    public void testNoNumberKontrollieren(){
    	boolean thrown=false;
    	
    	try{
    		new Roemisch("p").zahlKontrollieren();
    	}
    	catch(Exception e)
    	{
    		thrown = true;
    	}
    	assertTrue(thrown);
    }
    
    /*
    *   Test das kontrolliert das eine roemische Zahl fuer zahlKontrollieren 
    *   eine r ausgibt
    */	
    public void testRoemischKontrollieren(){
    	
    	try{
    		assertEquals('r',new Roemisch("MM").zahlKontrollieren());
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    
}