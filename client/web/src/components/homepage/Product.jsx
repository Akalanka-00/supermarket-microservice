import React, { useState } from 'react'
import Food from "../../assets/images/catrgory/food.jpg"
import {categoryItems, productItems} from '../../context/Products'
import {BsCartCheck} from "react-icons/bs"
const Product = () => {

  const [activeCategory, setActiveCategory] = useState("");
  
  return (
    <section className='product-container'>
      <div className="title">Categories</div>
      <div className="category-card-container">
       {categoryItems.map((item,index)=>(
        <div className={activeCategory===item.id?"active-category-card":"category-card"} key={index} onClick={()=>{setActiveCategory(item.id)}}>
        <img src={item.image}/>
         <span>{item.name}</span>
        </div>
       ))}
      </div>

      <div className="title">Products</div>
      <div className="product-card-container">
        {productItems.map((item,index)=>{
          if(item.category === activeCategory){
            return (
              <div className="product-card" key={index}>
              <img src={item.image}/>
              <div className="tag">
              <span>{item.name}</span>
              <div className="right-info">
              <span>Rs. {item.price}</span>
              <div className='add-to-cart'><span>Add to cart</span> <BsCartCheck/></div>
             
              </div>
               </div>
            </div>
            )
          }
        })}
      </div>
    </section>
  )
}

export default Product
