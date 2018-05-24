$(function() {
	$("input[type='password'][data-eye]").each(function(i) {
		var $this = $(this);

		$this.wrap($("<div/>", {
			style: 'position:relative'
		}));
		$this.css({
			paddingRight: 60
		});
		$this.after($("<div/>", {
			html: 'Show',
			class: 'btn btn-primary btn-sm',
			id: 'passeye-toggle-'+i,
			style: 'position:absolute;right:10px;top:50%;transform:translate(0,-50%);-webkit-transform:translate(0,-50%);-o-transform:translate(0,-50%);padding: 2px 7px;font-size:12px;cursor:pointer;'
		}));
		$this.after($("<input/>", {
			type: 'hidden',
			id: 'passeye-' + i
		}));
		$this.on("keyup paste", function() {
			$("#passeye-"+i).val($(this).val());
		});
		$("#passeye-toggle-"+i).on("click", function() {
			if($this.hasClass("show")) {
				$this.attr('type', 'password');
				$this.removeClass("show");
				$(this).removeClass("btn-outline-primary");
			}else{
				$this.attr('type', 'text');
				$this.val($("#passeye-"+i).val());				
				$this.addClass("show");
				$(this).addClass("btn-outline-primary");
			}
		});
	});
	
	// Login
	$("#btn_login").click(function() {
		var loginObj = new Object();
		loginObj.userid = $("#userid").val();
		loginObj.password = $("#password").val();
		var loginJson = JSON.stringify(loginObj);
		self.location = "main.html?userid=" + loginObj.userid;
		$.post("main/login.do",
				{"loginObj": loginObj},
				function(e) {
					e = JSON.parse(e);
					if(e.useridMsg) {
						$("#useridDiv").addClass("has-error");
						$("#useridMsg").removeClass("hidden");
						$("#useridMsg").text(e.useridMsg);
					} else if(e.passwordMsg) {
						$("#passwordDiv").addClass("has-error");
						$("#passwordMsg").removeClass("hidden");
						$("#passwordMsg").text(e.passwordMsg);
					} else {
						self.location = "main.html"
					}
				});
	});
	
	// Register
	$("#btn_register").click(function() {
		var registerObj = new Object();
		// registerObj.userid = $("#userid").val();
		registerObj.name = $("#userName").val();
		registerObj.email = $("#email").val();
		registerObj.password = $("#password").val();
		var registerJson = JSON.stringify(registerObj);
		alert(registerJson)
		$.post("/smartTerminalApp/register",
				{
					"userName":registerObj.name,
					"password":registerObj.password,
					"email":registerObj.email
//					"registerObj": registerObj
				},
				function(e) {
					alert(e);
					window.location.href ="/smartTerminalApp/helloAgain";
//					e = JSON.parse(e);
//					if(e.useridMsg) {
//						$("#useridDiv").addClass("has-error");
//						$("#useridMsg").removeClass("hidden");
//						$("#useridMsg").text(e.useridMsg);
//					}
				});
	});
});














