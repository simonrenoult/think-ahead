package modele;

import java.lang.reflect.Constructor;
import java.util.LinkedList;

/**
 * <b>AlignementCases est la classe representant un alignement de cases de la grille</b>
 * <p>
 * Un alignement de cases est caracterise par les attributs suivants :
 * <ul>
 * <li>Une orientation ; </li>
 * <li>Une liste de cases; </li>
 * <li> Une position dans la grille ; </li>
 * </ul>
 * </p>
 *
 * @see Case
 * @see Grille
 * @author Simon Renoult aKa G4llic4
 */
public class AlignementCases {

	// -------------------------------------------------------------//
	// -------------------------ATTRIBUTES--------------------------//
	// -------------------------------------------------------------//

	/**
	 * {@link Orientation} de la classe (1 ou 0 pour vertical ou horizontal).
	 */
	private Orientation orientation;
	
	/**
	 * {@link LinkedList} de {@link Case} contenant les cases de l'{@link AlignementCases}.
	 */
	private LinkedList<Case> lesCases;
	
	/**
	 * {@link Integer} contenant la position de l'{@link AlignementCases}.
	 */
	private int position;

	// -------------------------------------------------------------//
	// ------------------------CONSTRUCTORS-------------------------//
	// -------------------------------------------------------------//

	/**
	 * <b>Constructeur AlignementCases</b>
	 * <p>
	 * {@link Constructor} sans parametre de la classe {@link AlignementCases}.
	 * Initalise une nouvelle {@link Position} sans parametre, une nouvelle {@link LinkedList}
	 * de {@link Case} et la {@link Position} a 0.
	 * </p>
	 */
	public AlignementCases() {
		orientation = new Orientation();
		lesCases = new LinkedList<Case>();
		position = 0;
	}

	/**
	 * <b>Constructeur AlignementCases.</b>
	 * <p>
	 * {@link Constructor} parametre de la classe {@link AlignementCases}.
	 * Initalise une nouvelle les trois attributs selon les parametres en entree.
	 * </p>
	 * 
	 * @param or 
	 * 			{@link Orientation} de la classe nouvellement creee (0 ou 1).
	 * @param lC 
	 * 			{@link LinkedList} de {@link Case} de la classe nouvellement creee.
	 * @param pos 
	 * 			{@link Integer} contenant la position de l'{@link AlignementCases}.
	 */
	public AlignementCases(Orientation or, LinkedList<Case> lC, int pos) {
		orientation = or;
		lesCases = lC;
		position = pos;
	}

	// -------------------------------------------------------------//
	// ---------------------------GETTERS---------------------------//
	// -------------------------------------------------------------//

	/**
	 * <b>Getters.</b> 
	 * <p>
	 * Getter de l'attribut orientation.
	 * </p>
	 * 
	 * @return 
	 * 			{@link Orientation} Retourne l'{@link Orientation} de l'{@link AlignementCases}.
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * <b>Getters.</b> 
	 * <p>
	 * Getter de l'attribut lesCases.
	 * </p>
	 * 
	 * @return
	 * 			{@link LinkedList}<{@link Case}> Retourne la liste de {@link Case} de l'{@link AlignementCases}.
	 */
	public LinkedList<Case> getLesCases() {
		return lesCases;
	}

	/**
	 * <b>Getters.</b>
 	 * <p>
 	 * Getter de l'attribut position.
 	 * </p>
 	 * 
	 * @return 
	 * 			{@link Integer} Retourne la position de l'{@link AlignementCases}.
	 */
	public int getPosition() {
		return position;
	}

	// -------------------------------------------------------------//
	// ---------------------------SETTERS---------------------------//
	// -------------------------------------------------------------//

	/**
	 * <b>Setters</b>
	 * <p>
	 * Setter de l'attribut orientation.
	 * </p>
	 * 
	 * @param 
	 * 			orientation {@link Orientation} affectant l'attribut orientation de la classe.
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * <b>Setters.</b>
	 * <p>
	 * Setter de l'attribut lesCases.
	 * </p>
	 * 
	 * @param 
	 * 			lesCases {@link LinkedList} affectant l'attribut lesCases de la classe.
	 */
	public void setLesCases(LinkedList<Case> lesCases) {
		this.lesCases = lesCases;
	}

	/**
	 * <b>Setters.</b>
	 * <p>
	 * Setter de l'attribut position.
	 * </p>
	 * 
	 * @param 
	 * 			position {@link Integer} affectant l'attribut position de la classe.
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	// -------------------------------------------------------------//
	// ---------------------------METHODS---------------------------//
	// -------------------------------------------------------------//

	/**
	 * @category Methods
	 * @return 
	 * 			{@link Boolean} Retourne un booleen <code>true</code> si les cases sont vides
	 * 			<code>false</code> dans le cas contraire.
	 */

	public boolean estVide() {
		if (this.getLesCases().isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * @category Methods
	 * @return 
	 * 			{@link LinkedList} Retourne une {@link LinkedList} de {@link Case} contenant les
	 * 			{@link Case} libres de l'{@link AlignementCases} de l'objet courant.
	 */
	public LinkedList<Case> getCasesLibres() {
		LinkedList<Case> tmp = new LinkedList<Case>();

		for (Case c : lesCases)
			if (c.getJouePar() == null)
				tmp.add(c);

		return tmp;
	}

	/**
	 * @category Methods
	 * @see 
	 * 			getCasesLibres
	 * @return 
	 * 			{@link Case} Retourne une {@link Case} copie de la case ayant la plus haute valeur
	 * 			dans l'{@link AlignementCases} libres.
	 */
	public Case getCaseLibreValeurMax() {
	
		Case tmp = new Case();

		for (Case c : getCasesLibres())
			if (c.getValeur() > tmp.getValeur())
				tmp.clone(c);

		System.out.println(tmp);

		return tmp;
	}

	/**
	 * @category Methods
	 * @category System
	 * @return 
	 * 			{@link String} Retourne une {@link String} contenant les informations definies dans la methode.
	 */
	public String toString() {
		Integer pos = this.getPosition();
		return orientation.toString() + " ; " + pos.toString() + " ; "
				+ lesCases.toString() + " ; ";
	}
}
