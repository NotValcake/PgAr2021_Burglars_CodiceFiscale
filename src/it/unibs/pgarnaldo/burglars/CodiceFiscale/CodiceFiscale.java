package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import java.util.* ;    


public class CodiceFiscale {
	int lunghezza_cod;
	private String cod_fiscale = "";
	private Persona persona;
	private String nome = persona.getNome();
	private String cognome = persona.getCognome();
	char sesso = persona.getSesso();
	private String comune = persona.getComune();
	private HashMap<String, String> comuni = new HashMap<String, String>();  //farlo static?
	private String code_comune = comuni.get(comune);   //gets code value of comune in HashMap
	
    
    public String getCod_fiscale() {
		return cod_fiscale;
	}

	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
	}

	public Persona getPersona() {
		return persona;
	}

	  
	public String makeCF (Persona persona) {

		lunghezza_cod = 3;
	    cod_fiscale = generate3Caratteri(cognome);
	    lunghezza_cod = 6;
	    String consonanti_nome =  createConsonantiNome(nome);
	    if (consonanti_nome.length() >= 4)
	        cod_fiscale = cod_fiscale + consonanti_nome.charAt(1) + consonanti_nome.charAt(3) + consonanti_nome.charAt(4);
	    else cod_fiscale = generate3Caratteri(nome);
	  //generati primi 6 caratteri CF
	    
	    //faccio anno (3)
	    
	    
	return (String) cod_fiscale;
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
    	if (ci != 'a' || ci != 'e' || ci != 'i' || ci != 'o' || ci != 'u')
    		return true;
    	else
    		return false;
    }
	
	//makeCF and check if returned value is present in inputPersone.XML
	
	
	public boolean checkDay() {
		
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










