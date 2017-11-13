package pl.poznan.put.student.spacjalive.erp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.entity.Lending;

import java.util.List;

@Repository
public class LendingDAOImpl implements LendingDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Lending> getLendings() {
        Session session = sessionFactory.getCurrentSession();

        Query<Lending> query = session.createQuery("FROM Lending ");

        List<Lending> lendings = query.getResultList();

        return lendings;
    }

    @Override
    public void saveLending(Lending lending) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(lending);
    }

    @Override
    public void deleteLending(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("DELETE FROM Lending WHERE id=:lendingId");

        query.setParameter("lendingId", id);

        query.executeUpdate();
    }

    @Override
    public Lending getLending(int id) {
        Session session = sessionFactory.getCurrentSession();

        Lending lending = session.get(Lending.class, id);

        return lending;
    }
}
