package modern.io.dao.impl;

import modern.io.dao.studentDAO;
import modern.io.entity.student;
import modern.shared.dto.studentDTO;
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

public class studentDAOImpl implements studentDAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();

    }

    @Override
    public studentDTO getById(String id) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<student> criteria = cb.createQuery(student.class);

        //Query roots always reference entitie
        Root<student> profileRoot = criteria.from(student.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        student student = session.createQuery(criteria).getSingleResult();

        studentDTO studentDTO = new studentDTO();
        BeanUtils.copyProperties(student, studentDTO);

        return studentDTO;
    }

    @Override
    public studentDTO getByCode(String code) {
        // Fetch single result
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<student> criteria = cb.createQuery(student.class);

        //Query roots always reference entitie
        Root<student> profileRoot = criteria.from(student.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("code"), code));

        // Fetch single result
        student student = session.createQuery(criteria).getSingleResult();

        studentDTO studentDTO = new studentDTO();
        BeanUtils.copyProperties(student, studentDTO);

        return studentDTO;
    }

    @Override
    public studentDTO getByCodeAndMac(String code, String mac) {
        // Fetch single result
        Query query = session.createQuery("from student  where :code = code and macAddress = :mac");
        query.setParameter("mac", mac);
        query.setParameter("code", code);
        // Fetch single result
        student student = (student) query.getSingleResult();

        studentDTO studentDTO = new studentDTO();
        BeanUtils.copyProperties(student, studentDTO);

        return studentDTO;
    }


    @Override
    public studentDTO saveOne(studentDTO record) {
        studentDTO returnvalue = null;
        student student1 = new student();
        BeanUtils.copyProperties(record, student1);
        session.beginTransaction();
        session.save(student1);
        session.getTransaction().commit();
        returnvalue = new studentDTO();
        BeanUtils.copyProperties(student1, returnvalue);
        return returnvalue;
    }

    @Override
    public List<studentDTO> getAll(int start, int limit) {
        Query query = session.createQuery("from student order by code").setFirstResult(start).setMaxResults(limit);
        List<student> searchResults = query.list();
        List<studentDTO> returnValue = new ArrayList<>();
        for (student student : searchResults) {
            studentDTO studentDTO = new studentDTO();
            BeanUtils.copyProperties(student, studentDTO);
            returnValue.add(studentDTO);
        }
        return returnValue;

    }

    @Override
    public void updateOne(studentDTO studentDTO) {
        student student = new student();
        BeanUtils.copyProperties(studentDTO, student);
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
    }

    @Override
    public void deleteOne(studentDTO studentDTO) {
        student student = new student();
        BeanUtils.copyProperties(studentDTO, student);
        session.beginTransaction();
        session.delete(student);
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

