package modern.io.dao.impl;

import modern.io.dao.attTableDAO;
import modern.io.entity.attTable;
import modern.shared.dto.attTableDTO;
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

public class attTableDAOImpl implements attTableDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public attTableDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<attTable> criteria = cb.createQuery(attTable.class);

        //Query roots always reference entitie
        Root<attTable> profileRoot = criteria.from(attTable.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        attTable attTable = session.createQuery(criteria).getSingleResult();

        attTableDTO attTableDTO = new attTableDTO();
        BeanUtils.copyProperties(attTable, attTableDTO);

        return attTableDTO;
    }

    @Override
    public attTableDTO getBy4Fields(Date date, String locationId, String subjectId, int period) {
        // Fetch single result
        Query query = session.createQuery("from attTable \n" +
                "where :date = date \n" +
                "and location.id = :locationId\n" +
                "and subject.id = :subjectId\n" +
                "and period = :period");
        query.setParameter("date", date);
        query.setParameter("locationId", locationId);
        query.setParameter("subjectId", subjectId);
        query.setParameter("period", period);
        CriteriaBuilder cb = session.getCriteriaBuilder();

        attTable attTable = (attTable) query.getSingleResult();

        attTableDTO dtos = new attTableDTO();
        BeanUtils.copyProperties(attTable, dtos);

        return dtos;
    }

    @Override
    public attTableDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<attTable> criteria = cb.createQuery(attTable.class);

        //Query roots always reference entitie
        Root<attTable> profileRoot = criteria.from(attTable.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        attTable attTable = session.createQuery(criteria).getSingleResult();

        attTableDTO attTableDTO = new attTableDTO();
        BeanUtils.copyProperties(attTable, attTableDTO);

        return attTableDTO;
    }


    @Override
    public attTableDTO saveOne(attTableDTO attTable) {
        attTableDTO returnvalue = null;
        attTable attTable$ = new attTable();
        BeanUtils.copyProperties(attTable, attTable$);
        session.beginTransaction();
        session.save(attTable$);
        session.getTransaction().commit();

        returnvalue = new attTableDTO();
        BeanUtils.copyProperties(attTable$, returnvalue);
        return returnvalue;
    }

    @Override
    public List<attTableDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from attTable order by code").setFirstResult(start).setMaxResults(limit);
        List<attTable> searchResults = query.list();
        List<attTableDTO> returnValue = new ArrayList<>();
        for (attTable attTable : searchResults) {
            attTableDTO attTableDTO = new attTableDTO();
            BeanUtils.copyProperties(attTable, attTableDTO);
            returnValue.add(attTableDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(attTableDTO attTableDTO) {
        attTable attTable = new attTable();
        BeanUtils.copyProperties(attTableDTO, attTable);

        session.beginTransaction();
        session.update(attTable);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(attTableDTO attTableDTO) {
        attTable attTable = new attTable();
        BeanUtils.copyProperties(attTableDTO, attTable);

        session.beginTransaction();
        session.delete(attTable);
        session.getTransaction().commit();
    }

    @Override
    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }
}
