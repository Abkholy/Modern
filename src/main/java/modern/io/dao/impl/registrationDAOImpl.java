package modern.io.dao.impl;

import modern.io.dao.registrationDAO;
import modern.io.entity.registration;
import modern.shared.dto.registrationDTO;
import modern.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class registrationDAOImpl implements registrationDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public registrationDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<registration> criteria = cb.createQuery(registration.class);

        //Query roots always reference entitie
        Root<registration> profileRoot = criteria.from(registration.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        registration registration = session.createQuery(criteria).getSingleResult();

        registrationDTO registrationDTO = new registrationDTO();
        BeanUtils.copyProperties(registration, registrationDTO);

        return registrationDTO;
    }

    @Override
    public registrationDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<registration> criteria = cb.createQuery(registration.class);

        //Query roots always reference entitie
        Root<registration> profileRoot = criteria.from(registration.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        registration registration = session.createQuery(criteria).getSingleResult();

        registrationDTO registrationDTO = new registrationDTO();
        BeanUtils.copyProperties(registration, registrationDTO);

        return registrationDTO;
    }

    @Override
    public registrationDTO getByStudentId(String studentId, Date date) {
        Query query = session.createQuery("from registration " +
                "where student.id =:id and (:date between fromDate and toDate)");
        query.setParameter("date", date);
        query.setParameter("id", studentId);
        registration lines = (registration) query.getSingleResult();

        registrationDTO dtos = new registrationDTO();
        BeanUtils.copyProperties(lines, dtos);
//        reg = (registration) query.b();
        // Fetch single result

        return dtos;
    }

    @Override
    public registrationDTO saveOne(registrationDTO record) {
        registrationDTO returnvalue = null;
        registration registration1 = new registration();
        BeanUtils.copyProperties(record, registration1);
        session.beginTransaction();
        session.save(registration1);
        session.getTransaction().commit();
        returnvalue = new registrationDTO();
        BeanUtils.copyProperties(registration1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<registrationDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from registration order by code").setFirstResult(start).setMaxResults(limit);
        List<registration> searchResults = query.list();
        List<registrationDTO> returnValue = new ArrayList<>();
        for (registration registration : searchResults) {
            registrationDTO registrationDTO = new registrationDTO();
            BeanUtils.copyProperties(registration, registrationDTO);
            returnValue.add(registrationDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(registrationDTO registrationDTO) {
        registration registration = new registration();
        BeanUtils.copyProperties(registrationDTO, registration);
        session.beginTransaction();
        session.update(registration);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(registrationDTO registrationDTO) {
        registration registration = new registration();
        BeanUtils.copyProperties(registrationDTO, registration);
        session.beginTransaction();
        session.delete(registration);
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
