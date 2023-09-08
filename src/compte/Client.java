package compte;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
	final static int port = 2000;
	
	public static void main(String args[]) {
		Socket socket;
		final Scanner sc=new Scanner(System.in);
		try {
	        InetAddress serveur = InetAddress.getLocalHost();
	        socket = new Socket(serveur, port);

	        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        
	        DataInputStream is = new DataInputStream(socket.getInputStream());

	        
	        while(true){
	        	System.out.println("---------------------------------------- Bank of GP3 ----------------------------------------");
	        	System.out.println("Entrez le nom d'utilisateur:");
	        	String nameUser = sc.nextLine();
	        	int num_compte;
	        	do {
		        	System.out.println("Entrez le numero du compte (1:aissame-2:taha-3:mohammed-4:oussama-5:jamal-6:aymane) :");
		        	num_compte = sc.nextInt();
	        	}while(num_compte<0 || num_compte>6);

	        	System.out.println("Pour retirer entrez : 0 || Pour deposez entrez : 1");
	        	int retirer_deposer = sc.nextInt();
	        	System.out.println("Entrez la somme :");
	        	int somme = sc.nextInt();
	        	
	        	Transaction t = new Transaction(nameUser, num_compte, retirer_deposer, somme);
	        	
	        	os.writeObject(t);
	        	
	        	if(retirer_deposer == 0 && is.readInt() < somme ) {
	        		System.out.println(in.readLine());
	        		System.out.println(in.readLine());
	        	}else	System.out.println(in.readLine());
	        	
	        	
	        	os.flush();
	        	sc.nextLine();
	        	
	      	  }

	      } catch (Exception e) {
	        e.printStackTrace();
	      }finally {
	    	//os.close();
	      	sc.close();
	      }
	} 
	
}
