package com.workspace.vehicleidentifier;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for VehicleIdentifier App.
 */
public class VehicleIdentifierTest {
	static String vehicleXML;
	
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void getVehicleIdentifierTest() {
		String xmlData1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><vehicles><vehicle><id>vehicle 1</id><frame><material>plastic</material></frame><wheels><wheel><position>left rear</position><material>plastic</material></wheel><wheel><position>right rear</position><material>plastic</material></wheel><wheel><position>front</position><material>plastic</material></wheel></wheels><powertrain>human</powertrain></vehicle><vehicle><id>vehicle 2</id><frame><material>metal</material></frame><wheels><wheel><position>rear</position><material>metal</material></wheel><wheel><position>front</position><material>metal</material></wheel></wheels><powertrain>human</powertrain></vehicle></vehicles>";
		String xmlData2 = "abc";
		
		assertEquals("Vehicle 1 Motorcycle",VehicleIdentifier.getVehicleType(xmlData1));  
        assertEquals("abc",VehicleIdentifier.getVehicleType(xmlData2));  
	}
	
	@Test
	public void testFileForNullInput(){
		assertFalse(vehicleXML.contentEquals(null));
	}
}
