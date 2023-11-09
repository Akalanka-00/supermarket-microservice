import React,{useState} from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson1.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import { useNavigate } from 'react-router-dom'
const CustomerLogin = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [emailValid, setEmailValid] = useState(true);
    const [password, setPassword] = useState('');

    const handleEmailChange = (event) => {
      const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i; // A basic regex for email validation
      const enteredEmail = event.target.value;

      if (emailRegex.test(enteredEmail)) {
        setEmailValid(true);
        setEmail(enteredEmail);
      } else {
        setEmailValid(false);
      }
    };

    const handlePasswordChange = (event) => {
      const enteredPassword = event.target.value;
      setPassword(enteredPassword);
    };

    const handleLoginClick = () =>{
      const data = {
        email,
        password,
      };

      console.log(data);
    }

    // const handleSubmit = async (event) => {
    //   event.preventDefault();
  
    //   try {
    //     // Send a POST request to your backend with the email and password
    //     const response = await fetch('/api/admin/login', {
    //       method: 'POST',
    //       headers: {
    //         'Content-Type': 'application/json',
    //       },
    //       body: JSON.stringify({ email, password }),
    //     });
  
    //     if (response.status === 200) {
    //       // Successfully logged in, you can redirect to the admin dashboard
    //       navigate("/admin/dashboard");
    //     } else {
    //       // Handle login failure, show an error message to the user, e.g., incorrect email or password
    //     }
    //   } catch (error) {
    //     // Handle network or server errors
    //   }
    // };

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
        <input type="email" name="email" className="field" required placeholder='Enter your email'onChange={handleEmailChange}/>
        <input type="password" name="password" className="field" required placeholder='Enter your password'onChange={handlePasswordChange}/>
        <button type="submit" className="btn input-btn" onClick={handleLoginClick}>Login</button>
        <div className="login-options">Don't have an FLashMart account? <span onClick={()=>navigate("/customer/register")}>Register</span></div>
      </form>
      </div>
    </div>
  )
}

export default CustomerLogin
