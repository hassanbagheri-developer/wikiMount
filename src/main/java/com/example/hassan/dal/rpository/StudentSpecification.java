package com.example.hassan.dal.rpository;

import com.example.hassan.dal.entity.Student;
import com.example.hassan.dal.modle.StudentModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {

    public static Specification<Student>
    filterByDto(StudentModel dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Example pattern


            if (dto.getFirstName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), dto.getFirstName()));
            }
            if (dto.getLastName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), dto.getLastName()));
            }
            if (dto.getEmail() != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), dto.getEmail()));
            }
            if (dto.getCreateDateFrom() != null && dto.getCreateDateTo() != null) {
                LocalDateTime createDateFrom = LocalDateTime.parse(dto.getCreateDateFrom(), formatter);
                LocalDateTime createDateTo = LocalDateTime.parse(dto.getCreateDateTo(), formatter);
                predicates.add(criteriaBuilder.between(root.get("createDate"), createDateFrom, createDateTo));
            } else if (dto.getCreateDateFrom() != null) {
                LocalDateTime createDateFrom = LocalDateTime.parse(dto.getCreateDateFrom(), formatter);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), createDateFrom));
            } else if (dto.getCreateDateTo() != null) {
                LocalDateTime createDateTo = LocalDateTime.parse(dto.getCreateDateTo(), formatter);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), createDateTo));
            }
            if (dto.getLastModifiedDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastModifiedDate"), dto.getLastModifiedDate()));
            }
            if (dto.getDeleteDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("deleteDate"), dto.getDeleteDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
