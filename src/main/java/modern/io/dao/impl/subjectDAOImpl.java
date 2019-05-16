package modern.io.dao.impl;

import modern.io.dao.subjectDAO;
import modern.io.entity.subject;
import modern.shared.dto.subjectDTO;
import modern.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class subjectDAOImpl implements subjectDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public subjectDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<subject> criteria = cb.createQuery(subject.class);

        //Query roots always reference entitie
        Root<subject> profileRoot = criteria.from(subject.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        subject subject = session.createQuery(criteria).getSingleResult();

        subjectDTO subjectDTO = new subjectDTO();
        BeanUtils.copyProperties(subject, subjectDTO);

        return subjectDTO;
    }

    @Override
    public subjectDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<subject> criteria = cb.createQuery(subject.class);

        //Query roots always reference entitie
        Root<subject> profileRoot = criteria.from(subject.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        subject subject = session.createQuery(criteria).getSingleResult();

        subjectDTO subjectDTO = new subjectDTO();
        BeanUtils.copyProperties(subject, subjectDTO);

        return subjectDTO;
    }

    @Override
    public subjectDTO saveOne(subjectDTO record) {
        subjectDTO returnvalue = null;
        subject subject1 = new subject();
        BeanUtils.copyProperties(record, subject1);
        session.beginTransaction();
        session.save(subject1);
        session.getTransaction().commit();
        returnvalue = new subjectDTO();
        BeanUtils.copyProperties(subject1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<subjectDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from subject order by code").setFirstResult(start).setMaxResults(limit);
        List<subject> searchResults = query.list();
        List<subjectDTO> returnValue = new ArrayList<>();
        for (subject subject : searchResults) {
            subjectDTO subjectDTO = new subjectDTO();
            BeanUtils.copyProperties(subject, subjectDTO);
            returnValue.add(subjectDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(subjectDTO subjectDTO) {
        subject subject = new subject();
        BeanUtils.copyProperties(subjectDTO, subject);
        session.beginTransaction();
        session.update(subject);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(subjectDTO subjectDTO) {
        subject subject = new subject();
        BeanUtils.copyProperties(subjectDTO, subject);
        session.beginTransaction();
        session.delete(subject);
        session.getTransaction().commit();
    }

    @Override
    public void closeConnection() {
        if (session != null) {
            session.clear();
            session.close();
        }
    }
}
