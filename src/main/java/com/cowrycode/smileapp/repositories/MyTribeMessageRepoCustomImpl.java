package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.MyTribeMessageEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class MyTribeMessageRepoCustomImpl implements MyTribeMessageRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    CriteriaBuilder cb;


    @Override
    public List<MyTribeMessageEntity> getUnreadSmilePacks(String userID,  boolean isread) {
        try {
            cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<MyTribeMessageEntity> query = cb.createQuery(MyTribeMessageEntity.class);
            Root<MyTribeMessageEntity> tribemessageroot = query.from(MyTribeMessageEntity.class);
            query.orderBy(cb.desc(tribemessageroot.get("id")));
            CriteriaQuery<MyTribeMessageEntity> select = query.select(tribemessageroot);

            Path<String> user = tribemessageroot.get("receiverID");
            Path<String> read = tribemessageroot.get("isread");

            Predicate useridPredicate = cb.equal(user, userID);
            Predicate readPredicate = cb.equal(read, isread);

            select.where(useridPredicate, readPredicate);
            //TODO: IMPLEMENT PAGINATION
            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
