package com.pearson.glp.lae;

import lombok.Data;
import reactor.core.publisher.Mono;

public class testingMain {

  @Data
  static class Emp {
    String name;
  }

  public static void main(String[] args) {

    getEmployee().switchIfEmpty(Mono.defer(() -> printFunction()))
        .flatMap(x -> {
          System.out.println(x.getName());
          return Mono.just(x);
        })
        .subscribe(x -> callempty());

  }

  private static void callempty() {
      System.out.println("\n\n\nMain stream is sending a empty\n\n\n");

    getEmployeeEmpty().switchIfEmpty(Mono.defer(() -> printFunction()))
        .flatMap(x -> {
          System.out.println(x.getName());
          return Mono.just(x);
        })
        .subscribe();
  }

  private static Mono<? extends Emp> printFunction() {
    System.out.println("empty emp");
    Emp emp = new Emp();
    emp.setName("empty");
    return Mono.just(emp);
  }

  public static Mono<Emp> getEmployee() {
    Emp emp = new Emp();
    emp.setName("yogi");
    return Mono.just(emp);
  }

  public static Mono<Emp> getEmployeeEmpty() {
    return Mono.empty();
  }
}
