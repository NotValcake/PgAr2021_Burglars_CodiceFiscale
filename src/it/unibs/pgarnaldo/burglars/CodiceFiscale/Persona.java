package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import java.util.ArrayList;

/**
 * questa classe serve per creare l'oggetto Persona con i seguenti attributi:
 * <p>
 * 1) id, servirà per identificare la persona più facilmente, visto che il programma lavorerà con una lista di persone;
 * 2) nome e cognome;
 * 3) sesso;
 * 4) data di nascita (prima casella il giorno, seconda casella il mese, terza casella l'anno)
 * 5) comune di nascita, dal quale successivamente, in un'altra classe, si controllerà il codice corrispondente.
 * Da questi dati poi andremo a creare il codice fiscale di ogni persona.
 *
 * @author Burglars
 */


public class Persona {


    private int id;
    private String nome;
    private String cognome;
    private char sesso;
    private ArrayList<Integer> data = new ArrayList<Integer>();
    private String comune;

    public Persona(int id, String nome, char sesso, ArrayList<Integer> data, String comune) {

        this.id = id;
        this.nome = nome;
        this.sesso = sesso;
        this.data = data;
        this.comune = comune;

    }

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

    public ArrayList<Integer> getData() {

        return data;
    }

    public void setData(ArrayList<Integer> data) {

        this.data = data;
    }

}
