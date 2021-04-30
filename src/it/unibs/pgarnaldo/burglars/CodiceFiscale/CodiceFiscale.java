package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import java.util.*;

/**
 * Classe dedicata alla creazione e verifica di un codice fiscale
 *
 * @author burglars
 */

public class CodiceFiscale {

    private String cod_fiscale;

    public CodiceFiscale(Persona p) {
        this.cod_fiscale = makeCF(p);
    }

    public CodiceFiscale(String cf) {
        this.cod_fiscale = cf;
    }

    public String getCodFiscale() {

        return cod_fiscale;
    }

    public void setCodFiscale(String cod_fiscale) {

        this.cod_fiscale = cod_fiscale;
    }

    /**
     * genera un codice fiscale a partire da una persona
     *
     * @param persona Persona di cui generare il codice
     * @return Stringa contenente il codice fiscale
     */
    public String makeCF(Persona persona) {
        StringBuilder cf = new StringBuilder();
        cf.append(creaConsonantiCognome(persona.getCognome()));
        cf.append(creaConsonantiNome(persona.getNome()));

        if (persona.getData().get(0) % 100 < 10) { //se la persona è nata in un anno precedente al 2010 o al 1910 bisogna aggiungere uno 0
            cf.append("0");
        }
        cf.append((persona.getData().get(0) % 100)); //aggiunge le ultime due cifre dell'anno

        cf.append(CFConstants.MESI.get(persona.getData().get(1) - 1)); //aggiunge la lettera del mese

        if (persona.getSesso() == 'F') {
            cf.append(persona.getData().get(2) + 40);
        } else {
            if (persona.getData().get(2) < 10)//se la persona è nata in un mese precedente a  ottobre, bisogna aggiungere uno 0
                cf.append("0");
            cf.append(persona.getData().get(2));
        }

        cf.append(CFConstants.COMUNI.get(persona.getComune().toUpperCase()));
        cf.append(generaCarattereControllo(cf.toString()));

        setCodFiscale(cf.toString());
        return cf.toString();
    }


    /**
     * Controllo se il codice fiscale è valido
     *
     * @return true se il codice fiscale è valido
     **/
    public boolean isValid() {
        if (this.cod_fiscale.length() != CFConstants.LUNGHEZZA_CF) {
            return false;
        }
        //controllo che tutti i caratteri siano o numeri o lettere maiuscole
        for (int i = 0; i < CFConstants.LUNGHEZZA_CF; i++) {
            if (Character.isLowerCase(this.cod_fiscale.charAt(i)))
                return false;
        }

        String cod_comune = "";
        cod_comune = cod_comune + this.cod_fiscale.charAt(11) + this.cod_fiscale.charAt(12) + this.cod_fiscale.charAt(13) + this.cod_fiscale.charAt(14);
        char last_char = generaCarattereControllo(this.cod_fiscale.substring(0, 15)); //il carattere di controllo va generato escludendo l'ultimo carattere dal cf

        return controlPosition(this.cod_fiscale) &&
                checkValidConsonants(this.cod_fiscale) && //controlla ordine caratteri cognome
                checkValidConsonants(this.cod_fiscale.substring(3)) &&//controlla caratteri nome
                checkDay(this.cod_fiscale) &&
                checkMonth(this.cod_fiscale) &&
                checkDaysInMonth(this.cod_fiscale) &&
                CFConstants.COMUNI.containsValue(cod_comune) &&
                last_char == this.cod_fiscale.charAt(15);
    }

    /**
     * controlla se le tre lettere del nome o del cognome nel codice fiscale sono accettabili, cioè se sono in
     * ordine consonanti->vocali->eventuali X
     *
     * @param cod_fiscale codice fiscale da valutare
     * @return true se l'ordine delle lettere è corretto
     */
    private boolean checkValidConsonants(String cod_fiscale) {
        for (int i = 0; i < 2; i++) {
            if (!isConsonant(cod_fiscale.charAt(i)) && isConsonant(cod_fiscale.charAt(i + 1)) && cod_fiscale.charAt(i + 1) != 'X')
                return false;
        }
        return true;
    }


    /**
     * controlla se due codici fiscali sono uguali
     *
     * @param o un oggetto
     * @return true se l'oggetto e l'istanza sono uguali
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodiceFiscale)) return false;
        CodiceFiscale that = (CodiceFiscale) o;
        return Objects.equals(cod_fiscale, that.cod_fiscale);
    }

    /**
     * metodo toString
     *
     * @return il codice fiscale come una stringa
     */
    @Override
    public String toString() {
        return this.cod_fiscale;
    }

    /**
     * Genera carattere di controllo
     *
     * @param cf stringa contenente il codice fiscale
     * @return carattere di controllo del codice fiscale
     **/
    public char generaCarattereControllo(String cf) {
        int i;

        int somma = 0;

        for (i = 1; i < cf.length(); i += 2) { //partendo a contare da zero caratteri pari e dispari si invertono
            somma += CFConstants.CARATTERI_PARI.get(cf.charAt(i));
        }
        for (i = 0; i < cf.length(); i += 2) {
            somma += CFConstants.CARATTERI_DISPARI.get(cf.charAt(i));
        }
        return CFConstants.RESTO.get(somma % 26);
    }

    /**
     * genera i primi tre caratteri del codice fiscale, relativi al cognome
     *
     * @param cognome il cognome del proprietario del codice fiscale
     * @return stringa contenente le prime tre lettere
     **/
    public String creaConsonantiCognome(String cognome) {
        int i = 0;
        cognome = cognome.toUpperCase();//i caratteri di un codice fiscale sono solo maiuscoli
        StringBuilder cf = new StringBuilder();
        while (i < cognome.length() && cf.length() < 3) {
            char ci = cognome.charAt(i);
            if (isConsonant(ci)) {
                cf.append(ci);
            }
            i++;
        }
        if (cf.length() < 3) {
            i = 0;
            while (i < cognome.length() && cf.length() - 1 < 3) {
                char ci = cognome.charAt(i);
                if (!isConsonant(ci)) {
                    cf.append(ci);
                }
                i++;
            }
        }
        //controlla se non si è ancora riusciti ad arrivare a 3 caratteri aggiunge X
        while (cf.length() < 3)
            cf.append('X');

        return cf.toString();
    }

    /**
     * crea le tre lettere relative al nome nel codice fiscale
     *
     * @param nome il cognome del proprietario del codice fiscale
     * @return una stringa di tre lettere contenente le tre lettere del nome
     **/
    public String creaConsonantiNome(String nome) {
        nome = nome.toUpperCase();
        char[] array_nome = nome.toCharArray();
        ArrayList<Character> consonanti = new ArrayList<>();
        //prelevo tutte le consonanti del nome
        for (char l : array_nome) {
            if (CodiceFiscale.isConsonant(l))
                consonanti.add(l);
        }

        if (consonanti.size() == 3) {//se ho esattamente 3 consonanti le uso tutte e tre
            return consonanti.get(0).toString() + consonanti.get(1).toString() + consonanti.get(2).toString();
        }
        if (consonanti.size() > 3) {//se ho almeno 4 consonanti perndo la prima la terza e la quarta
            return consonanti.get(0).toString() + consonanti.get(2).toString() + consonanti.get(3).toString();
        }
        StringBuilder codice_nome = new StringBuilder();
        //se ho meno di 3 consonanti devo cercare le vocali
        for (Character c : consonanti) {
            codice_nome.append(c);
        }
        int i = 0;
        while (codice_nome.length() < 3 && i < nome.length()) {
            if (!isConsonant(nome.charAt(i)))
                codice_nome.append(nome.charAt(i));
            i++;
        }
        //se nemmeno le vocali bastano, aggiungo X
        while (codice_nome.length() < 3) {
            codice_nome.append('X');
            i++;
        }
        return codice_nome.toString();
    }

    /**
     * controlla se carattere è una consonante
     *
     * @param ci carattere da controllare
     * @return true se il carattere è una consonante
     **/
    private static boolean isConsonant(char ci) {
        return CFConstants.CONSONANTI.contains(ci);
    }

    /**
     * Controlla se i caratteri e numeri sono in posizione corretta
     *
     * @param cf il codice fiscale da controllare
     * @return true se lettere e numeri sono nelle posizioni corrette
     **/
    public boolean controlPosition(String cf) {
        for (int i = 0; i < 6; i++) {
            char x = cf.charAt(i);
            if (Character.isDigit(x))
                return false;
        }

        return Character.isDigit(cf.charAt(6)) &&
                Character.isDigit(cf.charAt(7)) &&
                Character.isAlphabetic(cf.charAt(8)) &&
                Character.isDigit(cf.charAt(9)) &&
                Character.isDigit(cf.charAt(10)) &&
                Character.isAlphabetic(cf.charAt(11)) &&
                Character.isDigit(cf.charAt(12)) &&
                Character.isDigit(cf.charAt(13)) &&
                Character.isDigit(cf.charAt(14)) &&
                Character.isAlphabetic(cf.charAt(15));//se posizioni non sono corrette
    }

    /**
     * Controlla se giorno è compreso tra
     *
     * @param cf codice fiscale di cui ricavare il giorno
     * @return boolean per controllare se il cod è giusto o meno
     **/
    public boolean checkDay(String cf) {
        String giornoS = String.copyValueOf(cf.toCharArray(), 9, 2);
        int giorno = Integer.parseInt(giornoS);
        return (giorno >= 1 && giorno <= 31) || (giorno >= 41 && giorno <= 71);
    }

    /**
     * controlla se il carattere del mese è valido
     *
     * @param cf stringa contenente il codice fiscale
     * @return true se il mese è accettabile
     */
    public boolean checkMonth(String cf) {
        String mese = String.valueOf(cf.charAt(8));
        return CFConstants.MESI.contains(mese);
    }

    /**
     * controlla che il giorno di nascita sia compatibile con il mese
     *
     * @param cf una stringa contenente il codice fiscale
     * @return true se giorno e mese sono compatibili
     */
    public boolean checkDaysInMonth(String cf) {
        char mese = cf.charAt(8);
        String giornoS = "";
        giornoS = giornoS + cf.charAt(9) + cf.charAt(10);
        int giorno = Integer.parseInt(giornoS);

        if (giorno < 1) {
            return false;
        }

        if (giorno >= 41 && giorno <= 71) {
            giorno -= 40;
        }

        if (mese == 'B' && giorno > 28) {
            return false;
        }

        if (mese == 'D' || mese == 'H' || mese == 'M' || mese == 'R' || mese == 'T' && giorno > 30) {
            return false;
        }

        if (giorno > 31) {
            return false;
        }
        return true;
    }

}
