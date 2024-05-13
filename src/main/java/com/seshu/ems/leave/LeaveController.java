package com.seshu.ems.leave;

import com.seshu.ems.leave.dto.CreateLeaveDto;
import com.seshu.ems.leave.dto.UpdateLeaveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;
    LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployeeId(@PathVariable("empId") Long empId){
        List<Leave> leaves = this.leaveService.getLeavesByEmployeeId(empId);
        return ResponseEntity.ok(leaves);
    }

    @PostMapping("/{empId}")
    public ResponseEntity<Leave> createLeave(@RequestBody() CreateLeaveDto createLeaveDto, @PathVariable("empId") Long empId) {
       Leave leave = this.leaveService.createLeave(createLeaveDto, empId);
       return ResponseEntity.ok(leave);
    }

    @PutMapping("/{leaveId}")
    public ResponseEntity<Leave> updateLeave(@RequestBody() UpdateLeaveDto updateLeaveDto, @PathVariable("leaveId") Long leaveId) {
        Leave leave = this.leaveService.updateLeave(updateLeaveDto, leaveId);
        return ResponseEntity.ok(leave);
    }

    @DeleteMapping("/{leaveId}")
    public ResponseEntity<String> deleteLeave(@PathVariable("leaveId") Long leaveId) {
        this.leaveService.deleteLeave(leaveId);
        return ResponseEntity.ok("Leave delete");
    }

}
