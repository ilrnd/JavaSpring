/*
 * 3.1 GET /student/{id} - получить студента по ID
 * 3.2 GET /student - получить всех студентов
 * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
 * 3.4 GET /group/{groupName}/student - получить всех студентов группы
 */
package ru.expogroup.HT2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping()
public class StudentController {

        private StudentRepository repository;

        @Autowired
        public StudentController(StudentRepository repository) {
            this.repository = repository;
        }

        @GetMapping("student/{id}")
        public Student getById(@PathVariable long id){
            return repository.getById(id);
        }

        @GetMapping("student")
        public List<Student> getAll(){
            return repository.getAll();
        }

        @GetMapping("student/search")
        public List<Student> getByStudentName(@RequestParam String name){
            return repository.getByStudentName(name);
        }

        @GetMapping(value = "/group/{groupName}/student")
        public List<Student> getAllByGroupName(@PathVariable String groupName) {
        return repository.getByGroupName(groupName);
    }
}
