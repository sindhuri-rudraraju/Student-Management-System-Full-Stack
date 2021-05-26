import axios from 'axios';

const STUDENT_API_BASE_URL = "http://localhost:8080/api/v1/students";
class StudentService{
    getStudents(){
        return axios.get(STUDENT_API_BASE_URL);
    }
    getStudentById(studentId){
        return axios.get(STUDENT_API_BASE_URL + '/' + studentId);
    }
    
    createStudent(student){
        return axios.post(STUDENT_API_BASE_URL, student);
    }

    updateStudent(student, studentId){
        return axios.put(STUDENT_API_BASE_URL + '/' + studentId, student);
    }

    deleteStudent(studentId){
        return axios.delete(STUDENT_API_BASE_URL + '/' + studentId);
    }
}
export default new StudentService()