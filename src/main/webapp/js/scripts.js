// ---------------------------
// Event Handlers for Buttons
// ---------------------------
document.getElementById("getAllClaims").addEventListener("click", getAllClaims);
document.getElementById("approvalByAgeBtn").addEventListener("click", getClaimsByCountry);
document.getElementById("agentsAndApprovalsBtn").addEventListener("click", getClaimsByReason);

// ---------------------------
// Get all claims first button
// ---------------------------
function getAllClaims() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);

            document.getElementById("numClaimsDiv").innerHTML = `<b>Total # Claims:</b> ${response.length}`;
            document.getElementById("leftChart").innerHTML = "# of Claims By Age Group";
            document.getElementById("rightChart").innerHTML = "# of Claims By Approval";

            let x = document.getElementById("claimsTable");
            x.remove();

            let table = document.createElement("table");
            table.setAttribute("id", "claimsTable");
            table.setAttribute("class", "table table-striped table-bordered table-sort sortable");
            let thead = document.createElement("thead");
            thead.setAttribute("id", "thead");
            let tbody = document.createElement("tbody");
            tbody.setAttribute("id", "tbody");
            table.appendChild(thead);
            table.appendChild(tbody);
            document.getElementById("claimsDiv").appendChild(table);

            // Create table header array
            let header = [
                "Claim ID", 
                "Insurance Type",  
                "Claim Amount", 
                "Date/Time", 
                "Customer Name", 
                "Customer Age", 
                "Agent Name", 
                "Country", 
                "View"
            ];

            // Create table header row
            let headerRow = document.createElement("tr");
            header.forEach(function (header) {
                let th = document.createElement("th");
                th.setAttribute("style", "cursor: pointer");
                th.innerHTML = header;
                headerRow.appendChild(th);
            });
            thead.appendChild(headerRow);

            // claimArray to hold all the claims
            let claimArray = [];
            for (let i = 0; i < response.length; i++) {
                let claim = response[i];
                claimArray.push(claim);
            }

            // push all the claims into the table
            for (let i = 0; i < claimArray.length; i++) {
                let claim = claimArray[i];
                let row = document.createElement("tr");
                let claimId = document.createElement("td");
                let reason = document.createElement("td");
                let amount = document.createElement("td");
                let datetime = document.createElement("td");
                let customerName = document.createElement("td");
                let customerAge = document.createElement("td");
                let agentName = document.createElement("td");
                let country = document.createElement("td");
                let view = document.createElement("td");

                claimId.innerHTML = claim.claimId;
                reason.innerHTML = claim.reason;
                amount.innerHTML = `$${claim.amount}`;
                datetime.innerHTML = claim.datetime;
                customerName.innerHTML = claim.customerName;
                customerAge.innerHTML = claim.customerAge;
                agentName.innerHTML = claim.agentName;
                country.innerHTML = claim.country;
                view.innerHTML = `<button id="viewButtons" type="button" class="btn btn-secondary" onclick="viewClaim(${claim.claimId})">View</button>`;

                row.appendChild(claimId);
                row.appendChild(reason);
                row.appendChild(amount);
                row.appendChild(datetime);
                row.appendChild(customerName);
                row.appendChild(customerAge);
                row.appendChild(agentName);
                row.appendChild(country);
                row.appendChild(view);

                document.getElementById("tbody").appendChild(row);
            }
            sorttable.makeSortable(table);
        }
    }
    xhr.open("GET", "http://localhost:8080/claims", true);
    xhr.send();
    getAgeChartData();
    getTypeChartData();
}

// ---------------------------
// View Claim Button Alert
// ---------------------------
function viewClaim(id) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            alert(`
            Claim ID: ${response.claimId}
            Customer ID: ${response.customerId} 
            Customer Name: ${response.customerName} 
            Customer Age: ${response.customerAge} 
            Insurance Type: ${response.reason} 
            Agent Id: ${response.agentId}
            Agent Name: ${response.agentName}
            Agent Rating: ${response.agentRating}
            Country: ${response.country}
            Date/Time: ${response.datetime} 
            Claim Amount: $${response.amount}
            Approval: ${(response.approval == "Y") ? "Approved" : "Denied"}
            `);
        }
    };
    xhr.open("GET", `http://localhost:8080/claims/${id}`, true);
    xhr.send();
}

// ---------------------------
// Bar Chart for Age Groups
// ---------------------------

function getAgeChartData() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            const labels = ["0-17", "18-25", "26-35", "36-45", "46-55", "56-79", "80+"];
            const data = [0, 0, 0, 0, 0, 0, 0];
            for (let i = 0; i < response.length; i++) {
                if (response[i].customerAge <= 17) {
                    data[0]++;
                } else if (response[i].customerAge <= 25) {
                    data[1]++;
                } else if (response[i].customerAge <= 35) {
                    data[2]++;
                } else if (response[i].customerAge <= 45) {
                    data[3]++;
                } else if (response[i].customerAge <= 55) {
                    data[4]++;
                } else if (response[i].customerAge <= 79) {
                    data[5]++;
                } else {
                    data[6]++;
                }
            }
            let ctx = document.getElementById('barChart').getContext('2d');
            
            let myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '# Of Customers Per Age Group',
                        data: data,
                        backgroundColor: 'rgba(0, 40, 255, 0.59)',
                        borderColor: 'rgba(0, 40, 255, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }
    }
    xhr.open("GET", "http://localhost:8080/claims", true);
    xhr.send();
}

// ---------------------------
// Pie Chart for Approvals
// ---------------------------

function getTypeChartData() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            let labels = [];
            let data = [];
            for (let i = 0; i < response.length; i++) {
                if (labels.indexOf(response[i].approval) == -1) {
                    labels.push(response[i].approval);
                    data.push(1);
                } else {
                    data[labels.indexOf(response[i].approval)]++;
                }
            }
            let ctx = document.getElementById('pieChart').getContext('2d');
            let myChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '# Of Approvals',
                        data: data,
                        backgroundColor: ['rgba(0, 182, 0, .75)', 'rgba(237, 20, 0, .75)'],
                        borderColor: 'rgba(0, 0, 0, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            display: false,
                            beginAtZero: true
                        }
                    },
                }
            });
        }
    }
    xhr.open("GET", "http://localhost:8080/claims", true);
    xhr.send();
}

// ---------------------------
// Get Country Data 2nd Button
// ---------------------------
function getClaimsByCountry() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            
            document.getElementById("numClaimsDiv").innerHTML = `<b>Total # Claims:</b> ${response.length}`;
            document.getElementById("leftChart").innerHTML = "# of Claims By Age Group";
            document.getElementById("rightChart").innerHTML = "# of Claims By Approval";

            let x = document.getElementById("claimsTable");
            x.remove();

            let table = document.createElement("table");
            table.setAttribute("id", "claimsTable");
            table.setAttribute("class", "table table-striped table-bordered table-sort sortable");
            let thead = document.createElement("thead");
            thead.setAttribute("id", "thead");
            let tbody = document.createElement("tbody");
            tbody.setAttribute("id", "tbody");
            table.appendChild(thead);
            table.appendChild(tbody);
            document.getElementById("claimsDiv").appendChild(table);

            // Create table header array
            let header = [
                "Country", 
                "Average Rating",
                "Approvals",  
                "Denials", 
                "Over 50 Approval Rate", 
                "Over 50 Denial Rate", 
                "Under 50 Approval Rate", 
                "Under 50 Denial Rate",
                "Top Insurance Reason", 
                "Total # Claims"
            ];

            // Create table header row
            let headerRow = document.createElement("tr");
            header.forEach(function (header) {
                let th = document.createElement("th");
                th.setAttribute("style", "cursor: pointer");
                th.innerHTML = header;
                headerRow.appendChild(th);
            });
            thead.appendChild(headerRow);

            // header Array to hold all the Countries
            let claimArray = [];
            for (let i = 0; i < response.length; i++) {
                let claim = response[i];
                claimArray.push(claim);
            }

            // Create table arrays with no duplicate countries
            // Add total number of claims to each country
            let countries = [];
            let countryAverageRatings = [];
            let approvals = [];
            let denials = [];
            let over50Approvals = [];
            let over50Denials = [];
            let under50Approvals = [];
            let under50Denials = [];
            let reason = [];
            let totalClaims = [];
            for (let i = 0; i < claimArray.length; i++) {
                if (countries.indexOf(claimArray[i].country) == -1) {
                    countries.push(claimArray[i].country);

                    approvals.push(0);
                    denials.push(0);
                    over50Approvals.push(0);
                    over50Denials.push(0);
                    under50Approvals.push(0);
                    under50Denials.push(0);
                    // count the number of reasons for each country and push the top reason to reason array
                    let reasonCount = {};
                    for (let j = 0; j < claimArray.length; j++) {
                        if (claimArray[j].country == countries[countries.length - 1]) {
                            if (reasonCount[claimArray[j].reason] == undefined) {
                                reasonCount[claimArray[j].reason] = 1;
                            } else {
                                reasonCount[claimArray[j].reason]++;
                            }
                        }
                    }
                    let topReason = "";
                    let topReasonCount = 0;
                    for (let key in reasonCount) {
                        if (reasonCount[key] > topReasonCount) {
                            topReason = key;
                            topReasonCount = reasonCount[key];
                        }
                    }
                    reason.push(topReason);
                    countryAverageRatings.push(0);
                    totalClaims.push(0);
                }
                let countryIndex = countries.indexOf(claimArray[i].country);
                if (claimArray[i].approval == "Y") {
                    approvals[countryIndex]++;
                    if (claimArray[i].customerAge >= 50) {
                        over50Approvals[countryIndex]++;
                    } else {
                        under50Approvals[countryIndex]++;
                    }
                } else {
                    denials[countryIndex]++;
                    if (claimArray[i].customerAge >= 50) {
                        over50Denials[countryIndex]++;
                    } else {
                        under50Denials[countryIndex]++;
                    }
                }
                countryAverageRatings[countryIndex] += claimArray[i].agentRating;
                totalClaims[countryIndex]++;
            }

            // Create table body
            for (let i = 0; i < countries.length; i++) {
                let row = document.createElement("tr");
                let country = document.createElement("td");
                country.innerHTML = countries[i];
                row.appendChild(country);
                let averageRating = document.createElement("td");
                averageRating.innerHTML = ((countryAverageRatings[i] / totalClaims[i]).toFixed(2));
                row.appendChild(averageRating);
                let approval = document.createElement("td");
                approval.innerHTML = approvals[i];
                row.appendChild(approval);
                let denial = document.createElement("td");
                denial.innerHTML = denials[i];
                row.appendChild(denial);
                let over50Approval = document.createElement("td");
                over50Approval.innerHTML = (over50Approvals[i] == 0) ? "0%" : ((over50Approvals[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(over50Approval);
                let over50Denial = document.createElement("td");
                over50Denial.innerHTML = (over50Denials[i] == 0) ? "0%" : ((over50Denials[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(over50Denial);
                let under50Approval = document.createElement("td");
                under50Approval.innerHTML = (under50Approvals[i] == 0) ? "0%" : ((under50Approvals[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(under50Approval);
                let under50Denial = document.createElement("td");
                under50Denial.innerHTML = (under50Denials[i] == 0) ? "0%" : ((under50Denials[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(under50Denial);
                let topReason = document.createElement("td");
                topReason.innerHTML = reason[i];
                row.appendChild(topReason);
                let totalClaim = document.createElement("td");
                totalClaim.innerHTML = totalClaims[i];
                row.appendChild(totalClaim);
                tbody.appendChild(row);
            }
            sorttable.makeSortable(table);
        }
    }

    xhr.open("GET", "http://localhost:8080/claims", true);
    xhr.send();
    getAgeChartData();
    getTypeChartData();
}

// ---------------------------
// Reason Table 3rd Button
// ---------------------------
function getClaimsByReason() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let response = JSON.parse(xhr.responseText);
            
            document.getElementById("numClaimsDiv").innerHTML = `<b>Total # Claims:</b> ${response.length}`;
            document.getElementById("leftChart").innerHTML = "# of Claims By Age Group";
            document.getElementById("rightChart").innerHTML = "# of Claims By Approval";

            let x = document.getElementById("claimsTable");
            x.remove();

            let table = document.createElement("table");
            table.setAttribute("id", "claimsTable");
            table.setAttribute("class", "table table-striped table-bordered table-sort sortable");
            let thead = document.createElement("thead");
            thead.setAttribute("id", "thead");
            let tbody = document.createElement("tbody");
            tbody.setAttribute("id", "tbody");
            table.appendChild(thead);
            table.appendChild(tbody);
            document.getElementById("claimsDiv").appendChild(table);

            // Create table header array
            let header = [
                "Insurance Reason", 
                "Approvals",  
                "Denials", 
                "Over 50 Approval Rate", 
                "Over 50 Denial Rate", 
                "Under 50 Approval Rate", 
                "Under 50 Denial Rate", 
                "Total # Claims"
            ];

            // Create table header row
            let headerRow = document.createElement("tr");
            header.forEach(function (header) {
                let th = document.createElement("th");
                th.setAttribute("style", "cursor: pointer");
                th.innerHTML = header;
                headerRow.appendChild(th);
            });
            thead.appendChild(headerRow);

            // claimArray to hold all the claims
            let claimArray = [];
            for (let i = 0; i < response.length; i++) {
                let claim = response[i];
                claimArray.push(claim);
            }

            // Create table arrays with no duplicate countries
            // Add total number of claims to each country
            let reasons = [];
            let approvals = [];
            let denials = [];
            let over50Approvals = [];
            let over50Denials = [];
            let under50Approvals = [];
            let under50Denials = [];
            let totalClaims = [];
            for (let i = 0; i < claimArray.length; i++) {
                if (reasons.indexOf(claimArray[i].reason) == -1) {
                    reasons.push(claimArray[i].reason);
                    approvals.push(0);
                    denials.push(0);
                    over50Approvals.push(0);
                    over50Denials.push(0);
                    under50Approvals.push(0);
                    under50Denials.push(0);
                    totalClaims.push(0);
                }
                let reasonIndex = reasons.indexOf(claimArray[i].reason);
                if (claimArray[i].approval == "Y") {
                    approvals[reasonIndex]++;
                    if (claimArray[i].customerAge >= 50) {
                        over50Approvals[reasonIndex]++;
                    } else {
                        under50Approvals[reasonIndex]++;
                    }
                } else {
                    denials[reasonIndex]++;
                    if (claimArray[i].customerAge >= 50) {
                        over50Denials[reasonIndex]++;
                    } else {
                        under50Denials[reasonIndex]++;
                    }
                }
                totalClaims[reasonIndex]++;
            }

            // Create table body
            for (let i = 0; i < reasons.length; i++) {
                let row = document.createElement("tr");
                let reason = document.createElement("td");
                reason.innerHTML = reasons[i];
                row.appendChild(reason);
                let approval = document.createElement("td");
                approval.innerHTML = approvals[i];
                row.appendChild(approval);
                let denial = document.createElement("td");
                denial.innerHTML = denials[i];
                row.appendChild(denial);
                let over50Approval = document.createElement("td");
                over50Approval.innerHTML = (over50Approvals[i] == 0) ? "0%" : ((over50Approvals[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(over50Approval);
                let over50Denial = document.createElement("td");
                over50Denial.innerHTML = (over50Denials[i] == 0) ? "0%" : ((over50Denials[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(over50Denial);
                let under50Approval = document.createElement("td");
                under50Approval.innerHTML = (under50Approvals[i] == 0) ? "0%" : ((under50Approvals[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(under50Approval);
                let under50Denial = document.createElement("td");
                under50Denial.innerHTML = (under50Denials[i] == 0) ? "0%" : ((under50Denials[i] / totalClaims[i]) * 100).toFixed(0) + "%";
                row.appendChild(under50Denial);
                let totalClaim = document.createElement("td");
                totalClaim.innerHTML = totalClaims[i];
                row.appendChild(totalClaim);
                tbody.appendChild(row);
            }
            sorttable.makeSortable(table);
        }
    }

    xhr.open("GET", "http://localhost:8080/claims", true);
    xhr.send();
    getAgeChartData();
    getTypeChartData();
}