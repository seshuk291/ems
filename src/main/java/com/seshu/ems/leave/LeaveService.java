package com.seshu.ems.leave;

import com.seshu.ems.custom_exceptions.ResourceNotFoundException;
import com.seshu.ems.employee.Employee;
import com.seshu.ems.employee.EmployeeRepository;
import com.seshu.ems.leave.dto.CreateLeaveDto;
import com.seshu.ems.leave.dto.UpdateLeaveDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Leave> getLeavesByEmployeeId(Long employeeId) {
         return this.leaveRepository
                 .findLeavesByEmployeeId(employeeId)
                 .orElseThrow(() -> new ResourceNotFoundException(String.format("Leaves with employee id: %d not found!", employeeId)));
    }

    public Leave createLeave(CreateLeaveDto createLeaveDto, Long employeeId) {
        Employee employee = this.employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id: %d Not found!", employeeId)));

        Leave leave = Leave
                .builder()
                .employee(employee)
                .startDate(createLeaveDto.startDate())
                .endDate(createLeaveDto.endDate())
                .reason(createLeaveDto.reason())
                .build();
        return this.leaveRepository.save(leave);
    }

    public Leave updateLeave(UpdateLeaveDto leaveDto, Long leaveId) {
        Leave existingLeave = this.leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave Not Found"));
        Leave leave = Leave.builder()
                .id(existingLeave.getId())
                .startDate(leaveDto.startDate())
                .endDate(leaveDto.endDate())
                .reason(leaveDto.reason())
                .build();
       return this.leaveRepository.save(leave);
    }

    public void deleteLeave(Long leaveId) {
        this.leaveRepository.deleteById(leaveId);
    }
}
