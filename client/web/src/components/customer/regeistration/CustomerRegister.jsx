import React,{ useState } from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson2.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import "../../../styles/error.css"
import { useNavigate } from 'react-router-dom'



const CustomerRegister = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [password, setPassword] = useState('');
    const [confirmpassword, setConfirmPassword] = useState('');

    const [emailError, setEmailError] = useState('');
    const [phoneError, setPhoneError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [passwordMatchError, setPasswordMatchError]=useState('')


    const isEmailValid = (email) => {
      const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      return emailRegex.test(email);
    };

    const isPhoneNumberValid = (phoneNumber) => {
      const phoneRegex = /^\d{10}$/; // Change the regex to match the desired phone number format
      return phoneRegex.test(phoneNumber);
    };


    const handleEmailChange = (e) => {
      const newEmail = e.target.value;
      setEmail(newEmail);
      if (!isEmailValid(newEmail)) {
        setEmailError('Invalid email format');
      } else {
        setEmailError('');
      }
    };

    const handlePhoneChange = (e) => {
      const newPhoneNumber = e.target.value.replace(/\D/g, ''); // Remove non-numeric characters
      setPhoneNumber(newPhoneNumber);
      if (!isPhoneNumberValid(newPhoneNumber)) {
        setPhoneError('Invalid phone number format');
      } else {
        setPhoneError('');
      }
    };

    const handlePasswordChange = (e) => {
      const newPassword = e.target.value;
      setPassword(newPassword);
      if (newPassword.length < 6) {
        setPasswordError('Password must be at least 6 characters long');
      } else {
        setPasswordError('');
      }
    };

    const handleConfirmPasswordChange = (e) => {
      const newConfirmPassword = e.target.value;
      setConfirmPassword(newConfirmPassword);
      if (newConfirmPassword !== password) {
        setPasswordMatchError('Passwords do not match');
      } else {
        setPasswordMatchError('');
      }
    };

    const handleSubmit = (e) => {
      e.preventDefault();
  
      if (isEmailValid(email) && isPhoneNumberValid(phoneNumber) && password.length >= 6 && password===confirmpassword)  {
        // Form is valid, you can proceed with registration
        
        // For example, navigate to the next page
        navigate('/customer/login');
      } else {
        // Handle validation errors
        
      }
    };

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

      <form action="post" className="login-form" onSubmit={handleSubmit}>
        <div className="input-names">
        <input type="text"  className="field" required placeholder='First Name'/>
        <input type="text" className="field" required placeholder='Last Name'/>
        </div>
        <input type="email" className="field" required placeholder='Enter your email'onChange={handleEmailChange}/>
        <input type="tel" className="field" required placeholder='Contact number'onChange={handlePhoneChange}/>

        <input type="password" className="field" required placeholder='Enter your password' onChange={handlePasswordChange}/>
        <input type="password" className="field" required placeholder='Re-enter your password' onChange={handleConfirmPasswordChange}/>

        {emailError && <p className="error-message">{emailError}</p>}
        {phoneError && <p className="error-message">{phoneError}</p>}
        {passwordError && <p className='error-message'>{passwordError}</p>}
        {passwordMatchError && <p className='error-message'>{passwordMatchError}</p>}

        <button type="submit" className="btn input-btn" >Register</button>
        <div className="login-options">Already have an FLashMart account? <span onClick={()=>navigate("/customer/login",{state:{email:"testmail@gmail.com"}})}>Login</span></div>
      </form>
      </div>
    </div>
    </div>
  )
}

export default CustomerRegister
