import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	Panel p;
	static double shader;
	final static Color DARKGREEN= new Color(0, 120, 0);
	final static Color DARKRED= new Color(180, 0, 0);
	final static Color DARKMAGENTA= new Color(200, 0, 255);
	
	GameWindow(int width, int height) {
		shader= 150.0/(Origin.tilesX*Origin.tilesY);
		Container pane= this.getContentPane();
		this.setSize(width, height +50);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		p= new Panel();
		pane.add(new JButton("Save"));
		pane.add(p);
	}
	
	/**
	 * The drawing panel
	 * @author Alex Holyoke
	 * 2011
	 */
	static class Panel extends JPanel{
		static int x= 0;
		static int y= 0;
		static Color c;
		
		public void paint(Graphics g) {
			Font font= new Font("Times", 0, 30);
			g.setFont(font);
			x= Origin.l.x;
			y= Origin.l.y;
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			for (int i= 0; i < Origin.tilesY; i++) {
				for (int i2= 0; i2 < Origin.tilesX; i2++) {
					drawNumberBombCoverFlag(g, i2, i);
				}
			}
		}
		
		/**
		 * draw number representing the amount of bombs in adjacent tiles
		 * then draws bomb if tile has bomb
		 * then draws cover if tile is still unrevealed
		 * then draws flag if tile is flagged
		 * also draws rectangular yellow cursor if neccessary
		 * @param g - the graphics object
		 * @param i2 - index location (column)
		 * @param i - index location (row)
		 */
		public void drawNumberBombCoverFlag(Graphics g, int i2, int i) {
			
			//Number
			g.setColor(Color.BLACK);
			Tile t= Origin.TileList[i2][i];
			if (t.adjs > 0) {
				if (t.adjs== 2)
					g.setColor(Color.BLUE);
				else if (t.adjs== 3)
					g.setColor(DARKGREEN);
				else if (t.adjs== 4)
					g.setColor(Color.ORANGE);
				else if (t.adjs== 5)
					g.setColor(DARKRED);
				else if (t.adjs== 6)
					g.setColor(DARKMAGENTA);
				else if (t.adjs > 6)
					g.setColor(Color.PINK);
					
				g.drawString(Integer.toString(t.adjs), i2*55 +15, i*55 +35);
			}
			
			// Bomb
			if (t.hasBomb) {
				g.setColor(Color.BLACK);
				g.fillOval(i2*55 +5, i*55 +5, 40, 40);
			}
			
			//Cover
			int shade= (int)(100 + i*i2*shader);
			c= new Color(shade, shade, shade); //calculate grayscale gradient for tile
			g.setColor(c);
			if (Origin.TileList[i2][i].covered)
				g.fill3DRect(i2*55, i*55, 50, 50, true);
			
			
			//Yellow Cursor
			g.setColor(Color.ORANGE);
			if (x > i2*55 && x < i2*55 +55 && y > i*55 && y < i*55 +55) {
				g.drawRect(i2*55 -60, i*55-60, 169, 169);
				g.drawRect(i2*55 -59, i*55 -59, 167, 167);
			}
			
			//Flags
			if (Origin.TileList[i2][i].flagged) {
				g.setColor(Color.RED);
				g.drawRect(i2*55+2, i*55+2, 45, 45);
			}
		}
		
	}
}