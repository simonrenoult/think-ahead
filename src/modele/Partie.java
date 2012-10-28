package modele;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.Random;

/**
 * <b>Partie est la classe maitresse du ThinkAhead.</b>
 * <p>
 * Une partie est caracterisee par les attributs suivants :
 * <ul>
 * <li>Une grille ; </li>
 * <li>Un tableau de joueurs ; </li>
 * <li> Une liste de coups ; </li>
 * <li> Un tableau des scores ; </li>
 * </ul>
 * </p>
 *
 * @see Grille 
 * @see Joueur
 * @see Coup
 * @author Simon Renoult aKa G4llic4
 * 
 */

public class Partie {

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//

	private Grille laGrille;
	private Joueur[] joueurs;
	private LinkedList<Coup> lesCoups;
	private int [] scores;
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//
	
	public Partie() {
		laGrille = new Grille();
		lesCoups = new LinkedList<Coup>();
		scores = new int[2];
		joueurs = new Joueur[2];
	}
	
	/**
	 *<p>
	 * Initialise une grille depuis le parametre g. Instancie un nouvel objet {@link LinkedList} de {@link Coup},
	 * un nouvel objet scores (tableau de deux {@link Integer}) et un nouveau tableau de deux {@link Joueur}. 
	 *</p>
	 * @category Constructors
	 * @param g
	 * 			{@link Grille} permettant une premiere saisie de {@link Grille}.
	 * 			{@link Constructor} de la classe {@link Partie}.
	 * 
	 */
	public Partie(Grille g) {
		laGrille = g;
		lesCoups = new LinkedList<Coup>();
		scores = new int[2];
		joueurs = new Joueur[2];
	}
	
	public Partie(Grille gr, Joueur[] j, LinkedList<Coup> lC, int[] sc) {
		this.laGrille = gr;
		this.joueurs = j;
		this.lesCoups = lC;
		this.scores = sc;
	}
	
	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	/**
	 * @return Retourne la grille des cases
	 */
	public Grille getLaGrille() {return laGrille;}
	
	/**
	 * @return Retourne le tableau des joueurs
	 */
	public Joueur[] getJoueurs() {return joueurs;}
	
	/**
	 * @category Getters
	 * @param val Num�ro du joueur dans le tableau 
	 * @return Retourne un joueur du tableau des joueurs
	 */
	public Joueur getJoueur(int val) {return this.joueurs[val];}
	
	public LinkedList<Coup> getLesCoups() {return lesCoups;}
	
	public int[] getScores() {return scores;}
	
	public Integer getScore(int indice) {
		Integer tmp = new Integer(this.scores[indice]);
		return tmp;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//
	
	public void setLaGrille(Grille laGrille) {this.laGrille = laGrille;}
	
	public void setJoueurs(Joueur[] joueurs) {this.joueurs = joueurs;}
	
	public void setLesCoups(LinkedList<Coup> lesCoups) {this.lesCoups = lesCoups;}

	public void setScores(int[] scores) {this.scores = scores;}
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		

	public void commencer() {
	
		//---------InitScores--------//
			scores[0] = 0;
			scores[1] = 0;
		//---------------------------//
	
		//---------InitAlignActif--------//
		int premiereOri = new Random().nextInt(2);
		int premierePos = 1+ new Random().nextInt((int) Math.sqrt(laGrille.getSize()));
		laGrille.setAlignActif(new Orientation(premiereOri), premierePos);
		//-------------------------------//
		
	}

	
	public void ajouterCoup(Coup coup) {
		this.lesCoups.addLast(coup);
	}
	
	/**
	 * 
	 * @param val
	 * 			Nombre de coup a supprimer
	 * @param j
	 * 			Joueur demandant le retour
	 * 
	 * @see Coup
	 * @see updateScore
	 */
	public void annulerCoup(int val, Joueur j) {
		//On boucle tant que le nombre de coup a supprimer n'est pas nul
		while(val > 0) {
			//On recupere le dernier coup de la liste
			Coup tmp = lesCoups.getLast();
			
			//On redefinit l'alignement actif
			if(val == 1) this.getLaGrille().setAlignActif(tmp.getOrientation(), 
					(tmp.getOrientation().getOri() == 1)? tmp.getPositionCase().getPos_x() : tmp.getPositionCase().getPos_y());
			
			for(Case c : getLaGrille().getLesCases())
				if(c.getPosition().equals(tmp.getPositionCase())) {
					//On redefinit le jouePar de la case a null
					c.setJouePar(null);
					//On met a jour les scores
					updateScore();					
				}
			
			//On supprime le dernier coup
			lesCoups.removeLast();			
			val--;
		}
	}
	
	/**
	 * <p>
	 * Mise a jour des scores par parcours de la grille
	 * </p>
	 * 			
	 */
	public void updateScore() {
		
		//Reinitalisation des scores
		scores[0] = 0;
		scores[1] = 0;
	
		//Parcours des cases
		for(Case c : this.getLaGrille().getLesCases())
			//Detection de la paternite des cases
			if(c.getJouePar() != null) {
				if( c.getJouePar().equals(this.getJoueur(0)))
					//MaJ du score
					scores[0] += c.getValeur();
				else if( c.getJouePar().equals(this.getJoueur(1)))
					scores[1] += c.getValeur();
		}
	}
	
	public void ajouterScore(Joueur j, int valeur) {
		if( j.equals(this.getJoueur(0)) && j == this.getJoueur(0))
			this.scores[0] += valeur;
		else if( j.equals(this.getJoueur(1)) && j == this.getJoueur(1))
			this.scores[1] += valeur;
		else System.err.println();
	}
	
	public String toString() {
		return 	laGrille.toString()+" ; "
				+joueurs.toString()+" ; "
				+lesCoups.toString()+" ; ";
	}
}
