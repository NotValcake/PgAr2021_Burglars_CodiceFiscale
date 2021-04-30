package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import java.util.ArrayList;


/**
 * questa classe serve per creare l'oggetto Persona con i seguenti attributi:
 * <p>
 * 1) id, servirà per identificare la persona più facilmente, visto che il programma lavorerà con una lista di persone;
 * 2) nome e cognome;
 * 3) sesso;
 * 4) data di nascita
 * 5) comune di nascita
 * Da questi dati poi andremo a creare il codice fiscale di ogni persona.
 *
 * @author Burglars
 */


public class Persona {


    private int id;
    private String nome;
    private String cognome;
    private char sesso;
    private ArrayList<Integer> data = new ArrayList<>();
    private String comune;


    /**
     * costruttore di persona
     *
     * @param id      indice univoco
     * @param nome    nome della persona
     * @param cognome cognome della persona
     * @param sesso   char M = maschio, F = femmina
     * @param data    Arraylist contenente la data nel formato aaaa-MM-gg
     * @param comune  stringa contenente il comune di nascita
     */
    public Persona(int id, String nome, String cognome, char sesso, ArrayList<Integer> data, String comune) {

        this.id = id;
        this.nome = nome;
        this.sesso = sesso;
        this.data = data;
        this.comune = comune;
        this.cognome = cognome;
    }

    /**
     * costruttore vuoto che inizializza un istanza di persona senza attributi
     */
    public Persona() {

    }

    public String getCognome() {

        return cognome;
    }

    public void setCognome(String cognome) {

        this.cognome = cognome;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public char getSesso() {

        return sesso;
    }

    public void setSesso(char sesso) {

        this.sesso = sesso;
    }

    public String getComune() {

        return comune;
    }

    public void setComune(String comune) {

        this.comune = comune;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    /**
     * restituisce la data di nascita della persona
     *
     * @return ArrayList di Integer contente anno di nascita, mese, giorno rispettivamente
     * nelle posizioni 0, 1, 2
     */
    public ArrayList<Integer> getData() {

        return data;
    }

    /**
     * imposta una data di nascita
     *
     * @param data un Arraylist di Integer contenente anno di nascita, mese, giorno rispettivamente
     *             nelle posizioni 0, 1, 2
     */
    public void setData(ArrayList<Integer> data) {

        this.data = data;
    }

}
