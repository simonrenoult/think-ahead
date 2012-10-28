package modele;


/**
 * <b>Orientation est la classe representant une orientation de jeu</b>
 * <p>
 * Une orientation est caracterisee par un seul attribut, ori, representant
 * l'orientation, verticale ou horizontale des cases
 * </p>
 *
 * @see Coup
 * @see AlignementCases 
 * @author Simon Renoult aKa G4llic4
 * 
 */
public class Orientation {

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//

	private int ori;
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	public Orientation() {
		ori = 0 ;
	}
	
	public Orientation(int val) {
		ori = val;
	}

	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	public int getOri() {
		return ori;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	public void setOri(int orientation) {
		this.ori = orientation;
	}
	
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		

	@Override
	public String toString() {
		if(this.ori == 0) return "Alignement horizontal ; ";
		return "Alignement vertical ; ";
	}
}
