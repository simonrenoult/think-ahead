package modeTexte;

import java.util.*;

import modele.*;


public class Toolbox {

	/**
	 * Methode standard de saisie d'une chaine de caracteres.
	 * Affiche une fleche en debut de saisie.
	 * @return {@link String} renvoie la chaine saisie.
	 */
	public static String saisieStr() {		
		fleche();
		Scanner sc = new Scanner(System.in);
		String tmp = sc.nextLine();
		return tmp;		
	}
	
	/**
	 * Methode de saisie par defaut d'une chaine de caracteres.
	 * @param s {@link String} à tester
	 * @param def {@link String}, valeur par defaut à concatener dans la chaine vide
	 * @return retourne une {@link String} contenant def.
	 */
	public static String setDefault(String s, String def) {
		if(s.length() == 0)
			return def;
		
		return s;		
	}
	
	/**
	 * Méthode standard de saisie d'un entier.
	 * Affiche une fleche en debut de saisie.
	 * @exception InputMismatchException Vérifie que la saisie est bien celle d'un entier
	 * @return {@link Integer} renvoie la valeur saisie.
	 */
	public static int saisieInt() {		
		
		boolean ok = true;
		int tmp = 0;
		
		do {
			try {
				ok = true;
				fleche();
				Scanner sc = new Scanner(System.in);
				tmp = sc.nextInt();
			}
			catch (InputMismatchException e){
				System.out.println("Euh... C'est pas une r�ponse �a !");
				ok = false;
			}
		}while(!ok);
		
		return tmp;
	
	}
	
	/**
	 * Methode d'affichage d'une liste de choix préfixé par un indice
	 * @param ht Liste contenant un indice et une chaine de caractere correspondant
	 */
	public static void multichoix(Hashtable<Integer, String> ht) {
		for(int i = 1 ; i <= ht.size() ; i++)
			System.out.println("| " +(i)+ " | " +ht.get(i) );
	}
	
	/**
	 * Methode de saisie d'un entier depuis une liste de choix.
	 * Vérifie que la saisie se trouve bien dans la liste proposee.
	 * @see multichoix
	 * @param ht Liste contenant un indice et une chaine de caractere correspondant.
	 * @return {@link Integer} renvoie la valeur saisie.
	 */
	public static int saisieMultichoix(Hashtable<Integer, String> ht) {
		Integer c;
		do {
			multichoix(ht);
			c = saisieInt();			
			if(!ht.containsKey(c))
				System.out.println("Erreur à la saisie, recommence !\n");
		}while(!ht.containsKey(c));
		
		ht.clear();
		
		return c;
	}
	
	/**
	 * Affichage d'un entier de debug
	 * @param val entier à afficher
	 */
	public static void afficher(int val) {
		Integer tmp = (Integer) val;
		System.out.println("##DEBUG_VAL : " +tmp+ "##");
	}
	
	/**
	 * Affichage d'un tableau une dimension d'entier de debug
	 * @param tmp tableau à afficher
	 */
	public void afficher(int [] tmp) {
		System.out.println("###DEB_DEBUG_TAB_1D###");		
		for (int i = 0 ; i < tmp.length ; i++) {
				Integer val = (Integer) tmp[i];
				System.out.print(val);
			}
		System.out.println("\n###FIN_DEBUG_TAB_1D###\n");		
	}
	
	/**
	 * Affichage d'un tableau deux dimensions d'entier de debug
	 * @param tmp tableau à afficher
	 */
	public static void afficher(int [][] tmp) {
		System.out.println("###DEB_DEBUG_TAB_2D###");		
		for(int i[] : tmp)
			for(int ibis : i) {
				Integer val = ibis ;
				System.out.print(val);
			}
		System.out.println("\n###FIN_DEBUG_TAB_2D###\n");		
	}

	/**
	 * Affichage des valeurs d'un tableau une dimension de cases de debug
	 * @param tmp tableau à afficher
	 */
	public static void afficher(Case [] tmp) {
		System.out.println("###DEB_DEBUG_TAB_Cases###");		
		for (int i = 0 ; i < tmp.length ; i++) {
				Integer val = (Integer) tmp[i].getValeur();
				System.out.print(val);
			}
		System.out.println("\n###FIN_DEBUG_TAB_Cases###\n");		
	}
	
	/**
	 * Affichage d'une chaine de debug
	 * @param tmp chaine de caractères à afficher.
	 */
	public static void afficher(String tmp){
		System.out.println("##DEBUG_STRING : " +tmp+ "##");
	}
	
	/**
	 * Outil d'agrément graphique du modeTexte
	 */
	public static void fleche() { System.out.print("--> "); }
	
	/**
	 * Saisie d'un tableau deux dimensions d'entiers selon une valeur ([0]) et son nombre d'occurence ([1])
	 * @param tmp tableau de valeurs/occurences
	 * @return un tableau deux dimensions de valeurs triées
	 */
	public static int [][] saisieTabVal(int [][] tmp) {
		int	val = 0, occ = 0;		
		String continuer = "Oui", forcer = "Oui";
		
		//-------SAISIE_VALEUR-------//
		System.out.println("\n[ * ] Saisir une valeur : ");
		val = Toolbox.saisieInt();
		//---------------------------//
		
		//-------VERIF_PRESENCE------//
		if(contenir(tmp, val))
			do {
				System.out.println("Valeur déjà présente, écraser ? (o/N)");
				forcer = Toolbox.saisieStr();
				forcer = setDefault(forcer, "Non");	
			}while(forcer.equals("Oui") && forcer.equals("Non"));
		//---------------------------//	
		
		//---------SAISIE_OCC--------//
		if(continuer.equals("Oui") && forcer.equals("Oui")) {
			System.out.println("Saisir son nombre d'occurrence dans la grille : ");
			occ = Toolbox.saisieInt();
			
			tmp[tmp.length-1][0] = val; 
			tmp[tmp.length-1][1] = occ; 
		}		
		//---------------------------//	

		//----------BOUCLER----------//
		System.out.println("Voulez-vous continuer à saisir des valeurs ? (O/n)");
		continuer = Toolbox.saisieStr();
		continuer = setDefault(continuer, "Oui");
		
	
		//
		if(continuer.equals("Oui") && forcer.equals("Oui")) {
			int [][] tmp2 = new int [tmp.length+1][2];
			for(int i = 0 ; i < tmp.length ; i++)
				for(int j = 0 ; j < 2 ; j++)
					tmp2[i][j] = tmp[i][j];
			
			tmp = tmp2;
			saisieTabVal(tmp);
		}
		
		//Sans "forcer" donc ecraser la valeur et son nombre d'occurence
		else if(continuer.equals("Oui") && forcer.equals("Non")) 
			saisieTabVal(tmp);
		//---------------------------//	

		else return tmp;

		return tmp;
	}	

	/**
	 * Methode permettant de savoir si une valeur est bien contenue dans un tableau d'occurence
	 * @param tab tableau deux dimensions d'entiers
	 * @param val valeur à vérifier l'existence
	 * @return Retourne un booléen <code>true</code> si la valeur est trouvée <code>false</code> dans le cas contraire
	 */
	public static boolean contenir(int [][] tab, int val) {
		for(int i = 0 ; i < tab.length ; i++)
			if(val == tab[i][0]) return true;

		return false;
	}
	
	
	public static void terminer(Partie p) {
		System.out.println();
		System.out.println("Partie terminee...");
		if(p.getScore(0)>p.getScore(1))
			System.out.println(p.getJoueur(0).getNom()+" a gagne !");
		else if(p.getScore(0)==p.getScore(1))
			System.out.println("Arf... Egalite !");
		else
			System.out.println(p.getJoueur(1).getNom()+" a gagne !");

		System.out.println("\n\tScores");
		System.out.println("\t" +p.getJoueur(0).getNom()+ " : " +p.getScore(0));
		System.out.println("\t" +p.getJoueur(1).getNom()+ " : " +p.getScore(1));
		
	}
	
	
}
