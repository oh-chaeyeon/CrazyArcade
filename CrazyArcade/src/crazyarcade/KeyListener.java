package crazyarcade;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyListener extends KeyAdapter {

	   private int mapNumber;

	   public KeyListener(int mapNumber) {
	      this.mapNumber = mapNumber;
	   }
	   
	   @Override
	   public void keyPressed(KeyEvent e) {
	      if (mapNumber == 1) {
	    	  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	  			Map1.person1.moveToLeft();
	  		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	  			Map1.person1.moveToRight();
	  		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	  			Map1.person1.moveToUp();
	  		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	  			Map1.person1.moveToDown();
	  		}
	  		
	  		
	  		else if (e.getKeyCode() == KeyEvent.VK_A) {
	              Map1.person2.moveToLeft();
	          } else if (e.getKeyCode() == KeyEvent.VK_D) {
	              Map1.person2.moveToRight();
	          } else if (e.getKeyCode() == KeyEvent.VK_W) {
	              Map1.person2.moveToUp();
	          } else if (e.getKeyCode() == KeyEvent.VK_S) {
	              Map1.person2.moveToDown();
	          }
	      } else if (mapNumber == 2) {
	    	  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	  			Map2.person1.moveToLeft();
	  		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	  			Map2.person1.moveToRight();
	  		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	  			Map2.person1.moveToUp();
	  		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	  			Map2.person1.moveToDown();
	  		}
	  		
	  		
	  		else if (e.getKeyCode() == KeyEvent.VK_A) {
	              Map2.person2.moveToLeft();
	          } else if (e.getKeyCode() == KeyEvent.VK_D) {
	              Map2.person2.moveToRight();
	          } else if (e.getKeyCode() == KeyEvent.VK_W) {
	              Map2.person2.moveToUp();
	          } else if (e.getKeyCode() == KeyEvent.VK_S) {
	              Map2.person2.moveToDown();
	          }
	  	
	      }
	   }
}

