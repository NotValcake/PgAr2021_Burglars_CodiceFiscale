package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import java.util.* ;

public class CodiceFiscale {
	int lunghezza_cod;
	private final static int LUNGHEZZA_CF = 16;
	private String cod_fiscale = "";
	private Persona persona;
	private String nome = persona.getNome();
	private String cognome = persona.getCognome();
	char sesso = persona.getSesso();
	private String comune = persona.getComune();
	private HashMap<String, String> comuni = new HashMap<String, String>(); 
	private String code_comune = comuni.get(comune);   //gets code value of comune in HashMap
	private ArrayList<Integer> data =  persona.getData(); 
    private final static  ArrayList<String> mesi = new ArrayList<String>();
    private final static HashMap<String, String> caratteriDispari = new HashMap<String, String>();
    private final static HashMap<String, String> caratteriPari = new HashMap<String, String>();
    
     private static void setMesi() {
    	mesi.add("A");
        mesi.add("B");
        mesi.add("C");
        mesi.add("D");
        mesi.add("E");
        mesi.add("H");
        mesi.add("L");
        mesi.add("M");
        mesi.add("P");
        mesi.add("R");
        mesi.add("S");
        mesi.add("T");
	}
     
     private static void createHashMap() {
    	 caratteriDispari.put("0","A");
    	 caratteriDispari.put("1","0");
    	 caratteriDispari.put("2","5");
    	 caratteriDispari.put("3","7");
    	 caratteriDispari.put("4","9");
    	 caratteriDispari.put("5","13");
    	 caratteriDispari.put("6","15");
    	 caratteriDispari.put("7","17");
    	 caratteriDispari.put("8","19");
    	 caratteriDispari.put("9","21");
    	 caratteriDispari.put("A","1");
    	 caratteriDispari.put("B","0");
    	 caratteriDispari.put("C","");
    	 caratteriDispari.put("D","");
    	 caratteriDispari.put("E","");
    	 caratteriDispari.put("F","");
    	 caratteriDispari.put("G","");
    	 caratteriDispari.put("H","");
    	 caratteriDispari.put("I","");
    	 caratteriDispari.put("J","");
    	 caratteriDispari.put("K","");
    	 caratteriDispari.put("L","");
    	 caratteriDispari.put("M","");
    	 caratteriDispari.put("N","");
    	 caratteriDispari.put("O","");
    	 caratteriDispari.put("P","");
    	 caratteriDispari.put("Q","");
    	 caratteriDispari.put("R","");
    	 caratteriDispari.put("S","");
    	 caratteriDispari.put("T","");
    	 caratteriDispari.put("U","");
    	 caratteriDispari.put("V","");
    	 caratteriDispari.put("W","");
    	 caratteriDispari.put("X","");
    	 caratteriDispari.put("Y","");
    	 caratteriDispari.put("Z","");
    	 //36
     }
     
     
    
    public String getCod_fiscale() {
		return cod_fiscale;
	}

	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
	}

	  
	public String makeCF (Persona persona) {

		lunghezza_cod = 3;
	    cod_fiscale = generate3Caratteri(cognome);
	    lunghezza_cod = 6;
	    String consonanti_nome =  createConsonantiNome(nome);
	    if (consonanti_nome.length() >= 4)
	        cod_fiscale = cod_fiscale + consonanti_nome.charAt(0) + consonanti_nome.charAt(2) + consonanti_nome.charAt(3);
	    else cod_fiscale = generate3Caratteri(nome);
	  //generati primi 6 caratteri CF
	    
	    cod_fiscale = cod_fiscale +(Integer.toString(data.get(1))).charAt(2) + (Integer.toString(data.get(1))).charAt(3) + mesi.get(data.get(2));
	    //3 alfanum per anno SOPRA
	   //yyyy-mm-dd
	    if (sesso == 'F')
	    	cod_fiscale = cod_fiscale + (data.get(3) + 40);
	    else if (data.get(3)<9)
	    	cod_fiscale = cod_fiscale + "0"+ data.get(3);
	    else 	
	    	cod_fiscale = cod_fiscale + data.get(3);
	    // comune 
	    cod_fiscale = cod_fiscale +code_comune;
	    
	    
	
	return (String) cod_fiscale;
	}
		 
	
	public char generaCarattereControllo() {
		char carattere = ' ';
		
		return carattere;
	}
	
	
	
	
	
		 
	/** genera parte di cod_fiscale che contiene cognome e nome 
	 * prende prime 3 consonanti
	 * se non ci sono aggiunge vocali
	 * se non sono ancora 3 caratteri trovati aggiunge X
	 *  
	 *  **/
	public String generate3Caratteri(String valore) {
		int i=0;
	    while (i < valore.length() || cod_fiscale.length() < lunghezza_cod ) { 
			char ci = valore.charAt(i);
			if (isConsonant(ci) == true){
				 cod_fiscale = cod_fiscale + ci ; 
			     i++;
			 }
		}
	    if (cod_fiscale.length() < lunghezza_cod) {
	    	i=0;
	    	 while (i<valore.length() || cod_fiscale.length() < lunghezza_cod) {  //se metto in while i=0 viene riinizializzata ogni ciclo?
	 			char ci = valore.charAt(i);
	 			if (isConsonant(ci) == false){
	 			     cod_fiscale = cod_fiscale + ci ; 
	 			     i++;
	 			}
	    	 }
	    }
	    /**controlla se non si è ancora riusciti ad arrivare a 3 caratteri aggiunge X**/
	    while (cod_fiscale.length() < lunghezza_cod)
	     	cod_fiscale = cod_fiscale + 'X';
	    
		return cod_fiscale;
	} 
	
	
	//data una stringa creo la sua corrisppondetìnte di sole consonanti
	public String createConsonantiNome(String valore) {
		String consonanti_valore = "";
		for(int i=0; i < valore.length() ; i++)
		{char ci = valore.charAt(i);
			if (isConsonant(ci) == true){
			     consonanti_valore = consonanti_valore + ci;
			}
		}
		return consonanti_valore;
	}
	
	//controlla se carattere è una consonante
    public boolean isConsonant(char ci) {
    	if (ci != 'A' || ci != 'E' || ci != 'I' || ci != 'O' || ci != 'U')
    		return true;
    	else
    		return false;
    }
	
	//makeCF and check if returned value is present in inputPersone.XML
	
	public boolean isValid(String codice_fis) {
		// int i = Integer.parseInt("200")  restituisce 200 come intero
		 //character.isDigit(ch)
		
		return false;
	}
	
	
	/**Controlla se i caratteri e numeri sono in posizione corretta
	 * 
	 * **/
	public boolean controlPosition(String codice_fis) {
		int i;
		for (i=0; i < 6; i++ ){
			char x = codice_fis.charAt(i);
			if( Character.isDigit(x) == true)
				return false;
		}
		if ( Character.isDigit(codice_fis.charAt(6)) == true  &&
			 Character.isDigit(codice_fis.charAt(7)) == true  &&  
		     Character.isDigit(codice_fis.charAt(8)) == false && 
		     Character.isDigit(codice_fis.charAt(9)) == true && 
		     Character.isDigit(codice_fis.charAt(10)) == true && 
		     Character.isDigit(codice_fis.charAt(11)) == false && 
		     Character.isDigit(codice_fis.charAt(12)) == true && 
			 Character.isDigit(codice_fis.charAt(13)) == true && 
		     Character.isDigit(codice_fis.charAt(14)) == true && 
		     Character.isDigit(codice_fis.charAt(15)) == false)
			return true;
			
		
		return false; //se posizioni non sono corrette
	}
    
    
	public boolean checkDay(String codice_fis) {
		// int i = Integer.parseInt("200") 
		String giornos = String(codice_fis.charAt(15));
		int giorno = Integer.parseInt();
		if ()
		
		return true;
	}
	
	
    public boolean checkMonth() {
		
		return true;
	}
	
    //controllo ultimo charattere
    public boolean checkLastChar() {
		
		return true;
	}
    
    
    
    
    
    
    
    
    
}










