package org.example.Currency.predicates;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;


public class IdPredicate implements Predicate<Long> {
    @Override
    public boolean test(Long id) {
        return !(id != null && id >= 0);
    }
}
