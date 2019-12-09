package ru.job4j.converter;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> innerIt;

            {
                innerIt = it == null ? null : it.next();
                while (it != null && it.hasNext() && !innerIt.hasNext()) {
                    innerIt = it.next();
                }
            }

            @Override
            public boolean hasNext() {
                return it != null && innerIt.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer result = innerIt.next();
                while (!innerIt.hasNext() && it.hasNext()) {
                    innerIt = it.next();
                }
                return result;
            }
        };
    }
}
