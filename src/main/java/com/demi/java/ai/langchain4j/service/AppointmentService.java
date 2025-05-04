package com.demi.java.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demi.java.ai.langchain4j.entities.Appointment;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
