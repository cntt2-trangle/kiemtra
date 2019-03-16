package vn.edu.leading.kiemtra.services;

import org.springframework.stereotype.Service;
import vn.edu.leading.kiemtra.models.StudentModel;
import vn.edu.leading.kiemtra.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentModel> findAll() {
        return studentRepository.findAll();
    }


    @Override
    public List<StudentModel> search(String term) {
        return studentRepository.findByTenContaining(term);
    }

    @Override
    public StudentModel findById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public boolean update(StudentModel student) {
        StudentModel studentModel = studentRepository.findById(student.getId()).orElse(null);
        if (studentModel == null) {
            return false;
        }
        studentRepository.save(student);
        return true;
    }

    @Override
    public void save(StudentModel student) {
        studentRepository.save(student);
    }

    @Override
    public boolean delete(Long id) {
        StudentModel studentModel = studentRepository.findById(id).orElse(null);
        if (studentModel == null) {
            return false;
        }
        studentRepository.delete(studentModel);
        return true;
    }
}

