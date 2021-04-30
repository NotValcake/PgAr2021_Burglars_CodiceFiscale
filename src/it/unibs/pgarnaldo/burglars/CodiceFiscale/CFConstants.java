package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe di utilità contenente le costanti necessarie  al funzionamento del
 * programma.
 * @author burglars
 */

public class CFConstants {
    public static final String DIV = "===================================";
    public static final String FILE_COMUNI = "inputFiles/comuni.xml";
    public static final String FILE_PERSONE = "inputFiles/inputPersone.xml";
    public static final String FILE_CODICI = "inputFiles/codiciFiscali.xml";
    public static final String FILE_OUTPUT = "codiciPersone.xml";
    public static final int LUNGHEZZA_CF = 16;

    public final static ArrayList<String> MESI = new ArrayList<>();
    public static final String ASSENTE = "ASSENTE";
    public static final String TAG_CODICE = "codice";

    static {
        String [] mesi = {"A","B","C","D","E","H","L","M","P","R","S","T"};
        MESI.addAll(Arrays.asList(mesi));
    }

    public static final ArrayList<Character> CONSONANTI = new ArrayList<>();

    static{
        Character [] consonanti = {'B', 'C', 'D','F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z'};
        CONSONANTI.addAll(Arrays.asList(consonanti));

    }
    
    public static HashMap<String, String> COMUNI = new HashMap<>();

    static {
        try {
            COMUNI = XMLReader.readComuni(FILE_COMUNI);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public final static Map<Character, Integer> CARATTERI_DISPARI = Map.ofEntries(
            Map.entry('0', 1),
            Map.entry('1', 0),
            Map.entry('2', 5),
            Map.entry('3', 7),
            Map.entry('4', 9),
            Map.entry('5', 13),
            Map.entry('6', 15),
            Map.entry('7', 17),
            Map.entry('8', 19),
            Map.entry('9', 21),
            Map.entry('A', 1),
            Map.entry('B', 0),
            Map.entry('C', 5),
            Map.entry('D', 7),
            Map.entry('E', 9),
            Map.entry('F', 13),
            Map.entry('G', 15),
            Map.entry('H', 17),
            Map.entry('I', 19),
            Map.entry('J', 21),
            Map.entry('K', 2),
            Map.entry('L', 4),
            Map.entry('M', 18),
            Map.entry('N', 20),
            Map.entry('O', 11),
            Map.entry('P', 3),
            Map.entry('Q', 6),
            Map.entry('R', 8),
            Map.entry('S', 12),
            Map.entry('T', 14),
            Map.entry('U', 16),
            Map.entry('V', 10),
            Map.entry('W', 22),
            Map.entry('X', 25),
            Map.entry('Y', 24),
            Map.entry('Z', 23));

    public final static Map<Character, Integer> CARATTERI_PARI = Map.ofEntries(
            Map.entry('0', 0),
            Map.entry('1', 1),
            Map.entry('2', 2),
            Map.entry('3', 3),
            Map.entry('4', 4),
            Map.entry('5', 5),
            Map.entry('6', 6),
            Map.entry('7', 7),
            Map.entry('8', 8),
            Map.entry('9', 9),
            Map.entry('A', 0),
            Map.entry('B', 1),
            Map.entry('C', 2),
            Map.entry('D', 3),
            Map.entry('E', 4),
            Map.entry('F', 5),
            Map.entry('G', 6),
            Map.entry('H', 7),
            Map.entry('I', 8),
            Map.entry('J', 9),
            Map.entry('K', 10),
            Map.entry('L', 11),
            Map.entry('M', 12),
            Map.entry('N', 13),
            Map.entry('O', 14),
            Map.entry('P', 15),
            Map.entry('Q', 16),
            Map.entry('R', 17),
            Map.entry('S', 18),
            Map.entry('T', 19),
            Map.entry('U', 20),
            Map.entry('V', 21),
            Map.entry('W', 22),
            Map.entry('X', 23),
            Map.entry('Y', 24),
            Map.entry('Z', 25));

    public final static Map<Integer, Character> RESTO = Map.ofEntries(
            Map.entry(0, 'A'),
            Map.entry(1, 'B'),
            Map.entry(2, 'C'),
            Map.entry(3, 'D'),
            Map.entry(4, 'E'),
            Map.entry(5, 'F'),
            Map.entry(6, 'G'),
            Map.entry(7, 'H'),
            Map.entry(8, 'I'),
            Map.entry(9, 'J'),
            Map.entry(10, 'K'),
            Map.entry(11, 'L'),
            Map.entry(12, 'M'),
            Map.entry(13, 'N'),
            Map.entry(14, 'O'),
            Map.entry(15, 'P'),
            Map.entry(16, 'Q'),
            Map.entry(17, 'R'),
            Map.entry(18, 'S'),
            Map.entry(19, 'T'),
            Map.entry(20, 'U'),
            Map.entry(21, 'V'),
            Map.entry(22, 'W'),
            Map.entry(23, 'X'),
            Map.entry(24, 'Y'),
            Map.entry(25, 'Z'));


}
