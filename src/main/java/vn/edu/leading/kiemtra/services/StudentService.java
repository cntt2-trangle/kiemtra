package vn.edu.leading.kiemtra.services;

import vn.edu.leading.kiemtra.models.StudentModel;

import java.util.List;

public interface StudentService {
    List<StudentModel> findAll();

    List<StudentModel> search(String term);

    StudentModel findById(Long id);

    boolean update(StudentModel student);

    void save(StudentModel student);

    boolean delete(Long id);
}
