import React from 'react'
import Avatar from "../../../../assets/icons/profile.png"
import deliveryPersons from '../../../../context/DeliveryPersons'
import { useNavigate } from 'react-router-dom'
const Delivery = () => {
  const navigate = useNavigate();
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
          <div className="personal">
          <div className="avatar">
            <img src={Avatar}/>
          </div>
          <div className="info">
            <div className="name">John Doe</div>
            <div className="email">johndoe@gmail.com</div>
            <div className="contact">071 123 4562</div>
          </div>
          </div>

          <div className="status">
            Status: <span>{item.status}</span>
          </div>

          <div className="options">
              <div className="btn option" onClick={()=>navigate("map/")}><span>View on Map</span> </div>
              {/* <div className="btn option" ><span>View</span> </div> */}
              </div>
        </div>
        ))}
      </div>
    </section>
  )
}

export default Delivery
