package com.alicansadeler.instagram.util;

import com.alicansadeler.instagram.entity.Reaction;
import com.alicansadeler.instagram.entity.UserData;
import com.alicansadeler.instagram.exceptions.ApiException;
import com.alicansadeler.instagram.repository.InstagramRepo;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InstaValidasyon {

    private static InstagramRepo manager;

    public static void userValid(UserData userData) {
        if (userData.getName() == null && userData.getSurname() == null) {
            throw new ApiException("İsim ve Soyisim alanı boş bırakılamaz", HttpStatus.BAD_REQUEST);
        }
    }

    public static void allUserValid() {
        List<UserData> dataList = manager.getAllUser();
        if (dataList.isEmpty()) {
            throw new ApiException("Kayıtlı kullanıcı bulunamadı.", HttpStatus.NOT_FOUND);
        }
    }

    public static void postReactionValid(Reaction reaction) {

        Set<Reaction> setList = new HashSet<>();
        setList.add(Reaction.CARE);
        setList.add(Reaction.LOVE);
        setList.add(Reaction.FUNNY);
        setList.add(Reaction.SAD);

        if (!setList.contains(reaction)) {
            throw new ApiException("Geçersiz reaksiyon girişi. Reaksiyon: " + reaction, HttpStatus.BAD_REQUEST);
        }

    }

    public static void idValid(Integer id) {
        if (id <= 0) {
            throw new ApiException("ID sıfır ve negatif olamaz", HttpStatus.BAD_REQUEST);
        }
    }

}
