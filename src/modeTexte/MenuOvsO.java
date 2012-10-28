package modeTexte;

import java.util.Hashtable;

import modele.Joueur;
import modele.JoueurOrdinateur;
import modele.Partie;

public class MenuOvsO {

	public MenuOvsO(Joueur [] tabJ, Partie p) {
		choixNivOrdinateur(tabJ,1);
		choixNivOrdinateur(tabJ,2);
		p.setJoueurs(tabJ);
	}
	
	public void choixNivOrdinateur(Joueur [] tabJ, int val) {
		int choix;
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		
		System.out.println("\n[ * ] Quel sera le niveau de l'adversaire "+(Integer) ((val%2 != 0)?1:2)+" ?");
		ht.put(1, "Trouffion");
		ht.put(2, "Fantassin");
		ht.put(3, "Champion");
		ht.put(4, "Godlike");
		choix = Toolbox.saisieMultichoix(ht);
		
		tabJ[((val%2 != 0)?0:1)] = new JoueurOrdinateur(choix);
		System.out.println("Voici venir..." +tabJ[(val%2 != 0)?0:1].getNom()+ " !");
	}
}
