

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BingServlet
 */
public class BingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("q");
		String searchUrl = "http://www.albumart.org/index.php?itempage=1&newsearch=1&searchindex=Music&skey=";
		
		searchUrl = searchUrl + URLEncoder.encode(query, "UTF-8");
		
		URLReader images = new URLReader();
		String image = parseAmazonImage(images.getText(searchUrl));
		
		response.getWriter().print(image);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private String parseAmazonImage(String html){
		String[] tokens = html.split("\"");
		for (int i=0; i<tokens.length; i++){
			if (tokens[i].contains("images-amazon"))
				return tokens[i];
		}
		return "notfound.jpg";
	}

}
