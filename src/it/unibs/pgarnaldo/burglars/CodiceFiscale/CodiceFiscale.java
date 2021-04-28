package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.*;

public class CodiceFiscale {

    private static final String FILE_COMUNI = "inputFiles/comuni.xml";
    private final static int LUNGHEZZA_CF = 16;

    private String cod_fiscale;
    private Persona persona;
    private final HashMap<String, String> comuni = XMLReader.readComuni(FILE_COMUNI);
    private final static ArrayList<String> mesi = new ArrayList<String>();
    /**
     * inizializza un HashMap contenente i caratteri di un codice fiscale e il relativo valore assegnato per il calcolo del carattere di controllo
     */
    private final static Map<String, Integer> caratteriDispari = Map.ofEntries(
            Map.entry("0", 1),
            Map.entry("1", 0),
            Map.entry("2", 5),
            Map.entry("3", 7),
            Map.entry("4", 9),
            Map.entry("5", 13),
            Map.entry("6", 15),
            Map.entry("7", 17),
            Map.entry("8", 19),
            Map.entry("9", 21),
            Map.entry("A", 1),
            Map.entry("B", 0),
            Map.entry("C", 5),
            Map.entry("D", 7),
            Map.entry("E", 9),
            Map.entry("F", 13),
            Map.entry("G", 15),
            Map.entry("H", 17),
            Map.entry("I", 19),
            Map.entry("J", 21),
            Map.entry("K", 2),
            Map.entry("L", 4),
            Map.entry("M", 18),
            Map.entry("N", 20),
            Map.entry("O", 11),
            Map.entry("P", 3),
            Map.entry("Q", 6),
            Map.entry("R", 8),
            Map.entry("S", 12),
            Map.entry("T", 14),
            Map.entry("U", 16),
            Map.entry("V", 10),
            Map.entry("W", 22),
            Map.entry("X", 25),
            Map.entry("Y", 24),
            Map.entry("Z", 23)
    );

    private final static Map<String, Integer> caratteriPari = Map.ofEntries(
            Map.entry("0", 0),
            Map.entry("1", 1),
            Map.entry("2", 2),
            Map.entry("3", 3),
            Map.entry("4", 4),
            Map.entry("5", 5),
            Map.entry("6", 6),
            Map.entry("7", 7),
            Map.entry("8", 8),
            Map.entry("9", 9),
            Map.entry("A", 0),
            Map.entry("B", 1),
            Map.entry("C", 2),
            Map.entry("D", 3),
            Map.entry("E", 4),
            Map.entry("F", 5),
            Map.entry("G", 6),
            Map.entry("H", 7),
            Map.entry("I", 8),
            Map.entry("J", 9),
            Map.entry("K", 10),
            Map.entry("L", 11),
            Map.entry("M", 12),
            Map.entry("N", 13),
            Map.entry("O", 14),
            Map.entry("P", 15),
            Map.entry("Q", 16),
            Map.entry("R", 17),
            Map.entry("S", 18),
            Map.entry("T", 19),
            Map.entry("U", 20),
            Map.entry("V", 21),
            Map.entry("W", 22),
            Map.entry("X", 23),
            Map.entry("Y", 24),
            Map.entry("Z", 25)
    );

    /**
     * inizializza un Arraylist contenente le lettere corrispondenti ai mesi
     */
    static{
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

    public CodiceFiscale() throws XMLStreamException {
    }

    public String getCodFiscale() {

        return cod_fiscale;
    }

    public void setCodFiscale(String cod_fiscale) {

        this.cod_fiscale = cod_fiscale;
    }


    public String makeCF(Persona persona) {
        cod_fiscale = generate3Caratteri(persona.getCognome());
        String consonanti_nome = createConsonantiNome(persona.getNome());
        if (consonanti_nome.length() >= 4)
            cod_fiscale = cod_fiscale + consonanti_nome.charAt(0) + consonanti_nome.charAt(2) + consonanti_nome.charAt(3);
        else cod_fiscale = generate3Caratteri(persona.getNome());
        //generati primi 6 caratteri CF

        cod_fiscale = cod_fiscale + (Integer.toString(persona.getData().get(1))).charAt(2) + (Integer.toString(persona.getData().get(1))).charAt(3) + mesi.get(persona.getData().get(2));
        //3 alfanum per anno SOPRA
        //yyyy-mm-dd
        if (persona.getSesso() == 'F')
            cod_fiscale = cod_fiscale + (persona.getData().get(3) + 40);
        else if (persona.getData().get(3) < 9)
            cod_fiscale = cod_fiscale + "0" + persona.getData().get(3);
        else
            cod_fiscale = cod_fiscale + persona.getData().get(3);
        // comune
        cod_fiscale = cod_fiscale + comuni.get(persona.getComune());


        return (String) cod_fiscale;
    }


    public char generaCarattereControllo() {
        char carattere = ' ';

        return carattere;
    }


    /**
     * genera parte di cod_fiscale che contiene cognome e nome
     * prende prime 3 consonanti
     * se non ci sono aggiunge vocali
     * se non sono ancora 3 caratteri trovati aggiunge X
     **/
    public String generate3Caratteri(String valore) {
        int i = 0;
        while (i < valore.length() || cod_fiscale.length() < 3) {
            char ci = valore.charAt(i);
            if (isConsonant(ci) == true) {
                cod_fiscale = cod_fiscale + ci;
                i++;
            }
        }
        if (cod_fiscale.length() < 3) {
            i = 0;
            while (i < valore.length() || cod_fiscale.length() < 3) {  //se metto in while i=0 viene riinizializzata ogni ciclo?
                char ci = valore.charAt(i);
                if (isConsonant(ci) == false) {
                    cod_fiscale = cod_fiscale + ci;
                    i++;
                }
            }
        }
        /**controlla se non si è ancora riusciti ad arrivare a 3 caratteri aggiunge X**/
        while (cod_fiscale.length() < 3)
            cod_fiscale = cod_fiscale + 'X';

        return cod_fiscale;
    }


    //data una stringa creo la sua corrisppondetìnte di sole consonanti
    public String createConsonantiNome(String valore) {
        String consonanti_valore = "";
        for (int i = 0; i < valore.length(); i++) {
            char ci = valore.charAt(i);
            if (isConsonant(ci)) {
                consonanti_valore = consonanti_valore + ci;
            }
        }
        return consonanti_valore;
    }

    //controlla se carattere è una consonante
    private boolean isConsonant(char ci) {
        return ci != 'A' && ci != 'E' && ci != 'I' && ci != 'O' && ci != 'U';
    }

    //makeCF and check if returned value is present in inputPersone.XML

    public boolean isValid(String codice_fis) {
        //controllo che tutti i caratteri siano o numeri o lettere maiuscole
        for (int i = 0; i < LUNGHEZZA_CF; i++) {
            if (Character.isLowerCase(codice_fis.charAt(i)))
                return false;
        }

        //manca carattere di controllo
        return controlPosition(codice_fis) &&
                checkDay(codice_fis) &&
                checkMonth(codice_fis);
    }


    /**
     * Controlla se i caratteri e numeri sono in posizione corretta
     **/
    public boolean controlPosition(String codice_fis) {
        for (int i = 0; i < 6; i++) {
            char x = codice_fis.charAt(i);
            if (Character.isDigit(x))
                return false;
        }
        return Character.isDigit(codice_fis.charAt(6)) &&
                Character.isDigit(codice_fis.charAt(7)) &&
                Character.isAlphabetic(codice_fis.charAt(8)) &&
                Character.isDigit(codice_fis.charAt(9)) &&
                Character.isDigit(codice_fis.charAt(10)) &&
                Character.isAlphabetic(codice_fis.charAt(11)) &&
                Character.isDigit(codice_fis.charAt(12)) &&
                Character.isDigit(codice_fis.charAt(13)) &&
                Character.isDigit(codice_fis.charAt(14)) &&
                Character.isAlphabetic(codice_fis.charAt(15));//se posizioni non sono corrette
    }

    /**
     * Controlla se giorno è compreso tra
     *
     * @param codice_fis codice fiscale di cui ricavare il giorno
     * @return boolean per controllare se il cod è giusto o meno
     **/
    public boolean checkDay(String codice_fis) {
        String giornoS = String.copyValueOf(codice_fis.toCharArray(), 9, 2);
        //giornoS = giornoS + codice_fis.charAt(9) + codice_fis.charAt(10);
        int giorno = Integer.parseInt(giornoS);
        return (giorno >= 1 && giorno <= 31) || (giorno >= 41 && giorno <= 71);
    }


    public boolean checkMonth(String codice_fis) {
        int i;
        String mese = String.valueOf(codice_fis.charAt(8));
        for (String m : mesi) {
            if (!mese.equals(m))
                return false;
        }
        return true;
    }

    //controllo ultimo charattere
    public boolean checkLastChar() {

        return true;
    }


}










