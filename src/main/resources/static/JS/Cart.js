const cartSection = document.getElementById("cart-section");

const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const headers = {
    'Content-Type': 'application/json'
}


// async function getCart(userId){
//     console.log(userId)
//     await fetch(`http://localhost:8080/api/v1/cart/${userId}`, {
//         method: "GET",
//         headers: headers
//     })
//         .then(response => response.json())
//         .then(data => createCartCard(data))
//         .catch(err => console.error(err))
//
// }

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
        return getAllCartDiscs();
}

async function subtractFromQuantity(cartDiscId){
    await fetch(`http://localhost:8080/api/v1/cart/subtract/${cartDiscId}`, {
            method: "PUT",
            headers: headers
    }
        )
        .catch(err => console.error(err))
    return getAllCartDiscs();
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
    for(let i = 0; i < array.length; i++){
        let cartDiscCard = document.createElement("div");
        cartDiscCard.classList.add("card")
        cartDiscCard.style.width = "250px";
        cartDiscCard.innerHTML = `
        <img src="${array[i].photo}" class="card-header" style="height: 100px; object-fit: scale-down;"/>
          <p class="card-body">${array[i].brand} ${array[i].mold} ${array[i].type} ${array[i].price} ${arr2[i].quantity} </p>
        <button class="btn btn-danger cart-header" onclick="deleteCartItem(${arr2[i].id})">Delete</button>
        <button onclick="subtractFromQuantity(${arr2[i].id})">-</button>
        <button onclick="addToQuantity(${arr2[i].id})">+</button>
            `

        cartSection.append(cartDiscCard)
    }
}

getAllItemsInCart();
// document.addEventListener('DOMContentLoaded', getCart)