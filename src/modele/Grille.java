package modele;


/**
 * <b>Grille est la classe centrale sur laquelle se joue une partie</b>
 * <p>
 * Une grille est caracterisee par les attributs suivants :
 * <ul>
 * <li>Un alignement de cases contenant les cases jouables ; </li>
 * <li>Un tableau de cases contenant toutes les cases ; </li>
 * </ul>
 * </p>
 * 
 * @see AlignementCases
 * @see Partie
 * @author Simon Renoult aKa G4llic4
 * 
 */
public class Grille {

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//

	private AlignementCases alignementActif;
	private Case [] lesCases;
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	/**
	 * Constructeur sans parametre
	 * @function Appel le constructeur parametre {@link Grille} avec pour param�tre 5 		
	 * @category Constructors
	 */
		public Grille() {
		this(5);
	}
	
	/**
	 * @category Constructors
	 * @param ca 
	 * 			tableau de cases	
	 */
	public Grille(Case [] ca) {
		alignementActif = null;
		lesCases = ca;
	}
	
	/**
	 * @category Constructors
	 * @param size 
	 * 			taille du tableau a instancier
	 */
	public Grille(int size) {
		alignementActif = new AlignementCases();
		Case [] tmp = new Case[size*size];
		this.setLesCases(tmp);
		creerCases(this.lesCases);
	}
		
	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	/**
	 * @category Getters
	 * @return 
	 * 			Alignement de cases de l'objet courant
	 */
	public AlignementCases getAlignementActif() {
		return alignementActif;
	}

	/**
	 * @category Getters
	 * @return 
	 * 			Tableau de cases de l'objet courant
	 */
	public Case[] getLesCases() {
		return lesCases;
	}

	/**
	 * @category Getters
	 * @return 
	 * 			Taille du tableau de l'objet courant 
	 */
	public int getSize() {
		return this.lesCases.length;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	/**
	 * @category Setters
	 * @param alignementActif 
	 * 			alignement de cases en cours
	 */
	public void setAlignementActif(AlignementCases alignementActif) {
		this.alignementActif = alignementActif;
	}
	
	/**
	 * @category Setters
	 * @param lesCases 
	 * 			tableau de cases
	 */
	public void setLesCases(Case[] lesCases) {
		this.lesCases = lesCases;
	}
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		

	//-----------ALIGNEMENT-----------//
	/**
	 * <p>
	 * Methode permettant de renverser un alignement de cases selon un precedent
	 * aligne de cases ainsi qu'un coup.
	 * </p>
	 * 
	 * @category Methods
	 * @category Alignement
	 * 
	 * @param aC 
	 * 			{@link AlignementCases} depuis lequel renverser l'alignement
	 * @param c 
	 * 			{@link Coup} provoquant le renversement
	 * @return 
	 * 			{@link AlignementCases} contenant le resultat du renversement
	 */
	public AlignementCases renverserAlignCases(AlignementCases aC, Coup c) {
		AlignementCases aC_tmp = new AlignementCases();
		
		//Si l'orientation est horizontale
		if(aC.getOrientation().getOri() == 1) {
			//On definie l'orientation de maniere inverse a la precedente
			aC_tmp.setOrientation(new Orientation(0));
			//On initaliser la position a celle en ordonnee
			aC_tmp.setPosition(c.getPositionCase().getPos_y());
			for(Case tmp : this.getLesCases())
				if(tmp.getPosition().getPos_y() == c.getPositionCase().getPos_y())
					aC_tmp.getLesCases().add(tmp);
		}
		//Si l'orientation est horizontale
		else if(aC.getOrientation().getOri() == 0) {
			//On definie l'orientation de maniere inverse a la precedente
			aC_tmp.setOrientation(new Orientation(1));
			//On initaliser la position a celle en ordonnee
			aC_tmp.setPosition(c.getPositionCase().getPos_x());
			for(Case tmp : this.getLesCases())
				if(tmp.getPosition().getPos_x() == c.getPositionCase().getPos_x())
					aC_tmp.getLesCases().add(tmp);
		}
		else System.out.println();
		
		return aC_tmp;
	}
	
	/**
	 * <p>
	 * Methode de renversement de l'alignement actif de la classe.
	 * <p>
	 * 
	 * @category Methods
	 * @category Alignement
	 * @param c 
	 * 		Coup provoquant l'inversion de l'alignement actif
	 */
	public void renverserAlignActif(Coup c) {
		//Verification de l'alignement actif puis inversion de ce dernier
		if(c.getOrientation().getOri() == 1)
			//On redefinie la position de l'alignement selon l'abscisse ou l'ordonne
			setAlignActif(new Orientation(0), c.getPositionCase().getPos_y());
		if(c.getOrientation().getOri() == 0)	
			setAlignActif(new Orientation(1), c.getPositionCase().getPos_x());
		
	}
	
	
	/**
	 * @category Methods
	 * @category Alignement
	 * @param ori
	 * 			{@link Orientation} nouvellement prise par l'alignementActif.
	 * 
	 * param num
	 * 			{@link Integer} definissant la position de l'alignementActif.
	 */
	public void setAlignActif(Orientation ori, int num) {
		//On vide l'alignementActif
		alignementActif.getLesCases().clear();
		
		//On redefinie l'orientation et la position de l'alignementActif
		alignementActif.setOrientation(ori);
		alignementActif.setPosition(num);
		
		for(int i = 0 ; i < getSize() ; i++) {
			if(ori.getOri() == 1 && lesCases[i].getPosition().getPos_x() == num)
				alignementActif.getLesCases().add(lesCases[i]);
			else if(ori.getOri() == 0 && lesCases[i].getPosition().getPos_y() == num)
				alignementActif.getLesCases().add(lesCases[i]);
		}
	}
	
	//-----------CREER_CASES----------//

	/**
	 * @category Methods
	 * @category CreerCases
	 * 
	 * @param tmp
	 * 			Tableau de {@link Case} instancie
	 * 
	 * @see setPosXY
	 */
	private void creerCases(Case [] tmp) {		
		for(int i = 0 ; i < tmp.length ; i++)
			this.lesCases[i] = new Case();
		
		setPosXY();
	}	
		
	/**
	 * @category Methods
	 * @category CreerCases 
	 * 
	 * @see creerCases
	 */
	private void setPosXY() {
		int j = 1;
		for(int i = 0 ; i < getSize() ; i++) {
			lesCases[i].getPosition().setPos_x(j);
			lesCases[i].getPosition().setPos_y(i/(int)Math.sqrt(getSize())+1);
			j++;
			if(j == Math.sqrt(getSize())+1) j = 1;			
		}
	}
	
	//-----------AFFICHAGES----------//

	/**
	 * @category Methods
	 * @category System
	 * @category Affichage
	 */
	public String toString() {
		return 	lesCases.toString()+" ; "+
				alignementActif.toString()+" ; ";
	}
	
	/**
	 * @category Affichage
	 * <p>
	 * Methode d'affichage d'une {@link Grille}
	 * </p>
	 *FIXME : a deplacer dans le mode texte.
	 */
	public void afficherLesCases() {
		int size = (int) Math.sqrt(getSize());
		
		//------IndicesHorizontaux------//
		for(int i = 0 ; i < size ; i++) {
			if(i+1 < 10) {
				if(i == 0) System.out.print("\t   " +(i+1)+ "\t ");
				else System.out.print((i+1)+"     ");
			}
			else if(i+1 > 9) {
				System.out.print((i+1)+"    ");
			}
		}
		//------------------------------//
				
		//-- Premiere ligne de +--- --//
		for(int i = 0 ; i < size; i++) {
			if(i == 0) System.out.print("\n\t+-----");
			else System.out.print("+-----");
		}
		System.out.println("+");
		//---------------36------------//

		
		for(int i = 0 ; i < getSize() ; i ++) {
			
			if(i%(size) == 0 && i != 0) {
				//-Dernier | de la ligne-//
				System.out.print("|");
				System.out.println();
				//-----------------------//

				//-Espacement des lignes-//
				for(int j = 0 ; j < size ; j++) {
					if(j == 0) System.out.print("\t+-----");
					else System.out.print("+-----");
				}
				System.out.println("+");
				//-----------------------//
			}
			
			//-Affichage de la valeur de la case-//
			//Nombres < 10
			if(this.lesCases[i].getValeur() < 10) {
				//1ers chiffres de la ligne
				if(i%size == 0) {
					if(lesCases[i].getJouePar() == null)
						System.out.print(
							"    " + (int)(1 + i/size)+ "\t|"
							+"  "+this.lesCases[i].afficherValeur()
							+"  ");
					else
						System.out.print(
							"    " + (int)(1 + i/size)+ "\t|"
							+"  "+" "
							+"  ");
				}
				//Autres chiffres
				else {
					if(lesCases[i].getJouePar() == null)
						System.out.print(
							"| "+" "
							+this.lesCases[i].afficherValeur()
							+ "  ");
					else
						System.out.print(
							"| "+" "
							+" "
							+ "  ");
				}
			}
			//Nombres >10
			else {
				//1ers chiffres de la ligne
				if(i%size == 0) {
					if(lesCases[i].getJouePar() == null)
						System.out.print(
							"    " + (int)(1 + i/size)+ "\t|"
							+" "+this.lesCases[i].afficherValeur()
							+"  ");
					else
						System.out.print(
							"    " + (int)(1 + i/size)+ "\t|"
							+" "+"  "
							+"  ");
				}
				//Autres chiffres
				else {
					if(lesCases[i].getJouePar() == null)
						System.out.print("| " +this.lesCases[i].afficherValeur()+ "  ");
					else
						System.out.print("| " +"  "+ "  ");
					
				}
			}
			//-----------------------------------//
		}
		
		//-Dernier | -//
		System.out.println("|");
		//-----------//
		
		//Derniere ligne de +---+ //
		for(int i = 0 ; i < size; i++) {
			if(i == 0) System.out.print("\t+-----");
			else System.out.print("+-----");
		}	
		System.out.println("+");
		//------------------------//
	}
	
		
	//--------INITIALISATIONS--------//

	/**
	 * @category Methods
	 * @category Initialisations
	 * 
	 * @see creerCases
	 */
	public void initAleatoire() {
		//Instancie de nouvelles cases
		creerCases(this.lesCases);
		
		//Parcourt toutes les cases du tableau et leur affecte une valeur entre 0 et 9.
		for(int i = 0 ; i < getSize() ; i++) {
				int rand = (int) ((Math.random()*10)-1);
				this.lesCases[i].setValeur(rand);			
		}
	}
	

	/**
	 * @category Methods
	 * @category Initialisations
	 * 
	 * @see creerCases
	 * @see repartirValeursParQuartiers
	 * @see melangerLesCases
	 */
	public void initQuartsAleatoirs() {
		//Instancie de nouvelles cases
		creerCases(this.lesCases);

		//Repartie les valeurs par quartier
		repartirValeursParQuartiers(getSize(), 1, this.lesCases);
		
		//Melange les cases
		melangerLesCases();				
	}
	

	/**
	 * @category Methods
	 * @category Initialisations
	 * 
	 * @param tabVal
	 * 			Tableau d'entier contenant les valeurs et leur nombre d'occurence(s)
	 * 
	 * @see creerCases
	 * @see repartirDepuisTableau
	 * @see repartirReste_Zero
	 * @see melangerLesCases
	 */
	public void initDepuisTableau_Zero(int [][] tabVal) {
		//Instancie de nouvelles cases
		creerCases(this.lesCases);
		
		//Repartie les valeurs selon leur nombre d'occurences
		repartirDepuisTableau(tabVal);
		
		//Initialise les valeurs non initalisee precedemment a 0
		repartirReste_Zero();
		
		//Melange les cases
		melangerLesCases();
	}
	
	
	/**
	 * @category Methods
	 * @category Initialisations
	 * 
	 * @param tabVal 
	 * 			Tableau d'entier contenant des valeurs et le nombre d'occurence(s) correspondant.
	 *
	 *@see creerCases
	 *@see repartirReste_Quartier
	 *@see melangerLesCases
	 *
	 */

	public void initDepuisTableau_Quartier(int [][] tabVal) {
		
		//Instancie de nouvelles cases
		creerCases(this.lesCases);

		Case [] tabTemp = repartirReste_Quartier(tabVal);
		
		//----Concaténation des deux tableaux----//
			
			//Indice = Taille totale - taille du tableau du reste r�parti par quarts
			//cad, indice à partir duquel concaténer les deux tableaux
			int indice = getSize() - tabTemp.length;
			for(int i = indice ; i < getSize() ; i++) 
				this.lesCases[i].setValeur(tabTemp[i - indice].getValeur());
			
		//Melange des cases
		melangerLesCases();
	}
	

	//------------MELANGES------------//	
	
	/**
	 * Methode de melange des cases.
	 * Procede a 1000 inversions de cases aleatoires.
	 * @category Methods
	 * @category Melanges
	 */
	public void melangerLesCases() {
		Case tmp = new Case();
		
		for(int cpt = 0 ; cpt < 1000 ; cpt ++)
			for(int i = 0 ; i < this.getSize() ; i++) {
					int rand = (int) (Math.random()*this.getSize());
					tmp.setValeur(this.lesCases[rand].getValeur());					
					this.lesCases[rand].setValeur(this.lesCases[i].getValeur());
					this.lesCases[i].setValeur(tmp.getValeur());
			}			
	}
	
	
	/**
	 * @category Methods
	 * @category Melanges
	 */
	public void melangerUnTableau(int [] tab) {
		int tmp = 0;
				
		for(int cpt = 0 ; cpt < 1000 ; cpt ++)
			for(int i = 0 ; i < tab.length ; i++) {
					int rand = (int) (Math.random()*tab.length);
					tmp = tab[rand];					
					tab[rand] = tab[i];
					tab[i]= tmp;
			}			
	}
	
		
	//----------REPARTITIONS----------//	

	/**
	 * Repartition recursive du tableau passe en parametre par quartiers aleatoires
	 * 
	 * @category Methods
	 * @category Repartitions
	 * 
	 * @param size 
	 * 			Taille du tableau
	 * @param val 
	 * 			Valeur du quartier en cours de repartition
	 * @param temp 
	 * 			Tableau sur lequel travaille l'algorithme
	 * 
	 * @return {@link Boolean}
	 * 			Condition d'arret de la boucle de recursion. Vrai si il reste une valeur
	 * 			non repartie, faux dans le cas contraire 
	 */
	private boolean repartirValeursParQuartiers(float size, int val, Case[] temp) {
		int cpt = 0;
		
		for (int i = 0 ; i < temp.length ; i++) 
			while(temp[i].getValeur() == -1 && cpt < (0.25)*size ) {
				temp[i].setValeur(val);
				cpt++;
			}			
				
		val++;
		size = (0.75f)*size;

		if(temp[temp.length-1].getValeur() == -1 )
			repartirValeursParQuartiers(size, val, temp);
		
		return true;
	}
	

	/**
	 * @category Methods
	 * @category Repartitions
	 * 
	 * @param tabVal 
	 * 			Tableau indiquant la valeur et le nombre d'occurence a inserer dans la grille finale
	 *
	 * @return {@link Integer}
	 * 			Retourne un tableau d'entiers reparti selon le tableau passe en parametre
	 */
	private int [] repartirValeursParIndice(int [][] tabVal) {
		int a = 0, 
			tailleTemp = 0;
		
		//Recuperation de la taille du tableau des valeurs
		for(int i = 0 ; i < tabVal.length ; i++)
			tailleTemp += tabVal[i][1];
			
		//Creation du tableau temporaire de la taille du nombre de valeurs
		int[] tabValReparties = new int[tailleTemp];

		//Affectation de toutes les valeurs xi en fonction de yi
		for(int i = 0 ; i < tabVal.length ; i++)
			while(tabVal[i][1] > 0) {
				tabValReparties[a] = tabVal[i][0];
				a++;
				tabVal[i][1]--;				
			}

		//Retourne un tableau contenant les valeurs contenues dans tabVal
		return tabValReparties;
	}
	
	
	/**
	 * Insere les valeurs passees en parametre dans la grille.
	 * 
	 * @category Methods
	 * @category Repartitions
	 * 
	 * @param tabVal 
	 * 			Tableau indiquant la valeur et le nombre d'occurence a inserer dans la grille finale
	 *
	 * @return {@link Integer}
	 * 			Retourne la taille du tableau reparti depuis tabVal
	 */
	private int repartirDepuisTableau(int [][] tabVal) {
		int tabTemp[] = repartirValeursParIndice(tabVal);

		//Cas ou le nombre de valeurs du tableau de valeurs est plus grand que la grille
		if(tabTemp.length > this.getSize())
			for(int i = 0 ; i < this.getSize() ; i++) 
				this.lesCases[i].setValeur(tabTemp[i]);
		//Cas ou a grille est plus grande que le nomnbre de valeurs
		else
			for(int i = 0 ; i < tabTemp.length ; i++) 
				this.lesCases[i].setValeur(tabTemp[i]);
		
		return tabTemp.length;
	}
	
	
	/**
	 * <p>
	 * Methode repartissant les cases non initialisees a 0.
	 * </p>
	 * 
	 * @category Methods
	 * @category Repartitions
	 */
	private void repartirReste_Zero() {		
		for(int i = 0 ; i < getSize() ; i++)
			if(lesCases[i].getValeur() == -1) lesCases[i].setValeur(0);		
	}
	

	/**
	 * <p>
	 * Methode repartissant les cases non initialisees par quartier.
	 * </p>
	 * 
	 * @category Methods
	 * @category Repartitions
	 * 
	 * @param tabVal 
	 * 			Tableau indiquant la valeur et le nombre d'occurence a inserer dans la grille finale
	 *
	 * @return {@link Case}
	 * 			Retourne un tableau de case reparti selon le tableau passe en parametre
	 */
	private Case [] repartirReste_Quartier(int [][] tabVal) {
		int s = repartirDepuisTableau(tabVal);

		//Création d'un tableau de la taille des valeurs non attribuées
		float size = this.getSize() - s;
		Case [] tabTemp = new Case[(int) size];			
		for(int i = 0 ; i < tabTemp.length ; i++) tabTemp[i] = new Case();

		//Repartition par quartier du tableau aux valeurs non attribuées
		//Premier terme : 1
		repartirValeursParQuartiers(size, 1,  tabTemp);
		
		return tabTemp;
	}
}