import React from "react";
import { categoryItems } from "../../../../context/Products";
import {BsPencil} from "react-icons/bs";
import {AiOutlineEye} from "react-icons/ai"
import { useNavigate } from "react-router-dom";

const Category = () => {
  const navigate = useNavigate();
  return (
    <section className="category-container">
      <div className="header">
        <div className="title">Product Categories</div>
        <div className="btn">Add Category</div>
      </div>
      <div className="category-list">
        {categoryItems.map((item, index) => (
          <div className="category-item" key={index}>
            <div className="info">
            <img src={item.image} />
            <div className="data">
              <div className="name">Name: </div>
              <div className="value">Food</div>
            </div>
            </div>

            <div className="data">
              <div className="name">No. of products: </div>
              <div className="value">15 </div>
            </div>

          
              <div className="options">
              <div className="btn option"><span>Edit</span> </div>
              <div className="btn option" onClick={()=>navigate(`/admin/dashboard/category/${index}`)}><span>View</span> </div>
              </div>
          </div>
        ))}
      </div>
    </section>
  );
};

export default Category;
