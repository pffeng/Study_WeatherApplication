/**
 * report页面下拉框实现
 * author:Phantom
 */
$(function(){
	$("#selectCityId").change(function(){
		var cityId = $("#selectCityId").val();
		var url = '/report/cityId/' + cityId;
		window.location.href = url;
	})
});