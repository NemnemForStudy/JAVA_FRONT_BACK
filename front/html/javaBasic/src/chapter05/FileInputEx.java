package chapter05;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputEx {

	public static void main(String[] args) {
		FileInputStream input = null;
		
		try {
			File file = new File("C:\\javac\\test.txt");
			input = new FileInputStream(file);
			int i = 0;
			while((i = input.read()) != -1) {
				System.out.println(i);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				input.close();
			} catch(IOException io) {
				
			}
		}

	}

}
