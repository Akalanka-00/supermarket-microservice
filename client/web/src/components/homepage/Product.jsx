import React, { useState } from 'react'
import Food from "../../assets/images/catrgory/food.jpg"
import {categoryItems, productItems} from '../../context/Products'
import {BsCartCheck} from "react-icons/bs"
const Product = () => {

  const [activeCategory, setActiveCategory] = useState(0);
  
  return (
    <section className='product-container'>
      <div className="title">Categories</div>
      <div className="category-card-container">
       {categoryItems.map((item,index)=>(
        <div className={activeCategory===index?"active-category-card":"category-card"} key={index} onClick={()=>{setActiveCategory(index)}}>
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
              <img src={Food}/>
              <div className="tag">
              <span>Rice</span>
              <div className="right-info">
              <span>Rs. 100</span>
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
