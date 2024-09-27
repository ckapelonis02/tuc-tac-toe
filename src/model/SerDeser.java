package model;

import java.io.*;

public class SerDeser {
	public static PlayerRoster pr = new PlayerRoster();
	
	public static void createFile() {
	    try {
	      File myObj = new File("tictactoe.ser");
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	        pr.addPlayer(new MrBean("mrbean1"));
	        pr.addPlayer(new MrBean("mrbean2"));
	        pr.addPlayer(new Hal("hal"));
	      } else {
	        System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	public static void serialize(PlayerRoster pr) {
	      try {
	          FileOutputStream fileOut = new FileOutputStream("tictactoe.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(pr);
	          out.close();
	          fileOut.close();
	          System.out.println("Serialized data is saved in tictactoe.ser");
	       } catch (IOException i) {
	          i.printStackTrace();
	       }
	}
	
	public static PlayerRoster deserialize() {
		try {
	         FileInputStream fileIn = new FileInputStream("tictactoe.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         pr = (PlayerRoster)in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (EOFException e) {
	    	  System.out.println("Obligatory Error");
	      }	catch (IOException i) {
	    	 System.out.println("IO Exception");
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("PlayerRoster class not found");
	         c.printStackTrace();
	      }
		
	      System.out.println("Deserialized PlayerRoster...");

	      return pr;
	}
}
