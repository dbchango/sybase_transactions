package sybase.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Tool {
	
	public Tool() {
		
	}
	
	public Properties readConfigFile(String file_path) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		try(InputStream resourceStream = loader.getResourceAsStream(file_path)) {
    	    props.load(resourceStream);
    	    return props;
    	}
    }
	
	public String extractSQL(String file_path) throws IOException {
		FileReader fr = new FileReader(file_path);
    	try (BufferedReader br = new BufferedReader(fr)) {
			return br.readLine();
		}
	}

}
