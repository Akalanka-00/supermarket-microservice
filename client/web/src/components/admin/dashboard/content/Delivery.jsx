import React from 'react'
import Avatar from "../../../../assets/icons/profile.png"
import deliveryPersons from '../../../../context/DeliveryPersons'
const Delivery = () => {
  return (
    <section className='delivery-container' >
      <div className="header">
        <div className="title">
          Delivers
        </div>
      </div>
      <div className="delivery-list">
        {deliveryPersons.map((item,index)=>(
          <div className="delivery-item" key={index}>
          <div className="avatar">
            <img src={Avatar}/>
          </div>
          <div className="info">
            <div className="name">John Doe</div>
            <div className="email">johndoe@gmail.com</div>
            <div className="contact">071 123 4562</div>
          </div>
        </div>
        ))}
      </div>
    </section>
  )
}

export default Delivery
