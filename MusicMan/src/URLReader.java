


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.tomcat.util.codec.binary.Base64;


public class URLReader {
	public String getText(String strurl) throws MalformedURLException, IOException{
		URL url = new URL(strurl);
	    
		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
	    httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
		
	    BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
		
		StringBuilder result = new StringBuilder();
		String inputline;
		
		while ((inputline = in.readLine()) != null)
			result.append(inputline);
		
		in.close();
		return result.toString();
	}
	
	public String getTextFromBing(String bingUrl) throws IOException{
		String accountKey = "";
		
		byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
		String accountKeyEnc = new String(accountKeyBytes);
		
		URL url = new URL(bingUrl);
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization", String.format("Basic %s", accountKeyEnc));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder result = new StringBuilder();
		String inputline;
		
		while ((inputline = in.readLine()) != null)
			result.append(inputline);
		
		in.close();
		return result.toString();		
	}
	


}
