package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student){

        //Student from the postman is already the basic attributes set.


        //Card Should be autogenerated when createStudent function is called.
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED); //Card status is being set.
        card.setStudentVariableName(student); //Foreign key attribute
        //Filling the value of the unidirectional part.


        //Lets go to the student
        student.setCard(card);


        //If there was a unidirectional mapping : we had to save both of them them
        //studentRepo.save () and cardRepo.save()
        //But we are super advance and are using bidirectional : Child will automatically be saved.

        studentRepository.save(student);
        //By cascading effect, child will automatically be saved (cardRepo will be saved)

        return "Student and Card added";
    }


    public String findNameByEmail(String email){

        Student student = studentRepository.findByEmail(email);

        return student.getName();
    }


    public String updateMobNo(StudentUpdateMobRequestDto studentReq){



        //CONVERT THE DTO TO ENTITY : saved better



        //First we will try to fetch originalData
        Student originalStudent = studentRepository.findById(studentReq.getId()).get();

        //We will keep the other properties as it is : and only change the required parameters

        originalStudent.setMobNo(studentReq.getMobNo());


        //Always entity object is being saved.
        studentRepository.save(originalStudent);

        return "Student has been updated successfully. ";

    }

}

/*
    1. Existing Functions with no definition.
    2. Existing function + with defining.
    3. New Fu
 */
