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



            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

//  cb = entityManager.getCriteriaBuilder();
//          CriteriaQuery<PatientProfile> query = cb.createQuery(PatientProfile.class);
//        Root<PatientProfile> patientProfileRoot = query.from(PatientProfile.class);
//
//        Path<String> firstname = patientProfileRoot.get("firstName");
//        Path<String> lastname = patientProfileRoot.get("lastName");
//        Path<Integer> age = patientProfileRoot.get("age");
//
//
//        Predicate firstNamePredicate = cb.equal(firstname, firstName);
//        Predicate lastPredicate = cb.equal(lastname, lastNaame);
//        Integer objAge = Integer.valueOf(patientAge);
//        Predicate agePredicate = cb.equal(age, objAge);
//
//        query.select(patientProfileRoot)
//        .where(firstNamePredicate, lastPredicate, agePredicate);
//
//        return entityManager.createQuery(query).getResultList();