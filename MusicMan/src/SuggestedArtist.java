import com.datastax.driver.core.Row;


public class SuggestedArtist {
	private String artist;
	private String suggested_artist;
	private double support;
	private double confidence;
	private double lift;
	
	public SuggestedArtist(Row row){
		this.setArtist(row.getString("artist"));
		this.setConfidence(row.getDouble("confidence"));
		this.setLift(row.getDouble("lift"));
		this.setSuggested_artist(row.getString("suggested_artist"));
		this.setSupport(row.getDouble("support"));
	}
	
	public void round(){
		double factor1 = 1e5;
		double factor2 = 1e1;
		this.setSupport(Math.round(this.getSupport()*factor1)/factor1);
		this.setConfidence(Math.round(this.getConfidence()*factor1)/factor1);
		this.setLift(Math.round(this.getLift()*factor2)/factor2);		
	}
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getSuggested_artist() {
		return suggested_artist;
	}
	public void setSuggested_artist(String suggested_artist) {
		this.suggested_artist = suggested_artist;
	}
	public double getSupport() {
		return support;
	}
	public void setSupport(double support) {
		this.support = support;
	}
	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	public double getLift() {
		return lift;
	}
	public void setLift(double lift) {
		this.lift = lift;
	}
	
}
