package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class CodFiscsTESTER {

	public static void main(String[] args) throws XMLStreamException {
		ArrayList<Integer> data = new ArrayList<>();
		data.add(2010);
		data.add(7);
		data.add(29);

		Persona p = new Persona(346, "Au", "au", 'M', data,"ACCETTURA");

		CodiceFiscale cf = new CodiceFiscale(p);
		System.out.println(cf.getCodFiscale());
		System.out.println(cf.isValid());



	}

}
