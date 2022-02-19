create table scale_drives (
	id int identity primary key,
	drive_date date,
	descr varchar(2000),
	start_time datetime,
	end_time datetime
)
