package models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "attendances")
@NamedQueries({
    @NamedQuery(
            name = "getAllAttendances",
            query = "SELECT a FROM Attendance AS a ORDER BY a.id DESC"
            ),
    @NamedQuery(
            name = "getAttendancesCount",
            query = "SELECT COUNT(a) FROM Attendance AS a"
            ),
    @NamedQuery(
            name = "getMyAllAttendances",
            query = "SELECT a FROM Attendance AS a WHERE a.employee = :employee ORDER BY a.id DESC"
            ),
    @NamedQuery(
            name = "getMyAttendancesCount",
            query = "SELECT COUNT(a) FROM Attendance AS a WHERE a.employee = :employee"
            ),
    @NamedQuery(
              name= "chceckAttendanceDate",
             query = "SELECT a FROM Attendance As a WHERE a.employee = :employee AND a.check_attendancedate = :check_date"
             )


})
@Entity
public class Attendance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable =false)
    private Employee employee;

    @Column(name = "start_work", nullable = true)
    private String start_work;

    @Column(name = "finish_work", nullable = true)
    private String finsh_work;

    @Column(name = "check_attendancedate", nullable = true)
    private Date  check_attendancedate;





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStart_work() {
		return start_work;
	}

	public void setStart_work(String start_work) {
		this.start_work = start_work;
	}

	public String getFinsh_work() {
		return finsh_work;
	}

	public void setFinsh_work(String finsh_work) {
		this.finsh_work = finsh_work;
	}

	public Date getCheck_attendancedate() {
		return check_attendancedate;
	}

	public void setCheck_attendancedate(Date check_attendancedate) {
		this.check_attendancedate = check_attendancedate;
	}





}