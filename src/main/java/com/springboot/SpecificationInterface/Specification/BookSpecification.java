package com.springboot.SpecificationInterface.Specification;

import com.springboot.SpecificationInterface.Controller.RequestPojo;
import com.springboot.SpecificationInterface.Entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasBookName(String bookName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("bookName"), bookName);
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("author"), author);
    }

    public static Specification<Book> hasPriceGreaterThan(Long price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(root.get("price"), price);
    }

    public static Specification<Book> filterBooks(RequestPojo requestPojo) {
        Specification<Book> spec = Specification.where(null);

        if (requestPojo.getBookName() != null) {
            spec = spec.or(hasBookName(requestPojo.getBookName()));
        }
        if (requestPojo.getAuthor() != null) {
            spec = spec.or(hasAuthor(requestPojo.getAuthor()));
        }

        if (requestPojo.getPriceGreaterThan() != null) {
            spec = spec.and(hasPriceGreaterThan(requestPojo.getPriceGreaterThan()));
        }
            if (requestPojo == null) {
                return Specification.where(null);
            }
            return spec;
        }
    }