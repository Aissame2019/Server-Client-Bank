package compte;
import java.io.Serializable;
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Compte c = new Compte();
	
	public Transaction(String nu, int nc, int nbr, int sm) {
		this.c.setUser(nu);
		this.c.num_compte = nc;
		this.c.setDeposer_retirer(nbr);
		this.c.setSomme(sm);
	}
}
