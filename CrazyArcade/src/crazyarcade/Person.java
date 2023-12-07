package crazyarcade;


import javax.swing.*;
import java.awt.*;

public class Person {
	
	private Image up;
    private Image down;
    private Image left;
    private Image right;

	private int pos_X, pos_Y;
	private String direction;
	private Image state;
	
	public void Bazzi() {
		
		this.up = new ImageIcon(getClass().getResource("/image/bazzi_back.png")).getImage();
		this.down = new ImageIcon(getClass().getResource("/image/bazzi_front.png")).getImage();
		this.left = new ImageIcon(getClass().getResource("/image/bazzi_left.png")).getImage();
		this.right = new ImageIcon(getClass().getResource("/image/bazzi_right.png")).getImage();
		
		 // 기본 방향 및 이미지 설정
        this.direction = "down";
        this.state = down;
	}
	
	public void Woonie() {
		
		this.up = new ImageIcon(getClass().getResource("/image/woonie_back.png")).getImage();
		this.down = new ImageIcon(getClass().getResource("/image/woonie_front.png")).getImage();
		this.left = new ImageIcon(getClass().getResource("/image/woonie_left.png")).getImage();
		this.right = new ImageIcon(getClass().getResource("/image/woonie_right.png")).getImage();
		
		 // 기본 방향 및 이미지 설정
        this.direction = "down";
        this.state = down;
	}
	
	public Image getState() {
		return state;
	}
	
	public void setState(Image image) {
		this.state = image;
	}
	
	public String getDirection( ) {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public int getPos_X() {
		return pos_X;
	}
	
	public void setPos_X(int pos_X) {
		this.pos_X = pos_X;
	}
	
	public int getPos_Y() {
		return pos_Y;
	}
	
	public void setPos_Y(int pos_y) {
		this.pos_Y = pos_Y;
	}
	
	public void setPosition(int pos_X, int pos_Y) {
		this.pos_X = pos_X;
		this.pos_Y = pos_Y;
	}
	
	public Image getLeft() {
		return left;
	}
	
	public Image getright() {
		return right;
	}
	
	public void moveToRight() {
		if (direction.equals("left")) {
			direction = "right";
		}
		
		if (pos_X <= 550)
			pos_X += 20;
		
		state = right;
	}
	
	public void moveToLeft() {
		if (direction.equals("right")) {
			direction = "left";
		}
		
		if (pos_X >= 0)
			pos_X -= 20;
		state = left;
	}
	
	public void moveToUp() {
		if (direction.equals("up")) {
			direction = "up";
		}
		if (pos_Y >= 0)
			pos_Y -= 20;
		state = up;
	}
	
	public void moveToDown() {
		if (direction.equals("down")) {
			direction = "down";
		}
		
		if (pos_Y <= 440)
			pos_Y += 20;
		state = down;
	}
	

	
	
	

	
}

