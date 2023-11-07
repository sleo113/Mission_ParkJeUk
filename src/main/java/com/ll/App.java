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
            }
            else if (cmd.equals("등록")) {
                registraction();
            }
            else if (cmd.equals("목록")) {
                list();
            }
            else if (cmd.startsWith("삭제?")) {
                Remove(cmd);
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

    void Remove(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

    int getParamAsInt(String cmd, String paramName, int defaultValue) {
        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String _paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (_paramName.equals(paramName)) {
                try {
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            }
        }
        return defaultValue;
    }
}