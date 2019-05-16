package modern.io.dao.impl;

import modern.io.dao.semesterDAO;
import modern.io.entity.semester;
import modern.shared.dto.semesterDTO;
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

public class semesterDAOImpl implements semesterDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public semesterDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<semester> criteria = cb.createQuery(semester.class);

        //Query roots always reference entitie
        Root<semester> profileRoot = criteria.from(semester.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        semester semester = session.createQuery(criteria).getSingleResult();

        semesterDTO semesterDTO = new semesterDTO();
        BeanUtils.copyProperties(semester, semesterDTO);

        return semesterDTO;
    }

    @Override
    public semesterDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<semester> criteria = cb.createQuery(semester.class);

        //Query roots always reference entitie
        Root<semester> profileRoot = criteria.from(semester.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        semester semester = session.createQuery(criteria).getSingleResult();

        semesterDTO semesterDTO = new semesterDTO();
        BeanUtils.copyProperties(semester, semesterDTO);

        return semesterDTO;
    }

    @Override
    public semesterDTO saveOne(semesterDTO semester) {
        semesterDTO returnvalue = null;
        semester semester1 = new semester();
        BeanUtils.copyProperties(semester, semester1);
        session.beginTransaction();
        session.save(semester1);
        session.getTransaction().commit();
        returnvalue = new semesterDTO();
        BeanUtils.copyProperties(semester1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<semesterDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from semester order by code").setFirstResult(start).setMaxResults(limit);
        List<semester> searchResults = query.list();
        List<semesterDTO> returnValue = new ArrayList<>();
        for (semester semester : searchResults) {
            semesterDTO semesterDTO = new semesterDTO();
            BeanUtils.copyProperties(semester, semesterDTO);
            returnValue.add(semesterDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(semesterDTO semesterDTO) {
        semester semester = new semester();
        BeanUtils.copyProperties(semesterDTO, semester);
        session.beginTransaction();
        session.update(semester);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(semesterDTO semesterDTO) {
        semester semester = new semester();
        BeanUtils.copyProperties(semesterDTO, semester);
        session.beginTransaction();
        session.delete(semester);
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
