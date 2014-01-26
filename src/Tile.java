public class Tile {
	int i= 0;
	int i2= 0;
	boolean covered= true;
	boolean flagged= false;
	boolean hasBomb= false;
	int adjs= 0; //number of bombs adjacent to tile
	
	/**
	 * Give the tile object essential information
	 * This cannot be used as a constructor since it relies on a process which relies on the fact that the tiles have already been created
	 * @param i2 - index location (column)
	 * @param i - index location (row)
	 */
	public void init(int i2, int i) {
		this.i= i;
		this.i2= i2;
		adjs= Origin.countAdjacents(i2, i);
	}
	
	/**
	 * flags the tile when the player right clicks this tile (assuming it is still covered)
	 */
	public void flag() {
		if (covered)
			flagged= !flagged;	
	}
	
	/**
	 * Called whenever this tile needs to be revealed
	 */
	public void click() {
		if (!(flagged || Origin.gameOver)) {
			if (covered) {
				Origin.clickCount++;
				covered= false;
			}
			if (hasBomb && Origin.hasStarted)
				Origin.gameOver();
			else if (!Origin.hasStarted) {
				Origin.clearStartingClick(i2, i);
				Origin.hasStarted= true;
				Origin.neededClicks= Origin.tilesX*Origin.tilesY -Origin.countBombs();
			}			
			if (Origin.clickCount== Origin.neededClicks)
				Origin.gameWin();
			if (adjs== 0)
				Origin.clickAdjacents(i2, i);
		}
	}
}