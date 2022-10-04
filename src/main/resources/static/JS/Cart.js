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
        // .then(res => console.log(res.json()))
        .then(res => res.json())
        // .then(data => createCartCard(data))
        .catch(err => console.error(err));
        // (response => response.json())
}

async function getAllCartDiscs(){
    await fetch(`http://localhost:8080/api/v1/cart/userdiscs/${userId}`, {
        method: "GET",
        headers: headers
    })
        // .then(res => console.log(res.json()))
        .then(res => res.json())
        .catch(err => console.error(err))
}

async function getCartData(){
    const getDiscInfo = await getAllItemsInCart();
    const getDiscQuantity = await getAllCartDiscs();
    console.log(getDiscInfo, getDiscQuantity)
    createCartCard(getDiscInfo, getDiscQuantity)

}

// async function getCartItems(){
//     await getAllCartItems().json();
// }


async function deleteCartItem(cartDiscId){
    await fetch(`http://localhost:8080/api/v1/cart/${userId}/${cartDiscId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    return getCart(userId);
}

async function updateQuantity(cartDiscId, type){
    let bodyObj = {
        type: type
    }
    await fetch(`http://localhost:8080/api/v1/cart/${cartDiscId}`, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))
        getAllCartDiscs();
}


const createCartCard = (array, arr2) => {

    cartSection.innerHTML = ''
    array.sort((a, b) => {
        return a.id - b.id;
    })
    arr2.sort((a, b) => {
        return a.id - b.id;
    })
    for(let i = 0; i < array.length; i++){
        let cartDiscCard = document.createElement("div");
        cartDiscCard.classList.add("card")
        cartDiscCard.style.width = "250px";
        cartDiscCard.innerHTML = `
        <img src="${obj.photo}" class="card-header" style="height: 100px; object-fit: scale-down;"/>
          <p class="card-body">${array[i].brand} ${array[i].mold} ${array[i].type} ${array[i].price} ${arr2[i].quantity} </p>
        <button class="btn btn-danger cart-header" onclick="deleteCartItem(${array[i].id})">Delete</button>
        <button onclick="updateQuantity(${array[i].id}, 'minus')">-</button>
        <button onclick="updateQuantity(${array[i].id}, 'plus')">+</button>
            `


    }
    array.forEach(obj => {
        console.log(obj)

          

        cartSection.append(cartDiscCard)
    })
}

getCartData();
// document.addEventListener('DOMContentLoaded', getCart)