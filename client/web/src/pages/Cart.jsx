import React, { useState } from 'react'
import NavBar from '../components/customer/cart/NavBar'
import CartCard from '../components/customer/cart/CartCard'

import "../styles/customer/cart.css"
import CheckOut from '../components/customer/cart/CheckOut'
import { productItems } from '../context/Products'

const Cart = () => {
  const [price, setPrice] = useState(0);
  return (
    <div>
      <NavBar title={"Cart"}/>
      <section>
      <div className="cart-container">
      <div className="cart-list-container">
      <div className="title"><span>FlashMart</span> Cart</div>

      <div className="cart-list">
      {productItems.map((item, index)=>(
        <CartCard key={index} itemQuantity={2} item={item} category={"Meat and Seafood"} price={price} setPrice={setPrice}/>
      ))}

     

      </div>
      </div>
      <CheckOut price={price}/>
      </div>
      
      </section>
    </div>
  )
}

export default Cart
