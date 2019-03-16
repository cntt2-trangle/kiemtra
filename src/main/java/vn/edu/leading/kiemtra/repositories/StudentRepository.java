package vn.edu.leading.kiemtra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.leading.kiemtra.models.StudentModel;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    List<StudentModel> findByTenContaining(String term);

}
