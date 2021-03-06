package ru.job4j.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();

        Map<Integer, String> prevMap = new HashMap<>();
        for (var prevElem : previous) {
            prevMap.put(prevElem.id, prevElem.name);
        }

        for (var currElem : current) {
            String preVal = prevMap.remove(currElem.id);
            if (preVal == null) {
                result.added++;
            } else {
                if (!preVal.equals(currElem.name)) {
                    result.changed++;
                }
            }
        }
        result.deleted = prevMap.size();

        return result;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info() {

        }

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
