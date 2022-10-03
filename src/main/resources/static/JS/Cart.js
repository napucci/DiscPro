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

async function getAllCartItems(){

    await fetch(`http://localhost:8080/api/v1/cart/using/${userId}`, {
        method: "GET",
        headers: headers
    })

        // .then(response => console.log(response))
        .then(response => response.json())
        // .then(data => createCartCard(data))
        .catch(err => console.error(err))

}

async function deleteCartItem(cartDiscId){
    await fetch(`http://localhost:8080/api/v1/cart/${userId}/${cartDiscId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    return getCart(userId);
}


const createCartCard = (array) => {

    cartSection.innerHTML = ''
    array.forEach(obj => {
        console.log(obj)

        cartSection.innerHTML = `
        <div>
        
        <p>${obj.quantity} ${obj.name} ${obj.type}</p>
            <button class="btn btn-danger" onclick="deleteCartItem(obj.id)">Delete</button>
        </div>
        `
    })
}

getAllCartItems();
// document.addEventListener('DOMContentLoaded', getCart)