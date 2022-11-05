package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class EmpathyRequestRepoCustomImpl implements EmpathyRequestRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    CriteriaBuilder cb;

    @Override
    public List<EmpathyRequestEntity> getUnrespondedMessages(Long userID) {
        try {
            cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<EmpathyRequestEntity> query = cb.createQuery(EmpathyRequestEntity.class);
            Root<EmpathyRequestEntity> empathyrequest = query.from(EmpathyRequestEntity.class);
            query.orderBy(cb.desc(empathyrequest.get("id")));
            CriteriaQuery<EmpathyRequestEntity> select = query.select(empathyrequest);

            Path<String> users = empathyrequest.get("respondedUsersIDs");
            Path<String> senders = empathyrequest.get("senderID");


           // Predicate useridPredicate = cb.like(users, "%" + userID + "%" );
            Predicate useridPredicate = cb.notLike(users, "%" + userID + "%" );
            Predicate sendersPredicate = cb.notEqual(senders,  userID );
            select.where(useridPredicate, sendersPredicate);
            //TODO: IMPLEMENT PAGINATION
            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
