package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class ClassMain {

    public static void main(String[] args) throws XMLStreamException {
        ArrayList<String> codici_fiscali = new ArrayList<>();
        ArrayList<String> cf = new ArrayList<>();
        ArrayList<String> invalidi = new ArrayList<>();
        ArrayList<String> spaiati= new ArrayList<>();
        ArrayList<String> presenti= new ArrayList<>();
        codici_fiscali =XMLReader.readCodici(CFConstants.FILE_PERSONE);
        cf=codici_fiscali;
        invalidi= errati(cf);
        presenti=accoppiati(cf);
        spaiati=cf;
        

    }

    public static ArrayList<String> accoppiati(ArrayList<String> cf ) throws XMLStreamException {

        ArrayList<Persona> persone = XMLReader.readPersone(CFConstants.FILE_PERSONE);
        String cf_persone;
        ArrayList<String> accoppiati=new ArrayList<>(); // i codici fiscali creati
        ArrayList<String> presenti=new ArrayList<>(); // i codici fiscali de

        for(int i=persone.size()-1;i>=0;i--){

            cf_persone=CodiceFiscale.makeCF(persone.get(i));
            if (cf.contains(cf_persone)){

                cf.remove(i);
                accoppiati.add(cf_persone);

            }
        }

        return accoppiati;
    }



    public static ArrayList<String> errati(ArrayList<String> cf ){

        ArrayList<String> invalidi = new ArrayList<>();

        for (int i=cf.size()-1; i>=0;i--){
            
            if(! cf.get(i).CodiceFiscale.isValid){

                invalidi.add(cf.get(i));
                cf.remove(i);
            }
                
        }

        return invalidi;

    }

    
}
