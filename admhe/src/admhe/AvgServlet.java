package admhe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisServer
 */
@WebServlet("/AvgServlet")
public class AvgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response,String average_value) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.setAttribute("average", average_value+"MWatt"); // η τιμή μας θα είναι διαθέσιμη στο .jsp ως ${average}
		request.getRequestDispatcher("page2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date_start = request.getParameter("Date_s");        //παίρνουμε τις ημερομηνίες από τα πεδία 
		String date_end = request.getParameter("Date_e");          //του page2.jsp και τις περνάμε στην getdata
		String avg = Average.getdata(date_start,date_end);
		doGet(request, response, avg);
	}

}
