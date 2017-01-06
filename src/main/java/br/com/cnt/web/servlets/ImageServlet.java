package br.com.cnt.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import br.com.cnt.model.dao.DaoException;
import br.com.cnt.model.dao.ImagemDAO;
import br.com.cnt.model.entity.Imagem;

import java.io.ByteArrayInputStream;

//import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

@WebServlet("/imagemservlet")
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ImagemDAO imagemDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String parameter = request.getParameter("id");
			if (StringUtils.isNotBlank(parameter)) {
				imagemDAO = new ImagemDAO();
				Imagem arquivo;
				arquivo = imagemDAO.buscar(Long.valueOf(parameter));
				if (arquivo != null) {
					response.setContentType(arquivo.getExtencao());

					byte[] imagem = arquivo.getData();
					ByteArrayInputStream bis = new ByteArrayInputStream(imagem);
					ServletOutputStream outputStream = response.getOutputStream();
					byte[] buffer = new byte[1024];
					int tamBuf = 0;
					while ((tamBuf = bis.read(buffer)) != -1) {
						outputStream.write(buffer, 0, tamBuf);
					}

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
}
