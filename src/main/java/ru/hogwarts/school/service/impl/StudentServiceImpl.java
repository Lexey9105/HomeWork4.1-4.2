package ru.hogwarts.school.service.impl;


import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("createStudent method has been invoked");
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("updateStudent method has been invoked");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        logger.info("getStudent method has been invoked");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            logger.error("There is no student with id: " + id);
            throw new EntityNotFoundException("Студент с " + id + "id не существует");
        }
    }

    @Override
    public Student deleteStudent(Long id) {
        logger.info("deleteStudent method has been invoked");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return student.get();
        } else {
            logger.error("There is no student with id: " + id);
            throw new EntityNotFoundException("студент с " + id + "id не существует");
        }

    }

    @Override
    public Collection<Student> findAll() {
        logger.info("findAll method has been invoked");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getByAge(int startAge, int finalAge) {
        logger.info("getByAge method has been invoked");
        return studentRepository.findStudentsByAgeBetween(startAge, finalAge);
    }

    @Override
    public Object TotalStudentsById() {
        logger.info("TotalStudentsById method has been invoked");
        return studentRepository.getTotalStudentsById();
    }

    @Override
    public Object AVGStudentsByAge() {
        logger.info("AVGStudentsByAge method has been invoked");
        return studentRepository.getAVGStudentsByAge();
    }

    @Override
    public Collection<Student> LastStudents() {
        logger.info("LastStudents method has been invoked");
        return studentRepository.getLastStudents();
    }

    @Override
    public List<String> getByNameBeginsLetter(String a) {
        logger.info("getByNameBeginsLetter method has been invoked");
        return studentRepository.findAll().stream()
                .filter(s -> s.getName().startsWith(a))
                .map(Student::getName)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }
@Override
    public Double getAverageAgeStudent() {
        logger.info("getAverageAgeStudent method has been invoked");
        return studentRepository.findAll().stream()
                .mapToInt(s -> s.getAge())
                .average()
                .orElse(0.0f);
    }
@Override
public  void printStudents(){
        List<Student> students=studentRepository.findAll();
        if (students.size()>=6){students.subList(0,2).forEach(this::printStudentName);}
    printStudents(students.subList(2,4));
    printStudents(students.subList(4,6));
}
@Override
    public  void printStudentsSync(){
        List<Student> students=studentRepository.findAll();
        if (students.size()>=6){students.subList(0,2).forEach(this::printStudentNameSync);}
        printStudentsSync(students.subList(2,4));
        printStudentsSync(students.subList(4,6));
    }



   private void printStudentName(Student student){
logger.info("Students :"+student.getId()+" "+ student.getName());
    }
    private synchronized void printStudentNameSync(Student student){
        logger.info("Students :"+student.getId()+" "+ student.getName());
    }


    public  void printStudents(List<Student> students){
        new Thread(()->{students.forEach(this::printStudentName);})
                .start();
    }

    public  void printStudentsSync(List<Student> students){
        new Thread(()->{students.forEach(this::printStudentNameSync);})
                .start();
    }
}