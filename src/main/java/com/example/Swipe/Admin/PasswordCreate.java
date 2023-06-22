package com.example.Swipe.Admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class PasswordCreate {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input password:");
        String password = scanner.nextLine();
        System.out.println(passwordEncoder.encode(password));
    }
}
