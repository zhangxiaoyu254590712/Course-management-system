package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import db.DbManager;
import domain.Course;

/**
*  @author Jie Zheng
*  @version create date: Oct 17, 2018
*
*/
public class CourseDaoImpl implements CourseDao {
	public List<Course> getCourseList() {
		List<Course> list = new ArrayList<>();
		String sql = "select c.*, u.f_name,u.l_name from courses c left join Users u on c.teacher_id = u.id;";
		DbManager db = new DbManager();
		Connection conn = null;
		try {
			conn = db.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Course c = new Course();
				c.setCid(rs.getInt(1));
				c.setCname(rs.getString(2));
				c.setPrefix(rs.getString(3));
				c.setCno(rs.getString(4));
				c.setSno(rs.getString(5));
				c.setRoom(rs.getString(6));
				c.setCapacity(rs.getInt(7));
				c.setYear(rs.getString(8));
				c.setSemester(rs.getString(9));
				c.setSday(rs.getString(10));
				c.setEday(rs.getString(11));
				c.setDays(rs.getString(12));
				c.setStime(rs.getString(13));
				c.setEtime(rs.getString(14));
				c.setTeacher_id(rs.getString(15));
				c.setSyllabus_no(rs.getString(16));
				c.setTeacher_name(rs.getString(17)+" "+rs.getString(18));
				list.add(c);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// get the course detail by course id
	@Override
	public Course getCourse(String id) {
		Connection conn;
		PreparedStatement ps;
		DbManager db = new DbManager();
		String sql = "select * from Courses where id = ?;";
		conn = db.getConnection();
		Course c = new Course();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			c.setCid(rs.getInt(1));
			c.setCname(rs.getString(2));
			c.setPrefix(rs.getString(3));
			c.setCno(rs.getString(4));
			c.setSno(rs.getString(5));
			c.setRoom(rs.getString(6));
			c.setCapacity(rs.getInt(7));
			c.setYear(rs.getString(8));
			c.setSemester(rs.getString(9));
			c.setSday(rs.getString(10));
			c.setEday(rs.getString(11));
			c.setDays(rs.getString(12));
			c.setStime(rs.getString(13));
			c.setEtime(rs.getString(14));
			c.setTeacher_id(rs.getString(15));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
		
	}
}
