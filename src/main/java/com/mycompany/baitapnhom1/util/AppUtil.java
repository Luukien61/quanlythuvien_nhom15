package com.mycompany.baitapnhom1.util;

import com.mycompany.baitapnhom1.entity.BookCategory;
import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AppUtil {
    @Setter
    @Getter
    private static UserEntity currentUser = null;
    private static KeyAdapter keyAdapter = null;
    private static Document document = null;

    public static List<BookEntity> initialBooks() {
        List<BookEntity> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            var total = new Random().nextInt(100);
            var book = BookEntity.builder()
                    .bookId(UUID.randomUUID().toString())
                    .bookName(UUID.randomUUID().toString())
                    .author(UUID.randomUUID().toString())
                    .category(BookCategory.SCIENCE)
                    .publishDate(new Date())
                    .publisher("NXB Kim Dong")
                    .totalQuantity(total)
                    .restQuantity(total / 2)
                    .build();
            items.add(book);
        }
        return items;
    }

    public static KeyAdapter getCharacterKeyAdapter() {
        if (keyAdapter == null) {
            keyAdapter = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!((c >= '0') && (c <= '9') ||
                            (c == KeyEvent.VK_BACK_SPACE) ||
                            (c == KeyEvent.VK_DELETE))) {
                        e.consume();  // ignore event
                    }
                }
            };
        }
        return keyAdapter;
    }

    public static Document getNumberRequireCharacter(int num) {
        if (document == null) {
            document = new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                    if (getLength() + str.length() <= num) {
                        super.insertString(offs, str, a);
                    }
                }
            };
        }
        return document;
    }

    public static String getPublishDateString(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return String.valueOf(localDate.getYear());
    }

    public static Date getPublishDate(int year) {
        LocalDate localDate = LocalDate.of(year,1,1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
