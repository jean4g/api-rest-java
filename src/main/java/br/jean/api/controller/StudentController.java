package br.jean.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import br.jean.api.entity.Student;

@RestController
@RequestMapping("/api/student")
public class StudentController {

  private final List<Student> studentList = new ArrayList<>();

  @GetMapping
  public List<Student> getAll() {

    return studentList;
  }

  @GetMapping("/{registration}")
  public Student getStudentEnrollment(@PathVariable int registration) {

    for (Student student : studentList) {
      if (student.getRegistration() == registration) {
        return student;
      }
    }

    return null;
  }

  @PostMapping
  public void insert(@RequestBody Student student) {

    studentList.add(student);
  }

  @PutMapping("/{registration}")
  public void update(@RequestBody Student newStudentData, @PathVariable int registration) {

    for (Student student : studentList) {
      if (student.getRegistration() == registration) {
        BeanUtils.copyProperties(newStudentData, student);
      }
    }
  }

  @PatchMapping("/{registration}")
  public void updateRa(@RequestParam("ra") String ra, @PathVariable int registration) {

    for (Student student : studentList) {
      if (student.getRegistration() == registration) {
        student.setRa(ra);
      }
    }
  }

  @DeleteMapping("/{registration}")
  public void delete(@PathVariable int registration) {

    for (Student student : studentList) {
      if (student.getRegistration() == registration) {
        studentList.remove(student);
      }
    }
  }

}
