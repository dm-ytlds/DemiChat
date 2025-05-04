package com.demi.java.ai.langchain4j;

import com.demi.java.ai.langchain4j.entities.Appointment;
import com.demi.java.ai.langchain4j.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Test
    public void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setUsername("demi");
        appointment.setIdCard("idCard number");
        appointment.setDepartment("男科");
        appointment.setDate("2025-05-03");
        appointment.setTime("上午");

        Appointment appointment1 = appointmentService.getOne(appointment);
        System.out.println(appointment1);
    }

    @Test
    public void testSave() {
        Appointment appointment = new Appointment();
        appointment.setUsername("demi");
        appointment.setIdCard("idCard number");
        appointment.setDepartment("男科");
        appointment.setDate("2025-05-03");
        appointment.setTime("上午");
        appointment.setDoctorName("Doc. She");

        boolean isSuccess = appointmentService.save(appointment);
        System.out.println(isSuccess);
    }

    @Test
    public void testDelete() {
        appointmentService.removeById(1L);
    }
}
