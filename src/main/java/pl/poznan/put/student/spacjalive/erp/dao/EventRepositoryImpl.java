package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Event;
import pl.poznan.put.student.spacjalive.erp.exceptions.NotFoundException;

import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Event> getEvents() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Event> query = session.createQuery("FROM Event e ORDER BY e.date ASC ", Event.class);
		List<Event> events = query.getResultList();
		
		return events;
	}
	
	@Override
	public List<Event> getEvents(Boolean archived, Boolean published) {
		Session session = sessionFactory.getCurrentSession();
		
		String query = "FROM Event ";
		if(archived != null || published != null) {
			query += "WHERE ";
			boolean and = false;
			if(archived != null) {
				query += "archived=:archived ";
				and = true;
			}
			
			if(published != null) {
				if(and)
					query += "and ";
				query += "published=:published ";
			}
		}
		query += "ORDER BY date ASC ";
		Query<Event> q = session.createQuery(query, Event.class);
		if(archived != null)
			q.setParameter("archived", archived);
		if(published != null)
			q.setParameter("published", published);
		
		return q.getResultList();
	}
	
	@Override
	public void saveEvent(Event event) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(event);
	}
	
	@Override
	public void deleteEvent(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("DELETE FROM Event WHERE id=:eventId");
		query.setParameter("eventId", id);
		
		query.executeUpdate();
	}
	
	@Override
	public Event getEvent(int id) throws NotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Event event = session.get(Event.class, id);
		if(event == null)
			throw new NotFoundException();
		return event;
	}
}
