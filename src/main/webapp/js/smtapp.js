var baseController = "/smartTerminalApp/";

$(function() {
	$("#btn_register").click(
			function() {
				$.post(baseController + "doctor/register", {
					"nirc" : $("#nirc").val(),
					"userName" : $("#userName").val(),
					"password" : $("#password").val(),
					"email" : $("#email").val()
				}, function(e) {
					e = JSON.parse(e);
					if (e.state > 0) {
						window.location.href = baseController
								+ "doctor/login?nirc=" + $("#nirc").val();
					} else {
						alert(e.msg);
					}
				});
			});

	$("#btn_login").click(
			function() {
				$.post(baseController + "doctor/login", {
					"nirc" : $("#nirc").val(),
					"password" : $("#password").val()
				}, function(e) {
					e = JSON.parse(e);
					if (e.state > 0) {
						window.location.href = baseController + "doctor/main?docid="
								+ e.docid;
					} else {
						alert(e.msg);
					}
				});
			});

	$("#btn_updateInfo").click(
			function() {
				$.post(baseController + "doctor/info/" + $("#hd_docid").val(),
						{
							"phoneNumber" : $("#phoneNumber").val(),
							"hospital" : $("#hospital").val(),
							"department" : $("#department").val()
						}, function(e) {
							e = JSON.parse(e);
							alert(e.msg);
							if (e.state > 0) {
								window.location.href = baseController
										+ "doctor/info/" + e.docid;
							}
						});
			});

	$("#btn_addPatient").click(function() {
		$.post(baseController + "doctor/patient/", {
			"nirc" : $("#nirc").val(),
			"userName" : $("#userName").val(),
			"gender" : $("#gender").val() == 'Male' ? 1 : 0,
			"birthday" : new Date($("#birthday").val()),
			"phoneNumber" : $("#phoneNumber").val(),
			"docid" : $("#hd_docid").val()
		}, function(e) {
			e = JSON.parse(e);
			if (e.state > 0) {
				window.location.href = baseController + "doctor/main/"
			} else {
				alert(e.msg);
			}
		});
	});

	$("#btn_search")
			.click(
				function() {
					if($("#query_nirc").val().length > 0){
						$.post(baseController + "doctor/search/"+$("#query_nirc").val(), {
						},function(e){
							$("table tbody").empty();
							$.each($.parseJSON(e),function(i,n)
									{
										if(n.subscribe){
											$("#tb_query").append('<tr><td>'+n.pName+'</td><td>'+n.pNirc+'</td><td>'+n.gender+'</td><td><button class="btn btn-primary" value='+n.pId+' disabled=true">followed</button></td></tr>');
										}else{
											$("#tb_query").append('<tr><td>'+n.pName+'</td><td>'+n.pNirc+'</td><td>'+n.gender+'</td><td><button class="btn btn-primary" value='+n.pId+' onclick="subscribe(this)">&nbsp;&nbsp;follow&nbsp;&nbsp;</button></td></tr>');
										}
									}
								);
							}
						)
					}
				}
			);
						
	$('#birthday').datetimepicker({
		format : 'yyyy-mm-dd hh:ii'
	});
});

function subscribe(tb_btn){
    $.post(baseController + "doctor/subscribe/", {
		"docid" : $("#hd_docid").val(),
    	"patid" : $(tb_btn).val()
	}, function(e){
		e = JSON.parse(e);
		if(e.state>0){
			$(tb_btn).attr("disabled","true"); 
			$(tb_btn).text("followed"); 
		}
	});
};

function unsubscribe(btn){
    $.post(baseController + "doctor/unsubscribe/", {
		"docid" : $("#hd_docid").val(),
    	"patid" : $(btn).val()
	}, function(e){
		e = JSON.parse(e);
		if(e.state > 0){
			window.location.href = baseController + "doctor/"+$("#hd_docid").val()+"/patientlist";
		}else{
			alert(e.msg);
		}
	});
};

function b_refresh(){
	$.post(baseController + "record/list", {
		"minTime" : 0,
	},function(e){
		$("#div_records").empty();
		var e = $.parseJSON(e);
		if(e.state > 0){
			var minTime = new Date().getTime();
			$.each($.parseJSON(e.records), function(i,n){
				minTime = n.addTime < minTime ? n.addTime : minTime;
				$("#div_records").append(
					'<div class="col-lg-4 mb-4">\
						<div class="card h-100">\
							<h4 class="card-header">' + n.patientName + '</h4>\
							<div class="card-body">\
								<p class="card-text">'+ new Date(n.addTime).format('yyyy-MM-dd h:m:s') + '</p>\
								<img\
									src="'+n.imgUrl+'"\
									alt="Test Record" width="100%" height="250" />\
								<table width="100%" border="1" frame=void\
										rules=none id="tbcom_'+n.recordId+'">\
								</table>\
							</div>\
							<div class="card-footer">\
								<table width="100%" border="1" class="table" frame=void\
									rules=none>\
									<tr>\
										<td colspan="3"><input type="text" class="form-control"\
											id="email" value="" placeholder="Please enter comment."></td>\
										<td colspan="1"><button type="button" value="'+n.recordId+'"\
												class="btn btn-primary" onclick="comment(this)">Submit</button></td>\
									</tr>\
								</table>\
							</div>\
						</div>\
					</div>'
				);
				if(n.comments.length>0){
					var json_coms = n.comments;
					if(json_coms.length > 0){
						$.each(json_coms, function(i,c){
							$("#tbcom_"+n.recordId).append('<tr><td colspan="1" align="center">'+c.docName+'</td><td colspan="3"><input type="text" class="form-control" id="email" value="'+c.content+'" disabled></td></tr>');
						});
					}
				}
			});
			$("#hd_minTime").val(minTime);
		}else{
			alert(e.msg);
		}
		$('html,body').animate({ scrollTop: 0 }, 500);
	})
};

function b_list(){
	minTime = $("#hd_minTime").val();
	$.post(baseController + "record/list", {
		"minTime" : minTime,
	},function(e){
		var e = $.parseJSON(e);
		if(e.state > 0){
			$.each($.parseJSON(e.records), function(i,n){
				minTime = n.addTime < minTime ? n.addTime : minTime;
				$("#div_records").append(
					'<div class="col-lg-4 mb-4">\
						<div class="card h-100">\
							<h4 class="card-header">' + n.patientName + '</h4>\
							<div class="card-body">\
								<p class="card-text">'+ new Date(n.addTime).format('yyyy-MM-dd h:m:s') + '</p>\
								<img\
									src="'+n.imgUrl+'"\
									alt="Test Record" width="100%" height="250" />\
								<table width="100%" border="1" frame=void\
										rules=none id="tbcom_'+n.recordId+'">\
								</table>\
							</div>\
							<div class="card-footer">\
								<table width="100%" border="1" class="table" frame=void\
									rules=none>\
									<tr>\
										<td colspan="3"><input type="text" class="form-control"\
											id="email" value="" placeholder="Please enter comment."></td>\
										<td colspan="1"><button type="button" value="'+n.recordId+'"\
												class="btn btn-primary" onclick="comment(this)">Submit</button></td>\
									</tr>\
								</table>\
							</div>\
						</div>\
					</div>'
				);
				if(n.comments.length>0){
					var json_coms = n.comments;
					if(json_coms.length > 0){
						$.each(json_coms, function(i,c){
							$("#tbcom_"+n.recordId).append('<tr><td colspan="1" align="center">'+c.docName+'</td><td colspan="3"><input type="text" class="form-control" id="email" value="'+c.content+'" disabled></td></tr>');
						});
					}
				}
			});
			$("#hd_minTime").val(minTime);
		}else{
			alert(e.msg);
		}
	})
};

function comment(btn){
	var content = $(btn).parent().prev().children(":first").val();
	$.post(baseController + "record/comment/", {
		"docID" : $("#hd_docid").val(),
		"docName" : $("#hd_docName").val(),
    	"recordId" : $(btn).val(),
    	"content" : content
	}, function(e){
		e = JSON.parse(e);
		if(e.state > 0){
			$("#tbcom_"+$(btn).val()).append('<tr><td colspan="1" align="center" vertical-align="middle">'+$("#hd_docName").val()+'</td><td colspan="3"><input type="text" class="form-control" id="email" value="'+content+'" disabled></td></tr>');
		}else{
			alert(e.msg);
		}
	});
};

Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}