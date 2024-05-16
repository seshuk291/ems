package com.seshu.ems.leave.dto;

import java.util.Date;

public record CreateLeaveDto(Date startDate, Date endDate, String reason) {}
