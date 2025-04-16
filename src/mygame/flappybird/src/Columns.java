package mygame.flappybird.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Columns{

	private ArrayList<Rectangle> columns;
	private int count, space = 100, width = 70, height;
	private int distence, score = 0;
	private Random rand;
	private Bird bird;
	public Columns(Bird bird) {
		columns = new ArrayList<Rectangle>();
		this.bird = bird;
		rand = new Random();
		height = 50 + rand.nextInt(FlappyBird.HEIGHT - space - 200);
		columns.add(new Rectangle(FlappyBird.WIDTH + 10, 0, width, height));
		columns.add(new Rectangle(FlappyBird.WIDTH + 10, height + space, width,
				FlappyBird.HEIGHT - height - space - 100));
		count = 1;
	}
	
	public void tick() {
		collision();
		gameScore();
		if(count < 5) {
			Rectangle rect = columns.get(columns.size() - 1);
			height = 50 + rand.nextInt(FlappyBird.HEIGHT - space - 200);
			distence = 100 + rand.nextInt(100); 
			columns.add(new Rectangle(rect.x + width + distence, 0, width, height));
			columns.add(new Rectangle(rect.x + width + distence, height + space, width,
					FlappyBird.HEIGHT - height - space - 100));
			count++;
		}
		
		for(int i = 0; i < columns.size(); i++) {
			if(columns.get(i).x + width < 20) {
				columns.remove(i);
				count--;
			}
				columns.get(i).x -= 5;
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < columns.size(); i++) {
			Rectangle rect = columns.get(i);
			g.setColor(new Color(255, 53, 5));
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.drawString("Score : " + score, 0, 25);
	}
	
	public int getScore() {
		return score;
	}

	public void collision() {
		for(int i = 0; i < columns.size(); i++) {
			if(columns.get(i).intersects(bird.getBounds()) && !bird.isGameOver()) {
				bird.setPosX(bird.getPosX() - 5);
				bird.setGameOver(true);
				bird.setVelX(-5);
				bird.setVelY(8);
			}
			
			if(bird.isGameOver() && bird.getPosX() + 22 > columns.get(i).x) {
				if(bird.getPosY() > columns.get(i).y - 16 && columns.get(i).y != 0) {
					bird.setPosY(columns.get(i).y - 16);
				}
				if(bird.getPosY() > columns.get(i+1).y - 16 && columns.get(i).y == 0) {
					bird.setPosY(columns.get(i+1).y - 16);
				}
			}
			
			if(bird.isGameOver() && bird.getPosY() == FlappyBird.HEIGHT - 115) {
				bird.setVelX(-5);
			}
		}
	}
	
	public void gameScore() {
		for(int i = 0; i < columns.size(); i++) {
			if(columns.get(i).y== 0 && (columns.get(i).x - 5 + width / 2) < bird.getPosX() &&
					(columns.get(i).x + 2 + width / 2) > bird.getPosX()) {
				score++;
			}
		}
	}
}
