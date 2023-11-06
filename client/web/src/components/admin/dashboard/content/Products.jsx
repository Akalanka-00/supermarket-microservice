import React from 'react'
import { useParams } from 'react-router-dom';
import { categoryItems, productItems } from '../../../../context/Products';

const Products = () => {
  const {id} = useParams();
  const category = categoryItems[id];
  const containerStyle = {
    background: `url(${category.image}) no-repeat center center fixed`,
    backgroundSize: 'cover',
  };
  return (
    <section className="product-container">
      <div className="header" style={containerStyle}>
        <div className="bg-filter">
         <div className="title"> {category.name}</div>
         <div className="product-info-header">
          <div className="no-of-products"><div className="name">No. of Products:</div> <div className="value">{category.noOfProducts}</div></div>
          <div className="no-of-products"><div className="name">No. of Products in Stock: </div><div className="value">{category.noOfProducts}</div></div>

         </div>
        </div>
      </div>
      <div className="product-list">
        {productItems.map((item,index)=>{
          console.log(item.category, id)
          if(item.category+"" === id){
            return(
              <div className="product-item" key={index}>
                <div className="product-header">
                <img src={item.image}/>
                <div className="product-info">
                <div className="data">
                    <div className="name">Name: </div>
                    <div className="value">{item.name}</div>
                  </div>
                  <div className="data">
                    <div className="name">Unit Price: </div>
                    <div className="value">Rs. {item.price}</div>
                  </div>
                  <div className="data">
                    <div className="name">Available Quantity: </div>
                    <div className="value"> 1532 </div>
                  </div>
                </div>
                </div>
                <div className="status">
                    <div className="name">Status: </div>
                    <div className="value">In Stock</div>
                  </div>

                  <div className="options">
                    <div className="btn option">Update Details</div>
                    <div className="btn-warning option">Remove</div>


                  </div>
              </div>
            )
          }
        })}
      </div>
    </section>
  )
}

export default Products
