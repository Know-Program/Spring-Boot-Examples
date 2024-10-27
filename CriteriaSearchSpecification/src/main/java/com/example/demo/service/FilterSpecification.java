package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestDto.GlobalOperator;
import com.example.demo.dto.SearchRequestDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class FilterSpecification<T> {
    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {
        return new Specification<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos,
            GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (SearchRequestDto requestDto : searchRequestDtos) {
                System.out.println(requestDto.getOperation());
                switch (requestDto.getOperation()) {
                case EQUAL:
                    Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(equal);
                    break;
                case LIKE:
                    if (root.get(requestDto.getColumn()).getJavaType() == String.class) {
                        Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()),
                                "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                    } else {
                        throw new IllegalArgumentException("LIKE operator is only applicable to String values");
                    }
                    break;
                case IN:
                    // assume input is string and separated by comma:- "name1,name2,name3"
                    String[] values = requestDto.getValue().split(",");
                    Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(values));
                    predicates.add(in);
                    break;
                case GREATER_THAN:
                    Predicate greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()),
                            requestDto.getValue());
                    predicates.add(greaterThan);
                    break;
                case LESS_THAN:
                    Predicate lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()),
                            requestDto.getValue());
                    predicates.add(lessThan);
                    break;
                case BETWEEN:
                    String[] boundaryValues = requestDto.getValue().split(",");
                    Predicate between = criteriaBuilder.between(root.get(requestDto.getColumn()), boundaryValues[0],
                            boundaryValues[1]);
                    predicates.add(between);
                    break;
                case JOIN:
                    Predicate join = criteriaBuilder.equal(root.join(requestDto.getJoinTable()).get(requestDto.getColumn()), requestDto.getValue());
                    predicates.add(join);
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected Value for Operation: " + requestDto.getOperation());
                }
            }
            if (globalOperator.equals(GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
