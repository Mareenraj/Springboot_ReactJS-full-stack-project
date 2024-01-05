import {useEffect, useState} from "react";
import axios from "axios";
interface Student {
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    department: string,
}

const StudentView = () => {
    const [students, setStudents] = useState<Student[]>([]);

    useEffect(() =>{
        loadStudents();
    })
    const loadStudents = async() => {
        try{
            const result = await axios.get("http://localhost:8080/api/students",{validateStatus: () => {
                return true;
                }});
            if(result.status === 302){
                setStudents(result.data);
            }
        }
        catch (e) {
            console.log(e)
        }
    }
    return (
        <section>
            <table className= "table table-bordered table-hover">
                <thead className="text-center">
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th colSpan={3}>Actions</th>
                </tr>
                </thead>
                <tbody className="text-center">
                {students.map((student, index) => (
                    <tr key={student.id}>
                        <td scope="row" key={index}> {index + 1}</td>
                        <td>{student.firstName}</td>
                        <td>{student.lastName}</td>
                        <td>{student.email}</td>
                        <td>{student.department}</td>
                        <td className="mx-2"><button className="btn btn-info">view</button></td>
                        <td className="mx-2"><button className="btn btn-warning">Update</button></td>
                        <td className="mx-2"><button className="btn btn-danger">Delete</button></td>
                    </tr>
                ))}
                </tbody>
            </table>
        </section>
    )
}

export default StudentView;