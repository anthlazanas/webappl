myHeaders = new Headers({                                           
  "Authorization": "Token b487c4bf21886584befce6b034b6cb2ddb03eb18" // ορίζουμε header με το
});                                                                 // token μας

var obj = {  
  method: 'GET',
  headers: myHeaders
}

const url = "https://data.gov.gr/api/v1/query/admie_realtimescadasystemload";

fetch(url, obj).then(function(response) {         // κάνουμε fetch request στο API
  return response.json()	                      // και τα χρησιμοποιούμε για να ορίσουμε
}).then(function(data) {                          // τις επιλογές των dropdown menus
  console.log(data)	
  createdatelist(data)
  createtimelist(data)
})
	
function createdatelist(file){                    // δημιουργία λίστας ημερομηνιών
  document.getElementById("Date").innerHTML = `
  <select>
    <option>Choose Date</option>
      ${file.map(function (element, i=0) {
        if (i%24 == 0) {
          return `<option>${JSON.stringify(element.date).slice(1,11)}</option>`;
        }
        i += 1
      }).join('')}
    </select>  
  ` 	
}

function createtimelist(file){                    // δημιουργία λίστας ωρών
  document.getElementById("Time").innerHTML = `
  <select>
	<option>Choose Time</option>
	  ${file.map(function (element, i=0) {
	    if (Math.floor(i/24) == 0) {
	      return `<option>${JSON.stringify(element.date).slice(12,20)}</option>`;
	    }
	    i += 1;
	  }).join('')}
	</select>  
  ` 	
}




function get_value() {                                  //συνάρτηση που ενεργοποιείται με το κουμπί
	var d = document.querySelector("#Date select");     //και επιστρέφει την τιμή κατανάλωσης 
	var chosen_date = d.options[d.selectedIndex].text;  //για την επιλεγμένη μέρα και ώρα
    var t = document.querySelector("#Time select");
    var chosen_time = t.options[t.selectedIndex].text;

    fetch(url, obj).then(function(response) {         
    	  return response.json()	        
    	}).then(function(data) { 
    		data.map(function(element){
    		  var date_string  = JSON.stringify(element.date);
    		  if (date_string.includes(chosen_date)&&date_string.includes(chosen_time)){
    		    alert(JSON.stringify(element.energy_mwh)+" MWatt");	  
    		  }	
    		})
    	})
}

