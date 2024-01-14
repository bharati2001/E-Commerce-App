function showAllOrders(){
    const token = localStorage.getItem("token");
    if (token) {
        const username=localStorage.getItem("username");
        document.getElementById("username").innerText=`Hi! ${username}`;
        const apiUrl = "http://localhost:8080/orderhistory/allorderhistory";
        fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Authorization': token,
                'Content-Type': 'application/json'
            },
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            let productField=document.getElementById("cartField");
            for (let i = 0; i < data.length; i++) {
                const child = data[i];
                productField.innerHTML+=`<div class="row shadow cart mb-3" style="height: 200px;">
                <div class="col-md-4">
                  <img style="width:100%; height:170px;" src="${child.thumbnail}" class="rounded" alt="...">
                </div>
                <span onclick="removeProductFromCart(${child.id})" class="close">&#x2715;</span>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title">${child.title}</h5>
                    <p class="card-text">${child.description}</p>
                    <p class="card-text">Price -<strong>₹ ${child.price}</strong></p>
                    <p class="card-text">Offer -<strong>${child.discountPercentage}%</strong><p/>
                    <p class="card-text"><small class="text-body-secondary">Brand - ${child.brand}</small></p>
                  </div>
                </div>
              </div>`;
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
    }
}
showAllOrders();