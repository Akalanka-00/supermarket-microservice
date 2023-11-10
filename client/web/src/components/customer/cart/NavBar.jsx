import React, { useState } from 'react'
import navItems from '../../../context/HomepageNavItems'
import { SiSourceengine } from "react-icons/si";
import LOGO from "../../../assets/icons/logo.png"
import {BsCartCheck} from "react-icons/bs"
import {MdOutlineLocalShipping} from "react-icons/md"
import Avatar from "../../../assets/icons/profile.png"
import { useNavigate, useParams } from "react-router-dom";

const NavBar = ({title}) => {

  const [isUser, setAsUser] = useState(true);

  const navigate = useNavigate();
  return (
    <div className="navbar-container">
      <div className="nav-logo" onClick={()=>navigate("/")}>
        <img src={LOGO}/>
        <span>FlashMart</span>
      </div>

      {/* <div className="nav-items">
        {navItems.map((item,index)=>(
          <div className="" key={index}>
            <a href={`#${item.link}`}>{item.name}</a>
          </div>
        ))}
      </div> */}

      <div className="heading">
        {title}
      </div>

      <div className="corner">
        {isUser?<div className='registered-user-container'>
        
        <div className="cart" onClick={()=>navigate("/customer/shipping")}>
            <MdOutlineLocalShipping className='cart-icon' />
            <span>1</span>
          </div>

        <div className="cart" onClick={()=>navigate("/customer/cart")}>
            <BsCartCheck className='cart-icon' />
            <span>6</span>
          </div>
          

          <div className="avatar">
            <img src={Avatar}/>
          </div>
        </div>:<div className='non-registered-user-container'>
          <div className="btn" onClick={()=>navigate("/customer/login")}>Login</div>
          </div>}
      </div>
    </div>
  )
}

export default NavBar
