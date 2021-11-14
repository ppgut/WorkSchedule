SELECT s.id, d.fulldate, e.first_name, e.last_name, e.position, s.base_shift, s.start_offset, s.shift_extension 
FROM schedule s
JOIN date d on s.scheduled_day_id = d.id
JOIN employee e on s.employee_id = e.id;