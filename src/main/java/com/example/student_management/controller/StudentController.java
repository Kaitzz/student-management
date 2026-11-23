package com.example.student_management.controller;

import com.example.student_management.dto.StudentDto;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    // Build Add Student REST API
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Get Student REST API
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentID){
        StudentDto studentDto = studentService.getStudentById(studentID);
        return ResponseEntity.ok(studentDto);
    }

    // Get All Students (With Optional Firstname Filter) REST API
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam(required = false) String firstName){
        List<StudentDto> students = studentService.getAllStudents();

        if (firstName != null) {
            students = students.stream()
                    .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                    .toList();
        }

        return ResponseEntity.ok(students);
    }

    // Build Update Student REST API
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId,
                                                    @RequestBody StudentDto updatedStudent){
        StudentDto studentDto = studentService.updateStudent(studentId, updatedStudent);
        return ResponseEntity.ok(studentDto);
    }

    // Build Delete Student REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully.");
    }
}
