

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MusicServlet
 */
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataFactory df;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicServlet() {
        super();
        // TODO Auto-generated constructor stub
		//df = new DataFactory("dse.music-network.org", "music");
        String nodes[] = {"c1.music-network.org", "c2.music-network.org", "h1.music-network.org", "h2.music-network.org", "s1.music-network.org", "s2.music-network.org"};
		df = new DataFactory(nodes, "music");

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchArtist = request.getParameter("q");
		String limit = request.getParameter("l");
		if (limit.equals("")){
			limit="10";
		}
		request.setAttribute("suggested_artists", df.getSuggestedArtists(searchArtist, limit));
		request.getRequestDispatcher("suggested.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] artists = request.getParameterValues("artist");
		df.writeMultipleNewPlayList(artists);
		
		request.setAttribute("msg", "Thank you for sharing your music. You may upload more playlists, or return to the music search.");
		request.getRequestDispatcher("uploadplaylist.jsp").forward(request, response);	
	}

}
