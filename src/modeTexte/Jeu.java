package modeTexte;

import java.util.LinkedList;

import modele.*;


public class Jeu {

public Jeu(Partie laGuerre) {
		partie(laGuerre);
	}

	public void partie(Partie laGuerre) {
		//Initialisation des variables (scores et alignement)
		laGuerre.commencer();
		
		System.out.println(laGuerre.getJoueur(1).getNom() 
				+ " : \" Viens voir papa !\"");		
		
		//--------BoucleJeu--------//
		//Compteur de boucles
		int i = 0;

		do {						
			afficheScoresGrille(laGuerre);			
			tourJ(laGuerre, i);
			i++;
			
			if((laGuerre.getLaGrille().getAlignementActif().getCasesLibres().isEmpty())) break;
			
			afficheScoresGrille(laGuerre);			
			tourJ(laGuerre, i);
			i++;			
		}while(!(laGuerre.getLaGrille().getAlignementActif().getCasesLibres().isEmpty()));
		//--------------------------//
		
		//Fin du jeu : affichage des scores
		Toolbox.terminer(laGuerre);
	}
	
	/**
	 * <p>Methode de selection d'une case pour un joueur.</p>
	 * 
	 * @param laGuerre 
	 * 			{@link Partie} sur laquelle jouer un tour.
	 * @param i 
	 * 			{@link Integer} compteur de tour.
	 */
	public void tourJ(Partie laGuerre, int i) {	
		
		//Cas d'une IA
		if(laGuerre.getJoueur((i%2==0)?0:1) instanceof JoueurOrdinateur) {
			JoueurOrdinateur j = (JoueurOrdinateur) laGuerre.getJoueur((i%2==0)?0:1);
			j.jouer(laGuerre);		
		}
		//Cas d'un joueur Humain.
		else if(laGuerre.getJoueur((i%2==0)?0:1) instanceof JoueurHumain) {			

			JoueurHumain j = (JoueurHumain) laGuerre.getJoueur((i%2==0)?0:1);
			
			revenir(laGuerre, i);
			afficheCoupsJouables(laGuerre);
			Position posCoup = choixCoup(laGuerre);
			j.jouerCoup(laGuerre, posCoup);
		}
	}
	
	/**
	 * <p>Affichage du score et de la grille d'une partie.</p>
	 * @param laGuerre 
	 * 			{@link Partie} sur laquelle chercher les scores et la grille.
	 */
	public void afficheScoresGrille(Partie laGuerre) {
		//-------Grille------//
		System.out.println("\n\tChamp de bataille : \n");
		laGuerre.getLaGrille().afficherLesCases();
		//-------------------//			

		//-------Scores------//
		System.out.println("\n\tScores");
		System.out.println("\t" +laGuerre.getJoueur(0).getNom()+ " : " +laGuerre.getScore(0));
		System.out.println("\t" +laGuerre.getJoueur(1).getNom()+ " : " +laGuerre.getScore(1));
		//-------------------//
		
	}
	
	/**
	 * <p>Affichage des coups jouables pour un joueur.</p>
	 * @param laGuerre 
	 * 			{@link Partie} sur laquelle chercher les coups jouables.
	 */
	public void afficheCoupsJouables(Partie laGuerre) {
		System.out.println("\n\tVoici les soldats ennemis a portee !");
		System.out.print("\t");
		Toolbox.fleche();
		
		if(laGuerre.getLaGrille().getAlignementActif().getOrientation().getOri() == 1)
			System.out.print("Vertical (X : ");
		else
			System.out.print("Horizontal (Y : ");
		
		System.out.print(new Integer(laGuerre.getLaGrille().getAlignementActif().getPosition())+ ") : [ ");
		
		for(Case c : laGuerre.getLaGrille().getAlignementActif().getCasesLibres())
			System.out.print(c.afficherValeur()+ " ");
		
		System.out.println("]");
	}

	/**
	 * Methode de choix d'un coup. 
	 * Verifie que les coordonnees entrees se trouvent dans l'alignement de cases libres.
	 * @param laGuerre 
	 * 			{@link Partie} sur laquelle chercher le coup.
	 * 
	 * @return 
	 * 			{@link Position} 
	 */
	public Position choixCoup(Partie laGuerre) {
		Position posCoup = null;
		LinkedList<Position> posAlignActif = new LinkedList<Position>(); 
		
		//Alias
		for(Case c : laGuerre.getLaGrille().getAlignementActif().getCasesLibres())
			posAlignActif.add(c.getPosition());		
		
		do {
			System.out.println("\n[ * ] On tape ou ?");
			System.out.print("X ");
			int x = Toolbox.saisieInt();
			System.out.print("Y ");
			int y = Toolbox.saisieInt();
			
			//Creation d'un tuple des coordonnees du coup desiree 
			posCoup = new Position(x, y);
			
			//On verifie sa jouabilite (presence dans l'alignement actif)
			if( !(posAlignActif.contains(posCoup)) )
				System.out.println("Je ferai pas ca a ta place !");
		
		//On boucle tant que cette derniere condition est vraie
		}while( !(posAlignActif.contains(posCoup)) );
		
		return posCoup;
	}

	/**
	 * Methode permettant de revenir a un coup precedent.
	 * 
	 * @param laGuerre
	 * 			{@link Partie} sur laquelle travaille la methode.
	 * @param i
	 * 			{@link Integer} compteur de tours.
	 */
	public void revenir(Partie laGuerre, int i) {
		//Pour ne pas proposer de revenir au tour 1
		if(i>1) {
			
				int nbCoup = 0;
					
				do {
					//-----PROPOSITION-----//
					System.out.println("\n\tRevenir en arriere ? (o/N)");
					String revenir = Toolbox.saisieStr();
					revenir = Toolbox.setDefault(revenir, "N");
					//---------------------//
					
					//----------OUI--------//
					if(revenir.toUpperCase().equals("O")) {
						
						System.out.println("De combien de coup(s) revenir en arriere ?");
						nbCoup = Toolbox.saisieInt();
					}
					//--------------------//
					
					//Valeur de retour trop grande
					if(nbCoup > i)
						System.out.println("On n'y est pas encore !");
					//Retourner au coup de son adversaire
					else if(nbCoup%2 != 0)
						System.out.println(laGuerre.getJoueur((i%2==0)?0:1).getNom()
								+ " : Hey ! Pas touche !");
					//Si tout va bien, on peut annuler le coup
					else {
						laGuerre.annulerCoup(nbCoup, laGuerre.getJoueur((i%2==0)?0:1));
						afficheScoresGrille(laGuerre);
					}
					
				//On boucle tant que le coup n'est pas a soi ou qu'il est trop grand
				}while(nbCoup%2 != 0 || nbCoup > i);
			}
	}
}

