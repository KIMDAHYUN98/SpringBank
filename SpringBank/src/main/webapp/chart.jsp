<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!--Load the AJAX API-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '부서');
        data.addColumn('number', '인원수');
        // 서버로부터 데이터를 들고오자
        var arr = [];
        // ajax를 이용한 서버로부터 차트 데이터를 가져와서 addRows에 추가한다.
        $.ajax({
        	url: "getChartData",
        	async: false,
        	dataType: "json",
        	success: function(result) {
 			// result 갯수만큼 for문 실행 , 서버에서 받아온 데이터 [{}, {}] --> [[], []]
 			for(o of result) { // of = 배열, in = 인덱스
 				arr.push( [  o.DAY, o.PRICE ] );
 			// vo -> paseInt 사용, map -> bigDecimal로 인해 parseInt 사용할 필요 X
 			}
        }
    });

        data.addRows(arr);
		
        // Set chart options
        var options = {'title':'일변 판매 내역',
                       'width':400,
                       'height':300,
                        colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'],
                        vAxis : {format: "$#,###", gridlines: {count: 4} }
        			};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>