package mygame.flappybird.src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {

	private int posX, posY, velX = 0, velY = 0;
	private boolean gameOver = false;
	private BufferedImage image;

	public Bird(int posX, int posY) throws IOException {
		this.posX = posX;
		this.posY = posY;
		image = ImageIO.read(getClass().getResource("Bird.png"));
	}
	
	public void tick() {
		posX += velX;
		
		if(velY < 10 && !isGameOver()) {
			velY++;
		}
		if(posY < FlappyBird.HEIGHT - 115) {
		    posY += velY;
		    if(posY > FlappyBird.HEIGHT - 115) {
		    	posY = FlappyBird.HEIGHT - 115;
		    }
		}
		else {
			gameOver = true;
		}
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawImage(image, posX, posY, 32, 32, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(posX + 8, posY + 5, 20, 16);
	}
}
