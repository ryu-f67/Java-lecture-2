package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Processing {

  public List<Student> studentList;
  Scanner scanner = new Scanner(System.in);

  public Processing(List<Student> studentList) {
    this.studentList = studentList;
  }

  // 1. 学生を追加
  public void addStudent() {
    try {
      System.out.print("追加する学生の名前を入力してください: ");
      String name = scanner.next();
      System.out.print(name + "の点数を入力してください: ");
      int score = scanner.nextInt();
      studentList.add(new Student(name, score));
      System.out.println("追加が完了しました。");
    } catch (InputMismatchException e) {
      System.out.println("無効な値が入力されました。");
      System.out.println("もう一度やり直してください。");
    }
  }

  // 2. 学生を削除
  public void removeStudent() {
    System.out.print("削除する学生の名前を入力してください: ");
    String name = scanner.next();
    // allMatchだと空のリストに対してtrueを返してしまうためnoneMatchを使用。
    boolean exists = studentList.stream()
            .noneMatch(student -> Objects.equals(student.getName(), name));

    if (!exists) {
      studentList.removeIf(student -> Objects.equals(student.getName(), name));
      System.out.println(name + "の削除が完了しました。");
    } else {
      System.out.println(name + "は存在しません。");
      System.out.println("もう一度やり直してください。");
    }
  }

  // 3. 点数を更新
  public void updateScore() {
    try {
      System.out.print("点数を更新する学生の名前を入力してください: ");
      String name = scanner.next();
      System.out.print(name + "の点数を入力してください: ");
      int score = scanner.nextInt();
      // allMatchだと空のリストに対してtrueを返してしまうためnoneMatchを使用。
      boolean exists = studentList.stream()
          .noneMatch(student -> Objects.equals(student.getName(), name));

      if (!exists) {
        studentList.stream()
            .filter(student -> Objects.equals(student.getName(), name))
            .forEach(student -> student.setScore(score));
        System.out.println("更新が完了しました。");
      } else {
        System.out.println(name + "は存在しません。");
        System.out.println("もう一度やり直してください。");
      }
    } catch (InputMismatchException e) {
      System.out.println("無効な値が入力されました。");
      System.out.println("もう一度やり直してください。");
    }
  }

  // 4. 平均点を計算
  public void showAverageScore(){
    Double averageScore = studentList.stream()
        .collect(Collectors.averagingDouble(Student::getScore));
    System.out.printf("平均点 : %.1f 点%n", averageScore);
  }

  // 5. 全学生の情報を表示
  public void showAllScore() {
    System.out.println("学生一覧 :");
    studentList.forEach(Student::detailScore);
  }

  public void selectNumber() {
    String selectedNumber;
    do {
      System.out.println("1. 学生を追加");
      System.out.println("2. 学生を削除");
      System.out.println("3. 点数を更新");
      System.out.println("4. 平均点を計算");
      System.out.println("5. 全学生の情報を表示");
      System.out.println("6. 終了");
      System.out.print("数字を選択してください : ");
      selectedNumber = scanner.next();
      System.out.println();

      switch (selectedNumber) {
        case "1" -> addStudent();
        case "2" -> removeStudent();
        case "3" -> updateScore();
        case "4" -> showAverageScore();
        case "5" -> showAllScore();
        case "6" -> System.out.println("プログラムを終了します。");
        default -> System.out.println("無効な値が入力されました。");
      }
      System.out.println();
      scanner.nextLine();
    } while (!selectedNumber.equals("6"));
  }
}
