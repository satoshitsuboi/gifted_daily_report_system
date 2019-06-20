package csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Attendance;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class AttendanceCSV
 */
@WebServlet("/attendances/csv")
public class AttendanceCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendanceCSV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/octet-stream" + ";charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"attendance.csv\"");

		EntityManager em = DBUtil.createEntityManager();

		try (PrintWriter pw = response.getWriter()) {
			List<Attendance> all_attendances = em.createNamedQuery("getAllAttendances", Attendance.class)
					.getResultList();
        for(int a=0;a<all_attendances.size(); a++){

        	Employee employee = all_attendances.get(a).getEmployee();
        	String start_work = all_attendances.get(a).getStart_work();
        	String finsh_work = all_attendances.get(a).getFinsh_work();

        	// csv内での表記
        	String outputAttendances = employee.getCode() +","+employee.getName() + ","  +"," + start_work + "," + finsh_work + ","
        			+ "\r\n";
        	// csvファイルに書き込み
        	pw.print(outputAttendances);
        }
 }
		catch (IOException e) {
            e.printStackTrace();
        }


	}

}
