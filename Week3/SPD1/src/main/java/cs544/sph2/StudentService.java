package cs544.sph2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentService {

    @Resource
    private StudentDao studentdao;

    public Student getStudent(long studentid) {
        Student s = studentdao.findById(studentid).orElse(new Student());
        return s;
    }
}
