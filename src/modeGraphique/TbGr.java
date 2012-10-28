package modeGraphique;

import jtuto.gui.Couleur;
import jtuto.gui.FenetreJeu;

public class TbGr {
	
	public static int szX = 400;
	public static int szY = 265;
	public static int pas = szX/133;
	
	public int stoi(String str) {
		boolean ok = true;
		int tmp = 0;
		do {
			try {
				tmp = Integer.parseInt(str); 
			}
			catch (NumberFormatException e){
				ok = false;
			}
		}while(!ok);
		
		return tmp;
	}

	public static void ajouterBouton(FenetreJeu fj, String id, String titre, int posX, int posY) {
		fj.ajouterBouton(id, posX, posY, 140, 50, titre, Couleur.BLANC,
				Couleur.NOIR);
	}

	public static String itos(int val) {
		Integer tmp = new Integer(val);
		return tmp.toString();
	}
}
