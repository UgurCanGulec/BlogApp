package com.gulecugurcan.repository;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public abstract class AbstractQuerySpecification<T> implements Specification<T> {
}
