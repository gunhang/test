<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="pageMaker" value="${dataMap.pageMaker }" />
<c:set var="cri" value="${dataMap.pageMaker.cri }" />
<c:set var="noticeList" value="${dataMap.noticeList }" />

<style>
#row {
	padding: 20px;
}
</style>
<head>
</head>

<title>세탁량</title>

<body>
	<h3>세탁량 그래프/상세 게시물</h3>
	<div id="row">
		<div class="card card-primary card-outline">
			<div class="card-header">
				<h3 class="card-title">
					<i class="far fa-chart-bar"></i> Bar Chart
				</h3>
				<div class="card-tools">
					<button type="button" class="btn btn-tool"
						data-card-widget="collapse">
						<i class="fas fa-minus"></i>
					</button>
					<button type="button" class="btn btn-tool"
						data-card-widget="remove">
						<i class="fas fa-times"></i>
					</button>
				</div>
			</div>
			<div class="card-body">
				<div id="bar-chart"
					style="height: 300px; padding: 0px; position: relative;">
					<canvas class="flot-base" width="763" height="300"
						style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 763.5px; height: 300px;"></canvas>
					<canvas class="flot-overlay" width="763" height="300"
						style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 763.5px; height: 300px;"></canvas>
					<div class="flot-svg"
						style="position: absolute; top: 0px; left: 0px; height: 100%; width: 100%; pointer-events: none;">
						<svg style="width: 100%; height: 100%;">
							<g class="flot-x-axis flot-x1-axis xAxis x1Axis"
								style="position: absolute; inset: 0px;">
							<text x="160.83638139204544" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">February</text>
							<text x="298.0213068181818" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">March</text>
							<text x="429.8536931818182" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">April</text>
							<text x="559.4473100142045" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">May</text>
							<text x="36.23299893465909" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">January</text>
							<text x="684.0912198153409" y="294"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: center;">June</text></g>
							<g class="flot-y-axis flot-y1-axis yAxis y1Axis"
								style="position: absolute; inset: 0px;">
							<text x="8.9521484375" y="269" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">0</text>
							<text x="8.9521484375" y="205.5"
								class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">5</text>
							<text x="1" y="15" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">20</text>
							<text x="1" y="142" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">10</text>
							<text x="1" y="78.5" class="flot-tick-label tickLabel"
								style="position: absolute; text-align: right;">15</text></g></svg>
					</div>
				</div>
			</div>

		</div>
		
		<div class="col-12">
			<div class="card card-secondary card-outline">
				<div class="card-header">
					<h3 class="card-title">세탁량</h3>
					<div class="card-tools">
						<div class="input-group input-group-sm" style="width: 200px;">
							<input type="text" name="table_search"
								class="form-control float-right" placeholder="Search">
							<div class="input-group-append">
								<button type="submit" class="btn btn-default">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>

				<div class="card-body table-responsive p-0">
					<table class="table table-hover text-nowrap">
						<thead>
							<tr>
								<th>글번호</th>
								<th>제목</th>
								<th>담당자</th>
								<th>첨부파일</th>
								<th>등록일자</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>183</td>
								<td>John Doe</td>
								<td>11-7-2014</td>
								<td><span class="tag tag-success">Approved</span></td>
								<td>Bacon</td>
								<td></td>
							</tr>
							<tr>
								<td>219</td>
								<td>Alexander Pierce</td>
								<td>11-7-2014</td>
								<td><span class="tag tag-warning">Pending</span></td>
								<td>Bacon</td>
								<td></td>
							</tr>
							<tr>
								<td>657</td>
								<td>Bob Doe</td>
								<td>11-7-2014</td>
								<td><span class="tag tag-primary">Approved</span></td>
								<td>Bacon</td>
								<td></td>
							</tr>
							<tr>
								<td>175</td>
								<td>Mike Doe</td>
								<td>11-7-2014</td>
								<td><span class="tag tag-danger">Denied</span></td>
								<td>Bacon</td>
								<td></td>
							</tr>
							<tr>
								<td>175</td>
								<td>Mike Doe</td>
								<td>11-7-2014</td>
								<td><span class="tag tag-danger">Denied</span></td>
								<td>Bacon</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>

		</div>
	</div>


	<script
		src="https://adminlte.io/themes/v3/plugins/flot/plugins/jquery.flot.resize.js"></script>
	<script
		src="https://adminlte.io/themes/v3/plugins/flot/plugins/jquery.flot.pie.js"></script>

	<script>
		$(function() {
			/*
			 * Flot Interactive Chart
			 * -----------------------
			 */
			// We use an inline data source in the example, usually data would
			// be fetched from a server
			var data = [], totalPoints = 100

			function getRandomData() {

				if (data.length > 0) {
					data = data.slice(1)
				}

				// Do a random walk
				while (data.length < totalPoints) {

					var prev = data.length > 0 ? data[data.length - 1] : 50, y = prev
							+ Math.random() * 10 - 5

					if (y < 0) {
						y = 0
					} else if (y > 100) {
						y = 100
					}

					data.push(y)
				}

				// Zip the generated y values with the x values
				var res = []
				for (var i = 0; i < data.length; ++i) {
					res.push([ i, data[i] ])
				}

				return res
			}

			var interactive_plot = $.plot('#interactive', [ {
				data : getRandomData(),
			} ], {
				grid : {
					borderColor : '#f3f3f3',
					borderWidth : 1,
					tickColor : '#f3f3f3'
				},
				series : {
					color : '#3c8dbc',
					lines : {
						lineWidth : 2,
						show : true,
						fill : true,
					},
				},
				yaxis : {
					min : 0,
					max : 100,
					show : true
				},
				xaxis : {
					show : true
				}
			})

			var updateInterval = 500 //Fetch data ever x milliseconds
			var realtime = 'on' //If == to on then fetch data every x seconds. else stop fetching
			function update() {

				interactive_plot.setData([ getRandomData() ])

				// Since the axes don't change, we don't need to call plot.setupGrid()
				interactive_plot.draw()
				if (realtime === 'on') {
					setTimeout(update, updateInterval)
				}
			}

			//INITIALIZE REALTIME DATA FETCHING
			if (realtime === 'on') {
				update()
			}
			//REALTIME TOGGLE
			$('#realtime .btn').click(function() {
				if ($(this).data('toggle') === 'on') {
					realtime = 'on'
				} else {
					realtime = 'off'
				}
				update()
			})
			/*
			 * END INTERACTIVE CHART
			 */

			/*
			 * LINE CHART
			 * ----------
			 */
			//LINE randomly generated data
			var sin = [], cos = []
			for (var i = 0; i < 14; i += 0.5) {
				sin.push([ i, Math.sin(i) ])
				cos.push([ i, Math.cos(i) ])
			}
			var line_data1 = {
				data : sin,
				color : '#3c8dbc'
			}
			var line_data2 = {
				data : cos,
				color : '#00c0ef'
			}
			$.plot('#line-chart', [ line_data1, line_data2 ], {
				grid : {
					hoverable : true,
					borderColor : '#f3f3f3',
					borderWidth : 1,
					tickColor : '#f3f3f3'
				},
				series : {
					shadowSize : 0,
					lines : {
						show : true
					},
					points : {
						show : true
					}
				},
				lines : {
					fill : false,
					color : [ '#3c8dbc', '#f56954' ]
				},
				yaxis : {
					show : true
				},
				xaxis : {
					show : true
				}
			})
			//Initialize tooltip on hover
			$('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css(
					{
						position : 'absolute',
						display : 'none',
						opacity : 0.8
					}).appendTo('body')
			$('#line-chart')
					.bind(
							'plothover',
							function(event, pos, item) {

								if (item) {
									var x = item.datapoint[0].toFixed(2), y = item.datapoint[1]
											.toFixed(2)

									$('#line-chart-tooltip').html(
											item.series.label + ' of ' + x
													+ ' = ' + y).css({
										top : item.pageY + 5,
										left : item.pageX + 5
									}).fadeIn(200)
								} else {
									$('#line-chart-tooltip').hide()
								}

							})
			/* END LINE CHART */

			/*
			 * FULL WIDTH STATIC AREA CHART
			 * -----------------
			 */
			var areaData = [ [ 2, 88.0 ], [ 3, 93.3 ], [ 4, 102.0 ],
					[ 5, 108.5 ], [ 6, 115.7 ], [ 7, 115.6 ], [ 8, 124.6 ],
					[ 9, 130.3 ], [ 10, 134.3 ], [ 11, 141.4 ], [ 12, 146.5 ],
					[ 13, 151.7 ], [ 14, 159.9 ], [ 15, 165.4 ], [ 16, 167.8 ],
					[ 17, 168.7 ], [ 18, 169.5 ], [ 19, 168.0 ] ]
			$.plot('#area-chart', [ areaData ], {
				grid : {
					borderWidth : 0
				},
				series : {
					shadowSize : 0, // Drawing is faster without shadows
					color : '#00c0ef',
					lines : {
						fill : true
					//Converts the line chart to area chart
					},
				},
				yaxis : {
					show : false
				},
				xaxis : {
					show : false
				}
			})

			/* END AREA CHART */

			/*
			 * BAR CHART
			 * ---------
			 */

			var bar_data = {
				data : [ [ 1, 10 ], [ 2, 8 ], [ 3, 4 ], [ 4, 13 ], [ 5, 17 ],
						[ 6, 9 ] ],
				bars : {
					show : true
				}
			}
			$.plot('#bar-chart', [ bar_data ], {
				grid : {
					borderWidth : 1,
					borderColor : '#f3f3f3',
					tickColor : '#f3f3f3'
				},
				series : {
					bars : {
						show : true,
						barWidth : 0.5,
						align : 'center',
					},
				},
				colors : [ '#3c8dbc' ],
				xaxis : {
					ticks : [ [ 1, 'January' ], [ 2, 'February' ],
							[ 3, 'March' ], [ 4, 'April' ], [ 5, 'May' ],
							[ 6, 'June' ] ]
				}
			})
			/* END BAR CHART */

			/*
			 * DONUT CHART
			 * -----------
			 */

			var donutData = [ {
				label : 'Series2',
				data : 30,
				color : '#3c8dbc'
			}, {
				label : 'Series3',
				data : 20,
				color : '#0073b7'
			}, {
				label : 'Series4',
				data : 50,
				color : '#00c0ef'
			} ]
			$.plot('#donut-chart', donutData, {
				series : {
					pie : {
						show : true,
						radius : 1,
						innerRadius : 0.5,
						label : {
							show : true,
							radius : 2 / 3,
							formatter : labelFormatter,
							threshold : 0.1
						}

					}
				},
				legend : {
					show : false
				}
			})
			/*
			 * END DONUT CHART
			 */

		})

		/*
		 * Custom Label formatter
		 * ----------------------
		 */
		function labelFormatter(label, series) {
			return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
					+ label + '<br>' + Math.round(series.percent) + '%</div>'
		}
	</script>