package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;


import java.util.List;

public class MyTribeMessageRepoCustomImpl implements MyTribeMessageRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    CriteriaBuilder cb;


    @Override
    public List<MyTribeMessageEntity> getUnreadSmilePacks(String userID, String userName,  boolean isread) {
        try {
            cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<MyTribeMessageEntity> query = cb.createQuery(MyTribeMessageEntity.class);
            Root<MyTribeMessageEntity> tribemessageroot = query.from(MyTribeMessageEntity.class);
            query.orderBy(cb.desc(tribemessageroot.get("id")));
            CriteriaQuery<MyTribeMessageEntity> select = query.select(tribemessageroot);

            Path<String> user = tribemessageroot.get("receiverID");

            Path<String> read = tribemessageroot.get("isread");
//            Path<Boolean> isapproved = tribemessageroot.get("isapproved");


            Predicate useridPredicate = cb.equal(user, userID);
            Predicate userNamePredicate = cb.equal(user, userName);

            Predicate identifierPredicate = cb.or(useridPredicate, userNamePredicate);

            Predicate readPredicate = cb.equal(read, isread);
//            Predicate approvePredicate = cb.equal(isapproved, true);

           // select.where(useridPredicate, readPredicate, approvePredicate);
            select.where(identifierPredicate, readPredicate);
            //TODO: IMPLEMENT PAGINATION
            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
