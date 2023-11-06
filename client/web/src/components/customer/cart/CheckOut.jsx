import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const CheckOut = () => {
  const navigate = useNavigate();
  const [paymentMethod, setPaymentMethod] = useState("creditDebit");

  const handleRadioChange = (e) => {
    setPaymentMethod(e.target.value);
  };

  const handlePayNowClick = () => {
    const amount = 1000;
    const discount = 100;
    const totalAmount = amount - discount;

    const data = {
      paymentMethod,
      amount,
      discount,
      totalAmount,
    };

    console.log(data);
    
    // fetch("YOUR_BACKEND_ENDPOINT", {
    //   method: "POST",
    //   headers: {
    //     "Content-Type": "application/json",
    //   },
    //   body: JSON.stringify(data),
    // })
    //   .then((response) => response.json())
    //   .then((responseData) => {
    //     console.log("Response from the backend:", responseData);

        navigate("/customer/cart/payment");
      // })
      // .catch((error) => {
      //   console.error("Error sending data to the backend:", error);
      // });
  };

  return (
    <div className="checkout-section">
      <div className="checkout-container">
        <div className="title">Checkout</div>

        <div className="payment-method-selection">
          <div className="label">Select the Payment Method</div>
          <div className="methods">
            <label>
              <input
                type="radio"
                name="paymentMethod"
                value="creditDebit"
                className="method"
                checked={paymentMethod === "creditDebit"}
                onChange={handleRadioChange}
              />
              <span>Credit/Debit Card</span>
            </label>

            <label>
              <input
                type="radio"
                name="paymentMethod"
                value="cash"
                className="method"
                checked={paymentMethod === "cash"}
                onChange={handleRadioChange}
              />
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

        <div className="btn pay-btn" onClick={handlePayNowClick}>
          Pay Now
        </div>
      </div>
    </div>
  );
};

export default CheckOut;
