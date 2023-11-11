import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import MapICon from "../../../assets/icons/location.png";
import estimateTravelTime, { FlashMartLocation } from "../../../helper/distance";
import SelectLocationModal from "../../modals/SelectLocationModal";
import Swal from "sweetalert2";
const CheckOut = ({price}) => {
  const navigate = useNavigate();
  const [paymentMethod, setPaymentMethod] = useState("creditDebit");
  const [lat, setLat] = useState("");
  const [lon, setLon] = useState("");
  const data = {lat, lon}
  const [city, setCity] = useState("Nan");
  const [distance, setDistance] = useState({
    distanceInKm: 0,
    averageSpeed: 0,
    estimatedTime: 0,
  });
  const discount = 100;
  const API_key = "1b3f1718d7b11482ccd6e7166344da2f";
  const endPoint = `https://api.openweathermap.org/data/2.5/weather?`;
  const [modalOpen, setModalOpen] = useState(false);

  const openLocationPicker = ()=>setModalOpen(true);
  const closeLocationPicker = ()=>setModalOpen(false);

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

    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#00B250",
      cancelButtonColor: "#d33",
      confirmButtonText: "Pay Now"
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: "Order confirmed!",
          text: "Your order has been confirmed.",
          icon: "success"
        }).then((res)=>{
          if(res.isConfirmed){
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

   //////////////////////////////////////////////////////////////////////////// navigate("/customer/cart/payment");
    // })
    // .catch((error) => {
    //   console.error("Error sending data to the backend:", error);
    // });
    
          }
        });

    
      }
    });

    
  };

  useEffect(() => {
    navigator.geolocation.getCurrentPosition((position) => {
      setLat(position.coords.latitude);
      setLon(position.coords.longitude);
      setDistance(estimateTravelTime(FlashMartLocation, [
        position.coords.latitude,
        position.coords.longitude,
      ]));
      const finalEndPoint = lat && lon?`${endPoint}lat=${lat}&lon=${lon}&appid=${API_key}`:`${endPoint}lat=${position.coords.latitude}&lon=${position.coords.longitude}&appid=${API_key}`;
      axios.get(finalEndPoint).then((res) => {
        setCity(res.data.name);
      });
    });
  }, []);

  return (
    <div className="checkout-section">
      <div className="checkout-container">
        <div className="title">Checkout</div>

        <div className="checkout-shipping-container">
          <img src={MapICon} />
          <div className="shiping-info">
            <div className="city">{city}</div>
            <div className="data">
              <div className="name">Distance:</div>
              <div className="value">{distance.distanceInKm.toFixed(2)} Km</div>
            </div>

            <div className="data">
              <div className="name">ETA:</div>
              <div className="value">{distance.estimatedTime}</div>
            </div>

            <div className="data">
              <div className="name">Delivery Cost:</div>
              <div className="value">Rs. {(distance.distanceInKm*25).toFixed(0)}</div>
            </div>
          </div>

          <div className="btn btn-change" onClick={openLocationPicker}>Change</div>
        </div>
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
          <div className="value">Rs. {price}</div>
        </div>

        <div className="name-value">
          <div className="name">Discount</div>
          <div className="value">Rs. {discount}</div>
        </div>

        <div className="name-value">
          <div className="name">Total Amount</div>
          <div className="value">Rs. {price-discount}</div>
        </div>

        <div className="btn pay-btn" onClick={handlePayNowClick}>
          Pay Now
        </div>
      </div>
      {modalOpen && <SelectLocationModal closeModal={closeLocationPicker} data={data}/>}
    </div>
  );
};

export default CheckOut;
