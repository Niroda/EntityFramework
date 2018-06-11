package se.codepool.ef.utility;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateBuilder<T> {
	Predicate build(CriteriaBuilder cb, Root<T> root);
}