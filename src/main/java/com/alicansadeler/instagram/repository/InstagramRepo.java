package com.alicansadeler.instagram.repository;

import com.alicansadeler.instagram.entity.Reaction;
import com.alicansadeler.instagram.entity.UserData;


import java.util.List;


public interface InstagramRepo {
    // yeni kullanıcı oluştur
    UserData save(UserData userData);

    // userları getir
    List<UserData> getAllUser();

    // reaksiyona göre getir
    List<UserData> getUserReactions(Reaction reaction);

    // X postuna reaksiyon gösteren kullanıcılar
    List<Object[]> getUserByReactionOfPost(Integer postID);

    // X postuna gelen toplam reaksiyon sayısı
    List<Object[]> getCountOfReaction(Integer postID);

    // en çok reaksiyon alan ilk 3 post
    List<Object[]> getMostReaction();

    // verilen tarih aralığında X reaksiyonuna sahip postları getir.
}
