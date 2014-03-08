

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.gson.Gson;


public class DataFactory {

	private String[] nodes;
	private String keyspace;
	private Cluster cluster;
	private Session session;
	private PreparedStatement ps_getArtist;
	
	public DataFactory(String[] nodes, String keyspace){
		this.setNodes(nodes);
		this.setKeyspace(keyspace);
		connect();
	}
	
	public void connect() {
		Builder builder = Cluster.builder();
		//builder.addContactPoints(node).withCredentials("travis", "travis");
		builder.addContactPoints(nodes);
		
		//Connection Options
		builder.socketOptions().setConnectTimeoutMillis(10000);
		builder.socketOptions().setReadTimeoutMillis(10000);
		builder.withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE);
		builder.withReconnectionPolicy(new ConstantReconnectionPolicy(100));
		
		cluster = builder.build();
		session = cluster.connect(keyspace);
		
		//Initialize prepared statements
		ps_getArtist = session.prepare("select * from artist_lookup where artist_id = ?");
	}
	
	public String[] getNodes() {
		return nodes;
	}
	public void setNodes(String[] nodes) {
		this.nodes = nodes;
	}
	public String getKeyspace() {
		return keyspace;
	}
	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}
	public Cluster getCluster() {
		return cluster;
	}
	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void writePlayList(PlayList pl){
		Statement statement = QueryBuilder.insertInto("playlist")
			.value("playlist_id", pl.getPlaylist_id())
			.value("artist_id", pl.getArtist_id())
			.value("song_id", pl.getSong_id());
		session.execute(statement);
	}
	
	public void writeNewPlayList(NewPlaylist newpl){
		if (newpl.isValid()){
			Statement statement = QueryBuilder.insertInto("newplaylist")
					.value("playlist_id", newpl.getPlaylist_id())
					.value("artist_name", newpl.getArtist_name());
				session.execute(statement);		
			writeNewArtist(newpl.getArtist_name());
		}
	}
	
	public void writeNewArtist(Artist artist){
		Statement statement = QueryBuilder.insertInto("artist")
				.value("artist_id", artist.getArtist_name())
				.value("artist_name", artist.getArtist_name());
			session.execute(statement);		
	}

	public void writeNewArtist(String artist_name){
		Statement statement = QueryBuilder.insertInto("artist")
				.value("artist_id", artist_name)
				.value("artist_name", artist_name);
			session.execute(statement);	
	}
	
	public void writeMultipleNewPlayList(String[] artists){
		UUID uuid = UUID.randomUUID();
		for (int i=0;i<artists.length; i++){
			NewPlaylist newpl = new NewPlaylist(uuid, artists[i]);
			writeNewPlayList(newpl);
		}
	}
	
	
	public String getSuggestedArtists(String searchArtist, String limit){
		Statement statement = QueryBuilder.select()
				.all()
				.from("affinity")
				.where(QueryBuilder.eq("artist", searchArtist))
				.limit(Integer.parseInt(limit));
		ResultSet results = session.execute(statement);
		List<SuggestedArtist> suggestedartists = new ArrayList<SuggestedArtist>();
		
		for (Row row : results){
			SuggestedArtist artist = new SuggestedArtist(row);
			artist.round();
			suggestedartists.add(artist);
		}
		
		return new Gson().toJson(suggestedartists);		
	}
	
	public Artist getArtist(int artist_id){
		return new Artist(session.execute(ps_getArtist.bind(artist_id)).one());
	}
	
}
