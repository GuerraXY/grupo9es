package testes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import program.Program;

class ProgramTest {

	Program p = new Program();
	@Test
	void queryTest() {

		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> alentejo = new ArrayList<String>();
		ArrayList<String> centro = new ArrayList<String>();
		ArrayList<String> lisboa = new ArrayList<String>();
		ArrayList<String> algarve = new ArrayList<String>();
		ArrayList<String> norte = new ArrayList<String>();
		alentejo.add("50");
		alentejo.add("50");
		alentejo.add("50");
		centro.add("0");
		centro.add("50");
		centro.add("50");
		lisboa.add("0");
		lisboa.add("50");
		lisboa.add("50");
		algarve.add("50");
		algarve.add("50");
		algarve.add("50");
		norte.add("0");
		norte.add("50");
		norte.add("50");
		map.put("Alentejo", alentejo);
		map.put("Centro", centro);
		map.put("Lisboa", lisboa);
		map.put("Algarve", algarve);
		map.put("Norte", norte);
		
		HashMap<String, ArrayList<String>> map2 = p.queryRDF();
		assertTrue(map.equals(map2));
	
	}
	@Test
	void validadeHashMapTest() {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> alentejo = new ArrayList<String>();
		alentejo.add("0");
		alentejo.add("");
		alentejo.add("");
		map.put("alentejo", alentejo);
		HashMap<String, ArrayList<String>> map2 = new HashMap<String, ArrayList<String>>();
		ArrayList<String> alentejo2 = new ArrayList<String>();
		alentejo2.add("0");
		alentejo2.add("0");
		alentejo2.add("0");
		map2.put("alentejo", alentejo2);
		p.validateHashMap(map);
		
		assertTrue(map.equals(map2));
	}
	
	

}
