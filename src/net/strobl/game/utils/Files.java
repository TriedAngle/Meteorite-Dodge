package net.strobl.game.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Files {

	private static FileWriter fw;
	private static PrintWriter pw;
	private static FileReader fr;
	private static BufferedReader br;

	// public static final String date = "yyyy-MM-dd HH:mm:ss";
	public static final String date = "dd,MM,yyyy+HH:mm:ss";

	public static String now() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(date);
		return dateFormat.format(calendar.getTime());
	}

	public static void writeNumber(String file, int number) {
		try {
			fw = new FileWriter(file, true);
			fw.write(number + "," + now());
			fw.write(System.lineSeparator());
			// fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readNumbers(String file) {

	}

}
