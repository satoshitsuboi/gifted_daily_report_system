package csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportCSV
 */
@WebServlet("/reports/csv")
public class ReportCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportCSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードと出力するCSVファイル名を設定
        response.setContentType("application/octet-stream" + ";charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"reports.csv\"");

		EntityManager em = DBUtil.createEntityManager();
		try (PrintWriter pw = response.getWriter()) {
            List<Report> all_reports = em.createNamedQuery("getAllReports", Report.class)
                    .getResultList();
            for (int r = 0; r < all_reports.size(); r++) {

                Employee employee = all_reports.get(r).getEmployee();
                Date date = all_reports.get(r).getReport_date();
                String title = all_reports.get(r).getTitle();
                String content = all_reports.get(r).getContent();

                //CSVファイル内部に記載する形式で文字列を設定
                String outputReports = employee.getCode() + ","+employee.getName() +","+ date+","+title+","+content+","
                         + "\r\n";

                //CSVファイルに書き込み
                pw.print(outputReports);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	}





