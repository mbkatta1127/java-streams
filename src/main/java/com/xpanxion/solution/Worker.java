package com.xpanxion.solution;

import java.util.*;
import java.util.stream.Collectors;

import com.xpanxion.java.assignments.DataAccess;
import com.xpanxion.java.assignments.model.Cat;
import com.xpanxion.java.assignments.model.Department;
import com.xpanxion.java.assignments.model.Person;
import com.xpanxion.java.assignments.model.Product;

public class Worker {
    DataAccess da = new DataAccess();
    List<Department> departmentList = da.getDepartments();
    List<Person> personList = da.getPeople();
    public void ex1 () {
        // System.out.println("Start here");
        List<Product> changedList = da.getProducts().stream().map(product -> new Product(product.getId(),
                product.getDepartmentId(),
                (departmentList.get(product.getDepartmentId() - 1).getName()),
                product.getName(),
                product.getPrice(),
                product.getSku())).collect(Collectors.toList());

        printProductList(changedList);
    }

    public void ex2(){
        List<Product> changedList = da.getProducts().stream().map(product -> new Product(product.getId(),
                product.getDepartmentId(),
                "N/A",
                product.getName(),
                product.getPrice(),
                product.getSku())).collect(Collectors.toList());

        printProductList(changedList);
    }

    public void ex3(){
        List<Product> changedList = da.getProducts().stream().filter(product -> product.getDepartmentId()==1 && product.getPrice()>=10).collect(Collectors.toList());

        printProductList(changedList);
    }

    public void ex4(){
        List<Product> changedList = da.getProducts().stream().map(product -> new Product(product.getId(),
                product.getDepartmentId(),
                (departmentList.get(product.getDepartmentId() - 1).getName()),
                product.getName(),
                product.getPrice(),
                product.getSku())).collect(Collectors.toList());

        float sum = changedList.stream().filter(product -> product.getDepartmentName().equals("Food")).
                mapToInt(product -> (int) product.getPrice()).sum();

        System.out.println(String.format("$%.2f", sum));
    }

    public void ex5(){
        //System.out.println(personList.get(1).getSsn().substring(7));;
        List<Person> changedList = personList.stream().filter(person -> person.getId()<=3)
                .map(person -> new Person(person.getId(), person.getFirstName(), person.getLastName(), person.getAge(), person.getSsn().substring(7)))
                .collect(Collectors.toList());

        printPersonList(changedList);
    }

    public void ex6(){
        List<Cat> catList = da.getCats();
        Comparator<Cat> catComparator = new Comparator<Cat>() {
            @Override
            public int compare(Cat c1, Cat c2) {
                return c1.getName().compareTo(c2.getName());
            }
        };

        catList.sort(catComparator);

        for(Cat c: catList){
            System.out.println(c);
        }

    }

    public void ex7(){
        String words = da.getWords();
        Map<String, Integer> wordsHashMap = new HashMap<>();
        String[] wordsArray = words.split(" ");

        for(String w: wordsArray){
            Integer index = wordsHashMap.get(w);

            if(index==null) wordsHashMap.put(w,1);
            else wordsHashMap.put(w,index+1);
        }

        List<String> wordsFromHashMap = new ArrayList<>(wordsHashMap.keySet());
        Collections.sort(wordsFromHashMap);

        for(String w: wordsFromHashMap){
            System.out.println(w + " = " + wordsHashMap.get(w));
        }
    }

    public void ex8(){
        List<Person> changedList = personList.stream()
                .map(person -> new Person(person.getId(), person.getFirstName(), null,0,null))
                .collect(Collectors.toList());

        printPersonList(changedList);
    }

    public void printProductList(List<Product> changedList){
        for (Product p : changedList) {
            System.out.println(p);
        }
    }

    public void printPersonList(List<Person> changedList){
        for (Person p : changedList) {
            System.out.println(p);
        }
    }
}
