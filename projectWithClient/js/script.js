const form = document.getElementById("currency__form");
        const btn= document.getElementsByClassName("button_submit")[0];
        btn.addEventListener('click',functSubmit);
        //form.addEventListener('submit', functSubmit);
        
        
        function functSubmit(event) {
            const currencies=document.getElementById('selected__currency').value;
            console.log(currencies);
            const startdate= form.elements["startdate"].value;
            const enddate= form.elements["enddate"].value;
            console.log(currencies);
            console.log(startdate);
            console.log(enddate);
            const URLlink=`https://www.nbrb.by/API/ExRates/Rates/Dynamics/${currencies}?startDate=${startdate}&&endDate=${enddate}`;
            console.log(URLlink);
            getResponse(URLlink);

        }


    
        
        async function getResponse(url) {
	        const response = await fetch(url);
          const data1 = await response.json();
          console.log(data1);
          var arr = new Array();
          for(var i=0; i<data1.length; i++){
            arr[i] = new Array();
            for(var j=0; j<2; j++){
                arr[i][1] = data1[i].Cur_OfficialRate;
                arr[i][0] = data1[i].Date.substring(0,10);
            }
          }
          
          localStorage.setItem("key2",JSON.stringify(arr));

          google.charts.load('current', {'packages':['corechart']});
          google.charts.setOnLoadCallback(drawChart);
        }
        

      

      function drawChart() {
        let arr=JSON.parse( localStorage.getItem("key2")) ; 
        arr[0]= ["Date", "Currency"];
        
          
        var data = google.visualization.arrayToDataTable(arr);

        var options = {
          title: 'Currency',
          curveType: 'function',
          legend: { position: 'bottom' }
        };
        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
        chart.draw(data, options); 
      }
     

