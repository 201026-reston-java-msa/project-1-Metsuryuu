//JavaScript to get "Pending" requests.

//Let's use a GET request.
fetch('http://localhost:8080/project-1/getRequests', {
    method: "POST",
    
    //so the idea here is I send a JSON object which gets processed in a switch and returns the relevant requests.
    body: JSON.stringify({
        statusId: 1
    }),

    headers: {
        "Content-type": "application/json; charset=UTF-8"
    }
})
.then(data => {
    if(data.ok){
        console.log("Success")
    }else{
        console.log("Server Error")
    }
})
.catch(error => console.log("Error"))
.then(response => response.json())
.then(json => {
    let li = `<tr><th>Amount</th><th>Submitted</th><th>Description</th></tr>`;

    json.array.forEach(pending => {
        //This is the idea, obviously change your variable names.
        li += `<tr>
            <td>${pending.amount}</td>
            <td>${pending.submitted}</td>
            <td>${pending.description}</td>
        </tr>`;
    });

    //Display to the screen.
    document.getElementById("pending").innerHTML = li;
});

