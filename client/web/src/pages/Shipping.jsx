import React, { useState } from "react";
import NavBar from "../components/customer/cart/NavBar";
import CurrentOrder from "../components/customer/shipping/CurrentOrder";
import LoadingScreen from "../components/shared/LoadingScreen";
import "../styles/customer/shipping.css";
import OrderHistory from "../components/customer/shipping/OrderHistory";
import Offcanvas from "react-bootstrap/Offcanvas";
import { BsBagCheck } from "react-icons/bs";
import { productItems } from "../context/Products";
const Shipping = () => {
  const [isLoading, setAsLoading] = useState(false);
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  // setTimeout(() => {
  //   setAsLoading(false)
  // }, 2000);

  return (
    <div>
      {isLoading && <LoadingScreen />}
      <div className="">
        <NavBar title={"Shipping"} />
        <CurrentOrder setShow={setShow} show={show} />
        <OrderHistory setShow={setShow} show={show} />
      </div>

      <Offcanvas show={show} onHide={handleClose} placement="end" scroll={true}>
        <Offcanvas.Header closeButton>
          <Offcanvas.Title>
            <div className="shipping-header">
              <BsBagCheck />
              <span>Order List</span>
            </div>
          </Offcanvas.Title>
        </Offcanvas.Header>
        <Offcanvas.Body className="shipping-item-offcanvas-body">
          {productItems.map((item, index) => (
            <div className="shipping-item-container" key={index}>
              <div className="item-header">
                <img src={item.image}/>
                <div className="shipping-item-info">
                <div className="name">{item.name}</div>
                <div className="unit-price">Rs. {item.price}</div>
                </div>

              </div>

              <div className="shipping-item-price-info">
                <div className="name">Quantity: 3</div>
                <div className="unit-price">Rs. {item.price*3}</div>
                </div>
            </div>
          ))}

          <div className="box-breaker">
            <div className="total-bill-container">
              <div className="name">Total Bill</div>
              <div className="total-price">Rs. 10320</div>
            </div>
          </div>
        </Offcanvas.Body>
      </Offcanvas>
    </div>
  );
};

export default Shipping;
