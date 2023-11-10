import React from "react";
import { RiCoupon4Fill } from "react-icons/ri";
import OrderImage from "../../../assets/icons/order.png";
import orderList from "../../../context/Order";

const OrderHistory = ({show, setShow}) => {

  const handleClose = () => setShow(false);
  const handleShow = () => {
    if(show){
      handleClose();
    }
    setShow(true);
  };

  return (
    <section className="ongoing-order-container">
      <div className="header">
        <div className="heading">
          <div className="title">Previous Orders </div>
          <span>02</span>
        </div>
       <div className="order-list">
       {orderList.map((item, index)=>(
          <div className="ongoing-order-body">
          <div className="order-header">
            <img src={OrderImage} />
            <div className="order-info">
              <div className="order-id">
                9c77d6ba-675f-494d-abdb-259f20217740
              </div>
              <div className="data">
                <div className="name">Ordered Date</div>
                <div className="value">2023-05-14</div>
              </div>

              <div className="data">
                <div className="name">Ordered Time</div>
                <div className="value">20:10:18</div>
              </div>
              <div className="data">
                <div className="name">Bill amount</div>
                <div className="value">Rs. 5600</div>
              </div>
            </div>

            
          </div>
         

            <div className="order-status">
              Picked Up
            </div>

          <div className="btn" onClick={handleShow}>Items</div>
        </div>
        ))}
       </div>
      </div>
    </section>
  );
};

export default OrderHistory;
