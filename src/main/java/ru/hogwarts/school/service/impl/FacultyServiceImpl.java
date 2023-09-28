package ru.hogwarts.school.service.impl;


import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;

import java.util.Comparator;
import java.util.Optional;

import java.util.stream.Collectors;




@Service
public class FacultyServiceImpl implements FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
   private final FacultyRepository facultyRepository;
@Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {

        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("createFaculty method has been invoked");
    return facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("updateFaculty method has been invoked");
    return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("getFaculty method has been invoked");
       Optional<Faculty>faculty= facultyRepository.findById(id);
        if(faculty.isPresent()){
        return faculty.get();}
        else {
            logger.error("There is no faculty with id: " + id);
            throw new EntityNotFoundException("факульттет с "+id+"id не существует");
        }
    }

    @Override
    public Faculty deleteFaculty(Long id) {
        logger.info("deleteFaculty method has been invoked");
        Optional<Faculty>faculty= facultyRepository.findById(id);
        if(faculty.isPresent()){
             facultyRepository.deleteById(id);
            return faculty.get();}
        else
        {
            logger.error("There is no faculty with id: " + id);
            throw new EntityNotFoundException("факульттет с "+id+"id не существует");
        }
    }
    @Override
    public Collection<Faculty> findAll(){
        logger.info("findAll method has been invoked");
    return facultyRepository.findAll();
    }
    @Override
    public Collection<Faculty> getByColorOrName(String name, String color) {
        logger.info("getByColorOrName method has been invoked");
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase( name,  color);
    }
    public String getLongestFaculty(){
        logger.info("getLongestFaculty method has been invoked");
    return facultyRepository.findAll().stream()
            .map(Faculty::getName)
            .max(Comparator.comparingInt(String ::length))
            .orElse("");
    }
}
