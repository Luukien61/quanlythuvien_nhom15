package com.mycompany.baitapnhom1.util;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class AppUtil {
    @Setter
    @Getter
    private static UserEntity currentUser=null;

    public static List<BookEntity> initialBooks(){
        List<BookEntity> items= new ArrayList<>();
        for(int i =0 ; i<20;i++){
            var total =new Random().nextInt(100);
            var book = BookEntity.builder()
                    .bookId(UUID.randomUUID().toString())
                    .bookName(UUID.randomUUID().toString())
                    .author(UUID.randomUUID().toString())
                    .category(BookCategory.SCIENCE)
                    .publishDate(new Date())
                    .publisher("NXB Kim Dong")
                    .totalQuantity(total)
                    .restQuantity(total/2)
                    .build();
            items.add(book);
        }
        return items;
    }
}
