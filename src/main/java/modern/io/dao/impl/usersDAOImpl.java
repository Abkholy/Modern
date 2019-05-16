package modern.io.dao.impl;

import modern.io.dao.usersDAO;
import modern.io.entity.users;
import modern.shared.dto.usersDTO;
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

public class usersDAOImpl implements usersDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public usersDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<users> criteria = cb.createQuery(users.class);

        //Query roots always reference entitie
        Root<users> profileRoot = criteria.from(users.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        users users = session.createQuery(criteria).getSingleResult();

        usersDTO usersDTO = new usersDTO();
        BeanUtils.copyProperties(users, usersDTO);

        return usersDTO;
    }

    @Override
    public usersDTO getbyEmail(String email) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<users> criteria = cb.createQuery(users.class);

        //Query roots always reference entitie
        Root<users> profileRoot = criteria.from(users.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("email"), email));

        // Fetch single result
        users users = session.createQuery(criteria).getSingleResult();

        usersDTO usersDTO = new usersDTO();
        BeanUtils.copyProperties(users, usersDTO);

        return usersDTO;
    }

    @Override
    public usersDTO saveOne(usersDTO record) {
        usersDTO returnvalue = null;
        users users1 = new users();
        BeanUtils.copyProperties(record, users1);
        session.beginTransaction();
        session.save(users1);
        session.getTransaction().commit();
        returnvalue = new usersDTO();
        BeanUtils.copyProperties(users1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<usersDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from users order by email").setFirstResult(start).setMaxResults(limit);
        List<users> searchResults = query.list();
        List<usersDTO> returnValue = new ArrayList<>();
        for (users users : searchResults) {
            usersDTO usersDTO = new usersDTO();
            BeanUtils.copyProperties(users, usersDTO);
            returnValue.add(usersDTO);
        }
        return returnValue;
    }

    @Override
    public void updateOne(usersDTO usersDTO) {
        users users = new users();
        BeanUtils.copyProperties(usersDTO, users);
        session.beginTransaction();
        session.update(users);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(usersDTO usersDTO) {
        users users = new users();
        BeanUtils.copyProperties(usersDTO, users);
        session.beginTransaction();
        session.delete(users);
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
