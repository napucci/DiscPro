const cartSection = document.getElementById("cart-section");

const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const headers = {
    'Content-Type': 'application/json'
}


async function getCart(userId){
    console.log(userId)
    await fetch(`http://localhost:8080/api/v1/cart/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCartCard(data))
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
        <p>${obj.quantity} ${obj.name}</p>
            <button class="btn btn-danger" onclick="deleteCartItem(obj.id)">Delete</button>
        </div>
        `
    })
}

getCart(userId);
// document.addEventListener('DOMContentLoaded', getCart)