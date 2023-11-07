import React, { useState } from 'react'
import NavBar from '../components/customer/cart/NavBar'
import CurrentOrder from '../components/customer/shipping/CurrentOrder'
import LoadingScreen from '../components/shared/LoadingScreen';
import "../styles/customer/shipping.css"

const Shipping = () => {
  const [isLoading, setAsLoading] = useState(false);
  // setTimeout(() => {
  //   setAsLoading(false)
  // }, 2000);

  return (
    <div>
      {isLoading && <LoadingScreen/>}
      <div className="">
      <NavBar title={"Shipping"}/>
      <CurrentOrder/>
      </div>

      
    </div>
  )
}

export default Shipping
