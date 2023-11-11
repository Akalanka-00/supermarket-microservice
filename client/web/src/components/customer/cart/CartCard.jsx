import React, { useEffect, useState } from "react";
const CartCard = ({ itemQuantity, item, category, price, setPrice }) => {
  const [quantity, setQuantity] = useState(itemQuantity);
  
  const increaseQuantity = () => {
    setQuantity(quantity + 1);
    setPrice(price +  item.price);
  };
  const decreaseQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
      setPrice(price - item.price);
    }
  };

  useEffect(()=>{
    setPrice(item.price*itemQuantity)
  },[setPrice])

  return (
    <div className="cart-card-container">
      <div className="left">
        <img src={item.image} />
      </div>
      <div className="middle">
        <div className="name">{item.name}</div>
        <div className="category">{category}</div>
        <span className="quantity-span">Quantity</span>
        <div className="quantity">
          <div className="minus" onClick={decreaseQuantity}>
            -
          </div>
          <div className="amount">{quantity}</div>
          <div className="plus" onClick={increaseQuantity}>
            +
          </div>
        </div>
      </div>

      <div className="right">
        <div className="prices">
          <div className="price">
            Unit Price <span>Rs. {item.price}</span>
          </div>
          <div className="price">
            Total Price <span>Rs. {item.price * quantity}</span>
          </div>
        </div>

        <div className="remove">Remove Item</div>
      </div>
    </div>
  );
};

export default CartCard;
