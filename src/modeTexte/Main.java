package modeTexte;

import java.util.*;

import modele.Grille;
import modele.Joueur;
import modele.Partie;

public class Main {

	public static void main (String [] args) {

		choixMenu();
	}	
		
	public static void choixMenu() {
		System.out.println("## THINK AHEAD beta ##");
		System.out.println("Bienvenue dans le jeu ThinkAhead !\n");
		System.out.println("[ * ] Que voulez-vous faire ?");
		
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		int choix;	
		
		//Affichage Partie
		ht.put(1,"Je veux me battre !");
		//Affichage Charger
		ht.put(2,"Charger une partie.");
		//Affichage Aide
		ht.put(3,"J'y comprends rien a ton jeu...");
		ht.put(4, "Quitter.");

		choix = Toolbox.saisieMultichoix(ht);		
		
		switch(choix) {
		case 1 :
				System.out.println("\nBattons-nous alors !\n");
				typeAdversaire();
				break;
		case 2 :
				System.out.println("\nEn chantier, veuillez passer votre chemin !\n");
				main(null);
				//ChargerPartie cp = new ChargerPartie();
				break;
		case 3 :
				System.out.println("\nC'est balot !\n");
				main(null);
				//Regle r = new Regle();
				break;
		case 4 :
				System.exit(0);
				break;
		default :
				System.out.println("Erreur, veuillez recommencer !");
				main(null);
		}		
	}
	
	public static void typeAdversaire() {
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		int choix;
		
		System.out.println("[ * ] Quel type d'adversaire desires-tu ?");
		ht.put(1, "Une boite en ferraille.");
		ht.put(2, "Un innocent !");
		ht.put(3, "Faisons plutot combattre des machines !");
		choix = Toolbox.saisieMultichoix(ht);

		
		Joueur [] tabJ = new Joueur[2];		

		System.out.println("\n[ * ] Quelle sera la taille du champs de bataille ?");
		Grille gr = new Grille(Toolbox.saisieInt());

		System.out.println("\n[ * ]Quel type d'initialisation ?");
		choixTypeInit(gr);		
		
		//------------CreationPartie-----------//
		Partie laGuerre = new Partie(gr);
		//-------------------------------------//
		
		switch(choix) {
		case 1 :
			MenuHvsO m_hvso = new MenuHvsO(tabJ, laGuerre);
			break;
		case 2 :
			MenuHvsH m_hvsh = new MenuHvsH(tabJ, laGuerre);
			break;
		case 3 :
			MenuOvsO m_ovso = new MenuOvsO(tabJ, laGuerre);
			break;
		default:
			System.out.println("ERREUR::AffichagePartie.java::AffichagePartie(int)");
			Main.main(null);
		}
		
		Jeu j = new Jeu(laGuerre);			
	}
	
	/**
	 * Methode
	 * @param gr
	 * @return 
	 */
	public static void choixTypeInit(Grille gr) {
		int choix;
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		
		ht.put(1, "Nombres aleatoirs entre 0 et 9");
		ht.put(2, "Un quart de chaque valeur");
		ht.put(3, "Saisir les valeurs manuellement et le reste a� 0");
		ht.put(4, "Saisir les valeurs manuellement " +
					  "et le reste en quarts de chaque valeur");
		choix = Toolbox.saisieMultichoix(ht);	
		
		switch(choix) {
		case 1:	
				gr.initAleatoire();
				break;
		case 2:	
				gr.initQuartsAleatoirs();
				break;
		case 3:	
				int [][] tabVal_Zero = Toolbox.saisieTabVal(new int[1][2]);
				gr.initDepuisTableau_Zero(tabVal_Zero);
				break;
		case 4:	
				int [][] tabVal_Quartier = Toolbox.saisieTabVal(new int[1][2]);
				gr.initDepuisTableau_Quartier(tabVal_Quartier);
				break;
		default:
				System.out.println("ERREUR :: Main.java :: choixTypeInit(Grille)");
		}
	}
}