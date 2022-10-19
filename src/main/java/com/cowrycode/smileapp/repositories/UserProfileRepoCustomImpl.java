package com.cowrycode.smileapp.repositories;

import com.cowrycode.smileapp.domains.UserProfileEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class UserProfileRepoCustomImpl implements UserProfileRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    CriteriaBuilder cb;

    @Override
    public List<UserProfileEntity> getTopPerformers() {
        try {
            cb = entityManager.getCriteriaBuilder();

            CriteriaQuery<UserProfileEntity> query = cb.createQuery(UserProfileEntity.class);
            Root<UserProfileEntity> patientProfileRoot = query.from(UserProfileEntity.class);
            query.orderBy(cb.asc(patientProfileRoot.get("accumulatedValue")).reverse());
            CriteriaQuery<UserProfileEntity> select = query.select(patientProfileRoot);

            Path<String> voided = patientProfileRoot.get("voided");

            Predicate voidPredicate = cb.equal(voided, false);


            select.where(voidPredicate);

            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}