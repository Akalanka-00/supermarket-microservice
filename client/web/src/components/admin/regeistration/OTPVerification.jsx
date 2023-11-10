import React from 'react'
import OTP from "../../../assets/images/otp.jpg"
import { useLocation, useNavigate } from 'react-router-dom';
import "../../../styles/customer/login.css"

const OTPVerification = () => {
    const location = useLocation();
    const data = location.state;
    console.log(data);
    const navigate = useNavigate();
  return (
    <div className='otp-container'>
      <div className="header">
        <img src={OTP}/>
        <span>OTP Verification</span>
        <div className="sub-title">One time Password has been sent to your registered email address.</div>
        <input className='otp-field' placeholder='Enter OTP'/>
      </div>

      <div className="bottom">
        <div className="no-otp">Don't you recieved the OTP? <span>Resend OTP</span></div>
        <div className="btn verify-otp" onClick={()=>{navigate("/")}}>Verify</div>
      </div>
    </div>
  )
}

export default OTPVerification
