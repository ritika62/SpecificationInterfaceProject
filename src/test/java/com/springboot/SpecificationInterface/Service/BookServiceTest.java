package com.springboot.SpecificationInterface.Service;
import com.springboot.SpecificationInterface.Controller.RequestPojo;
import com.springboot.SpecificationInterface.Dao.BookDao;
import com.springboot.SpecificationInterface.Entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

 @Mock
 private BookDao bookDao;

 @InjectMocks
 private BookService bookService;

 private RequestPojo requestPojo;
 private Book book;

 @BeforeEach
 public void setUp() {
  requestPojo = new RequestPojo();
  book = new Book(2L,"Hibernate","James",1000L,"Advance");
 }

 @Test
 public void testFilterBooksByBookNameOrAuthorAndPriceGreaterThan() {
  requestPojo.setBookName("spring");
  requestPojo.setAuthor("john");
  requestPojo.setPriceGreaterThan(100L);
  List<Book> testBooks = new ArrayList<>();
 when(bookDao.findAll(any(Specification.class))).thenReturn(testBooks);
  List<Book> actualBooks = bookService.filterBooks(requestPojo);
  assertEquals(testBooks,actualBooks);
  verify(bookDao,times(1)).findAll(any(Specification.class));

 }
}

