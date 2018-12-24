package net.strobl.game.managers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{

	private static boolean leftButton;
	private static boolean clicked;
	private static int x = 0;
	private static int y = 0;
	
	public static void tick() {
		clicked = false;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if(!(e.getButton() == MouseEvent.BUTTON1)) {
			leftButton = false;
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(!leftButton) {
				clicked = true;
			}
			leftButton = true;
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftButton = false;
		}		
	}
	
	public static boolean isLeftButton() {
		return leftButton;
	}
	
	public static void setLeftButton(boolean isLeftButton) {
		leftButton = isLeftButton;
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static boolean hasClicked() {
		return clicked;
	}
	
}
