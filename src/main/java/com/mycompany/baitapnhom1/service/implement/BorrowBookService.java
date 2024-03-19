package com.mycompany.baitapnhom1.service.implement;

import com.mycompany.baitapnhom1.entity.BookEntity;
import com.mycompany.baitapnhom1.entity.BorrowFormEntity;
import com.mycompany.baitapnhom1.entity.ReturnState;
import com.mycompany.baitapnhom1.repository.BorrowBookRepository;
import com.mycompany.baitapnhom1.service.IBorrowBookService;
import com.mycompany.baitapnhom1.util.JOptionPaneUtil;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static com.mycompany.baitapnhom1.entity.ReturnState.*;

@Service
@AllArgsConstructor
public class BorrowBookService implements IBorrowBookService {
    private BorrowBookRepository borrowBookRepository;
    private BookService bookService;
    private UserService userService;

    @Override
    public void saveNew(BorrowFormEntity item) {
        try {
            var book = item.getBook();
            var remainingQuantity = book.getRestQuantity();
            var borrowed = item.getQuantity();
            if (item.getQuantity() <= remainingQuantity) {
                borrowBookRepository.save(item);
                book.setRestQuantity(book.getRestQuantity() - item.getQuantity());
                bookService.updateQuantity(book, borrowed);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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
            bookService.updateQuantity(book, quantity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<BorrowFormEntity> fetchAll() {
        return borrowBookRepository.findAll();
    }

    @Override
    public List<BorrowFormEntity> findAllByUser(String userId) throws RuntimeException {
        var items = borrowBookRepository.findAllByUser(userId.trim().toUpperCase());
        if (!items.isEmpty()) return items;
        throw new RuntimeException("This user has not borrowed any books yet");
    }

    @Override
    public List<BorrowFormEntity> findAllByBook(String bookId) {
        return null;
    }

    @Override
    public BorrowFormEntity findByBorrowId(String id) {
        return borrowBookRepository.findByBorrowId(id)
                .orElseThrow(() -> new RuntimeException("There no item matching"));
    }

    @Override
    @Transactional
    public void deleteItem(String id) {
        borrowBookRepository.deleteByBorrowId(id);
    }

    @Override
    public List<BorrowFormEntity> findAllByUserIdAndBookId(String userId, String bookId) {
        if (!userId.isBlank() && !bookId.isBlank()) {
            return borrowBookRepository.findAllByUserAndBook(userId.trim().toUpperCase(), bookId.trim());
        }
        if (!userId.isBlank()) {
            return borrowBookRepository.findAllByUser(userId.trim().toUpperCase());
        }
        return borrowBookRepository.findAllByBook(bookId.trim());
    }

    public void addNewBorrowItem(String userId, String bookId, int time, int quantity){
        if (!userId.isBlank() && !bookId.isBlank()) {
            var items = findAllByUserIdAndBookId(userId,bookId);
            if(items.isEmpty() || checkAllReturn(items)){
                try {
                    saveNew(bookId, userId, quantity, time);
                    JOptionPaneUtil.showMessageDialog("Added successfully", 800, null);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }else {
                throw new RuntimeException("This user is currently borrowing the book");
            }
        } else {
            throw new RuntimeException("Please fill the require fields");
        }
    }

    private boolean checkAllReturn(List<BorrowFormEntity> items) {
        for(BorrowFormEntity item : items){
            var state = item.getState().getState();
            if(state.equals(ReturnState.NOT_YET.getState())
                    || state.equals(ReturnState.EXPIRED.getState())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void returnBook(String borrowId) {
        try {
            var item = findByBorrowId(borrowId.trim());
            var book = bookService.findBookByBookId(item.getBook().getBookId());
            var quantity = item.getQuantity();
            item.setState(RETURNED);
            borrowBookRepository.save(item);
            bookService.updateQuantity(book, -quantity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void displayData(DefaultTableModel model, List<BorrowFormEntity> items, Function<BookEntity,String> function) {
        int index = 1;
        LocalDate borrowDate;
        LocalDate returnDate;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.setRowCount(0);
        for (BorrowFormEntity item : items) {
            borrowDate = item.getBorrowDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            returnDate = item.getExpiredDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            var data = new Object[]{
                    index,
                    item.getBorrowId(),
                    item.getUser().getPersonalId(),
                    function.apply(item.getBook()),
                    item.getQuantity(),
                    borrowDate.format(dateTimeFormatter),
                    returnDate.format(dateTimeFormatter),
                    item.getState().getState()
            };
            model.addRow(data);
            index += 1;
        }
    }

    @Transactional
    public void expireBorrowForm() {
        borrowBookRepository.expireBorrowForm(EXPIRED, new Date(), NOT_YET);
    }

    public List<BorrowFormEntity> findAllExpiredBorrowedForm(){
        return borrowBookRepository.findAllByState(EXPIRED);
    }

}
