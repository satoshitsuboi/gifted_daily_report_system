package controllers.attendances;

import
java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Attendance;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class AttendancesNewServlet
 */
@WebServlet("/attendances/new")
public class AttendancesNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendancesNewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManager em = DBUtil.createEntityManager();
		Attendance a = new Attendance();

		HttpSession session = ((HttpServletRequest) request).getSession();
		Employee login_employee = (Employee) session.getAttribute("login_employee");

		ZonedDateTime zonedDateTime = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
		Instant instant = zonedDateTime.toInstant();
		Date check_date = Date.from(instant);

		List<Attendance> checkattendances = em.createNamedQuery("chceckAttendanceDate", Attendance.class)
				.setParameter("employee", login_employee)
				.setParameter("check_date", check_date)
				.getResultList();

		String buttonType = request.getParameter("buttontype");

		if (checkattendances.size() == 0) {
			if (buttonType.equals("start_work")) {
				a.setEmployee(login_employee);
				Timestamp timestamp01 = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String str01 = sdf01.format(timestamp01);
				a.setStart_work(str01);
				a.setCheck_attendancedate(check_date);
				request.getSession().setAttribute("flush", "出勤登録が完了しました。");

				em.getTransaction().begin();
				em.persist(a);
				em.getTransaction().commit();
				em.close();

			} else {
				request.setAttribute("errors", "出勤データが登録できていません。");
			}

		}

		else {
			Attendance attend = checkattendances.get(0);

			if (attend.getStart_work() != null && buttonType.equals("finsh_work") && attend.getFinsh_work()== null) {
				Timestamp timestamp02 = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf02 = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
				String str02 = sdf02.format(timestamp02);
				attend.setFinsh_work(str02);
				request.getSession().setAttribute("flush", "お疲れ様でした！退勤登録が完了しました。");


				checkattendances.set(0, attend);
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.close();

			} else {
				request.setAttribute("errors", "既に登録が完了しています。");
				request.getSession().removeAttribute("errors");
			}

		}
		if (request.getSession().getAttribute("flush") != null) {
			request.setAttribute("flush", request.getSession().getAttribute("flush"));
			request.getSession().removeAttribute("flush");

		}


		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/attendances/_form.jsp");
		rd.forward(request, response);

	}

}
