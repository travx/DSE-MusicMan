

import com.datastax.driver.core.Row;

public class Artist {
	private String artist_name;
	
	public Artist(String artist_name){
		this.setArtist_name(artist_name);
	}
	
	public Artist(Row row){
		this.setArtist_name(row.getString("artist_name"));
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	
}
