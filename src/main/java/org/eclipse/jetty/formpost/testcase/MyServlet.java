package org.eclipse.jetty.formpost.testcase;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int contentLength = req.getContentLength();
		InputStream in = req.getInputStream();
		// Read the input stream
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		int bytes = 0;
		while (bytes != -1) {
			bytes = in.read(buffer, 0, buffer.length);
			if (bytes != -1) {
				bytesRead += bytes;
			}
		}
		in.close();
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>")
		  .append("content length = ")
		  .append(contentLength)
		  .append(", bytes read from input stream = ")
		  .append(bytesRead)
		  .append("</body></html>");
		Writer out = resp.getWriter();
		Reader rdr = new StringReader(sb.toString());
		
		while (true) {
			int ch = rdr.read();
			if (ch == -1) break;
			out.write(ch);
		}
		out.close();
		resp.setStatus(200);
	}
}
