package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.EmpathyRequestEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class EmpathyRequestRepoCustomImpl implements EmpathyRequestRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    CriteriaBuilder cb;

    @Override
    public List<EmpathyRequestEntity> getUnrespondedMessages(String userID, String usernaame) {
        try {
            cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<EmpathyRequestEntity> query = cb.createQuery(EmpathyRequestEntity.class);
            Root<EmpathyRequestEntity> empathyrequest = query.from(EmpathyRequestEntity.class);
            query.orderBy(cb.desc(empathyrequest.get("id")));
            CriteriaQuery<EmpathyRequestEntity> select = query.select(empathyrequest);

            Path<String> users = empathyrequest.get("respondedUsersIDs");
            Path<String> senders = empathyrequest.get("senderID");


            Path<LocalDateTime> date_created = empathyrequest.get("dateCreated");

            LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
            LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

            Predicate dateDD = cb.between(date_created, start, end);

            Predicate useridPredicate = cb.notLike(users, "%" + userID + "%" );
            Predicate sendersPredicate = cb.notEqual(senders,  userID );

            Predicate userNamePredicate = cb.notLike(users, "%" + usernaame + "%" );
            Predicate sendersNamePredicate = cb.notEqual(senders,  usernaame );

            select.where(useridPredicate, sendersPredicate, userNamePredicate, sendersNamePredicate, dateDD);
            //TODO: IMPLEMENT PAGINATION
            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
