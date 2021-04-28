package it.unibs.pgarnaldo.burglars.CodiceFiscale;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class CodFiscsTESTER {

	public static void main(String[] args) throws XMLStreamException {
		ArrayList<Integer> data = new ArrayList<>();
		data.add(2001);
		data.add(1);
		data.add(6);
		Persona io = new Persona(0,"Riccardo","Valtorta",'M', data,"BRESCIA");
		CodiceFiscale cf = new CodiceFiscale();
		System.out.println(cf.makeCF(io));
	}

}
