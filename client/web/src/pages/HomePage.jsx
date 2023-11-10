import React from 'react'
import NavBar from '../components/homepage/NavBar'

import "../styles/homepage/homepage.css"
import About from '../components/homepage/About'
import Product from '../components/homepage/Product'

const HomePage = () => {
  return (
    <div>
      <NavBar/>
      <About/>
      <Product/>
    </div>
  )
}

export default HomePage
