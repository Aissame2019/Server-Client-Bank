package compte;
import java.io.PrintStream;
import java.io.Serializable;
public class Compte implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int num_compte;
	public int sold;
	
	private int deposer_retirer; // 0 : retirer , 1 : deposer
	private String nameUser="";
	private int somme=0;
	
	
	public int getDeposer_retirer() {
		return deposer_retirer;
	}
	public void setDeposer_retirer(int deposer_retirer) {
		this.deposer_retirer = deposer_retirer;
	}
	public void setUser(String s) {
		this.nameUser = s;
	}
	public void setSomme(int s) {
		this.somme = s;
	}
	public String getUser() {
		
		return nameUser;
		
	}
	public int getSomme() {
		return somme;
		
	}
	
	public synchronized void retirer(int somme,String ss,PrintStream out) throws InterruptedException {

		while(sold < somme) {
			
			try {
				System.out.println("wait");
				out.println("La somme a retire est plus grand que le solde || en attent de deposer ...");
				wait();
				
			} catch(Exception e) {
				Thread.currentThread().interrupt();
				System.err.println("Thread Interrupted");
			}		
		}
		
			sold -= somme;
			out.println("ancienne solde  = "+ (this.sold+somme) + " || Merci "+ ss +" OPERATION EFFECTUE et le nouveau Solde = "+ this.sold);
			System.out.println("Transaction succes | "+ss+" retirer et sold = "+sold);			
		
	
	
	}
	
		public synchronized void deposer(int somme,String ss,PrintStream out) throws InterruptedException {

			sold += somme;
			out.println("ancienne solde  = "+ (this.sold-somme) + " || Merci "+ ss +" OPERATION EFFECTUE et le nouveau Solde = "+ this.sold);
			notifyAll();
			System.out.println("Transaction succes | "+ss+" deposer et solde = "+sold);
		}

}
