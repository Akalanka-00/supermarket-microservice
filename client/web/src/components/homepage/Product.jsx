import React, { useState } from 'react'
import Food from "../../assets/images/catrgory/food.jpg"
import {categoryItems, productItems} from '../../context/Products'
import {BsCartCheck} from "react-icons/bs"
import ProductModel from './ProductModal';
const Product = () => {

  const [activeCategory, setActiveCategory] = useState(0);
  const [openProductModel, setOpenProductModel] = useState(false);
  const [selectedProduct, setSelectedProduct] = useState({
    name:"", image: null, price:0, category:0
  })
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
              <div className="product-card" key={index} onClick={()=>{
                setSelectedProduct(item);
                setOpenProductModel(true);
              }}>
                <BsCartCheck className='add-to-category'/>
              <img src={Food}/>
              <div className="tag">
              <span>Rice</span>
              <span>Rs. 100</span>
              </div>
            </div>
            )
          }
        })}
      </div>
      {openProductModel && <ProductModel product={selectedProduct}/>}
    </section>
  )
}

export default Product
