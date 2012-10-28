package modeTexte;

import modele.*;

public class MenuHvsH {

	public MenuHvsH(Joueur [] tabJ, Partie p) {		
		saisieNom(tabJ,0);
		saisieNom(tabJ,1);
		p.setJoueurs(tabJ);			
	}
	
	public void saisieNom(Joueur [] tabJ, int val) {
		System.out.println("\n[ * ] Quel est le nom du soldat "
				+(Integer) ((val%2==0)?0+1:1+1) +" ?");
		tabJ[(val%2==0)?0:1] = new JoueurHumain(Toolbox.saisieStr());
	}

}
