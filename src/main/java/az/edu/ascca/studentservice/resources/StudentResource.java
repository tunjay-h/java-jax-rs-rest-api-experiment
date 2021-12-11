/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package az.edu.ascca.studentservice.resources;

import az.edu.ascca.studentservice.models.Student;
import az.edu.ascca.studentservice.models.StudentDao;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("students")
public class StudentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        StudentDao studentDao = new StudentDao();

        return Response.ok(studentDao.getAllStudents()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {
        StudentDao studentDao = new StudentDao();

        return Response.ok(studentDao.getStudentById(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student st) {
        StudentDao studentDao = new StudentDao();
        boolean res = studentDao.createNewStudent(st.getFirstname(), st.getLastname());

        if (res) {
            return Response.ok("new student created").build();
        } else {
            return Response.serverError().build();
        }
    }

}
