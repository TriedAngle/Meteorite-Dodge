package net.strobl.game.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import net.strobl.game.Handler;

public class Fonts {
	
	public static Font playerNameFont;
	public static Font scoreFont;
	public static Font killFeedFontArial;
	public static Font killFeedFont;
	
	public static Font lobbyTeamNameFont;
	public static Font lobbyNameFont;
	public static Font buttonFont;
	public static Font playButtonFont;
	public static Font inputFieldFont;
	public static Font titleFont;
	public static Font winScreenWinner;
	public static Font errorFont;
	public static Font respawnTimerFont;
	public static Font notificationFont;
	public static Font winScreenNameFont;
	public static Font tabStatsFont;
	
	public static void init() {
		
		Font baseFont = null;
		try {
			//baseFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("gomarice_no_continue.ttf")));
			baseFont = Font.createFont(Font.TRUETYPE_FONT, Fonts.class.getResourceAsStream("/gomarice_no_continue.ttf"));
		} catch (Exception e) {
			System.out.println("Font not found");
			e.printStackTrace();
		}
		if(baseFont == null) {
			System.out.println("Font is null");
		}
		
		playerNameFont = baseFont.deriveFont(Font.PLAIN, 25);
		scoreFont = baseFont.deriveFont(Font.BOLD, 45);
		killFeedFontArial = new Font("Arial", Font.PLAIN, 30);
		killFeedFont = baseFont.deriveFont(Font.PLAIN, 30);
		lobbyTeamNameFont = baseFont.deriveFont(Font.BOLD, 45);
		lobbyNameFont = baseFont.deriveFont(Font.PLAIN, 30);
		buttonFont = new Font("Arial", Font.PLAIN, 30);
		playButtonFont = new Font("Arial", Font.BOLD, 50);
		inputFieldFont = new Font("", Font.ITALIC, 40);
		titleFont = baseFont.deriveFont(Font.BOLD, 75);
		winScreenWinner = baseFont.deriveFont(Font.BOLD, 60);
		errorFont = new Font("Arial", Font.ITALIC, 20);
		respawnTimerFont = baseFont.deriveFont(Font.BOLD, 70);
		notificationFont = baseFont.deriveFont(Font.PLAIN, 40);
		winScreenNameFont = baseFont.deriveFont(Font.BOLD, 55);
		tabStatsFont = baseFont.deriveFont(Font.ITALIC, 35);
	}
	
	public static void drawCenteredText(Graphics g, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawCenteredText(Graphics g, String[] strings, int x, int y, Font font, Color[] colors) {
		String text = "";
		for(String s : strings) {
			text += s;
		}
		
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text) / 2;
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		
		int xOffset = 0;
		for(int i = 0; i<strings.length; i++) {
			g.setColor(colors[i]);
			g.drawString(strings[i], x + xOffset, y);
			xOffset += metrics.stringWidth(strings[i]);
		}
	}
	
	public static void drawRightAllinedText(Graphics g, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		x = x - metrics.stringWidth(text);
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawLeftAllinedText(Graphics g, String text, int x, int y, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		
		y = y - metrics.getHeight() / 2 + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
	}
	
	public static void drawTitle(Graphics g, String text, Color color, Font font, int titleYOffset, boolean shadow) {
		if(shadow) {
			g.setColor(Color.GRAY);
			Fonts.drawCenteredText(g, text, Handler.getWidth()/2 + 4, titleYOffset + 5, font);
		}
		g.setColor(color);
		Fonts.drawCenteredText(g, text, Handler.getWidth()/2, titleYOffset, font);
	}

}
