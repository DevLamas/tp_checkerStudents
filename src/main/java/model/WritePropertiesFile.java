package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class WritePropertiesFile {
	public static void main(final String[] args) {

		final Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("src/main/resources/config.properties");

			prop.setProperty("db.url", "jdbc:mysql://localhost:3306/students_checker");
			prop.setProperty("db.user", "root");
			prop.setProperty("db.password", "");

			prop.store(output, null);

		} catch (final IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
