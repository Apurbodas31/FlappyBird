package mygame.flappybird.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static final String TITLE = "Flappy Bird";
	public static final int WIDTH = 640, HEIGHT = 480;
	public boolean running = false;
	private Bird bird;
	private Timer timer;
	private KeyInput input;
	private Columns columns;
	
	public FlappyBird() throws IOException {
		this.setFocusable(true);
		bird = new Bird(200, 100);
		timer = new Timer(30, this);
		input = new KeyInput(bird, this);
		addKeyListener(input);
		columns = new Columns(bird);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.ORANGE);
		g.fillRect(0, HEIGHT - 100, WIDTH, 100);
		g.setColor(Color.GREEN);
		g.fillRect(0, HEIGHT - 100, WIDTH, 20);
		columns.render(g);
		bird.render(g);
		if(!running) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Press Enter To Start Game", 130, HEIGHT / 2);
		}
		if(bird.getPosX() < -25) {
			g.setColor(new Color(8, 8, 8, 150));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("Your score is " + columns.getScore(), 120, 240);
			running = false;
		}
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			bird.tick();
			columns.tick();
			repaint();
		}
	}
	
	public static void main(String[] args) throws IOException {
		FlappyBird game = new FlappyBird();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
