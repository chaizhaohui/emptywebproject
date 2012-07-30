var isEmail = function(str){
	return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(str);
};
var isMobile = function(str){
	return /^((\(\d{3}\))|(\d{3}\-))?1(3|4|5|8)\d{9}$/.test(str);
};
var isPhone = function(str){
	return /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/.test(str);
};