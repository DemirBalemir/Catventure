package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private boolean paused=false;
	private PauseOverlay pauseOverlay;
	
	
	public Playing(Game game) {
		super(game);
		initClasses();
		
	}
	

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (40 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay= new PauseOverlay(this);
	}
		



		@Override
		public void update() {
		if(!paused) {
			levelManager.update();
			player.update();
		}else {
			pauseOverlay.update();
		}
			
		}



		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			levelManager.draw(g);
			player.render(g);
			
			if(paused)
			pauseOverlay.draw(g);
			
		}



		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == MouseEvent.BUTTON1)
				player.setAttacking(true);

		}

		public void mouseDragged(MouseEvent e) {
			if(paused)
				pauseOverlay.mouseDragged(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(paused)
				pauseOverlay.mousePressed(e);
			
		}



		@Override
		public void mouseReleased(MouseEvent e) {
			if(paused)
				pauseOverlay.mouseReleased(e);
			
		}



		@Override
		public void mouseMoved(MouseEvent e) {
			if(paused)
				pauseOverlay.mouseMoved(e);
			
		}



		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(true);
				break;
			case KeyEvent.VK_ESCAPE:
			paused=!paused;
			break;
		
			}
		}



		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			
			case KeyEvent.VK_SPACE:
				player.setJump(false);
				break;
			}
		}
		public void unpauseGame() {
			paused=false;
		}
		public void windowFocusLost() {
			player.resetDirBooleans();
		}

		public Player getPlayer() {
			return player;  
	}
}
