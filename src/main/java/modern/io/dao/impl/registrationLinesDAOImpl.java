package modern.io.dao.impl;

import modern.io.dao.registrationLinesDAO;
import modern.io.entity.registrationLines;
import modern.shared.dto.registrationLinesDTO;
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

public class registrationLinesDAOImpl implements registrationLinesDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public registrationLinesDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<registrationLines> criteria = cb.createQuery(registrationLines.class);

        //Query roots always reference entitie
        Root<registrationLines> profileRoot = criteria.from(registrationLines.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        registrationLines registrationLines = session.createQuery(criteria).getSingleResult();

        registrationLinesDTO registrationLinesDTO = new registrationLinesDTO();
        BeanUtils.copyProperties(registrationLines, registrationLinesDTO);

        return registrationLinesDTO;
    }


    @Override
    public registrationLinesDTO saveOne(registrationLinesDTO record) {
        registrationLinesDTO returnvalue = null;
        registrationLines reg = new registrationLines();
        BeanUtils.copyProperties(record, reg);
        session.beginTransaction();
        session.save(reg);
        session.getTransaction().commit();
        returnvalue = new registrationLinesDTO();
        BeanUtils.copyProperties(reg, returnvalue);
        return returnvalue;
    }

    @Override
    public List<registrationLinesDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from registrationLines ").setFirstResult(start).setMaxResults(limit);
        List<registrationLines> searchResults = query.list();
        List<registrationLinesDTO> returnValue = new ArrayList<>();
        for (registrationLines registrationLines : searchResults) {
            registrationLinesDTO registrationLinesDTO = new registrationLinesDTO();
            BeanUtils.copyProperties(registrationLines, registrationLinesDTO);
            returnValue.add(registrationLinesDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(registrationLinesDTO registrationLinesDTO) {
        registrationLines registrationLines = new registrationLines();
        BeanUtils.copyProperties(registrationLinesDTO, registrationLines);
        session.beginTransaction();
        session.update(registrationLines);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(registrationLinesDTO registrationLinesDTO) {
        registrationLines registrationLines = new registrationLines();
        BeanUtils.copyProperties(registrationLinesDTO, registrationLines);
        session.beginTransaction();
        session.delete(registrationLines);
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
