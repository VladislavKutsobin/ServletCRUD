package app.repository.implementations;

import app.model.Skill;
import app.repository.SkillRepository;
import org.hibernate.Session;
import app.util.*;

import java.util.List;

public class SkillRepositoryImplementation implements SkillRepository {
    @Override
    public void save(Skill skill) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(skill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Skill> findAll() {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        String hql = "from Skill";
        List<Skill> skillList = session.createQuery(hql, Skill.class).getResultList();
        session.close();
        return skillList;
    }

    @Override
    public void update(Skill skill) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        int id = skill.getId();
        String newSkillName = skill.getName();

        session.beginTransaction();
        Skill skillToUpdate = session.get(Skill.class, id);
        skillToUpdate.setName(newSkillName);
        session.update(skillToUpdate);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer integer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Skill skillToDelete = session.get(Skill.class, integer);
        session.delete(skillToDelete);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Skill getById(Integer integer) {
        Session session = ConnectionUtil.getSessionFactory().openSession();
        Skill skillToShow = session.get(Skill.class, integer);
        session.close();
        return skillToShow;
    }
}
