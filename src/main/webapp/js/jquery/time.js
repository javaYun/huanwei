function current() {

	var d = new Date(), str = '';

	str += d.getFullYear() + '年';

	intMonth = d.getMonth() + 1;

	if (intMonth < 10) {

		intMonth = '0' + intMonth;

	}

	str += intMonth + '月';

	intDay = d.getDate();

	if (intDay < 10) {

		intDay = '0' + intDay;

	}

	str += intDay + '日';

	intHour = d.getHours();

	if (intHour < 10) {

		intHour = '0' + intHour;

	}

	str += '<b>' + intHour + ':';

	intMin = d.getMinutes();

	if (intMin < 10) {

		intMin = '0' + intMin;

	}

	str += intMin + ':';

	intSec = d.getSeconds();

	if (intSec < 10) {

		intSec = '0' + intSec;

	}

	str += intSec + '</b>';

	return str;
}

setInterval(function() {
	$("#currentTime").html(current)
}, 50);
