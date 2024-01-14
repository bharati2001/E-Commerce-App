function showAllCarts(){
    const token = localStorage.getItem("token");
    if (token) {
        const username=localStorage.getItem("username");
        document.getElementById("username").innerText=`Hi! ${username}`;
        const apiUrl = "http://localhost:8080/cart/getallcartproduct";
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
            if(data.length > 0) {
                document.getElementById("priceTab").style.display = "block";
            }
            let productField=document.getElementById("cartField");
            let totalitems=data.length;
            let totalprice=0;
            let totalDiscount=0;
            let deleveryCharge=0;
            productField.innerHTML="";
            for (let i = 0; i < data.length; i++) {
                const child = data[i];
                totalprice=totalprice+child.price;
                totalDiscount=totalDiscount+child.discountPercentage;
                deleveryCharge=deleveryCharge+50;
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
                    <p class="card-text">Quantity -<strong>${child.quantity}</strong><p/>
                  </div>
                </div>
              </div>`;
            }
            document.getElementById("items").innerText=totalitems;
            document.getElementById("totalActualPrice").innerText=`₹${totalprice}`;
            document.getElementById("discount").innerText=`-${parseInt(totalDiscount)}`;
            document.getElementById("deliverycharge").innerText=`+${deleveryCharge}`;
            document.getElementById("totalamount").innerText=`₹${parseInt(totalprice+totalDiscount+deleveryCharge)}`;
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
    }
}
showAllCarts();

function removeProductFromCart(id){
    const token = localStorage.getItem("token");
    if (token) {
        const apiUrl = `http://localhost:8080/cart/deleteproductfromcart/${id}`;
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
            if(data){
                if(data.status === 'success'){
                    location.reload();
                }
                else{
                    alert(data.message);
                }
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
    }
}

function placeOrder(){
    const token = localStorage.getItem("token");
    if (token) {
        const apiUrl = "http://localhost:8080/cart/deleteallproduct";
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
            if(data){
                if(data.status === 'success'){
                    alert(data.message);
                    location.reload();
                }
                else{
                    alert(data.message);
                }
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
    }
}