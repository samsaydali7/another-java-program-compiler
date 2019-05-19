function openTab(cityName) {
    var i;
    var x = document.getElementsByClassName("city");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(cityName).style.display = "block";
}

function fillTable(){
    fillHeaders();
    fillRows();
}

function fillHeaders(){
    var table_headers = document.getElementById("table_headers");
    headers.forEach(header => {
        table_headers.innerHTML += `<th>${header}</th>`
    });
}

function fillRows(){
    var table_rows = document.getElementById("table_body");
    for(i=0 ; i < rows.length ; i++){
        var row = "";
        row += `<tr>`;
        for(j=0 ; j < rows[i].length ; j++){
            console.log(rows[i][j]);
            row += `<td>${rows[i][j]}</td>`;
        }
        row += `</tr>`;
        table_rows.innerHTML += row;
    }
}
function fillExplain(){
    document.getElementById('explainOutput').innerText = explain_output;
}
fillTable();
fillExplain();