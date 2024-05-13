package com.seshu.ems.leave.dto;

import java.util.Date;

public record UpdateLeaveDto(Date startDate, Date endDate, String reason) {}