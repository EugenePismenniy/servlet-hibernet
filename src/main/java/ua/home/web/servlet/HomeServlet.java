package ua.home.web.servlet;

import ua.home.web.dao.AlbumDao;
import ua.home.web.domain.Album;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {


	private AlbumDao albumDao;

	@Override
	public void init() throws ServletException {
		albumDao = (AlbumDao) getServletContext().getAttribute("ua.home.web.dao.jpa.AlbumDaoJpaImpl");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.print("<html>");
		out.print("<body>");

		List<Album> albums = albumDao.getAlbums();

		for (Album album : albums) {
			out.print("<p>");
			out.print(album);
			out.print("</p>");
		}

		out.print("</body>");
		out.print("</html>");


	}
}
