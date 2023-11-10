import React from 'react'
import Logo from "../../assets/icons/logo-transparent.png"
import Shopping from '../../assets/images/shopping.png'
const About = () => {
  return (
    <section className='homepage-about-container'>
      <div className="left-homepage-container">
        <div className="heading">
          <div className="flashmart">
            <img src={Logo}/>
            <div className="title">FlashMart</div>
          </div>
         <div className="main-title-section">
         <div className="">The Fastest</div>
          <div className="">Delivery in <span className="color-title">Your</span></div>
          <div className="color-title">City</div>
         </div>
        </div>
        <div className="desc">Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis nam accusamus modi quibusdam velit sint praesentium facere non nobis consequuntur repellendus quidem at fugit blanditiis enim, dolorem quisquam animi porro?</div>
      
      </div>
      <div className="right-homepage-container">
<img src={Shopping}/>
      </div>
    </section>
  )
}

export default About
