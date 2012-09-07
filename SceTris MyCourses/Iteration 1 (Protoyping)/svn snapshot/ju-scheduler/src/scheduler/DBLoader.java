package scheduler;

import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import scheduler.dao.HibernateUtil;
import scheduler.data.Course;
import scheduler.data.Lecturer;
import scheduler.data.Room;

public class DBLoader {

	Session session;
	Transaction transaction;

	private Scheduler scheduler;

	public DBLoader() {
		this.scheduler = new Scheduler();
	}

	public DBLoader(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * Creates a new Scheduler and loads data into it.
	 */
	public void loadData() {

		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();

		loadLecturer();
		loadRooms();
		loadCourses();

		session.close();
	}

	private void loadLecturer() {

		transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Lecturer.class);
		Iterator<?> it = criteria.list().iterator();

		while (it.hasNext()) {
			Lecturer l = (Lecturer) it.next();
			this.scheduler.createLecturer(l.getName());
		}
		transaction.commit();

	}

	public void loadCourses() {

		transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Course.class);

		Iterator<?> it = criteria.list().iterator();

		while (it.hasNext()) {
			Course c = (Course) it.next();
			this.scheduler.createCourse(c.getName(), c.getLecturer(),
					c.getSeats(), c.getDuration());
		}
		transaction.commit();
	}

	public void loadRooms() {

		transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(Room.class);

		Iterator<?> it = criteria.list().iterator();

		while (it.hasNext()) {
			Room r = (Room) it.next();
			this.scheduler.createRoom(r.getName(), r.getCapacity());
		}

		transaction.commit();

	}

}