package app.repository.implementations;

import app.model.Developer;
import app.repository.DeveloperRepository;
import app.util.ConnectionUtil;
import org.hibernate.Session;

import java.util.List;

public class DeveloperRepositoryImplementation implements DeveloperRepository {
    @Override
    public void save(Developer developer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(developer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Developer> findAll() {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        String hql = "from Developer";

        List<Developer> developerList = session.createQuery(hql, Developer.class).getResultList();
        session.close();
        return developerList;
    }

    @Override
    public void update(Developer developer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        String newFirstName = developer.getFirstName();
        String newLastName = developer.getLastName();
        String newSpecialty = developer.getSpecialty();
        int id = developer.getId();

        session.beginTransaction();
        Developer developerToSave = session.get(Developer.class, id);
        developerToSave.setFirstName(newFirstName);
        developerToSave.setLastName(newLastName);
        developerToSave.setSpecialty(newSpecialty);
        session.update(developerToSave);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer integer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Developer developerToDelete = session.get(Developer.class, integer);
        session.delete(developerToDelete);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Developer getById(Integer integer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        Developer developerToShow = session.get(Developer.class, integer);
        session.close();
        return developerToShow;
    }
}
