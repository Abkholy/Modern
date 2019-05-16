package modern.io.dao.impl;

import modern.io.dao.locationDAO;
import modern.io.entity.location;
import modern.shared.dto.locationDTO;
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

public class locationDAOImpl implements locationDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public locationDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<location> criteria = cb.createQuery(location.class);

        //Query roots always reference entitie
        Root<location> profileRoot = criteria.from(location.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        location location = session.createQuery(criteria).getSingleResult();

        locationDTO locationDTO = new locationDTO();
        BeanUtils.copyProperties(location, locationDTO);

        return locationDTO;
    }

    @Override
    public locationDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<location> criteria = cb.createQuery(location.class);

        //Query roots always reference entitie
        Root<location> profileRoot = criteria.from(location.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        location location = session.createQuery(criteria).getSingleResult();

        locationDTO locationDTO = new locationDTO();
        BeanUtils.copyProperties(location, locationDTO);

        return locationDTO;
    }


    @Override
    public locationDTO getByMacAddress(String macAddress) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<location> criteria = cb.createQuery(location.class);

        //Query roots always reference entitie
        Root<location> profileRoot = criteria.from(location.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("scannerMacAddress"), macAddress));

        // Fetch single result
        location location = session.createQuery(criteria).getSingleResult();

        locationDTO locationDTO = new locationDTO();
        BeanUtils.copyProperties(location, locationDTO);

        return locationDTO;
    }

    @Override
    public locationDTO saveOne(locationDTO location) {
        locationDTO returnvalue = null;
        location location1 = new location();
        BeanUtils.copyProperties(location, location1);
        session.beginTransaction();
        session.save(location1);
        session.getTransaction().commit();
        returnvalue = new locationDTO();
        BeanUtils.copyProperties(location1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<locationDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from location order by code").setFirstResult(start).setMaxResults(limit);
        List<location> searchResults = query.list();
        List<locationDTO> returnValue = new ArrayList<>();
        for (location location : searchResults) {
            locationDTO locationDTO = new locationDTO();
            BeanUtils.copyProperties(location, locationDTO);
            returnValue.add(locationDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(locationDTO locationDTO) {
        location location = new location();
        BeanUtils.copyProperties(locationDTO, location);
        session.beginTransaction();
        session.update(location);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(locationDTO locationDTO) {
        location location = new location();
        BeanUtils.copyProperties(locationDTO, location);
        session.beginTransaction();
        session.delete(location);
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
