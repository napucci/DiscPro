const cartSection = document.getElementById("cart-section");
const orderMenu = document.getElementById("order-menu");

const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const headers = {
    'Content-Type': 'application/json'
}

async function getAllItemsInCart() {

    await fetch(`http://localhost:8080/api/v1/cart/using/${userId}`, {
        method: "GET",
        headers: headers
    })

        .then(res => res.json())
        .then(data => getAllCartDiscs(data))
        .catch(err => console.error(err));
}

async function getAllCartDiscs(array){
    await fetch(`http://localhost:8080/api/v1/cart/userdiscs/${userId}`, {
        method: "GET",
        headers: headers
    })
        // .then(res => console.log(res.json()))
        .then(res => res.json())
        .then(data => createCartCard(data, array))
        .catch(err => console.error(err))
}

async function deleteCartItem(cartDiscId){
    await fetch(`http://localhost:8080/api/v1/cart/${userId}/${cartDiscId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    getAllItemsInCart();
}

async function addToQuantity(cartDiscId){

    await fetch(`http://localhost:8080/api/v1/cart/${cartDiscId}`, {
        method: "PUT",
        headers: headers
    })
        .catch(err => console.error(err))
    getAllItemsInCart();
}

async function subtractFromQuantity(cartDiscId){
    await fetch(`http://localhost:8080/api/v1/cart/subtract/${cartDiscId}`, {
            method: "PUT",
            headers: headers
    }
        )
        .catch(err => console.error(err))
    getAllItemsInCart();
}

const createCartCard = (arr2, array) => {
    cartSection.innerHTML = ''
    array.sort((a, b) => {
        return a.id - b.id;
    })
    arr2.sort((a, b) => {
        return a.id - b.id;
    })
    console.log(array, arr2)
    for(let i = 0; i < array.length; i++) {
        let cartDiscCard = document.createElement("div");
        cartDiscCard.classList.add("card", "mt-2")
        cartDiscCard.style.width = "640px";
        cartDiscCard.style.height = "175px";
        cartDiscCard.innerHTML = `
              <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${array[i].photo}" class="card-img mt-3" alt="disc photo" style="height: 150px; object-fit: scale-down;">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${array[i].brand}, ${array[i].mold} </h5>
                        <p class="card-text">${array[i].type}: ${array[i].speed}, ${array[i].glide}, ${array[i].turn}, ${array[i].fade}</p>
                        <p class="card-text"><button onclick="subtractFromQuantity(${arr2[i].id})">-</button> Amount: ${arr2[i].quantity} <button onclick="addToQuantity(${arr2[i].id})">+</button></p>
                        <p class="card-text"><small class="text-muted">$${array[i].price * arr2[i].quantity}<button class="btn btn-danger cart-header float-end" style="transform: translateY(-40px);"onclick="deleteCartItem(${arr2[i].id})">Delete</button></small></p>  
                    </div>
                </div>
                </div>
            </div>
            `
        cartSection.append(cartDiscCard)
    }
        let subtotal = 0;
        for(let i = 0; i < array.length; i++){
            subtotal += (array[i].price * arr2[i].quantity)
        }
    const receipt = document.createElement("div")
        orderMenu.innerHTML = ``
        receipt.classList.add("mt-3")
        receipt.innerHTML =`
        <p>Order Subtotal: <span class="float-end">$${subtotal.toFixed(2)}</span></p>
        <p>Estimated Tax: <span class="float-end">${(subtotal * 1.06).toFixed(2)}</span></p>
        <p>Estimated Total: <span class="float-end mb-3">$${((subtotal * 1.06)).toFixed(2)}</span></p>
        <hr>
        <button class="btn btn-lg btn-success float-end mt-3" style="width: 390px" onclick="handleCheckout()">CHECKOUT</button>
        <div class="mt-3 text-center"><small class="text-muted" style="transform: translate(20px)">Order #30070429693</small></div>
    `
        orderMenu.append(receipt);
}

function handleCheckout(){

    cartSection.innerHTML = ``
    orderMenu.innerHTML = `Thank you for you purchase. Your order has begun processing.`

}


getAllItemsInCart();
// document.addEventListener('DOMContentLoaded', getCart)