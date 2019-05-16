package modern.io.dao.impl;

import modern.io.dao.locationTypeDAO;
import modern.io.entity.locationType;
import modern.shared.dto.locationTypeDTO;
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

public class locationTypeDAOImpl implements locationTypeDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public locationTypeDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<locationType> criteria = cb.createQuery(locationType.class);

        //Query roots always reference entitie
        Root<locationType> profileRoot = criteria.from(locationType.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        locationType locationType = session.createQuery(criteria).getSingleResult();

        locationTypeDTO locationTypeDTO = new locationTypeDTO();
        BeanUtils.copyProperties(locationType, locationTypeDTO);

        return locationTypeDTO;
    }

    @Override
    public locationTypeDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<locationType> criteria = cb.createQuery(locationType.class);

        //Query roots always reference entitie
        Root<locationType> profileRoot = criteria.from(locationType.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        locationType locationType = session.createQuery(criteria).getSingleResult();

        locationTypeDTO locationTypeDTO = new locationTypeDTO();
        BeanUtils.copyProperties(locationType, locationTypeDTO);

        return locationTypeDTO;
    }

    @Override
    public locationTypeDTO saveOne(locationTypeDTO locationType) {
        locationTypeDTO returnvalue = null;
        locationType locationType1 = new locationType();
        BeanUtils.copyProperties(locationType, locationType1);
        session.beginTransaction();
        session.save(locationType1);
        session.getTransaction().commit();
        returnvalue = new locationTypeDTO();
        BeanUtils.copyProperties(locationType1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<locationTypeDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from locationType ").setFirstResult(start).setMaxResults(limit);
        List<locationType> searchResults = query.list();
        List<locationTypeDTO> returnValue = new ArrayList<>();
        for (locationType locationType : searchResults) {
            locationTypeDTO locationTypeDTO = new locationTypeDTO();
            BeanUtils.copyProperties(locationType, locationTypeDTO);
            returnValue.add(locationTypeDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(locationTypeDTO locationTypeDTO) {
        locationType locationType = new locationType();
        BeanUtils.copyProperties(locationTypeDTO, locationType);
        session.beginTransaction();
        session.update(locationType);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(locationTypeDTO locationTypeDTO) {
        locationType locationType = new locationType();
        BeanUtils.copyProperties(locationTypeDTO, locationType);
        session.beginTransaction();
        session.delete(locationType);
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
