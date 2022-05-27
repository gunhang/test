<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><decorator:title default="Rundering" /></title>

<!-- Google Font: Source Sans Pro -->
<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback"> -->


<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">

<!-- Pretendard  -->
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/css/font/pretendard/pretendard-subset.css" />

<!--파비콘-->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/resources/image/Favicon.ico" />
<link rel="icon" href="<%=request.getContextPath() %>/resources/image/Favicon.svg" />	

<!-- common.js -->
<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>
<style type="text/css">
body {
	font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui,
		Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo',
		'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji',
		'Segoe UI Symbol', sans-serif;
}

/* [class*=sidebar-dark-] {
	background-color: rgb(37, 110, 159);
}
[class*=sidebar-dark-] .sidebar a {
	background-color: rgb(37, 110, 159);
} */
#sidebar {
	background-color: #7b9acc;
}

#sidebar a, #sidebar p {
	color: #000000bd;
}

#navbar {
	background-color: #fcf6f5;
}

#content-wrapper {
	background-color: #fcf6f5;
}

#logoutbtn {
	background-color: #7b9acc;
	/* color: black; */
	margin-top: 4px;
}

body {
	background: #292e33;
}
</style>

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/css/width.css" />
<decorator:head />

</head>
<body>

	<div class="wrapper">
		<%@ include file="/WEB-INF/include/branch_header.jsp"%>

		<%@ include file="/WEB-INF/include/branch_aside.jsp"%>



		<div class="content-wrapper" id="content-wrapper">
			<decorator:body />
		</div>

		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>



	<!-- jQuery -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
	<!-- summernote -->
	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote.min.js"></script>

	<script
		src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery-knob/jquery.knob.min.js"></script>

	<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>

	<script>
    $('div.wrapper').css({
       "max-width":"1280px",
       "margin" : "0 auto"
    });
  </script>
	<script>
  $(function () {
    /* jQueryKnob */

    $('.knob').knob({
      /*change : function (value) {
       //console.log("change : " + value);
       },
       release : function (value) {
       console.log("release : " + value);
       },
       cancel : function () {
       console.log("cancel : " + this.value);
       },*/
      draw: function () {

        // "tron" case
        if (this.$.data('skin') == 'tron') {

          var a   = this.angle(this.cv)  // Angle
            ,
              sa  = this.startAngle          // Previous start angle
            ,
              sat = this.startAngle         // Start angle
            ,
              ea                            // Previous end angle
            ,
              eat = sat + a                 // End angle
            ,
              r   = true

          this.g.lineWidth = this.lineWidth

          this.o.cursor
          && (sat = eat - 0.3)
          && (eat = eat + 0.3)

          if (this.o.displayPrevious) {
            ea = this.startAngle + this.angle(this.value)
            this.o.cursor
            && (sa = ea - 0.3)
            && (ea = ea + 0.3)
            this.g.beginPath()
            this.g.strokeStyle = this.previousColor
            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false)
            this.g.stroke()
          }

          this.g.beginPath()
          this.g.strokeStyle = r ? this.o.fgColor : this.fgColor
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false)
          this.g.stroke()

          this.g.lineWidth = 2
          this.g.beginPath()
          this.g.strokeStyle = this.o.fgColor
          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false)
          this.g.stroke()

          return false
        }
      }
    })
    /* END JQUERY KNOB */

    //INITIALIZE SPARKLINE CHARTS
    var sparkline1 = new Sparkline($('#sparkline-1')[0], { width: 240, height: 70, lineColor: '#92c1dc', endColor: '#92c1dc' })
    var sparkline2 = new Sparkline($('#sparkline-2')[0], { width: 240, height: 70, lineColor: '#f56954', endColor: '#f56954' })
    var sparkline3 = new Sparkline($('#sparkline-3')[0], { width: 240, height: 70, lineColor: '#3af221', endColor: '#3af221' })

    sparkline1.draw([1000, 1200, 920, 927, 931, 1027, 819, 930, 1021])
    sparkline2.draw([515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921])
    sparkline3.draw([15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21])

  })

</script>
</body>
</html>