package com.tangerine.specter.java16;

public class RecordModelDemo {

    public static void main(String[] args) {
        final User user = new User("小明", 18);
        System.out.println(user.name());
    }
}
