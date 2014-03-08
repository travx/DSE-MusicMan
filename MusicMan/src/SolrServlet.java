

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SolrServlet
 */
public class SolrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] solrserver;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolrServlet() {
        super();
        // TODO Auto-generated constructor stub
        
        //Hacked load balancer
        solrserver = new String[] {
        		"http://s1.music-network.org:8983/solr/music.artist/select?wt=json&indent=on&omitHeader=on&q=", 
        		"http://s2.music-network.org:8983/solr/music.artist/select?wt=json&indent=on&omitHeader=on&q="};
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String q = URLEncoder.encode(request.getParameter("q"), "UTF-8");
		//String strurl = "http://dse2:8983/solr/music.artist/select?wt=json&indent=on&omitHeader=on&q=" + q + "~";
		String strurl = solrserver[(int)Math.random()] + q + "~";
				
		response.getWriter().print(new URLReader().getText(strurl));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
