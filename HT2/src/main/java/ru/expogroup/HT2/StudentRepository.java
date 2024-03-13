package ru.expogroup.HT2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudentRepository {
    private final List<Student> list;
    public StudentRepository() {
        list = new ArrayList<>();
        list.add(new Student("Илья", "ДС"));
        list.add(new Student("Иван", "ДС"));
        list.add(new Student("Алексей", "ДС"));
        list.add(new Student("Кирилл", "ДМП"));
        list.add(new Student("Антон", "ДМП"));
        list.add(new Student("Юрий", "ДЛ"));
    }

    public Student getById(long id){
        return list.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAll(){
        return List.copyOf(list);
    }

    public List<Student> getByStudentName(String studentName){
        return list.stream()
                .filter(e -> e.getStudentName().contains(studentName))
                .toList();
    }

    public List<Student> getByGroupName(String groupName){
        return list.stream()
                .filter(e -> e.getGroupName().equals(groupName))
                .toList();
    }

}
