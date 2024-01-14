async function getProductIdFromUrl() {
    const token = localStorage.getItem("token");
    if (token) {
        const username = localStorage.getItem("username");
        document.getElementById("username").innerText = `Hi! ${username}`;
        
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        const apiUrl = `http://localhost:8080/product/getProduct?id=${id}`;

        try {
            const response = await fetch(apiUrl, {
                method: 'GET',
                headers: {
                    'Authorization': token,
                    'Content-Type': 'application/json'
                },
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            showProduct(data);
            const addToCartButton = document.querySelector('.btn-primary');
            addToCartButton.addEventListener('click', () => {
                const apiUrl = "http://localhost:8080/cart/saveproductoncart";
                fetch(apiUrl, {
                    method: 'POST',
                    headers: {
                        'Authorization': token,
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data),
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    if(data.status === 'success'){
                        alert(data.message);
                    }
                    else{
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('Fetch error:', error);
                });
            });
        } catch (error) {
            console.error('Fetch error:', error);
        }
    }
}

getProductIdFromUrl();

function showProduct(product){
    const productField=document.getElementById("productField");
    productField.innerHTML="";
    let child=product;
    productField.innerHTML=`<div class="col-md-6">
                                <img style="width:100%; height:400px;" src="${child.thumbnail}" class="rounded" alt="...">
                                </div>
                                <div class="col-md-5">
                                <div class="card-body">
                                    <h5 class="card-title">${child.title}</h5>
                                 <p class="card-text">${child.description}</p>
                                    <p class="card-text">Price -<strong>₹ ${child.price}</strong></p>
                                    <p class="card-text">Offer -<strong>${child.discountPercentage}%</strong><p/>
                                    <p class="card-text">Rating -<strong> ${child.rating}</strong><p/>
                                    <p class="card-text">Available -<strong> ${child.stock}</strong><p/>
                                    <p class="card-text">Brand -<strong> ${child.brand}</strong><p/>
                                    <p class="card-text"><small class="text-body-secondary">Category -${child.category}</small></p>
                                    <div class="d-grid">
                                        <button class="btn btn-primary" type="button">Add to Cart</button>
                                    </div>
                                </div>
                                </div>`;
}

