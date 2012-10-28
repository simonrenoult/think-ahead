package modeTexte;

import java.util.*;

import modele.*;


public class MenuHvsO {

	public MenuHvsO(Joueur [] tabJ, Partie p) {
		saisieNom(tabJ);
		choixNivOrdinateur(tabJ);		
		p.setJoueurs(tabJ);
	}
	
	public void saisieNom(Joueur [] tabJ) {
		System.out.println("\n[ * ] Quel est ton nom soldat ?");
		tabJ[0] = new JoueurHumain(Toolbox.saisieStr());
	}
	
	public void choixNivOrdinateur(Joueur [] tabJ) {
		int choix;
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
		
		System.out.println("\n[ * ] Quel sera le niveau de ton adversaire ?");
		ht.put(1, "Trouffion");
		ht.put(2, "Fantassin");
		ht.put(3, "Champion");
		ht.put(4, "Godlike");
		choix = Toolbox.saisieMultichoix(ht);
		
		tabJ[1] = new JoueurOrdinateur(choix);
		
		System.out.println("\nTres bien, si tel est ton choix, tu affronteras... "
				+tabJ[1].getNom()+ " !\n");
	}
}
