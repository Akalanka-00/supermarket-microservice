import React from 'react'
import NavBar from '../components/customer/cart/NavBar'
import CartCard from '../components/customer/cart/CartCard'

import "../styles/customer/cart.css"
import CheckOut from '../components/customer/cart/CheckOut'

const Cart = () => {
  return (
    <div>
      <NavBar title={"Cart"}/>
      <section>
      <div className="cart-container">
      <div className="cart-list-container">
      <div className="title"><span>FlashMart</span> Cart</div>

      <div className="cart-list">
      <CartCard name={"BBQ Deviled Chicken Rice"} itemQuantity={2} category={"Food"} unitPrice={1500}/>

      <CartCard name={"BBQ Deviled Chicken Rice"} itemQuantity={2} category={"Food"} unitPrice={1500}/>

      <CartCard name={"BBQ Deviled Chicken Rice"} itemQuantity={2} category={"Food"} unitPrice={1500}/>

      <CartCard name={"BBQ Deviled Chicken Rice"} itemQuantity={2} category={"Food"} unitPrice={1500}/>

      </div>
      </div>
      <CheckOut/>
      </div>
      
      </section>
    </div>
  )
}

export default Cart
