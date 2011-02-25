month_name = new Array("January","February","March","April","May","June","July","August","September","October","November","December");
day_name = new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
function cur_data() {
	var cur_date = new Date();
	var day_no = cur_date.getDay();
	var day = cur_date.getDate();
	var month = cur_date.getMonth();
	var year = cur_date.getFullYear();
	document.write(day_name[day_no] + ',' + '&nbsp;' + day + '&nbsp;' + month_name[month] + '&nbsp;' + year + '&nbsp;');
}
function month_year() {
	
	var lmd = document.lastModified;
	var d1 = Date.parse(lmd);
	var d2 = new Date(d1);
	var year = d2.getYear();
	var month = d2.getMonth();
	var day = d2.getDate();
	if( year < 2000 & year >= 100)
	{
    year += 1900;
	}
	if (day < 10) {
	day = "0"+day;
	}
	
	document.write(day + '&nbsp;' + month_name[month] + '&nbsp;' + year + '&nbsp;');
}
function cur_year() {
	var cur_date = new Date();
	var year = cur_date.getFullYear();
	document.write("2010 -" + '&nbsp;' + year);
}
