var searchForm = document.getElementById("flightSearchForm")
searchForm.addEventListener("submit", event => {
    if (!searchForm.checkValidity()) {
      event.preventDefault()
      event.stopPropagation()
    }
    searchForm.classList.add('was-validated')
  }, false)

//Disable return date picker when One-way is select
document.getElementById("oneWay").addEventListener("click", tripSelector);
document.getElementById("roundTrip").addEventListener("click", tripSelector);
function tripSelector(e) {
    if(e.target.id == "oneWay") {
        document.getElementById("returnDate").disabled = true;
    } else {
        document.getElementById("returnDate").disabled = false;
    }
}

//Show departure airport name when airport IATA is selected
document.getElementById("departureInput").addEventListener("input", onDepartureInput);
function onDepartureInput(e) {
    var departValue = e.target.value;
    var opts = document.getElementById("departureAirports").childNodes;
    for (var i = 0; i < opts.length; i++) {
        if (opts[i].value === departValue) {
            document.getElementById("departLocationText").innerText = opts[i].text;
            break;
        } else {
            document.getElementById("departLocationText").innerText = ""
        }
    }
}

//Show arrival airport name when airport IATA is selected
document.getElementById("arrivalInput").addEventListener("input", onArrivalInput);
function onArrivalInput(e) {
    var arrivalValue = e.target.value;
    var opts = document.getElementById("arrivalAirports").childNodes;
    for (var i = 0; i < opts.length; i++) {
        if (opts[i].value === arrivalValue) {
            document.getElementById("arriveLocationText").innerText = opts[i].text;
            break;
        } else {
            document.getElementById("arriveLocationText").innerText = ""
        }
    }
}

document.getElementById("departDate").addEventListener("input", onDepartureDateInput);
var today = new Date()
document.getElementById("departDate").valueAsDate = today;
document.getElementById("departureDateText").innerText = today.toUTCString().substring(0, today.toUTCString().length - 13);
function onDepartureDateInput(e) {
    var dateValue = e.target.value;
    var date = new Date(dateValue);
    document.getElementById("departureDateText").innerText = date.toUTCString().replace(" 00:00:00 GMT", "");
}


document.getElementById("returnDate").addEventListener("input", onReturnDateInput);
function onReturnDateInput(e) {
    var dateValue = e.target.value;
    var date = new Date(dateValue);
    var departureDate = document.getElementById("departDate").value;

    if(new Date(departureDate) < date){
        document.getElementById("returnDateText").innerText = date.toUTCString().replace(" 00:00:00 GMT", "");
    } else {
        document.getElementById("returnDate")
    }
}



