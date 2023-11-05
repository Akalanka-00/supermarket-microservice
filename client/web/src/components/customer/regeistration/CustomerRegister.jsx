import React from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson2.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import { useNavigate } from 'react-router-dom'
const CustomerRegister = () => {
    const navigate = useNavigate();
  return (
    <div>
       <div className='customer-login-container'>
      <div className="left">
      <span className='left-title'>Register Now!</span>
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

      <form action="post" className="login-form" onSubmit={()=>{
        navigate("/customer/otp")
      }}>
        <div className="input-names">
        <input type="text" className="field" placeholder='First Name'/>
        <input type="text" className="field" placeholder='Last Name'/>
        </div>
        <input type="email" className="field" placeholder='Enter your email'/>
        <input type="tel" className="field" placeholder='Contact number'/>

        <input type="password" className="field" placeholder='Enter your password'/>
        <input type="password" className="field" placeholder='Re-enter your password'/>
        <button type="submit" className="btn input-btn" >Register</button>
        <div className="login-options">Already have an FLashMart account? <span onClick={()=>navigate("/customer/login",{state:{email:"testmail@gmail.com"}})}>Login</span></div>
      </form>
      </div>
    </div>
    </div>
  )
}

export default CustomerRegister
