package modele;

/**
 * <b>Joueur est la classe representant un Joueur de la partie</b>
 * <p>
 * Une joueur est caracterise par l'attributs suivant :
 * <ul>
 * <li> Un nom ; </li>
 * </ul>
 * </p>
 *
 * @see JoueurHumain
 * @see JoueurOrdinateur 
 * @author Simon Renoult aKa G4llic4
 */
public class Joueur {

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//
	
	private String pseudo;
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//
	
	public Joueur() {
		this.pseudo = "Inconnu";
	}
	
	public Joueur(String n) {
		this.pseudo = n;
	}
	
	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//
	
	public String getNom() {
		return this.pseudo;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//
	
	public void setNom(String nom) {
		this.pseudo = nom;
	}
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		
	
	public void jouer() {}
	
	@Override
	public String toString() {
		return getNom()+ " ; ";
	}
	
	/**
	 * deepEquals verifiant les attributs ET la reference.
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Joueur)) return false;
		
		Joueur jTemp = (Joueur) obj;
		if( jTemp.getNom().equals(this.getNom()) && jTemp == this)
			return true;
		
		return false;
	}
}
