import React from "react";

const CheckOut = () => {
  return (
    <div className="checkout-section">
      <div className="checkout-container">
        <div className="title">Checkout</div>

        <div className="payment-method-selection">
        <div className="label">Select the Payment Method</div>
        <div className="methods">
          <label>
            <input type="radio" value={"check"} className="method" />
            <span>Credit/Debit Card</span>
          </label>

          <label>
            <input type="radio" value={"check"} className="method" />
            <span>On Cash</span>
          </label>
          
        </div>
        </div>

        <div className="name-value">
            <div className="name">Amount</div>
            <div className="value">Rs. 1000</div>
        </div>

        <div className="name-value">
            <div className="name">Discount</div>
            <div className="value">Rs. 100</div>
        </div>

        <div className="name-value">
            <div className="name">Total Amount</div>
            <div className="value">Rs. 900</div>
        </div>

        <div className="btn pay-btn">Pay Now</div>
      </div>
    </div>
  );
};

export default CheckOut;
