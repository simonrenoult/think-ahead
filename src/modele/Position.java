package modele;

/**
 * <b>Position est la classe representant une position dans la grille</b>
 * <p>
 * Une position est caracterisee par les attributs suivants :
 * <ul>
 * <li>Une abscisse ; </li>
 * <li>Une ordonnee ; </li>
 * </ul>
 * </p>
 *
 * @see Coup
 * @see Case 
 * @author Simon Renoult aKa G4llic4
 * 
 */

public class Position {

	//-------------------------------------------------------------//
	//-------------------------ATTRIBUTES--------------------------//
	//-------------------------------------------------------------//

	private int	pos_x,
				pos_y;
	
	//-------------------------------------------------------------//
	//------------------------CONSTRUCTORS-------------------------//
	//-------------------------------------------------------------//

	public Position() {
		pos_x = 0;
		pos_y = 0;
	}
	
	public Position(int x, int y) {
		pos_x = x;
		pos_y = y;
	}


	//-------------------------------------------------------------//
	//---------------------------GETTERS---------------------------//
	//-------------------------------------------------------------//	

	public int getPos_x() {
		return pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}
	
	//-------------------------------------------------------------//
	//---------------------------SETTERS---------------------------//
	//-------------------------------------------------------------//

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}
		
	//-------------------------------------------------------------//
	//---------------------------METHODS---------------------------//
	//-------------------------------------------------------------//		
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Position)) return false;
		
		Position tmp = (Position) obj;
		
		return(tmp.getPos_x() == this.getPos_x() 
				&& tmp.getPos_y() == this.getPos_y());
	}
	
	@Override
	public String toString() {
		Integer posX = new Integer(getPos_x());
		Integer posY = new Integer(getPos_y());
		return 	"[ " +posX.toString()+
				" , " +posY.toString()+ " ] ; ";
	}
}
