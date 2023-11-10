import React,{useState} from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson1.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import { useNavigate } from 'react-router-dom'
const AdminLogin = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [emailValid, setEmailValid] = useState(true);
    const [password, setPassword] = useState('');

    const handleEmailChange = (event) => {
      const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i; // A basic regex for email validation
      //const email = event.target.value;
  
      if (emailRegex.test(email)) {
        setEmailValid(true);
        setEmail(event.target.value);
      } else {
        setEmailValid(false);
      }
    };

    const handlePasswordChange = (event) => {
      setPassword(event.target.value);
    };

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
        <div><span>S</span>elect</div> <div><span>S</span>ell</div> <div><span>D</span>eliver</div>
      </div>

      <form action="" className="login-form" onSubmit={()=>navigate("/admin/dashboard")}>
        <input type="email" className="field" placeholder='Enter your email' onChange={handleEmailChange}/>
        <input type="password" className="field" placeholder='Enter your password'onChange={handlePasswordChange}/>
        <button type="submit" className="btn input-btn" >Login</button>
      </form>
      </div>
    </div>
  )
}

export default AdminLogin
