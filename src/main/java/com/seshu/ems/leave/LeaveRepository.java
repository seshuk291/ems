package com.seshu.ems.leave;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    Optional<List<Leave>> findLeavesByEmployeeId(Long id);
}
