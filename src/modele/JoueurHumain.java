package modele;

/**
 * <b>JoueurHumain est la classe representant un JoueurHumain de la grille</b>
 * <p>
 * Il ne possede aucun autre attibut que celui de sa classe mere
 * </p>
 *
 * @see Joueur
 * @author Simon Renoult aKa G4llic4
 */
public class JoueurHumain extends Joueur {

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	/**
	 * Constructeur non parametre de la classe {@link JoueurHumain}.
	 * Initialise un joueur humain depuis le constructeur de la classe
	 * mere avec comme parametre "JoueurHumain".
	 * @see {@link Joueur}
	 * @category Constructors 
	 */
	public JoueurHumain() {
		super("JoueurHumain");
	}
	
	/**
	 * Constructeur parametre de la classe {@link JoueurHumain}.
	 * Initialise un joueur humain depuis le constructeur de la classe
	 * mere avec comme parametre "nom".
	 * @see {@link Joueur}
	 * @category Constructors 
	 * @param nom {@link String} indiquant le nom du joueur.
	 */
	public JoueurHumain(String nom) {
		super(nom);
	}
	
	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		

	/**
	 * @param p {@link Partie} sur laquelle s'applique le coup.
	 * @param posCoup {@link Position} du coup joue.
	 * @return Retourne un booleen <code>true</code> si tout s'est bien deroule, faux dans le cas contraire.
	 * Methode jouant le coup du joueur humain.
	 */
	public boolean jouerCoup(Partie p, Position posCoup) {
		AlignementCases aActif = p.getLaGrille().getAlignementActif();

		if(!aActif.getCasesLibres().isEmpty()) {
			for(Case c : aActif.getCasesLibres())
				if(c.getPosition().equals(posCoup)) {
					//Modification du proprietaire de la case
					c.setJouePar(this);
					//Mise a jour du score
					p.ajouterScore(this, c.getValeur());
				}			
	
				//Ajout du coup a la liste
				Coup cpHum = new Coup(this, posCoup, aActif.getOrientation());
				p.ajouterCoup(cpHum);
		
				//Inversion de l'alignement
				p.getLaGrille().renverserAlignActif(cpHum);
				
				return true;
		}else return false;
		
	}

	/**
	 * @return Retourne la {@link String} renvoyee par la methode mere.
	 * {@link Override} de la classe toSting de la classe {@link Joueur}.
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/**
	 * @param obj {@link Object} sur lequel s'applique la methode.
	 * {@link Override} de la classe equals mere {@link Joueur}.
	 * Verifie le type du parametre obj puis les attributs de l'objet.
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof JoueurHumain)) return false;
		
		JoueurHumain jhTemp = (JoueurHumain) obj;
		if( super.equals(jhTemp) ) return true;
		
		return false;
	}
	
}
