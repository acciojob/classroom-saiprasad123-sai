package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentDb = new HashMap<>();
    HashMap<String,Teacher> teacherDb = new HashMap<>();
    HashMap<String, List<String>> teacherstudentDb = new HashMap<>();

    public String addStudent(Student student){
        studentDb.put(student.getName(),student);
        return "success";
    }

    public String addTeacher(Teacher teacher){
        teacherDb.put(teacher.getName(),teacher);
        return "success";
    }

    public String addStudentTeacher(String student,String teacher){
        if(teacherstudentDb.containsKey(teacher)){
            teacherstudentDb.get(teacher).add(student);
        }
        else{
            teacherstudentDb.put(teacher,new ArrayList<>());
            teacherstudentDb.get(teacher).add(student);
        }
        return "success";
    }

    public Student getStudentByName(String name){
        return studentDb.getOrDefault(name,null);
    }

    public Teacher getTeacherByName(String name){
        return teacherDb.getOrDefault(name,null);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        return teacherstudentDb.getOrDefault(teacher,null);
    }

    public List<String> getAllStudents(){
        List<String> res = new ArrayList<>();

        for(String name:studentDb.keySet()){
            res.add(name);
        }
        return res;
    }

    public String deleteTeacherByName(String teacher){

        if(teacherDb.containsKey(teacher)){
            if(teacherstudentDb.containsKey(teacher)){
                List<String> list = teacherstudentDb.get(teacher);
                for(String s:list){
                    studentDb.remove(s);
                }
                teacherstudentDb.remove(teacher);
            }
            teacherDb.remove(teacher);
        }
        return "success";
    }

    public String deleteTeachers(){

        for(String teacher:teacherDb.keySet()){
            if(teacherstudentDb.containsKey(teacher)){
                List<String> list = teacherstudentDb.get(teacher);
                for(String s:list){
                    studentDb.remove(s);
                }
                teacherstudentDb.remove(teacher);
            }
            teacherDb.remove(teacher);
        }
        return "success";
    }



}
