document.getElementById("signupBtn").addEventListener("click", function () {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var userData = {
        username: username,
        password: password
    };

    fetch('http://localhost:8080/user/createuser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    })
    .then(response => response.json())
    .then(data => {
        if(data){
            if(data.status === 'success'){
                alert(data.message);
                window.location.href = 'index.html';
            }
            else{
                alert(data.message);
            }
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});