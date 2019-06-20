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

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeeCSV
 */
@WebServlet("/employees/csv")
public class EmployeeCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeCSV() {
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
			List<Employee> all_employees = em.createNamedQuery("getAllEmployees", Employee.class)
					.getResultList();
			for(int e = 0; e<all_employees.size(); e++) {

				String code =all_employees.get(e).getCode();
                String name =all_employees.get(e).getName();
                String classfication =all_employees.get(e).getClassification();

             // csv内での表記
                String output_employees =   code + "," + name + "," + classfication+"," + "\r\n" ;

                // csvファイルに書き込み

                pw.print(output_employees);

			}
		}

	}

}
