# Entities
<hr/>

### Employee:
Attributes: Employee ID, Name, Contact Information, Address, Position, Salary, Tax Information.
### Department:
Attributes: Department ID, Name, Description.
### Leave:
Attributes: Leave ID, Employee ID, Start Date, End Date, Reason.
### Attendance:
Attributes: Attendance ID, Employee ID, Date, Time In, Time Out.
### Training:
Attributes: Training ID, Employee ID, Training Type, Date, Duration.
### Pay Grade:
Attributes: Pay Grade ID, Grade Name, Minimum Salary, Maximum Salary.

# Relationships
<hr/>

### Employee - Department:
An employee belongs to one department, and a department can have multiple employees.
### Employee - Leave:
An employee can take multiple leaves, and each leave is associated with one employee.
### Employee - Attendance:
An employee has attendance records, with each attendance entry linked to one employee.
### Employee - Training:
An employee can undergo multiple training sessions, and each training session is linked to one employee.
### Employee - Pay Grade:
Each employee is assigned a specific pay grade, defining their salary range.