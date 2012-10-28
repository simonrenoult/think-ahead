package modele;

/**
 * <b>Coup est la classe representant le coup d'un joueur</b>
 * <p>
 * Un coup est caracterisee par les attributs suivants :
 * <ul>
 * <li>Un proprietaire ; </li>
 * <li>Une position dans la grille ; </li>
 * <li>Une orientation ; </li>
 * </ul>
 * </p>
 *
 * @see Grille 
 * @author Simon Renoult aKa G4llic4
 * 
 */
public class Coup {
	
	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//

	/**
	 *  {@link Joueur} indiquant la paternite du coup joue.
	 */
	private Joueur joueur;
	
	/**
	 * {@link Position} du coup.
	 */
	private Position positionCase;
	
	/**
	 * {@link Orientation} dans laquelle s'est joue coup (1 ou 0).
	 */
	private Orientation orientation;

	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	/**
	 * <p>Constructeur sans parametre de la classe {@link Coup}.
	 * Initialise le joueur, la position et l'orientation a null.
	 * </p>
	 * 
	 * @category Constructor
	 */
	public Coup() {
		joueur = null;
		positionCase = null;
		orientation = null;
	}	
	
	/**
	 * <p>Constructeur parametre de la classe {@link Coup}.
	 * Initialise le joueur, la position et l'orientation selon les parametres
	 * d'entree.
	 * </p>
	 * 
	 * @param j 
	 * 			{@link Joueur} du coup instancie.
	 * 
	 * @param posCase
	 * 			{@link Position} du coup instanciee.
	 * @param or
	 * 			{@link Orientation} du coup instancie.
	 * 
	 * @category Constructor
	 */
	public Coup(Joueur j, Position posCase, Orientation or) {
		this.joueur = j;
		this.positionCase = posCase;
		this.orientation = or;
	}
	
	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	public Joueur getJoueur() {return joueur;}
	
	public Position getPositionCase() {return positionCase;}
	
	public Orientation getOrientation() {return orientation;}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	public void setJoueur(Joueur joueur) {this.joueur = joueur;}
	
	public void setPositionCase(Position posCase) {this.positionCase = posCase;}
	
	public void setOrientation(Orientation or) {this.orientation = or;}
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		


	/**
	 * @category Methods
	 * @category System
	 * @return 
	 * 			Retourne une {@link String} contenant un affichage standard de la classe.
	 */
	@Override
	public String toString() {
		return joueur.toString()+" ; "
				+positionCase.toString()+" ; "
				+orientation.toString()+" ; ";
	}
}
