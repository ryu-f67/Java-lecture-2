package org.example;

public class Student {

  String name;
  int score;

  public Student(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void detailScore() {
    System.out.println(name + " : " + score + "点");
  }
}
