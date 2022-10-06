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


// async function addToCart(obj){
//
//     const response = await fetch(`http://localhost:8080/api/v1/cart/${userId}`, {
//         method: "POST",
//         body: JSON.stringify(obj),
//         headers: headers
//     })
//         .catch(err => console.error(err.message))
//         if(response.status == 200){
//             return getCart();
//         }
// }

async function addToCartById(id){

    const response = await fetch(`http://localhost:8080/api/v1/cart/from/${userId}`, {
        method: "POST",
        body: JSON.stringify(id),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if(response.status == 200){
       console.log('Added successfully')
    }
}

const createDiscCard = (array) => {
    discContainer.innerHTML = '';
    console.log(array[0])
    array.forEach(obj => {
        let discCard = document.createElement("div")
        discCard.innerHTML = ` 
            <div class="card mt-2" style="max-width: 540px;">
              <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${obj.photo}" class="card-img" alt="disc photo" style="height: 150px; object-fit: scale-down;">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${obj.brand}, ${obj.mold} </h5>
                        <p class="card-text">${obj.type}: ${obj.speed}, ${obj.glide}, ${obj.turn}, ${obj.fade}</p>
                        <p class="card-text"><small class="text-muted">$${obj.price} </small></p>
                        <button class="btn btn-primary" onclick="addToCartById(${obj.id})">Add to Cart</button>
                    </div>
                </div>
                </div>
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