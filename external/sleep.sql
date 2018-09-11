create or replace package user_util
IS
PROCEDURE sleep (num_of_sec IN NUMBER);
END user_util;
/

create or replace package body user_util
IS
-- Procedure to cause Oracle to sleep NUM_OF_SEC seconds.  Can be invoked before
-- any other call to provide a wait.
PROCEDURE sleep (num_of_sec IN NUMBER)
IS
start_time NUMBER;
curr_time NUMBER;
end_time NUMBER;
BEGIN
-- Get current time (SSSSS format returns seconds since midnight)
-- Thus this routine will not work if started just before midnight
-- but it could be enhanced to support that as well.
select to_number(to_char(sysdate, 'SSSSS')) into start_time
from dual;

-- Calculate end of sleep time
end_time := start_time + num_of_sec;

-- Loop until End Time is reached
WHILE (1=1) LOOP
-- Get current time
select to_number(to_char(sysdate, 'SSSSS')) into curr_time
from dual;

-- Compare to end time and exit if time is past
IF (curr_time >= end_time) THEN
    EXIT;
END IF;
END LOOP;
END sleep;
END user_util;
/
