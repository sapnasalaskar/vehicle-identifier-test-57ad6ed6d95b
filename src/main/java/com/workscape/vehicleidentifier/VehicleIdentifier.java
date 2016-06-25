package com.workspace.vehicleidentifier;

import java.io.*;
import org.json.*;

public class VehicleIdentifier {
	
	public static String getVehicleType(String xmlString)
	{
		try
		{
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			JSONArray vehicleArr = xmlJSONObj.getJSONObject("vehicles").getJSONArray("vehicle");
			boolean flag = false;
			
			for(int i=0;i<vehicleArr.length();i++)
			{
				JSONObject jsonObj = vehicleArr.getJSONObject(i);
				Object materialList = jsonObj.getJSONObject("frame").get("material");
				String powerTrainList = jsonObj.getString("powertrain");
						
				if(jsonObj.getJSONObject("wheels").getJSONArray("wheel") != null )
				{
					JSONArray wheelArr = jsonObj.getJSONObject("wheels").getJSONArray("wheel");
				
					for(int j=0;j<wheelArr.length();j++)
					{
						JSONObject wheelsList = wheelArr.getJSONObject(j);
						String position = wheelsList.getString("position");
						String wheelMaterial = wheelsList.getString("material");
						
						if(wheelArr.length() == 3 && wheelMaterial.equalsIgnoreCase("plastic") && (position.equalsIgnoreCase("front") || position.equalsIgnoreCase("left rear") || position.equalsIgnoreCase("right rear")))
						{
							flag = true;
						}
						else if(wheelArr.length() == 2 && wheelMaterial.equalsIgnoreCase("metal") && (position.equalsIgnoreCase("front") || position.equalsIgnoreCase("rear")))
						{
							flag = true;
						}
						else if(wheelArr.length() == 2 && wheelMaterial.equalsIgnoreCase("internal combustion") && (position.equalsIgnoreCase("front") || position.equalsIgnoreCase("rear")))
						{
							flag = true;
						}
						else if(wheelArr.length() == 0 && wheelMaterial.equalsIgnoreCase("bernoulli"))
						{
							flag = true;
						}
						else if(wheelArr.length() == 4 && wheelMaterial.equalsIgnoreCase("internal combustion") && (position.equalsIgnoreCase("front right") || position.equalsIgnoreCase("front left") || position.equalsIgnoreCase("rear right") || position.equalsIgnoreCase("rear left")))
						{
							flag = true;
						}
					}
				
				if(materialList.equals("plastic") && powerTrainList.equalsIgnoreCase("human") && flag == true)
				{
					System.out.println("Vehicle "+(i+1)+" Big Wheel");
				}
				else if(materialList.equals("metal") && powerTrainList.equalsIgnoreCase("human") && flag == true)
				{
					System.out.println("Vehicle "+(i+1)+" Bicycle");
				}
				else if(materialList.equals("metal") && powerTrainList.equalsIgnoreCase("internal combustion") && wheelArr.length() == 2 && flag == true)
				{
					System.out.println("Vehicle "+(i+1)+" Motorcycle");
				}
				else if(materialList.equals("plastic") && powerTrainList.equalsIgnoreCase("bernoulli") && flag == true)
				{
					System.out.println("Vehicle "+(i+1)+" Hand Glider");
				}
				else if(materialList.equals("metal") && powerTrainList.equalsIgnoreCase("internal combustion") && wheelArr.length() == 4 && flag == true)
				{
					System.out.println("Vehicle "+(i+1)+" Car");
				}
			}
		}
					
		}
		catch(JSONException je)
		{
			System.out.println(je.toString());
		}
		return "";
	}
	
	public static void main(String[] args) throws IOException{
		File xmlFile = new File("C:/Users/sapna/workspace/VehicleIdentifier/resources/vehicles.xml");		
		Reader fileReader;
		try {
			fileReader = new FileReader(xmlFile);
			BufferedReader bufReader = new BufferedReader(fileReader);
			StringBuilder sb = new StringBuilder();
			String line = bufReader.readLine(); 
			while( line != null)
			{ 
				sb.append(line).append("\n"); 
				line = bufReader.readLine(); 
			}
			String xmlToString = sb.toString(); 
			bufReader.close(); 
			System.out.println(VehicleIdentifier.getVehicleType(xmlToString));
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}		
	}
	
}
