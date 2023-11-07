package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int Wise_sayingId;


    List<Wise_saying> wise_sayings;

    App() {
        scanner = new Scanner(System.in);

        Wise_sayingId = 0;

        wise_sayings = new ArrayList<>();
    }

    void run() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 : ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                registraction();
            } else if (cmd.equals("목록")) {
                list();
            }
        }
    }

    void registraction() {

        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작자 : ");
        String authorName = scanner.nextLine();

        Wise_sayingId++;
        int id = Wise_sayingId;

        Wise_saying wise_saying = new Wise_saying(id, content, authorName);
        wise_sayings.add(wise_saying);

        System.out.printf("%d번 명언 등록 완료.", Wise_sayingId);
        System.out.println();

    }

    void list() {

        System.out.println("번호 / 작자 / 명언");
        System.out.println("------------------");

        if (wise_sayings.isEmpty()) {
            System.out.println("등록된 명언이 존재하지 않습니다.");
        } else {
            for (int i = wise_sayings.size() - 1; i >= 0; i--) {
                Wise_saying quotation = wise_sayings.get(i);
                System.out.printf("%d / %s / %s\n", quotation.id, quotation.content, quotation.authorName);
            }
        }
    }
}