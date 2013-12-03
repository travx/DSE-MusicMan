import java.util.UUID;


public class NewPlaylist {
	private UUID playlist_id;
	private String artist_name;
	
	public NewPlaylist(UUID uuid, String artist_name){
		this.setArtist_name(artist_name);
		this.setPlaylist_id(uuid);
	}
	
	public boolean isValid(){
		return !(this.getArtist_name().isEmpty());
	}
	
	public UUID getPlaylist_id() {
		return playlist_id;
	}
	public void setPlaylist_id(UUID playlist_id) {
		this.playlist_id = playlist_id;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}	
	
}
