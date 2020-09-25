fetchAllPersons();

function fetchAllPersons() {
    let url = 'api/person/all';
    var personTable = document.getElementById("personTable");
    fetch(url)
            .then(res => res.json())
            .then(data => {
                console.log(data);
    })
}

function getTableHeader(){
    return `<thead><th>ID</th><th>First Name</th><th>Last Name</th><th>Phone</th><th>Street</th><th>Zip</th><th>City</th></thead>`;
}