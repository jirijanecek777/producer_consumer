package com.company.domain;

public class User {

    private Long id;

    private String guid;

    private String name;

    private User() {
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        private Long id;

        private String guid;

        private String name;

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withGuid(String guid) {
            this.guid = guid;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            var user = new User();
            user.id = this.id;
            user.guid = this.guid;
            user.name = this.name;
            return user;
        }
    }
}
