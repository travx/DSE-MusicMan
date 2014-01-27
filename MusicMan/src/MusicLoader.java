

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class MusicLoader {

	public static void main(String[] args) throws IOException {
//		DataFactory df = new DataFactory("dse1", "music");
		DataFactory df = DataFactory.newInstance();
		System.out.println("Connected to the cluster!");		
		
		BufferedReader br = new BufferedReader(new FileReader("/dse/aotm_list_ids.txt"));	
		System.out.println("Successfully connected to file.");
		
		String strLine;
		int playlists = 0;
		int songs = 0;
		
		System.out.println("Processing playlists.");
		
	    while((strLine = br.readLine()) != null)
	    {
	    	strLine = strLine.replace("#", "");
	    	strLine = strLine.replace(":", "");
	    	String[] tokens = strLine.split(" ");
	    	UUID uuid = UUID.randomUUID();
	    	
	    	for(int i=2; i<tokens.length; i=i+2){
	    		//PlayList pl = new PlayList(tokens[0], tokens[i-1], tokens[i]);
	    		//df.writePlayList(pl);
	    		
	    		Artist artist = df.getArtist(Integer.parseInt(tokens[i-1]));
	    		NewPlaylist newpl = new NewPlaylist(uuid, artist.getArtist_name());
	    		
	    		df.writeNewPlayList(newpl);

	    		songs++;
	    	}
	    	playlists++;
	    	
	    	//Output every 1000 rows
	    	if (playlists%1000 == 0){
		    	System.out.println("Processed " + playlists + " playlists and " + songs + " songs.");
	    	}
	    	
	    }
	    
	    br.close();
	    System.out.println("Finished. Processed " + playlists + " playlists and " + songs + " songs.");
	}

}
