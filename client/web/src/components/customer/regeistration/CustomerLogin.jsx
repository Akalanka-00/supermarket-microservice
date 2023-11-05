import React from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson1.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import { useNavigate } from 'react-router-dom'
const CustomerLogin = () => {
    const navigate = useNavigate();
  return (
    <div className='customer-login-container'>
      <div className="left">
        <span className='left-title'>Welcome Back!</span>
        <img src={DeliveryPerson}/>

      </div>
      <div className="right">
      <div className="header">
        <img src={LOGO}/>
        <span>FlashMart</span>
      </div>
      <div className="sub-title">
        <div><span>S</span>elect</div> <div><span>B</span>uy</div> <div><span>D</span>eliver</div>
      </div>

      <form action="" className="login-form">
        <input type="email" className="field" placeholder='Enter your email'/>
        <input type="password" className="field" placeholder='Enter your password'/>
        <button type="submit" className="btn input-btn" >Login</button>
        <div className="login-options">Don't have an FLashMart account? <span onClick={()=>navigate("/customer/register")}>Register</span></div>
      </form>
      </div>
    </div>
  )
}

export default CustomerLogin
