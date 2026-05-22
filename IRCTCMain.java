package project_1;

import java.util.Scanner;

/**
 * project_1.IRCTCMain — Entry point for the IRCTC project_1.Train project_1.Booking System.
 *
 * Run this file to start the application.
 * No framework, no external libraries — pure Java.
 *
 * How to compile & run:
 *   javac src/*.java -d out
 *   java -cp out project_1.IRCTCMain
 */
import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

public class IRCTCMain {

    public static final String R = "\u001B[0m";
    public static final String B = "\u001B[1m";
    public static final String CYAN   = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    public static final String GOLD   = "\u001B[33m";
    public static final String GREEN  = "\u001B[32m";
    public static final String RED    = "\u001B[31m";
    public static final String GRAY   = "\u001B[90m";

    public static void main(String[] args) {
        Scanner scanner        = new Scanner(System.in);
        TrainDatabase db       = new TrainDatabase();
        SearchService search   = new SearchService(db, scanner);
        BookingService booking = new BookingService(db, scanner);

        printBanner();

        boolean exit = false;
        while (!exit) {
            printMainMenu();
            System.out.print(B + GOLD + "  ❯ Selection Choice: " + R);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": search.start(); break;
                case "2": booking.start(); break;
                case "3": printAbout(); break;
                case "0": exit = true; break;
                default:
                    System.out.println(RED + "  [!] Oops! Invalid input. Try 0-3." + R);
            }
        }
        System.out.println("\n" + GREEN + B + "  ✔ Closing System Safely... Happy Journey! 🚂" + R);
        scanner.close();
    }

    private static void printBanner() {
        System.out.println(CYAN + B);
        System.out.println("  ██╗██████╗  ██████╗████████╗ ██████╗     ██╗██████╗ ");
        System.out.println("  ██║██╔══██╗██╔════╝╚══██╔══╝██╔════╝     ██║██╔══██╗");
        System.out.println("  ██║██████╔╝██║        ██║   ██║          ██║██████╔╝");
        System.out.println("  ██║██╔══██╗██║        ██║   ██║          ██║██╔══██╗");
        System.out.println("  ██║██║  ██║╚██████╗   ██║   ╚██████╗     ██║██║  ██║");
        System.out.println("  ╚═╝╚═╝  ╚═╝ ╚═════╝   ╚═╝    ╚═════╝     ╚═╝╚═╝  ╚═╝");
        System.out.println(GRAY + "  ────────────────────────────────────────────────────────" + R);
        System.out.println(B + PURPLE + "         ✨ NEXT-GEN RAILWAY RESERVATION SYSTEM ✨" + R);
    }

    private static void printMainMenu() {
        System.out.println("\n" + CYAN + "  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓" + R);
        System.out.println("  ┃ " + B + "      C O N S O L E    D A S H B O A R D        " + R + CYAN + "  ┃" + R);
        System.out.println("  ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫" + R);
        System.out.println("  ┃                                                     ┃");
        System.out.println("  ┃  " + GOLD + "[1]" + R + " Search & Discover Trains                      ┃");
        System.out.println("  ┃  " + GOLD + "[2]" + R + " Ticket Bookings & PNR Status                  ┃");
        System.out.println("  ┃  " + GOLD + "[3]" + R + " System Information                            ┃");
        System.out.println("  ┃  " + RED + "[0]" + R + " Exit Application                              ┃");
        System.out.println("  ┃                                                     ┃");
        System.out.println("  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛" + R);
    }

    private static void printAbout() {
        System.out.println("\n" + B + PURPLE + "  ╭────────────────── SYSTEM INFO ──────────────────╮" + R);
        System.out.println("     Dev Mode  : " + GREEN + "Enabled" + R);
        System.out.println("     UI Style  : " + CYAN + "Aesthetic ANSI Terminal" + R);
        System.out.println("     Core      : Java Multi-Object Database");
        System.out.println(B + PURPLE + "  ╰─────────────────────────────────────────────────╯" + R);
    }

}