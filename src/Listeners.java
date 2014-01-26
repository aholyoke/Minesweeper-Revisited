import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
public class Listeners extends MouseInputAdapter{
	int x= 0;
	int y= 0;
	Listeners() {
		Origin.g.p.addMouseListener(this);
		Origin.g.p.addMouseMotionListener(this);
	}
	public void mouseMoved(MouseEvent e) {
		x= e.getX();
		y= e.getY();
		Origin.g.p.repaint();
		
	}
	public void mouseClicked(MouseEvent e) {
		for (int i= 0; i < Origin.tilesY; i++)
			for (int i2= 0; i2 < Origin.tilesX; i2++)
				if (x > i2*55 && x < i2*55 +55 && y > i*55 && y < i*55 +55) {
					if(e.getButton()== 1)
						Origin.TileList[i2][i].click();
					else if (e.getButton()== 3)
						Origin.TileList[i2][i].flag();
				}
			
		
		Origin.g.p.repaint();
	}
}