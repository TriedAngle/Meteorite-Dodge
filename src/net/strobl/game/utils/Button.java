package net.strobl.game.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import net.strobl.game.managers.MouseManager;


public class Button {
	
	private int x, y;
	private int width, height;
	private int arcWidth, arcHeight;
	private boolean rounded = false;
	
	private boolean clicked;
	private boolean pressed;
	
	private String text;
	private Font font;
	private Color color = Color.BLACK;
	private Color textColor = Color.WHITE;
	
	
	public Button(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Button(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		
		rounded = true;
	}
	
	
	public void tick() {
		if(clicked)
			clicked = false;
		
		if(MouseManager.hasClicked() && containsMouse()) {
			pressed = true;
		}
		if(!MouseManager.isLeftButton()) {
			if(pressed && containsMouse()) {		// if the mouse button was released on the button and started on the button
				clicked = true;
			}
			pressed = false;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		
		if(rounded) {
			g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		}
		else {
			g.fillRect(x, y, width, height);
		}
		
		if(text != null) {
			g.setColor(textColor);
			Fonts.drawCenteredText(g, text, x + width/2, y + height/2, font);
		}
	}
	
	
	private boolean containsMouse() {
		if(MouseManager.getX() >= x && MouseManager.getX() <= x + width
				&& MouseManager.getY() >= y && MouseManager.getY() <= y + height) {
			return true;
		}
		return false;
	}
	
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setTextColor(Color color) {
		textColor = color;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
}
