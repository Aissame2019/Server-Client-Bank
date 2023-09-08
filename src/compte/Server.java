package compte;

import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.*;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {

	// number of port listening 
	final static int port = 2000;
	// socket of server
	private Socket socket;

	
	public ArrayList <Compte> c;
 
	
	public Server(Socket socket, ArrayList <Compte> c) {
		this.socket = socket;
		this.c = c;
	}

	public void run() {
		try {
			try {

				
				ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
				
				PrintStream out = new PrintStream(socket.getOutputStream());
				
				DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				System.out.println("Connexion avec le client : " + socket.getInetAddress() + "thread name " +Thread.currentThread().getName());

				// write in database

				
				
				while(true) {
					Transaction m = (Transaction) is.readObject();
					System.out.println(m.c.getUser());
					// while when no data base
					for(Compte c1 : c) {
						
						if(m.c.num_compte == c1.num_compte) {
							os.writeInt(c1.sold);
							if(m.c.getDeposer_retirer() == 0) {
								//this.c.retirer(m.getSomme(), m.getUser());
								Thread t2 = new ThreadRetirer(c1, m.c.getSomme(), m.c.getUser(),out);
								System.out.println("ancienne solde  : "+ c1.sold);
								t2.start();
								System.out.println("thread retirer lancer");
							}
							else if(m.c.getDeposer_retirer() == 1) {
								//this.c.deposer(m.getSomme(), m.getUser());
								Thread t1 = new ThreadDeposer(c1, m.c.getSomme(), m.c.getUser(),out);
								System.out.println("ancienne solde  : "+ c1.sold);
								t1.start();
								System.out.println("thread deposer lancer");
							}
							
						}else {
							// not found in server database 
							//out.println("compte n'existe pas");
							continue;
						}
						
					}
						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				socket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		try {
			
			ArrayList <Compte> comptes = new ArrayList <Compte>();
			Compte c = new Compte();
			Compte c1 = new Compte();
			Compte c2 = new Compte();
			Compte c3 = new Compte();
			Compte c4 = new Compte();
			Compte c5 = new Compte();
			c.num_compte = 1;
			c.sold = 1000;
			c.setUser("aissame");
			c1.num_compte = 2;
			c1.sold = 1000;
			c1.setUser("taha");
			c2.num_compte = 3;
			c2.sold = 1000;
			c2.setUser("mohammed");
			c3.num_compte = 4;
			c3.sold = 1000;
			c3.setUser("oussama");
			c4.num_compte = 5;
			c4.sold = 1000;
			c4.setUser("jamal");
			c5.num_compte = 6;
			c5.sold = 1000;
			c5.setUser("aymane");
			comptes.add(c);
			comptes.add(c1);
			comptes.add(c2);
			comptes.add(c3);
			comptes.add(c4);
			comptes.add(c5);
			
			@SuppressWarnings("resource")
			ServerSocket socketServeur = new ServerSocket(port);
			System.out.println("Lancement du serveur");
			while (true) {
				Socket socketClient = socketServeur.accept();
				Server t = new Server(socketClient, comptes);
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
