package modele;

/**
 * <b>Case est la classe representant une case de la grille</b>
 * <p>
 * Une case est caracterisee par les attributs suivants :
 * <ul>
 * <li>Une valeur ; </li>
 * <li>Un proprietaire ; </li>
 * <li> Une position dans la grille ; </li>
 * </ul>
 * </p>
 *
 * @see AlignementCases
 * @see Grille 
 * @author Simon Renoult aKa G4llic4
 * 
 */

public class Case {
	
	
	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//
	
	/**
	 * {@link Integer} contenant la valeur de la {@link Case}.
	 */
	private int valeur;
	
	/**
	 * {@link Joueur} ayant joue la {@link Case}
	 */
	private Joueur jouePar;
	
	/**
	 * {@link Position} de la {@link Case} dans la {@link Grille}.
	 */
	private Position position;

	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	/**
	 * <p>Constructeur sans parametre de la classe {@link Case}.
	 * Initialise la valeur a -1, jouePar a null et la position a une
	 * nouvelle {@link Position} non parametre.</p>
	 * 
	 * @category Constructor
	 */
	public Case() {
		valeur = -1;
		jouePar = null;
		position = new Position();
	}	
	
	/**
	 * <p>Constructeur parametre de la classe {@link Case}.
	 * Initialise la valeur a val, jouePar a j et la position a pos.
	 * </p>
	 * 
	 * @category Constructor
	 * @param val 
	 * 			{@link Integer} de la {@link Case} nouvellement creee.
	 * @param j 
	 * 			{@link Joueur} ayant joue la {@link Case} creee.
	 * @param pos 
	 * 			{@link Position} de la case creee. 
	 */
	public Case(int val, Joueur j, Position pos) {
		valeur = val;
		jouePar = j;
		position = pos;
	}
	
	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	/**
	 * <b>Getters.</b>
	 * <p>
	 * Getter de l'attribut valeur.
	 * </p>
	 * 
	 * @category Getters
	 * @return 
	 * 		{@link Integer} Retourne l'{@link Integer} valeur de la {@link Case}.
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * <b>Getters.</b>
	 * <p>
	 * Getter de l'attribut jouePar.
	 * </p>
	 * 
	 * @category Getters
	 * @return 
	 * 			{@link Joueur} Retourne le {@link Joueur} ayant joue la {@link Case}.
	 */
	public Joueur getJouePar() {
		return jouePar;
	}
	
	/**
	 * <b>Getters.</b>
	 * <p>
	 * Getter de l'attribut position.
	 * </p>
	 * 
	 * @category Getters
	 * @return 
	 * 			{@link Position} Retourne la {@link Position} de la {@link Case}.
	 */
	public Position getPosition() {
		return position;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	/**
	 * <b>Setters.</b>
	 * <p>
	 * Setter de l'attribut valeur.
	 * </p>
	 * 
	 * @category Setters
	 * @param 
	 * 			valeur {@link Integer} affectant l'attribut valeur de la classe.
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * <b>Setters.</b>
	 * <p>
	 * Setter de l'attribut jouePar.
	 * </p>
	 * 
	 * @category Setters
	 * @param 
	 * 			jouePar {@link Joueur} affectant l'attribut jouePar de la classe.
	 */
	public void setJouePar(Joueur jouePar) {
		this.jouePar = jouePar;
	}
	
	/**
	 * <b>Setters.</b>
	 * <p>
	 * Setter de l'attribut position.
	 * </p>
	 * 
	 * @category Setters
	 * @param 
	 * 			position {@link Position} affectant l'attribut position de la classe.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}		
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		

	/**
	 * <p>
	 * Verifie si une cas a deja ete jouee.
	 * </p>
	 * 
	 * @category Methods
	 * @return {@link Boolean} 
	 * 			Retourne <code>true</code> si la case passee en parametre n'a pas ete jouee, <code>false</code> dans le cas contraire.
	 */
	public boolean estLibre() {
		return (this.jouePar == null);		
	}
	
	/**
	 * @category Methods
	 * @return 
	 * 		{@link String} Retourne une {@link String} contenant la valeur de la {@link Case} passee de l'objet courant.
	 */
	public String afficherValeur() {
		Integer val = this.getValeur();
		return val.toString();
	}
	
	/**
	 * @category Methods
	 * @category System
	 * @return 
	 * 			Retourne une {@link String} contenant un affichage standard de la methode
	 */
	public String toString() {
		Integer val = new Integer(getValeur());
		if(jouePar == null) 
			return 	" Valeur : " +val.toString()+ " ; "+
			"Joue par personne ; "
			+position.toString();
		
		return 	val.toString()+" ; "+
				jouePar.toString()+" ; "+
				position.toString()+" ; ";
	}

	/**
	 * @category Methods
	 * @category System
	 * @return 
	 * 			Retourne une {@link String} contenant un affichage standard de la classe.
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof Case)) return false;
		
		Case c = (Case) obj;
		
		return((c.getJouePar() == null)?true:c.getJouePar().equals(this.getJouePar())
				&& c.getPosition().equals(this.getPosition())
				&& c.getValeur() == this.getValeur());
	}
	

	/**
	 * <p>
	 * Clone l'objet courant selon les attributs de l'objet passe en parametre.
	 * </p>
	 * 
	 * @category Methods
	 * @category System
	 * @param obj 
	 * 			{@link Object} sur lequel s'applique la methode.
	 * @return {@link Boolean} 
	 * 			Retourne <code>false</code> si l'objet passe en parametre n'est pas une {@link Case}, <code>true</code> dans le cas contraire.
	 */
	public boolean clone(Object obj) {
		if(!(obj instanceof Case)) return false;
		
		Case c = (Case) obj;
		this.setJouePar(c.getJouePar());
		this.setPosition(c.getPosition());
		this.setValeur(c.getValeur());
		
		return true;
	}
	
}
