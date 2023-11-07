import React from 'react'
import "../../styles/loadingScreen.css"
import Logo from "../../assets/icons/logo-transparent.png"
const LoadingScreen = () => {
  return (
    <div className='loading-container'>
     <img src={Logo}/>
    </div>
  )
}

export default LoadingScreen
