import com.google.common.base.Optional;


public class Injections {

    public static final String CASSANDRA_ENDPOINT_PROPERTY = "musicman.cassandra.endpoint";
    public static final String SOLR_ENDPOINT_PROPERTY = "musicman.solr.endpoint";
    
    public static String getCassandraEndpoint() {
        System.out.println("CASS ENDPT: "+CASSANDRA_ENDPOINT_PROPERTY+"="+System.getProperty(CASSANDRA_ENDPOINT_PROPERTY));
        return getSystemProperty(CASSANDRA_ENDPOINT_PROPERTY)
//            .or("dse.music-network.org");
            .or("dse1");
    }

    public static String getSolrEndpoint() {
        return getSystemProperty(SOLR_ENDPOINT_PROPERTY).or("dse2:8983");
    }

    public static String getKeyspace() {
        return "music";
    }

    private static Optional<String> getSystemProperty(String propertyName) {
        return Optional.fromNullable(System.getProperty(propertyName));
    }

}
