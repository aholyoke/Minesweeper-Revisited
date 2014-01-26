import javax.swing.JOptionPane;

public class Origin {
	static boolean gameOver= true;
	static Tile[][] TileList;
	static int tilesX= 10;
	static int tilesY= 10;
	static GameWindow g;
	static MainMenu m;
	static Listeners l;
	static int clickCount= 0;
	static boolean hasStarted= false;
	static int neededClicks= 0;
	
	public static void main(String[] args) {
		m= new MainMenu(); //Create GUI for Main Menu
	}
	
	public static void startGame() {
		clickCount= 0; //When valid click count equals needed clicks, the game is won
		gameOver= false;
		hasStarted= false;
		
		//Get the settings stored in the sliders
		tilesX= MainMenu.WidthSlide.getValue();
		tilesY= MainMenu.HeightSlide.getValue();
		
		//JFrame window for the game
		g= new GameWindow(tilesX*55, tilesY*55);
		
		//2Dimensional array of Tiles: Warning array is empty and will need to be populated
		TileList= new Tile[tilesX][tilesY];
		
		//Mouse input object
		l = new Listeners();
		
		//==Assign Mines: Populate TileList with mines and give them bombs randomly==//
		double difficulty= (double)(MainMenu.DifficultySlide.getValue()+1)/25.0;
		for (int i= 0; i < tilesY; i++) {
			for (int i2= 0; i2 < tilesX; i2++) {
				TileList[i2][i]= new Tile();
				if (Math.random() < difficulty) {
					TileList[i2][i].hasBomb= true;
					neededClicks--;
				}
			}
		}
		
		//==Initialise Tiles: Tell them their indices and have them count their surrounding mines==//
		for (int i= 0; i < tilesY; i++) {
			for (int i2= 0; i2 < tilesX; i2++) {
				TileList[i2][i].init(i2, i);
			}
		}
	}
	
	public static int countAdjacents(int i2, int i) {
		int adj= 0;
		try {
			if (Origin.TileList[i2-1][i-1].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2][i-1].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2+1][i-1].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2-1][i].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2+1][i].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2-1][i+1].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2][i+1].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2+1][i+1].hasBomb)
				adj++;
		}catch(ArrayIndexOutOfBoundsException e) {}
		
		return adj;
	}

	public static void clickAdjacents(int i2, int i) {
		try {
			if (Origin.TileList[i2-1][i-1].covered)
				Origin.TileList[i2-1][i-1].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2][i-1].covered)
				Origin.TileList[i2][i-1].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2+1][i-1].covered)
				Origin.TileList[i2+1][i-1].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2-1][i].covered)
				Origin.TileList[i2-1][i].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2+1][i].covered)
				Origin.TileList[i2+1][i].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2-1][i+1].covered)
				Origin.TileList[i2-1][i+1].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2][i+1].covered)
				Origin.TileList[i2][i+1].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			if (Origin.TileList[i2+1][i+1].covered)
				Origin.TileList[i2+1][i+1].click();
		}catch(ArrayIndexOutOfBoundsException e) {}
		
	}

	public static void clearStartingClick(int i2, int i) {
		Origin.TileList[i2][i].hasBomb= false;
		try {
			Origin.TileList[i2-1][i-1].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2][i-1].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2+1][i-1].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2-1][i].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2+1][i].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2-1][i+1].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2][i+1].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		try {
			Origin.TileList[i2+1][i+1].hasBomb= false;
		}catch(ArrayIndexOutOfBoundsException e) {}
		recalcAdjs();
		
	}
	
	private static void recalcAdjs() {
		for (int i= 0; i < Origin.tilesY; i++)
			for (int i2= 0; i2< Origin.tilesX; i2++)
				Origin.TileList[i2][i].init(i2, i);
	}

	public static int countBombs() {
		int bombCount= 0;
		for (int i= 0; i < Origin.tilesY; i++)
			for (int i2= 0; i2 < Origin.tilesX; i2++)
				if (Origin.TileList[i2][i].hasBomb)
					bombCount++;
		return bombCount;
	}

	/**
	 * Called when bomb is hit (not for victories)
	 */
	public static void gameOver() {
		Origin.gameOver= true;
		g.p.repaint();
		JOptionPane.showMessageDialog(m, "Better luck next time...", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		g.dispose();
	}
	
	/**
	 * Called when all tiles have been revealed
	 */
	public static void gameWin() {
		gameOver= true;
		g.p.repaint();
		JOptionPane.showMessageDialog(m, "You Win!", "WIN", JOptionPane.ERROR_MESSAGE);
		g.dispose();
	}
}