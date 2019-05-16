package modern.io.dao.impl;

import modern.io.dao.timeTableDAO;
import modern.io.entity.timeTable;
import modern.shared.dto.timeTableDTO;
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

public class timeTableDAOImpl implements timeTableDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public timeTableDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<timeTable> criteria = cb.createQuery(timeTable.class);

        //Query roots always reference entitie
        Root<timeTable> profileRoot = criteria.from(timeTable.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        timeTable timeTable = session.createQuery(criteria).getSingleResult();

        timeTableDTO timeTableDTO = new timeTableDTO();
        BeanUtils.copyProperties(timeTable, timeTableDTO);

        return timeTableDTO;
    }

    @Override
    public timeTableDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<timeTable> criteria = cb.createQuery(timeTable.class);

        //Query roots always reference entitie
        Root<timeTable> profileRoot = criteria.from(timeTable.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        timeTable timeTable = session.createQuery(criteria).getSingleResult();

        timeTableDTO timeTableDTO = new timeTableDTO();
        BeanUtils.copyProperties(timeTable, timeTableDTO);

        return timeTableDTO;
    }

    @Override
    public timeTableDTO saveOne(timeTableDTO record) {
        timeTableDTO returnvalue = null;
        timeTable timeTable1 = new timeTable();
        BeanUtils.copyProperties(record, timeTable1);
        session.beginTransaction();
        session.save(timeTable1);
        session.getTransaction().commit();
        returnvalue = new timeTableDTO();
        BeanUtils.copyProperties(timeTable1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<timeTableDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from timeTable order by code").setFirstResult(start).setMaxResults(limit);
        List<timeTable> searchResults = query.list();
        List<timeTableDTO> returnValue = new ArrayList<>();
        for (timeTable timeTable : searchResults) {
            timeTableDTO timeTableDTO = new timeTableDTO();
            BeanUtils.copyProperties(timeTable, timeTableDTO);
            returnValue.add(timeTableDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(timeTableDTO timeTableDTO) {
        timeTable timeTable = new timeTable();
        BeanUtils.copyProperties(timeTableDTO, timeTable);
        session.beginTransaction();
        session.update(timeTable);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(timeTableDTO timeTableDTO) {
        timeTable timeTable = new timeTable();
        BeanUtils.copyProperties(timeTableDTO, timeTable);
        session.beginTransaction();
        session.delete(timeTable);
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
