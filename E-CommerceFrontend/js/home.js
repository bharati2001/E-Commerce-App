function showAllProducts() {
    const token = localStorage.getItem("token");
    if (token) {
        const username=localStorage.getItem("username");
        document.getElementById("username").innerText=`Hi! ${username}`;
        const apiUrl = "http://localhost:8080/product/getAllProducts";
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
            let productField=document.getElementById("productField");
            productField.innerHTML="";
            for (let i = 0; i < data.length; i++) {
                const child = data[i];
                productField.innerHTML+=`<div onclick="getProduct(${i})" class="col-md-3 mb-4">
                                            <div class="card shadow" style="cursor:pointer;">
                                            <img style="width:100%; height:200px;" src="${child.thumbnail}" class="card-img-top" alt="...">
                                            <div class="card-body">
                                                <span class="card-text">${child.brand}</span>
                                                <h6 class="card-title">${child.title}</h6>
                                                <div class="d-flex justify-content-between">
                                                    <p><strong>₹ ${child.price}</strong></p>
                                                    <p><strong>% ${child.discountPercentage}</strong></p>
                                                </div>
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

showAllProducts();

function getProduct(productId) {
    const id=productId+1;
    window.location.href = `product.html?id=${id}`;
}
