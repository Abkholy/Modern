package modern.io.dao.impl;

import modern.io.dao.attTableLinesDAO;
import modern.io.entity.attTableLines;
import modern.shared.dto.attTableLinesDTO;
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

public class attTableLinesDAOImpl implements attTableLinesDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public attTableLinesDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<attTableLines> criteria = cb.createQuery(attTableLines.class);

        //Query roots always reference entitie
        Root<attTableLines> profileRoot = criteria.from(attTableLines.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        attTableLines attTableLines = session.createQuery(criteria).getSingleResult();

        attTableLinesDTO attTableLinesDTO = new attTableLinesDTO();
        BeanUtils.copyProperties(attTableLines, attTableLinesDTO);

        return attTableLinesDTO;
    }

    @Override
    public attTableLinesDTO saveOne(attTableLinesDTO attTableLines) {
        attTableLinesDTO returnvalue = null;
        attTableLines attTableLines$ = new attTableLines();
        BeanUtils.copyProperties(attTableLines, attTableLines$);
        session.beginTransaction();
        session.save(attTableLines$);
        session.getTransaction().commit();

        returnvalue = new attTableLinesDTO();
        BeanUtils.copyProperties(attTableLines$, returnvalue);
        return returnvalue;
    }

    @Override
    public List<attTableLinesDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from attTableLines order by code,date").setFirstResult(start).setMaxResults(limit);

        List<attTableLines> searchResults = query.list();

        List<attTableLinesDTO> returnValue = new ArrayList<attTableLinesDTO>();


        for (attTableLines attTableLines : searchResults) {
            attTableLinesDTO attTableLinesDTO = new attTableLinesDTO();
            BeanUtils.copyProperties(attTableLines, attTableLinesDTO);
            returnValue.add(attTableLinesDTO);
        }

        return returnValue;
    }

    @Override
    public void updateOne(attTableLinesDTO Lines) {
        attTableLines attTableLines = new attTableLines();
        BeanUtils.copyProperties(Lines, attTableLines);

        session.beginTransaction();
        session.update(attTableLines);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(attTableLinesDTO Lines) {
        attTableLines attTableLines = new attTableLines();
        BeanUtils.copyProperties(Lines, attTableLines);

        session.beginTransaction();
        session.delete(attTableLines);
        session.getTransaction().commit();
    }

    @Override
    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }
}
