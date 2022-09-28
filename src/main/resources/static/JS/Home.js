const cookieArr = document.cookie.split("=")
const userId = cookieArr[1]

const discContainer = document.querySelector(".disc-container")

const headers = {
    'Content-Type': 'application/json'
}

async function getDiscs(){
    await fetch('http://localhost:8080/api/v1/discs', {
        method: "GET",
        headers: headers,
    })
        .then(response => response.json())
        .then(data => createDiscCard(data))
        .catch(err => console.error(err))
}


async function addToCart(obj){
    const response = await fetch(`http://localhost:8080/api/v1/cart/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
        if(response.status == 200){
            return getCart();
        }
}

const createDiscCard = (array) => {
    discContainer.innerHTML = '';
    array.forEach(obj => {
        let discCard = document.createElement("div")
        discCard.innerHTML = `
            <div>
                <p>${obj.id}</p>
                <p>${obj.brand}</p>
                <p>${obj.mold}</p>
                <p>${obj.type}</p>
                <p>${obj.speed}</p>
                <p>${obj.glide}</p>
                <p>${obj.turn}</p>
                <p>${obj.fade}</p>
                <p>${obj.price}</p>
                <img src="${obj.photo}" alt="disc photo" style="height: 150px; object-fit: scale-down;"/>
                <button class="btn btn-primary" onclick="addToCart(this.obj)">Add to Cart</button>
            </div>
        `
        discContainer.append(discCard);
    })
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

document.addEventListener('DOMContentLoaded', getDiscs)