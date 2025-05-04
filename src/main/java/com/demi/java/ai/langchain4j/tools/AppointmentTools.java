package com.demi.java.ai.langchain4j.tools;

import com.demi.java.ai.langchain4j.entities.Appointment;
import com.demi.java.ai.langchain4j.service.AppointmentService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {
    @Autowired
    private AppointmentService appointmentService;

    @Tool(name = "预约挂号", value = "根据参数，先执行工具方法 queryDepartment 查询是否可预约，并直接给用户回答是否可预约，并让用户确认所有预约信息")
    public String bookAppointment(Appointment appointment) {
        // 查找数据库中是否已有预约记录
        Appointment result = appointmentService.getOne(appointment);
        if (result == null) {
            appointment.setId(null);  // 避免大模型幻觉，设置id为null
            if (appointmentService.save(appointment)) {
                return "预约成功";
            } else {
                return "预约失败";
            }
        }
        return "您在相同的科室，相同的时间段，已经有预约，请您重新选择科室或时间段";
    }

    @Tool(name = "取消预约", value = "根据参数，查询数据库中是否有预约记录，如果存在则删除预约记录并返回取消预约成功，否则返回取消预约失败")
    public String cancelAppointment(Appointment appointment) {
        Appointment result = appointmentService.getOne(appointment);
        if (result != null) {
            if (appointmentService.removeById(appointment.getId())) {
                return "取消预约成功";
            } else {
                return "取消预约失败";
            }
        }
        return "您没有预约记录，无需取消预约";
    }

    @Tool(name = "查询是否有号源", value = "根据科室名称，日期，时间和医生查询是否有号源，并返回给用户")
    public boolean queryAppointment(
            @P(value = "科室名称") String name,
            @P(value = "日期") String date,
            @P(value = "时间，可选值：上午、下午") String time,
            @P(value = "医生名称", required = false) String doctorName
    ) {
        System.out.println("查询是否有号源");
        System.out.println("科室名称：" + name);
        System.out.println("日期：" + date);
        System.out.println("时间：" + time);
        System.out.println("医生名称：" + doctorName);
        return true;
    }
}
