package modeGraphique;

import modele.*;

import jtuto.gui.Constantes;
import jtuto.gui.Couleur;
import jtuto.gui.FenetreJeu;
import jtuto.gui.GestionnaireEvenements;

public class Portail extends FenetreJeu implements GestionnaireEvenements {

	// -------------------------------------------------------------//
	// -------------------------ATTRIBUTES--------------------------//
	// -------------------------------------------------------------//

	private static final long serialVersionUID = 1L;
	private Partie p = new Partie();
	private Joueur j [] = new Joueur[2];
	private Position posCoup;
	private int i = 0;

	// -------------------------------------------------------------//
	// ------------------------CONSTRUCTORS-------------------------//
	// -------------------------------------------------------------//

	public Portail(int sizeX, int sizeY) {

		super("portail", 200, 200, "Think Ahead", sizeX, sizeY, Couleur.NOIR);

		menuPortail();

		fixerGestionnaireEvenements(this);
	}

	// -------------------------------------------------------------//
	// -------------------------EVENEMENTS--------------------------//
	// -------------------------------------------------------------//

	@Override
	public void gereEvenement(String nomComposant, int typeEvenement,
			String val, int xSouris, int ySouris) {
		

		// -------------PORTAIL------------- //
		if (nomComposant.equals("jouer"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				String tmp = boiteDeSaisie("Quel est ton nom ?", "Pseudo");
				j[0] = new JoueurHumain(tmp);
				effacerEcranComplet();
				menuGrille();
			}
		// TODO : charger partie
		if (nomComposant.equals("charger"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {}
		// TODO : aide/regles
		if (nomComposant.equals("aide"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {}
		if (nomComposant.equals("quitter"))
			if (typeEvenement == Constantes.CLICK_BOUTON)
				System.exit(0);
		// ---------------------------------- //

		
		// -------------GRILLE--------------- //
		if (nomComposant.equals("aleatoire"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				String str = lireTexteComposant("grille_taille_saisie");
				p = new Partie(new Grille(Integer.parseInt(str)));
				p.getLaGrille().initAleatoire();
				
				effacerEcranComplet();
				menuAdversaire();
			}
		if (nomComposant.equals("quartier"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				String str = this.lireTexteComposant("grille_taille_saisie");
				p = new Partie(new Grille(Integer.parseInt(str)));
				p.getLaGrille().initQuartsAleatoirs();
				
				effacerEcranComplet();
				menuAdversaire();
			}
		if (nomComposant.equals("tableau"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				// TODO : saisie val depuis tableau
				String str = this.lireTexteComposant("grille_taille_saisie");
				p = new Partie(new Grille(Integer.parseInt(str)));
				p.getLaGrille().initDepuisTableau_Quartier(null);
				
				effacerEcranComplet();
				menuAdversaire();
			}
		// ----------------------------------//

		
		// -----------ADVERSAIRE-------------//

		// ------General------//
		if (nomComposant.equals("ordi"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				effacerEcranComplet();
				menuOrdinateur();
				
				effacerEcranComplet();
				dimensionner(100 * (int) (Math.sqrt(p.getLaGrille().getSize())),
						70 * ((int) Math.sqrt(p.getLaGrille().getSize())));
				menuJeu();
			}
		if (nomComposant.equals("hum"))
			if (typeEvenement == Constantes.CLICK_BOUTON){
				String tmp = boiteDeSaisie("Quel est le nom de ton adversaire ?", "Pseudo");
				j[1] = new JoueurHumain(tmp);
				
				effacerEcranComplet();
				dimensionner(100 * (int) (Math.sqrt(p.getLaGrille().getSize())),
						70 * ((int) Math.sqrt(p.getLaGrille().getSize())));
				menuJeu();
			}				
		if (nomComposant.equals("ordi_ordi"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				String tmp = boiteDeSaisie("Quel est le nom de ton adversaire ?", "Pseudo");
				j[1] = new JoueurHumain(tmp);
				
				effacerEcranComplet();
				dimensionner(100 * (int) (Math.sqrt(p.getLaGrille().getSize())),
						70 * ((int) Math.sqrt(p.getLaGrille().getSize())));
				menuJeu();
			}
		if (nomComposant.equals("retour_j"))
			if (typeEvenement == Constantes.CLICK_BOUTON) {
				effacerEcranComplet();
				menuGrille();	
				
				effacerEcranComplet();
				dimensionner(100 * (int) (Math.sqrt(p.getLaGrille().getSize())),
						70 * ((int) Math.sqrt(p.getLaGrille().getSize())));
				menuJeu();
			}
		// ------------------//

		// ----Ordinateur---//
		if (nomComposant.equals("trouffion"))
			if (typeEvenement == Constantes.CLICK_BOUTON)
				j[1] = new JoueurOrdinateur(1);
		if (nomComposant.equals("fantassin"))
			if (typeEvenement == Constantes.CLICK_BOUTON)
				j[1] = new JoueurOrdinateur(2);
		if (nomComposant.equals("champion"))
			if (typeEvenement == Constantes.CLICK_BOUTON)
				j[1] = new JoueurOrdinateur(3);
		/*
		 * if (nomComposant.equals("godlike")) if (typeEvenement ==
		 * Constantes.CLICK_BOUTON) p.getJoueurs()[1] = new JoueurOrdinateur(4);
		 */
		// ---------------- //

		// -------JEU------ //
		for(Case c : p.getLaGrille().getAlignementActif().getCasesLibres() )
			if(nomComposant.equals("b"+TbGr.itos(c.getPosition().getPos_x())+ TbGr.itos(c.getPosition().getPos_y())))
				if(typeEvenement == Constantes.CLICK_BOUTON) {
					
					posCoup = c.getPosition();
					System.out.println(posCoup);
					tourJ(i);
					i++;
					
					tourJ(i);
					i++;	
					
				}		
	}


	// ------------------------------------------------------------- //
	// -----------------------------MENUS--------------------------- //
	// ------------------------------------------------------------- //

	// ------------PORTAIL------------ //
	public void menuPortail() {
		TbGr.ajouterBouton(this, "jouer", "Jouer !", (2 * TbGr.szX / 3) - 3
				* TbGr.pas, TbGr.pas);
		TbGr.ajouterBouton(this, "charger", "Charger.", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, 21 * TbGr.pas);
		TbGr.ajouterBouton(this, "aide", "Aidez-moi...", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, 41 * TbGr.pas);
		TbGr.ajouterBouton(this, "quitter", "Quitter", TbGr.pas, 61 * TbGr.pas);
	}
	// ------------------------------- //

	
	// ------------GRILLE------------- //
	public void menuGrille() {

		ajouterZoneDeTexte("grille_taille", TbGr.pas, 0, 100 * TbGr.pas,
				13 * TbGr.pas, "Quelle sera la taille du champs de bataille ?",
				Couleur.NOIR, Couleur.BLANC);
		ajouterZoneDeSaisie("grille_taille_saisie", 95 * TbGr.pas,
				3 * TbGr.pas, 20 * TbGr.pas, 8 * TbGr.pas, Couleur.BLANC,
				Couleur.BLEU);
		ajouterZoneDeTexte("grille_type", TbGr.pas, 10 * TbGr.pas, 2 * TbGr.szX
				/ 3 - 3 * TbGr.pas, 81 * TbGr.pas,
				"Comment initaliser la grille ?", Couleur.NOIR, Couleur.BLANC);

		TbGr.ajouterBouton(this, "aleatoire", "Aleatoirement", 2 * TbGr.szX / 3
				- 3 * TbGr.pas, 21 * TbGr.pas);
		TbGr.ajouterBouton(this, "quartier", "Par Quartiers", 2 * TbGr.szX / 3
				- 3 * TbGr.pas, 41 * TbGr.pas);
		TbGr.ajouterBouton(this, "tableau", "Aleatoirement", 2 * TbGr.szX / 3
				- 3 * TbGr.pas, 61 * TbGr.pas);
	}
	// ------------------------------- //

	
	// ----------ADVERSAIRE----------- //
	public void menuAdversaire() {
		ajouterZoneDeTexte("titre_adv", TbGr.pas, 10 * TbGr.pas,
				100 * TbGr.pas, 13 * TbGr.pas,
				"Quel type d'adversaire desires-tu ?", Couleur.NOIR,
				Couleur.BLANC);

		TbGr.ajouterBouton(this, "ordi", "Ordinateur", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, TbGr.pas);
		TbGr.ajouterBouton(this, "hum", "Humain", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, 21 * TbGr.pas);
		TbGr.ajouterBouton(this, "ordi_ordi", "Machine vs Machine", 2
				* TbGr.szX / 3 - 3 * TbGr.pas, 41 * TbGr.pas);
		TbGr.ajouterBouton(this, "retour_j", "<<<<", TbGr.pas, 61 * TbGr.pas);
	}
	// ------------------------------- //

	
	// -----------ORDINATEUR-----------//
	public void menuOrdinateur() {
		ajouterZoneDeTexte("titre_ordi", TbGr.pas, 10 * TbGr.pas,
				100 * TbGr.pas, 13 * TbGr.pas,
				"Quel niveau desires-tu affronter ?", Couleur.NOIR,
				Couleur.BLANC);
		TbGr.ajouterBouton(this, "trouffion", "Trouffion", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, TbGr.pas);
		TbGr.ajouterBouton(this, "fantassin", "Fantassin", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, 21 * TbGr.pas);
		TbGr.ajouterBouton(this, "champion", "Champion", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, 41 * TbGr.pas);
		TbGr.ajouterBouton(this, "godlike", "Godlike", 2 * TbGr.szX / 3 - 3
				* TbGr.pas, 61 * TbGr.pas);
	}
	// ------------------------------- //


	// --------------JEU-------------- //
	public void menuJeu() {
		p.setJoueurs(j);
		p.commencer();
		
		for (Case c : p.getLaGrille().getLesCases())
			ajouterBouton(
					"b"+TbGr.itos(c.getPosition().getPos_x())+ TbGr.itos(c.getPosition().getPos_y()), 
					c.getPosition().getPos_x() * 45, c.getPosition().getPos_y() * 45, 40, 40,
					TbGr.itos(c.getValeur()), Couleur.BLANC, Couleur.NOIR);

		ajouterZoneDeTexte(
				"scores_j1",
				TbGr.szX - 10 * TbGr.pas, 10 * TbGr.pas,
				200, 200,
				p.getJoueur(0).getNom() + " : " + TbGr.itos(p.getScore(0)),
				Couleur.BLANC,	Couleur.VERT_FONCE);
		ajouterZoneDeTexte(
				"scores_j2",
				TbGr.szX - 10 * TbGr.pas, 15 * TbGr.pas,
				200, 200,
				p.getJoueur(1).getNom() + " : "	+ TbGr.itos(p.getScore(1)),
				Couleur.BLANC,	Couleur.VERT_FONCE);
		
		setDisponbilite();
		
	}
	// ------------------------------- //
	
	public void tourJ(int i) {
		
		if((p.getLaGrille().getAlignementActif().getCasesLibres().isEmpty())) 
			boiteDeMessage("Partie terminee", "Fin!");
		
		//Cas d'une IA
		if(p.getJoueur((i%2==0)?0:1) instanceof JoueurOrdinateur) {
			JoueurOrdinateur j = (JoueurOrdinateur) p.getJoueur((i%2==0)?0:1);
			j.jouer(p);
			System.out.println("LA");
		}
		//Cas d'un joueur Humain.
		else if(p.getJoueur((i%2==0)?0:1) instanceof JoueurHumain) {			

			JoueurHumain j = (JoueurHumain) p.getJoueur((i%2==0)?0:1);
			//revenir(p, i);
			j.jouerCoup(p, posCoup);	
			System.out.println("ICI");
		}
		
		setDisponbilite();
		
	}
	
	//Mise a jour de la disponibilite des cases selon l'alignement actif
	public void setDisponbilite() {
		
		for(Case c : p.getLaGrille().getLesCases())
			fixerDisponibiliteComposant("b"
					+TbGr.itos(c.getPosition().getPos_x())
					+ TbGr.itos(c.getPosition().getPos_y()),
					true);
		
		for(Case c : p.getLaGrille().getLesCases())
			for(Case _c : p.getLaGrille().getAlignementActif().getCasesLibres())
				if(c.getPosition().getPos_x() != _c.getPosition().getPos_x() 
					&& c.getPosition().getPos_y() != _c.getPosition().getPos_y())
						fixerDisponibiliteComposant("b"
								+TbGr.itos(c.getPosition().getPos_x())
								+ TbGr.itos(c.getPosition().getPos_y()),
								false);
	}
	
	
}
