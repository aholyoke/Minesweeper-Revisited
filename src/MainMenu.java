import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainMenu extends JFrame{
	static JTextField WidthField;
	static JSlider WidthSlide;
	static JTextField HeightField;
	static JSlider HeightSlide;
	static JSlider DifficultySlide;
	
	/**
	 * Main Menu GUI
	 */
	MainMenu() {
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Quit program on close
		
		//Get content pane and set layout of gui elements
		Container contentPane= this.getContentPane();
		FlowLayout layout= new FlowLayout();
		contentPane.setLayout(layout); 
		
		//==Create and initialise GUI==//
		Font font = new Font("Times", 0, 40);
		JLabel MinesweeperTitle= new JLabel("Mine Sweeper        ", JLabel.LEFT);
		MinesweeperTitle.setFont(font);
		
		JButton StartButton= new JButton("Start");
		StartButton.addActionListener(new StartButtonListener()); //Instantiate listener and add it
		
		WidthSlide= new JSlider(JSlider.HORIZONTAL, 2, 34, 18);
		WidthSlide.setMajorTickSpacing(8);
		WidthSlide.setMinorTickSpacing(1);
		WidthSlide.setSnapToTicks(true);
		WidthSlide.setPaintTicks(true);
		WidthSlide.setPaintLabels(true);
		WidthSlide.addChangeListener(new WidthSlideListener()); //Instantiate listener and add it
		
		WidthField= new JTextField(2);
		WidthField.setText("18");
		WidthField.addActionListener(new WidthFieldListener()); //Instantiate listener and add it

		HeightSlide= new JSlider(JSlider.HORIZONTAL, 2, 18, 10);
		HeightSlide.setMajorTickSpacing(8);
		HeightSlide.setMinorTickSpacing(1);
		HeightSlide.setSnapToTicks(true);
		HeightSlide.setPaintLabels(true);
		HeightSlide.setPaintTicks(true);
		HeightSlide.addChangeListener(new HeightSlideListener()); //Instantiate listener and add it
		
		HeightField= new JTextField(2);
		HeightField.setText("10");
		HeightField.addActionListener(new HeightFieldListener()); //Instantiate listener and add it
		
		DifficultySlide= new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		
		
		//==Add GUI to content pane==//
		contentPane.add(MinesweeperTitle);
		contentPane.add(StartButton);
		contentPane.add(new JLabel("                                                                         "));
		contentPane.add(WidthSlide);
		contentPane.add(WidthField);
		contentPane.add(new JLabel("Width                          "));
		contentPane.add(HeightSlide);
		contentPane.add(HeightField);
		contentPane.add(new JLabel("Height                         "));
		contentPane.add(DifficultySlide);
		contentPane.add(new JLabel("Difficulty                            "));
		this.setVisible(true);
	}
	
	/**
	 * Listen to Width slider, adjust Width field appropriately
	 * @author alex
	 *
	 */
	private static class WidthSlideListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			WidthField.setText(Integer.toString(WidthSlide.getValue()));
		}
	}
	
	/**
	 * Listen to Width field, adjust Width slider appropriately
	 * @author alex
	 *
	 */
	private static class WidthFieldListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			WidthSlide.setValue(Integer.parseInt(WidthField.getText()));
		}
	}
	
	/**
	 * Listen to Height slider, adjust Height field appropriately
	 * @author alex
	 *
	 */
	private static class HeightSlideListener implements ChangeListener{
		public void stateChanged(ChangeEvent e) {
			HeightField.setText(Integer.toString(HeightSlide.getValue()));
		}
	}
	
	/**
	 * Listen to Height field, adjust Height slider appropriately
	 * @author alex
	 *
	 */
	private static class HeightFieldListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			HeightSlide.setValue(Integer.parseInt(HeightField.getText()));
		}
	}
	
	/**
	 * Listen to Start Button, Call startGame() when pressed
	 * @author alex
	 *
	 */
	private static class StartButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Origin.startGame();
		}
	}
}