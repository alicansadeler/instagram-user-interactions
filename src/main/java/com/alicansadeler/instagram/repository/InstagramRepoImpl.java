package com.alicansadeler.instagram.repository;

import com.alicansadeler.instagram.entity.Reaction;
import com.alicansadeler.instagram.entity.UserData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class InstagramRepoImpl implements InstagramRepo {

    private final EntityManager entityManager;

    @Autowired
    public InstagramRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserData save(UserData userData) {
        entityManager.persist(userData);
        return userData;
    }

    @Override
    public List<UserData> getAllUser() {
        TypedQuery<UserData> query = entityManager.createQuery(
                "SELECT s FROM UserData s", UserData.class
        );
        return query.getResultList();
    }

    @Override
    public List<UserData> getUserReactions(Reaction reaction) {
        TypedQuery<UserData> query = entityManager.createQuery(
                "SELECT DISTINCT ud FROM UserData ud " +
                        "INNER JOIN UserReaction ur ON ud.id = ur.user.id " +
                        "WHERE ur.reactionType = :reaction AND ur.post IS NOT NULL", UserData.class
        );
        query.setParameter("reaction", reaction);
        return query.getResultList();
    }


    @Override
    public List<Object[]> getUserByReactionOfPost(Integer postID) {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT us.name, us.surname, ur.post.id, ur.comment.id " +
                        "FROM UserReaction ur " +
                        "INNER JOIN UserData us " +
                        "ON ur.user.id = us.id " +
                        "WHERE ur.user.id = :postID", Object[].class
        );
        query.setParameter("postID", postID);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getCountOfReaction(Integer postID) {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT ur.post.id, COUNT(ur.post.id) " +
                        "FROM UserReaction ur " +
                        "INNER JOIN UserData us " +
                        "ON ur.user.id = us.id " +
                        "WHERE ur.post.id = :postID " +
                        "GROUP BY ur.post.id", Object[].class
        );
        query.setParameter("postID", postID);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getMostReaction() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT ur.post.id, us.name, us.surname, COUNT(ur.post.id) AS reactionCount " +
                        "FROM UserReaction ur " +
                        "INNER JOIN UserData us ON ur.user.id = us.id " +
                        "WHERE ur.post.id IS NOT NULL " +
                        "GROUP BY ur.post.id, us.name, us.surname " +
                        "ORDER BY reactionCount DESC", Object[].class
        );
        query.setMaxResults(3);
        return query.getResultList();
    }
}