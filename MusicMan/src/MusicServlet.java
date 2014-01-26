

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
        df = DataFactory.newInstance();
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
