package scheduler.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import scheduler.Scheduler;
import scheduler.data.*;

public class DBConnection {

	public static void commitFinalSchedule(Scheduler scheduler) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Integer ID = null;

		try {

			for (Lecturer lecturer : scheduler.getLecturers()) {
				ID = (Integer) session.save(lecturer);
				if (ID == null) {
					throw new HibernateException("Lecturer could not be saved.");
				}
			}

			for (Room room : scheduler.getRooms()) {
				ID = (Integer) session.save(room);
				if (ID == null) {
					throw new HibernateException("Room could not be saved.");
				}
			}

			for (Course course : scheduler.getCourses()) {
				ID = (Integer) session.save(course);
				if (ID == null) {
					throw new HibernateException("Course could not be saved.");
				}
			}

//			for (ScheduleEntry scheduleEntry : scheduler.getScheduleEntries()) {
//				ID = (Integer) session.save(scheduleEntry);
//				if (ID == null) {
//					throw new HibernateException(
//							"Schedule Entry could not be saved.");
//				}
//			}

			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
