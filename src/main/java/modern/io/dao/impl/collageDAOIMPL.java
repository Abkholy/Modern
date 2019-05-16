package modern.io.dao.impl;

import modern.io.dao.collageDAO;
import modern.io.entity.collage;
import modern.shared.dto.collageDTO;
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

public class    collageDAOIMPL implements collageDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public collageDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<collage> criteria = cb.createQuery(collage.class);

        //Query roots always reference entitie
        Root<collage> profileRoot = criteria.from(collage.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        collage collage = session.createQuery(criteria).getSingleResult();

        collageDTO collageDTO = new collageDTO();
        BeanUtils.copyProperties(collage, collageDTO);

        return collageDTO;
    }

    @Override
    public collageDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<collage> criteria = cb.createQuery(collage.class);

        //Query roots always reference entitie
        Root<collage> profileRoot = criteria.from(collage.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        collage collage = session.createQuery(criteria).getSingleResult();

        collageDTO collageDTO = new collageDTO();
        BeanUtils.copyProperties(collage, collageDTO);

        return collageDTO;
    }

    @Override
    public collageDTO saveOne(collageDTO collage) {
        collageDTO returnvalue = null;
        collage collage1 = new collage();
        BeanUtils.copyProperties(collage, collage1);
        session.beginTransaction();
        session.save(collage1);
        session.getTransaction().commit();
        returnvalue = new collageDTO();
        BeanUtils.copyProperties(collage1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<collageDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from collage order by code").setFirstResult(start).setMaxResults(limit);
        List<collage> searchResults = query.list();
        List<collageDTO> returnValue = new ArrayList<>();
        for (collage collage : searchResults) {
            collageDTO collageDTO = new collageDTO();
            BeanUtils.copyProperties(collage, collageDTO);
            returnValue.add(collageDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(collageDTO collageDTO) {
        collage collage = new collage();
        BeanUtils.copyProperties(collageDTO, collage);
        session.beginTransaction();
        session.update(collage);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(collageDTO collageDTO) {
        collage collage = new collage();
        BeanUtils.copyProperties(collageDTO, collage);
        session.beginTransaction();
        session.delete(collage);
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
