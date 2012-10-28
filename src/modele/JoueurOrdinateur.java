package modele;

import java.util.Random;

/**
 * <b>JoueurOrdinateur est la classe representant unr IA</b>
 * <p>
 * Une IA est caracterisee par les attributs suivants :
 * <ul>
 * <li>Un nom, herite de la classe mere {@link Joueur} ; </li>
 * <li>Un type de jeu ; </li>
 * <li>Un niveau de jeu ; </li>
 * </ul>
 * </p>
 *
 * @see Joueur
 * @author Simon Renoult aKa G4llic4
 */
public class JoueurOrdinateur extends Joueur{

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//

	private String typeDeJeu;
	private int niveau;
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	/**
	 * @category Constructor
	 */
	public JoueurOrdinateur() {
		super();
		typeDeJeu = "facile";
		niveau = 1;
	}
	
	/**
	 * 
	 * @category Constructor
	 * @param nom
	 * @param tdj
	 * @param nv
	 */
	public JoueurOrdinateur(String nom, String tdj, int nv) {
		super(nom);
		typeDeJeu = tdj;
		niveau = nv;
	}
	
	/**
	 * 
	 * @category Constructor
	 * @param nv
	 */
	public JoueurOrdinateur(int nv) {
		selecteurIA(nv);
	}

	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	/**
	 * @category Getters
	 * @return
	 */
	public String getTypeDeJeu() {
		return typeDeJeu;
	}

	/**
	 * @category Getters
	 * @return
	 */
	public int getNiveau() {
		return niveau;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	/**
	 * @category Setters
	 * @param typeDeJeu
	 */
	public void setTypeDeJeu(String typeDeJeu) {
		this.typeDeJeu = typeDeJeu;
	}

	/**
	 * @category Setters
	 * @param niveau
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		

	/**
	 * @category Methods
	 * @category IA
	 * @param nv Niveau determinant le nom et le type de jeu de l'IA
	 */
	public void selecteurIA(int nv) {
		switch(nv) {
		case 1:
				this.setTypeDeJeu("Trouffion");
				this.setNiveau(nv);
				super.setNom("Bender");
				break;
		case 2 :
				this.setTypeDeJeu("Fantassin");
				this.setNiveau(nv);
				super.setNom("Astroboy");
				break;
		case 3 :
				this.setTypeDeJeu("Champion");
				this.setNiveau(nv);
				super.setNom("Goldorak");
				break;		
		case 4 :
				this.setTypeDeJeu("Godlike");
				this.setNiveau(nv);
				super.setNom("R2D2");
				break;
		default :
				System.out.println("ERREUR::JoueurOrdinateur.java::selecteurNiveau(int)");
				break;
		}		
	}

	/**
	 * @category Methods
	 * @category Strategy
	 * 
	 * @param p 
	 * 			{@link Partie} sur laquelle travaille l'ordinateur
	 */
	public void jouer(Partie p) {

		//Alias afin de racourcir les appels aux methodes
		AlignementCases aActif = p.getLaGrille().getAlignementActif();
		JoueurOrdinateur ordi = (JoueurOrdinateur) p.getJoueur(1);

		Case choix = new Case();
			
		if(!aActif.getCasesLibres().isEmpty()) {
			switch(ordi.getNiveau()) {
				case 1:
					choix = strategieTrouffion(p);
					break;
				case 2:
					choix = strategieFantassin(p);
					break;
				case 3:
					choix = strategieChampion(p);
					break;
				case 4:
					choix = strategieGodlike(p);
					break;
				default:
					System.out.println("ERREUR::JoueurOrdinateur.java::selecteurStrategie(AlignementCases)");
			}
		
			//Creation d'un nouveau coup selon le choix de l'IA
			Coup tmp = new Coup(p.getJoueur(1), 
					choix.getPosition(), 
					aActif.getOrientation());
			
			for(Case c : aActif.getCasesLibres())
				if(c.getPosition().equals(tmp.getPositionCase())) {
					//Modification du proprietaire de la case
					c.setJouePar(this);
					p.updateScore();
				}
			//Ajout du coup a la liste des coups
			p.ajouterCoup(tmp);
		
			//Inversion de l'alignement
			p.getLaGrille().renverserAlignActif(tmp);
		}
	}
	
	/**
	 * @category Methods
	 * @category Strategy
	 * 
	 * @param p 
	 * 			{@link Partie} sur laquelle travaille l'ordinateur
	 * 
	 * @return {@link Case} choisie par l'IA
	 */
	public Case strategieTrouffion(Partie p) {		
		int val = new Random().nextInt(p.getLaGrille().getAlignementActif().getCasesLibres().size());
		Case caseTemp = p.getLaGrille().getAlignementActif().getCasesLibres().get(val);
		
		return caseTemp;
	}
		
	/**
	 * @category Methods
	 * @category Strategy
	 * @param p {@link Partie} sur laquelle travaille l'ordinateur
	 * @return {@link Case} choisie par l'IA
	 */
	public Case strategieFantassin(Partie p) {
		
		return p.getLaGrille().getAlignementActif().getCaseLibreValeurMax();
	}
		
	/**
	 * Choisi la case permettant d�avoir un �cart maximal entre la valeur de cette case et celle 
	 * dont la valeur est la plus �lev�e au prochain coup
	 * @category Methods
	 * @category Strategy
	 * @param p {@link Partie} sur laquelle travaille l'ordinateur
	 * @return {@link Case} Retourne la case choisie par l''ordinateur
	 */
	public Case strategieChampion(Partie p) {
		
		//Alignement actif temporaire
		AlignementCases aC_tmp = new AlignementCases();
		
		int ori = p.getLaGrille().getAlignementActif().getOrientation().getOri();
		
		//Case temporaire
		Case choix = new Case(Integer.MIN_VALUE, this, new Position());
		
		//Parcourt des cases libres
		for(Case c : p.getLaGrille().getAlignementActif().getCasesLibres()) {
			//On renverse chacune des cases pour creer un nouvel alignement actif temporaire
			aC_tmp = p.getLaGrille().renverserAlignCases(p.getLaGrille().getAlignementActif(), 
					new Coup(this, c.getPosition(), 
					new Orientation((ori == 1) ? 0 : 1 )));
			//On fait la difference entre les valeurs des cases libres et celle des nouveaux alignements
			//On retient celle dont la différence est maximale
			for(Case _c : aC_tmp.getCasesLibres())
				if(c.getValeur() - _c.getValeur() > choix.getValeur())
					choix.clone(c);				
		}		
		
		return choix;
	}
		
	/**
	 * TODO : code la strategie de niveau 4
	 * @category Methods
	 * @category Strategy
	 * @param aC
	 * @return
	 */
	public Case strategieGodlike(Partie p) {
		
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof JoueurOrdinateur)) return false;
		
		JoueurOrdinateur joTemp = (JoueurOrdinateur) obj;
		
		if( super.equals(joTemp) 
				&& this.getNiveau() == joTemp.getNiveau()
				&& this.getTypeDeJeu().equals(joTemp.getTypeDeJeu()) )
			return true;
		
		return false;
	}
}
