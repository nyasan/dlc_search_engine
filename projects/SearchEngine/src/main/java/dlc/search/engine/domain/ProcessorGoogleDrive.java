package dlc.search.engine.domain;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;

public class ProcessorGoogleDrive implements Processor {
	
	Logger logger = Logger.getLogger(ProcessorGoogleDrive.class);

	@Override
	public Map<String, Integer> process(String uri) {
		// TODO Auto-generated method stub
		// throw new NotImplementedException("TODO");

		URL fileURL;
		try {
			fileURL = new URL(
					"https://drive.google.com/folderview?id=0B_R7SeoAotsmUUtYendIX04zRjA&usp=sharing");
					//"https://doc-0c-ao-docs.googleusercontent.com/docs....");
			try (Scanner readFile = new Scanner(fileURL.openStream())) {
				while (readFile.hasNext()) {
					String fileData = readFile.nextLine();
					System.out.println(fileData);
					logger.debug(fileData);
					
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

}
