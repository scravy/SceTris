package scheduler.dao.tests;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.Assert;
import org.junit.Test;
import scheduler.dao.HibernateUtil;
import scheduler.data.*;

public class DAOTest {

    Session session;
    Transaction transaction = null;

    Lecturer lecturer = null;
    Course course = null;
    Room room = null;
    
    Integer lecturerID;
    Integer courseID;
    Integer roomID;

    @Test
    public void testSaveAndDeleteLecturer() {

	session = HibernateUtil.getSessionFactory().openSession();
	
	try {
	    transaction = session.beginTransaction();
	    lecturer = testClasses.lecturer;
	    lecturerID = (Integer) session.save(lecturer);
	    transaction.commit();

	    Assert.assertTrue(lecturerID != null);

	    transaction = session.beginTransaction();
	    lecturer = (Lecturer)session.get(Lecturer.class, lecturerID);
	    session.delete(lecturer);
	    transaction.commit();

	    lecturer = (Lecturer)session.get(Lecturer.class, lecturerID);
	    Assert.assertTrue(lecturer == null);

	} catch (HibernateException e) {
	    transaction.rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}

    }

    @Test
    public void testSaveAndDeleteCourse() {

	session = HibernateUtil.getSessionFactory().openSession();
	
	try {
	    transaction = session.beginTransaction();
	    lecturerID = (Integer) session.save(testClasses.lecturer);
	    courseID = (Integer) session.save(testClasses.course);
	    transaction.commit();

	    Assert.assertTrue(courseID != null);

	    transaction = session.beginTransaction();
	    Lecturer lecturer = (Lecturer) session.get(Lecturer.class,lecturerID);
	    Course course = (Course) session.get(Course.class, courseID);
	    session.delete(course);
	    session.delete(lecturer);
	    transaction.commit();

	    lecturer = (Lecturer) session.get(Lecturer.class, lecturerID);
	    Assert.assertTrue(lecturer == null);

	} catch (HibernateException e) {
	    transaction.rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
    }

    @Test
    public void testSaveAndDeleteRoom() {

	session = HibernateUtil.getSessionFactory().openSession();
	transaction = null;
	
	try {
	    transaction = session.beginTransaction();
	    room = testClasses.room;
	    roomID = (Integer) session.save(room);
	    transaction.commit();

	    Assert.assertTrue(roomID != null);

	    transaction = session.beginTransaction();
	    room = (Room)session.get(Room.class, roomID);
	    session.delete(room);
	    transaction.commit();

	    room = (Room)session.get(Room.class, roomID);
	    Assert.assertTrue(room == null);

	} catch (HibernateException e) {
	    transaction.rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}

    }

}
