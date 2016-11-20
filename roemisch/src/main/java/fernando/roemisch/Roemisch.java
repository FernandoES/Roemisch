import java.lang.Character;
import java.lang.Integer;
import java.lang.NumberFormatException;



public class Roemisch
{
    public static String zahl;
	public String latein;
	public int arabisch;
	
    public static void main(String[] args)
	{
		if(args.length!=1)
		{
			System.out.println("Sie sollten eine Zahl (Romisch oder Arabisch) mit dem Program schreiben");
		}
		Roemisch roemisch = new Roemisch(args[0]);
		try
		{
		    switch (roemisch.zahlKontrollieren())
		    {
		    	case 'a': roemisch.latein = roemisch.arabischNachRoemisch();
		    	          roemisch.arabisch= Integer.parseInt(roemisch.zahl);
		    	          System.out.println("Arabische Zahl gegeben "+roemisch.arabisch+"\nRoemische Zahl "+roemisch.latein);
		    	          break;
		    	case 'r': roemisch.arabisch = roemisch.roemischNachArabisch();
		    	          roemisch.latein = zahl.toUpperCase();
		    	          System.out.println("Roemische Zahl gegeben "+roemisch.latein+"\nArabische Zahl "+roemisch.arabisch);
		    	          break;
		    }
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
	}
	
	/*
	*   Einzig Constructor
	*/
	public Roemisch(String gegebenZahl){
	zahl=gegebenZahl.toLowerCase();
	}
	
	/*
	*    Method das kontroliert ob die Eingabe eine arabische oder romische Zahl ist
	*    throw: Exception wenn die Eingabe eine falsche Zahl ist
	*    Ausgabe: a fuer arabisch, r fuer romisch
	*/
	public char zahlKontrollieren() throws Exception
	{
		try
		{
		    boolean kannRomischSein=true;
		    boolean kannArabischSein=true;
		    for (int i=0;i<zahl.length()&&(kannRomischSein||kannArabischSein);i++)
		    {
		    	if(!Character.isDigit(zahl.charAt(i)))
		    	{
		    		kannArabischSein=false;
		    		switch (zahl.charAt(i))
		    		{
		    			case 'i':
		    			case 'v':
		    			case 'x':
		    			case 'l':
		    			case 'c':
		    			case 'd':
		    			case 'm': break;
		    			default: kannRomischSein=false;
		    		}
		    	}
		    	else
		    	{
		    		kannRomischSein=false;
		    	}
		    }
		    if (kannArabischSein) return 'a';
		    else if (kannRomischSein) return 'r';
			else throw new Exception("Nummer mit falser Struktur");
		}
		catch(Exception e)
		{
			if(e.getMessage().equals(""))throw new Exception("Problem bei Zahlkontrolierung");
			else throw new Exception(e.getMessage());
		}
	}
	
	/*
	*    Method das verwandeln roemische Zahl in arabische Zahl
	*    throw: Exception wenn die Eingabe keine romische Zahl ist
	*    Ausgabe: Int die eine arabische Zahl enthalt
	*/
	public int roemischNachArabisch() throws Exception
    {
		String error="Romische Nummer mit false Struktur";
	    boolean kannRomischSein=true; 
		arabisch=0;
		int MCount=0;
		int DCount=0;
		int CCount=0;
		int LCount=0;
		int XCount=0;
		int VCount=0;
		int ICount=0;
		boolean minusC=false;
		boolean minusX=false;
		boolean minusI=false;
		for(int i=0;i<zahl.length();i++)
		{
			
			switch (zahl.charAt(i))
			{
				case 'm': if (MCount>4||DCount>0||LCount>0||
				    XCount>0||VCount>0||ICount>0)
				    {
			            throw new Exception(error);
					}
					arabisch+=1000;
                    MCount++;
					break;
				case 'd': arabisch+=500;
				    if (DCount>0||CCount>1||LCount>0||
				    XCount>1||VCount>0||ICount>0||(minusX&&zahl.charAt(i-1)!='x'))
                    {
					  throw new Exception(error);
					}
					DCount++;
					break;
				case 'c': if ((CCount>2&&!minusX)||LCount>0||XCount>1||VCount>0||ICount>0||(minusX&&zahl.charAt(i-1)!='x'))
				    {
					    throw new Exception(error);
					}
				    if (i==zahl.length()-1)
					{
					    if (minusC)
					    {
					  	   throw new Exception(error);
					    }
					    else
					    {
					    arabisch+=100;
					    }
					}
					else if(zahl.charAt(i+1)!='m'&&
					zahl.charAt(i+1)!='d')
					{
					    if (minusC)
					    {
					       throw new Exception(error);
					    }
					    else
					    {
					       arabisch+=100;
					    }
					}
				    else
					{
					  arabisch -=100;
					  minusC=true;
					}
                    CCount++;
					break;
				case 'l': arabisch+=50;
				    if (LCount>0||XCount>1||VCount>0||ICount>0)
                    {
					  throw new Exception(error);
					}
					LCount++;
					break;
				case 'x': if ((XCount>2&&zahl.charAt(i-1)=='x')||LCount>1||VCount>0||ICount>1||(minusI&&zahl.charAt(i-1)!='i'))
				    {
					    throw new Exception(error);
					}
				    if (i==zahl.length()-1)
					{
					 if (minusX)
					 {
					  throw new Exception(error+"hier2");
					 }
					 else
					 {
					 arabisch+=10;
					 }
					}
					else if(zahl.charAt(i+1)!='c'&&
					zahl.charAt(i+1)!='l'&&
					zahl.charAt(i+1)!='d')
					{
					 if (minusX)
					 {
					  throw new Exception(error+"hier3");
					 }
					 else
					 {
					 arabisch+=10;
					 }
					}
				    else if (minusX||XCount>0)
					{
					 throw new Exception(error+"hier4");
					}
					else
					{
					 arabisch -=10;
					 minusX=true;
					 minusC=false;
					}
                    XCount++;
					break;
				case 'v': arabisch+=5;
				    if (VCount>0||ICount>1||(minusI&&zahl.charAt(i-1)!='i'))
                    {
					 throw new Exception(error);
					}
					VCount++;
					break;
				case 'i': if (ICount>2)
				    {
					    throw new Exception(error);
					}
				    if (i==zahl.length()-1)
					{
					 if (minusI)
					 {
					    throw new Exception(error);
					 }
					 else
					 {
					 arabisch+=1;
					 }
					}
					 else if(zahl.charAt(i+1)!='x'&&
					zahl.charAt(i+1)!='v')
					{
					    if (minusI)
					    {
					        throw new Exception(error);
					    }
					    else
					    {
					    arabisch+=1;
					    }
					}
				    else
					{
					    arabisch -=1;
					    minusI=true;
					    minusX=false;
					}
                    ICount++;
					break;
				default: throw new Exception(error);
			}
		}
		return arabisch;
	}
	
	/*
	*    Method das verwandeln arabische Zahl in romische Zahl
	*    throw: Exception wenn die Eingabe keine arabische Zahl ist
	*    Ausgabe: String die eine romische Zahl enthalt
	*/
	public String arabischNachRoemisch() throws Exception
	{
		try
		{
		    int nummer = Integer.parseInt(zahl);
		    String latein="";
            if (nummer>=5000)
		    {
		       throw new Exception("Bitte geben Sie eine Zahl nicht grosser als 4999");
		    }
		    if (nummer<=0)
		    {
		    	  throw new Exception("Bitte geben Sie eine Zahl grosser als 0");
		    }
			while(nummer>=1000)
			{
				latein+="M";
				nummer-=1000;
			}
			if(nummer>=900)
			{
				latein+="CM";
				nummer-=900;
			}
			if(nummer>=400&&nummer<500)
			{
				latein+="CD";
				nummer-=400;
			}
			if(nummer>=500)
			{
				latein+="D";
				nummer-=500;
			}
			while(nummer>=100)
			{
				latein+="C";
				nummer-=100;
			}
			if(nummer>=90)
			{
				latein+="XC";
				nummer-=90;
			}
			if(nummer>=40&&nummer<50)
			{
				latein+="XL";
				nummer-=40;
			}
			if(nummer>=50)
			{
				latein+="L";
				nummer-=50;
			}
			while(nummer>=10)
			{
				latein+="X";
				nummer-=10;
			}
			if(nummer==9)
			{
				latein+="IX";
				nummer-=9;
			}
			if(nummer==4)
			{
				latein+="IV";
				nummer-=4;
			}
			if(nummer>=5)
			{
				latein+="V";
				nummer-=5;
			}
			while(nummer>=1)
			{
				latein+="I";
				nummer-=1;
			}
		    return latein;
		}
		catch(NumberFormatException e)
		{
			throw new Exception("Zahl so gross fuer Java");
		}
		catch(Exception e)
		{
			if(e.getMessage().equals(""))throw new Exception("Problem bei Bildung der romischen Nummer");
			else throw new Exception(e.getMessage());
		}
	}
}