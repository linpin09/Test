import java.net.*;
import java.io.*;


public class Test {
		
	public static void main(String[] args) throws IOException {
		PrintStream output = new PrintStream(new File("AppIdList.txt"));
		String url = "http://steamcommunity.com/id/SpliffLife/games?tab=all";
		output.println(getUrlSource(url));
		output.close();
		sendRequest();
		//findAppIds();
	}
	
	/*public static String findAppIDs() {
		
		try {
			Scanner scanner = new Scanner("AppIdList.txt");
			String a = "a";
			return a;
			
		} catch (FileNotFoundException ex) {
		     logger.log(ex);
		     throw ex;
		}
	}*/
	
	public static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
	public static void sendRequest() throws IOException {
		
	String url = "http://steamcommunity.com/remoteactions/modifyappstate";
	String charset = "UTF-8";
	String param1 = URLEncoder.encode("sessionid=ODI0MTQ1MjE%3D&appid=221640&operation=install", charset);
	System.out.println(param1);
	//String query = String.format(param1);
	//String query = "sessionid=ODI0MTQ1MjE%3D&appid=221640&operation=install";
	String query = "sessionid%3DODI0MTQ1MjE%253D%26appid%3D221640%26operation%3Dinstall";
	
	
	URLConnection urlConnection = new URL(url).openConnection();
	urlConnection.setUseCaches(false);
	urlConnection.setDoOutput(true); // Triggers POST.
	urlConnection.setRequestProperty("accept-charset", charset);
	urlConnection.setRequestProperty("content-type", "application/JSON");

	OutputStreamWriter writer = null;
	try {
	    writer = new OutputStreamWriter(urlConnection.getOutputStream(), charset);
	    writer.write(query); // Write POST query string (if any needed).
	} finally {
	    if (writer != null) try { writer.close(); } catch (IOException logOrIgnore) {}
	}
	
	InputStream result = urlConnection.getInputStream();
	System.out.println(result);
	// Now do your thing with the result.
	// Write it into a String and put as request attribute
	// or maybe to OutputStream of response as being a Servlet behind `jsp:include`.
	}
}