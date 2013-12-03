

public class PlayList {
	private int playlist_id;
	private int artist_id;
	private int song_id;

	public PlayList(int playlist_id, int artist_id, int song_id){
		this.setArtist_id(artist_id);
		this.setPlaylist_id(playlist_id);
		this.setSong_id(song_id);
	}

	public PlayList(String playlist_id, String artist_id, String song_id){
		this.setArtist_id(Integer.parseInt(artist_id));
		this.setPlaylist_id(Integer.parseInt(playlist_id));
		this.setSong_id(Integer.parseInt(song_id));
	}
	
	public int getPlaylist_id() {
		return playlist_id;
	}
	public void setPlaylist_id(int playlist_id) {
		this.playlist_id = playlist_id;
	}
	public int getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	
}
