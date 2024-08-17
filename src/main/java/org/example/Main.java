package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    List<Student> studentList = new ArrayList<>();

    Processing processing = new Processing(studentList);

    processing.selectNumber();
  }
}
