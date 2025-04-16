package mygame.flappybird.src;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Bird bird;
	private FlappyBird game;
	
	public KeyInput(Bird bird, FlappyBird game) {
		this.bird = bird;
		this.game = game;
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP && !bird.isGameOver()) { 
			bird.setPosY(bird.getPosY() - 35);
			bird.setVelY(0);
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER && !game.running) {
			game.running = true;
		}
	}
}
