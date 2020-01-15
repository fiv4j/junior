package ru.job4j.statistic;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();

        for (var prevUser : previous) {
            User currUser = findById(current, prevUser.id);
            if (currUser == null) {
                result.deleted++;
                continue;
            }
            if (!currUser.name.equals(prevUser.name)) {
                result.changed++;
            }
        }

        if ((previous.size() - result.deleted) != current.size()) {
            for (var currUser : current) {
                User prevUser = findById(previous, currUser.id);
                if (prevUser == null) {
                    result.added++;
                }
            }
        }

        return result;
    }

    private User findById(List<User> users, int id) {
        User result = null;
        for (var user : users) {
            if (user.id == id) {
                result = user;
                break;
            }
        }
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
