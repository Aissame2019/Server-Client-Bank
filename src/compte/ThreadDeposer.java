package compte;

import java.io.PrintStream;

public class ThreadDeposer extends Thread {
	public Compte c;
	public int nbr; 
	public String ss;
	public PrintStream p;
	
	public ThreadDeposer(Compte c1, int nbr,String ss,PrintStream p) {
		this.p = p;
		this.c = c1;
		this.nbr = nbr;
		this.ss=ss;
	}
	public void run() {

			 try
			 {
			this.c.deposer(nbr,ss,p);
			 }
			 catch (InterruptedException e) {
				 e.printStackTrace();
			 }
			 			
		}
}
