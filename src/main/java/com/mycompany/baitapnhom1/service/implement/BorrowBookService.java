package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.ReturnState;
import com.mycompany.baitapnhom1.entity.UserEntity;
import com.mycompany.baitapnhom1.repository.BorrowBookRepository;
import com.mycompany.baitapnhom1.service.IBorrowBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static com.mycompany.baitapnhom1.entity.ReturnState.NOT_YET;

@Service
@AllArgsConstructor
public class BorrowBookService implements IBorrowBookService {
    private BorrowBookRepository borrowBookRepository;
    private BookService bookService;
    private UserService userService;

    @Override
    public void saveNew(BorrowFormEntity item) {
        try{
            var book =item.getBook();
            var remainingQuantity = book.getRestQuantity();
            var borrowed= item.getQuantity();
            if(item.getQuantity()<=remainingQuantity){
                borrowBookRepository.save(item);
                book.setRestQuantity(book.getRestQuantity()-item.getQuantity());
                bookService.updateQuantity(book,borrowed);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveNew(String bookId, String userId, int quantity, int time) {
        try {
            var user = userService.findUserByPersonalId(userId);
            var book = bookService.findBookByBookId(bookId);
            if (book.getRestQuantity() < quantity) {
                throw new RuntimeException("The remaining quantity of this book is insufficient");
            }
            var curentDate = new Date();
            var milliseconds = curentDate.getTime();
            long returnTime = ((long) time * 30 * 24 * 60 * 60 * 1000);
            var borrowBook = BorrowFormEntity.builder()
                    .book(book)
                    .borrowDate(curentDate)
                    .borrowId("U" + userId + "B" + bookId + "T" + milliseconds)
                    .quantity(quantity)
                    .expiredDate(new Date(milliseconds + returnTime))
                    .user(user)
                    .state(NOT_YET)
                    .build();
            borrowBookRepository.save(borrowBook);
            bookService.updateQuantity(book,quantity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BorrowFormEntity> fetchAll() {
        return borrowBookRepository.findAll();
    }

    @Override
    public List<BorrowFormEntity> findAllByUser(UserEntity user) {
        return null;
    }

    @Override
    public List<BorrowFormEntity> findAllByBook(BookEntity book) {
        return null;
    }

    @Override
    public BorrowFormEntity findByBorrowId(String id) {
        return null;
    }
}
