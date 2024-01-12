var departureDateFieldInput = document.getElementById("departDate");
var departureDateText = document.getElementById("departureDateText");
var returnDateFieldInput = document.getElementById("returnDate");
var returnDateText = document.getElementById("returnDateText");
var searchForm = document.getElementById("flightSearchForm");
var oneWayButton = document.getElementById("oneWay");
var roundTripButton = document.getElementById("roundTrip");
var departureAirportInput = document.getElementById("departureInput");
var departureAirportSelect = document.getElementById("departureAirports");
var departureLocationText = document.getElementById("departLocationText");
var arrivalAirportInput = document.getElementById("arrivalInput");
var arrivalAirportSelect = document.getElementById("arrivalAirports");
var arrivalLocationText = document.getElementById("arriveLocationText");
var searchButton = document.getElementById("searchButton");
var dtToday = new Date();

//Search form handle validate check
searchForm.addEventListener("submit", event => {
    if (!searchForm.checkValidity()) {
      event.preventDefault();
      event.stopPropagation();
      searchButton.disabled = false;
      searchButton.innerText = "Search"
    } else {
      searchButton.disabled = true;
      searchButton.innerText = "Searching..."
    }
    searchForm.classList.add('was-validated');

}, false)


//Disable return date picker when One-way is select
oneWayButton.addEventListener("click", tripSelector);
roundTripButton.addEventListener("click", tripSelector);
function tripSelector(e) {
    if(e.target.id == "oneWay") {
        returnDateFieldInput.disabled = true;
        roundTripButton.checked = false;
        returnDateText.innerText = "";
        returnDateFieldInput.value = ""
    } else {
        returnDateFieldInput.disabled = false;
        oneWayButton.checked = false;
    }
}

//Show departure airport name when airport IATA is selected
departureAirportInput.addEventListener("input", onDepartureInput);
departureAirportInput.addEventListener("blur", onDepartureInputBlur);

//departure input validate with airport list
function onDepartureInputBlur(e) {
    var departValue = e.target.value;
    var opts = departureAirportSelect.childNodes;
    if (Object.values(opts).filter(opt => opt.value === departValue).length === 0) {
        departureAirportInput.setAttribute("aria-invalid", "true");
        departureAirportInput.setCustomValidity("Enter a valid departure airport.")
    } else {
        departureAirportInput.removeAttribute("aria-invalid")
        departureAirportInput.setCustomValidity("")
    }
}

//Show selected arrival airport name when airport IATA is selected
function onDepartureInput(e) {
    var departValue = e.target.value;
    var opts = departureAirportSelect.childNodes;
    for (var i = 0; i < opts.length; i++) {
        if (opts[i].value === departValue) {
            departureLocationText.innerText = opts[i].text;
            break;
        } else {
            departureLocationText.innerText = ""
        }
    }
}


arrivalAirportInput.addEventListener("input", onArrivalInput);
arrivalAirportInput.addEventListener("blur", onArrivalInputBlur);

//Show arrival airport name when airport IATA is selected
function onArrivalInput(e) {
    var arrivalValue = e.target.value;
    var opts = arrivalAirportSelect.childNodes;
    for (var i = 0; i < opts.length; i++) {
        if (opts[i].value === arrivalValue) {
            arrivalLocationText.innerText = opts[i].text;
            break;
        } else {
            arrivalLocationText.innerText = ""
        }
    }
}

//arrival input validate with airport list
function onArrivalInputBlur(e) {
    var arrivalValue = e.target.value;
    var opts = arrivalAirportSelect.childNodes;
    if (Object.values(opts).filter(opt => opt.value === arrivalValue).length === 0) {
        arrivalAirportInput.setAttribute("aria-invalid", "true");
        arrivalAirportInput.setCustomValidity("Enter a valid arrival airport.")
    } else {
        arrivalAirportInput.removeAttribute("aria-invalid")
        arrivalAirportInput.setCustomValidity("")
    }
}

//initial date inputs
departureDateFieldInput.addEventListener("input", onDepartureDateInput);
departureDateFieldInput.valueAsDate = dtToday;
departureDateText.innerText = getUTCFormattedDateString(dtToday);
departureDateFieldInput.setAttribute("min", getISOFormattedDateString(dtToday, 0));
returnDateFieldInput.setAttribute("min", getISOFormattedDateString(dtToday, 0));

//departure date on input
function onDepartureDateInput(e) {
    var dateValue = e.target.value;
    var date = new Date(dateValue);
    departureDateText.innerText = getUTCFormattedDateString(date);

    returnDateFieldInput.setAttribute("min",  getISOFormattedDateString(date, 1));
}

returnDateFieldInput.addEventListener("input", onReturnDateInput);

//return date on input
function onReturnDateInput(e) {
    var dateValue = e.target.value;
    var returnDate = new Date(dateValue);
    var departureDateValue = departureDateFieldInput.value;

    if(new Date(departureDateValue) <= returnDate){
        returnDateText.innerText = getUTCFormattedDateString(returnDate);
    }
}

//Convert Date to UTC E, dd MMM yyyy format
function getUTCFormattedDateString(date) {
    return date.toUTCString().substring(0, date.toUTCString().length - 13);
}

//Convert Date to ISO yyyy-MM-dd format with day offset
function getISOFormattedDateString(date, offset) {
    var month = date.getMonth() + 1;
    var day = date.getDate() + offset;
    var year = date.getFullYear();
    if(month < 10) {
        month = '0' + month.toString();
    }
    if(day < 10){
        day = '0' + day.toString();
    }
    var minDate = year + '-' + month + '-' + day;
    return minDate;
}



