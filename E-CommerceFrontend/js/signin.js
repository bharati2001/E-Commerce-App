document.getElementById("signinBtn").addEventListener("click", function () {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var userData = {
        username: username,
        password: password
    };

    fetch('http://localhost:8080/api/auth/user', {
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
                localStorage.setItem("username", username);
                localStorage.setItem("token", data.message);
                window.location.href = 'home.html';
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