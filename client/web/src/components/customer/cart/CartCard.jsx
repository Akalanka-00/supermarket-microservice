import React, { useState } from 'react'
import Item from "../../../assets/images/catrgory/food.jpg"
const CartCard = ({name, itemQuantity, category, unitPrice}) => {

    
    const [quantity, setQuantity] = useState(itemQuantity);

    const increaseQuantity =()=>setQuantity(quantity+1);
    const decreaseQuantity =()=>quantity>1 && setQuantity(quantity-1);

  return (
    <div className='cart-card-container'>
      <div className="left">
        <img src={Item}/>
      </div>
      <div className="middle">
        <div className="name">{name}</div>
        <div className="category">{category}</div>
        <span className='quantity-span'>Quantity</span>
        <div className="quantity">
            <div className="minus" onClick={decreaseQuantity}>-</div>
            <div className="amount">{quantity}</div>
            <div className="plus"onClick={increaseQuantity}>+</div>
        </div>

      </div>

      <div className="right">
      <div className="prices">
            <div className="price">Unit Price <span>Rs. {unitPrice}</span></div>
            <div className="price">Total Price <span>Rs. {unitPrice*quantity}</span></div>
        </div>

        <div className="remove">Remove Item</div>
      </div>
    </div>
  )
}

export default CartCard
