//得到select项的个数
jQuery.fn.size = function () {
	return jQuery(this).get(0).options.length;
};
//获得选中项的索引
jQuery.fn.getSelectedIndex = function () {
	return jQuery(this).get(0).selectedIndex;
};
//获得当前选中项的文本
jQuery.fn.getSelectedText = function () {
	if (this.size() == 0) {
		return "\u4e0b\u62c9\u6846\u4e2d\u65e0\u9009\u9879";
	} else {
		var index = this.getSelectedIndex();
		return jQuery(this).get(0).options[index].text;
	}
};
//获得当前选中项的值
jQuery.fn.getSelectedValue = function () {
	if (this.size() == 0) {
		return "\u4e0b\u62c9\u6846\u4e2d\u65e0\u9009\u4e2d\u503c";
	} else {
		return jQuery(this).val();
	}
};
//设置select中值为value的项为选中
jQuery.fn.setSelectedValue = function (value) {
	jQuery(this).get(0).value = value;
};
//设置select中文本为text的第一项被选中
jQuery.fn.setSelectedText = function (text) {
	var isExist = false;
	var count = this.size();
	for (var i = 0; i < count; i++) {
		if (jQuery(this).get(0).options[i].text == text) {
			jQuery(this).get(0).options[i].selected = true;
			isExist = true;
			break;
		}
	}
	if (!isExist) {
		alert("\u4e0b\u62c9\u6846\u4e2d\u4e0d\u5b58\u5728\u8be5\u9879");
	}
};
//设置选中指定索引项
jQuery.fn.setSelectedIndex = function (index) {
	var count = this.size();
	if (index >= count || index < 0) {
		alert("\u9009\u4e2d\u9879\u7d22\u5f15\u8d85\u51fa\u8303\u56f4");
	} else {
		jQuery(this).get(0).selectedIndex = index;
	}
};
//判断select项中是否存在值为value的项
jQuery.fn.isExistItem = function (value) {
	var isExist = false;
	var count = this.size();
	for (var i = 0; i < count; i++) {
		if (jQuery(this).get(0).options[i].value == value) {
			isExist = true;
			break;
		}
	}
	return isExist;
};
//向select中添加一项，显示内容为text，值为value,如果该项值已存在，则提示
jQuery.fn.addOption = function (text, value) {
	if (this.isExistItem(value)) {
		alert("\u5f85\u6dfb\u52a0\u9879\u7684\u503c\u5df2\u5b58\u5728");
	} else {
		jQuery(this).get(0).options.add(new Option(text, value));
	}
};
//删除select中值为value的项，如果该项不存在，则提示
jQuery.fn.removeItem = function (value) {
	if (this.isExistItem(value)) {
		var count = this.size();
		for (var i = 0; i < count; i++) {
			if (jQuery(this).get(0).options[i].value == value) {
				jQuery(this).get(0).remove(i);
				break;
			}
		}
	} else {
		alert("\u5f85\u5220\u9664\u7684\u9879\u4e0d\u5b58\u5728!");
	}
};
//删除select中指定索引的项
jQuery.fn.removeIndex = function (index) {
	var count = this.size();
	if (index >= count || index < 0) {
		alert("\u5f85\u5220\u9664\u9879\u7d22\u5f15\u8d85\u51fa\u8303\u56f4");
	} else {
		jQuery(this).get(0).remove(index);
	}
};
//删除select中选定的项
jQuery.fn.removeSelected = function () {
	var index = this.getSelectedIndex();
	this.removeIndex(index);
};
//清除select中的所有项
jQuery.fn.clearAll = function () {
	jQuery(this).get(0).options.length = 0;
};

